package app.yokan.datasource.animeav1

import android.util.Log
import app.yokan.anilist.model.AniListCoverImage
import app.yokan.anilist.model.AniListMedia
import app.yokan.anilist.model.AniListRelation
import app.yokan.anilist.model.AniListTitle
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import me.him188.ani.utils.coroutines.runSuspendCatching
import me.him188.ani.utils.logging.logger
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AnimeAV1Client(
    private val httpClient: HttpClient = HttpClient(),
) {
    private val logger = logger("AnimeAV1Client")

    // Caché en memoria con expiración (TTL) y límite de entradas para evitar URLs caducadas y fugas de RAM
    private val slugCache = TtlCache<String, String>(ttlMillis = SLUG_CACHE_TTL_MS, maxSize = 512)
    private val episodeSourcesCache = TtlCache<String, List<AnimeAV1Source>>(ttlMillis = SOURCES_CACHE_TTL_MS, maxSize = 256)
    private val resolvedStreamCache = TtlCache<String, WebStreamSource>(ttlMillis = STREAM_CACHE_TTL_MS, maxSize = 128)

    companion object {
        private const val LOG_TAG = "YokanPlayer"
        private const val BASE_URL = "https://animeav1.com"
        private const val UNS_BIO_BASE = "https://animeav1.uns.bio"
        private const val DEFAULT_USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"

        private const val SLUG_CACHE_TTL_MS = 6 * 60 * 60_000L // 6 horas
        private const val SOURCES_CACHE_TTL_MS = 60 * 60_000L // 60 minutos
        private const val STREAM_CACHE_TTL_MS = 30 * 60_000L // 30 minutos (tokens HLS/MP4 caducan)

        /** Timeout total por candidato (extracción + sonda rápida). */
        private const val RESOLVE_TIMEOUT_MS = 9_000L

        /** Timeout corto de verificación HTTP Range para descartar servidores colgados (p.ej. a3.mp4upload). */
        private const val STREAM_PROBE_TIMEOUT_MS = 3_500L

        /** Margen que se le da a un servidor de mayor prioridad para imponerse sobre el más rápido. */
        private const val PRIORITY_GRACE_MS = 600L

        private val TURBO_KEY_CODEPOINTS = listOf("𝕊", "𝔼", "𝕮", "𝚁", "𝔼", "𝚃", "𝓚", "𝓔", "𝓨", "𝟙", "𝟚", "𝟛")
            .joinToString("") { Character.codePointAt(it, 0).toString(16) }
        private val TURBO_IV_SALT = listOf("🔐", "🗝️", "🔑", "🚪", "🛡️", "⚡")
            .joinToString("") { Character.codePointAt(it, 0).toString(16) }
    }

    private enum class ProbeResult {
        ALIVE,
        DEAD,
        TIMEOUT,
    }

    private data class ResolvedCandidate(
        val index: Int,
        val stream: WebStreamSource,
        val probe: ProbeResult,
    )

    /** Elimina un stream fallido de la caché en RAM para que un reintento extraiga token/espejo nuevo. */
    fun invalidateStreamCache(stream: WebStreamSource) {
        resolvedStreamCache.removeIf {
            it.streamUrl == stream.streamUrl ||
                (it.serverName.equals(stream.serverName, ignoreCase = true) && it.isDub == stream.isDub)
        }
    }

    /**
     * Paso 1 (Opción B del md): Generación determinista de slug a partir del título.
     */
    fun titleToSlug(title: String): String {
        return title.lowercase()
            .replace(Regex("[^a-z0-9\\s-]"), "")
            .trim()
            .replace(Regex("\\s+"), "-")
    }

    /**
     * Búsqueda en el catálogo de AnimeAV1 mediante el endpoint /catalogo?search={nombre}
     */
    suspend fun searchCatalog(query: String): List<AnimeAV1MediaItem> = withContext(Dispatchers.IO) {
        runSuspendCatching {
            val encodedQuery = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8.name())
            val url = "$BASE_URL/catalogo?search=$encodedQuery"
            val response = httpClient.get(url) {
                header("User-Agent", DEFAULT_USER_AGENT)
                header("Referer", "$BASE_URL/")
            }

            if (!response.status.isSuccess()) {
                logger.warn("AnimeAV1 catalogo devolvió estado: ${response.status}")
                return@runSuspendCatching emptyList()
            }

            val html = response.bodyAsText()
            val items = mutableListOf<AnimeAV1MediaItem>()

            // Regex primario: enlace y título del botón accesible
            val regex = Regex("""href="/media/([a-zA-Z0-9-]+)"[^>]*><span[^>]*>Ver\s+([^<]+)</span></a>""")
            regex.findAll(html).forEach { match ->
                val slug = match.groupValues[1]
                val title = match.groupValues[2].trim()
                if (items.none { it.slug == slug }) {
                    items.add(AnimeAV1MediaItem(slug = slug, title = title))
                }
            }

            // Fallback en caso de variación en la plantilla HTML
            if (items.isEmpty()) {
                val fallbackRegex = Regex("""href="/media/([a-zA-Z0-9-]+)"""")
                fallbackRegex.findAll(html).forEach { match ->
                    val slug = match.groupValues[1]
                    if (items.none { it.slug == slug }) {
                        items.add(AnimeAV1MediaItem(slug = slug, title = slug.replace("-", " ")))
                    }
                }
            }

            items
        }.getOrElse { error ->
            logger.warn("Error buscando en AnimeAV1 para '$query': ${error.message}")
            emptyList()
        }
    }

    /**
     * Obtiene los metadatos completos de una serie directamente desde AnimeAV1
     * (útil para animes o versiones especiales que no existen en AniList).
     */
    suspend fun getAnimeAV1Media(slug: String): AniListMedia? = withContext(Dispatchers.IO) {
        runSuspendCatching {
            val url = "$BASE_URL/media/$slug/__data.json"
            val response = httpClient.get(url) {
                header("User-Agent", DEFAULT_USER_AGENT)
                header("Referer", "$BASE_URL/media/$slug")
            }
            if (!response.status.isSuccess()) return@runSuspendCatching null

            val jsonText = response.bodyAsText()
            val root = Json.parseToJsonElement(jsonText).jsonObject
            val nodes = root["nodes"]?.jsonArray ?: return@runSuspendCatching null

            for (node in nodes) {
                if (node !is JsonObject) continue
                val dataList = node["data"] as? JsonArray ?: continue
                if (dataList.size <= 1) continue
                val mediaObj = dataList[1] as? JsonObject ?: continue
                if ("episodesCount" !in mediaObj) continue

                fun resolveElement(element: JsonElement?): JsonElement? {
                    if (element == null) return null
                    val prim = element as? JsonPrimitive ?: return element
                    val idx = prim.intOrNull
                    if (idx != null && idx in 0 until dataList.size) {
                        return dataList[idx]
                    }
                    return prim
                }

                val animeId = resolveElement(mediaObj["id"])?.jsonPrimitive?.intOrNull ?: 0
                val title = resolveElement(mediaObj["title"])?.jsonPrimitive?.contentOrNull ?: slug.replace("-", " ")
                val synopsis = resolveElement(mediaObj["synopsis"])?.jsonPrimitive?.contentOrNull
                val episodesCount = resolveElement(mediaObj["episodesCount"])?.jsonPrimitive?.intOrNull ?: 1
                val coverUrl = "https://cdn.animeav1.com/covers/$animeId.jpg"
                val backdropUrl = "https://cdn.animeav1.com/backdrops/$animeId.jpg"

                // Guardar en caché para que las peticiones de episodios no requieran resolver el slug de nuevo
                val cacheKey = "$title|$title"
                slugCache[cacheKey] = slug
                slugCache[title.lowercase().trim()] = slug
                slugCache[slug] = slug

                return@runSuspendCatching AniListMedia(
                    id = -animeId,
                    title = AniListTitle(romaji = title, english = title),
                    coverImage = AniListCoverImage(large = coverUrl, medium = coverUrl),
                    bannerImage = backdropUrl,
                    description = synopsis,
                    episodes = episodesCount,
                    status = "FINISHED",
                )
            }
            null
        }.getOrNull()
    }

    /**
     * Busca versiones especiales o alternativas alojadas en AnimeAV1 que no formen parte
     * de las relaciones estándar de AniList (ej: Director's Cut / Shin Henshuu-ban).
     */
    suspend fun findSpecialVersions(
        romajiTitle: String,
        englishTitle: String?,
        existingRelationIds: Set<Int> = emptySet(),
    ): List<AniListRelation> = withContext(Dispatchers.IO) {
        runSuspendCatching {
            // Buscamos con las palabras principales de la franquicia
            val cleanTitle = romajiTitle.replace(Regex("[^a-zA-Z0-9\\s]"), " ").trim()
            val tokens = cleanTitle.split(Regex("\\s+")).filter { it.length > 1 }.take(2).joinToString(" ")
            val query = tokens.ifBlank { romajiTitle.take(15) }

            val candidates = searchCatalog(query)
            val specialRelations = mutableListOf<AniListRelation>()

            val baseSlug = titleToSlug(romajiTitle)
            val rootSlug = baseSlug.split("-").take(2).joinToString("-")

            for (candidate in candidates) {
                val slug = candidate.slug
                val isFranchise = slug.startsWith(rootSlug) ||
                    candidate.title.contains(tokens.split(" ").firstOrNull().orEmpty(), ignoreCase = true)
                if (!isFranchise) continue

                val isSpecial = slug.contains("shin-henshuu-ban", ignoreCase = true) ||
                    slug.contains("director", ignoreCase = true) ||
                    slug.contains("recut", ignoreCase = true) ||
                    slug.contains("uncensored", ignoreCase = true) ||
                    slug.contains("sin-censura", ignoreCase = true)

                if (isSpecial) {
                    val media = getAnimeAV1Media(slug)
                    if (media != null && media.id !in existingRelationIds) {
                        specialRelations.add(
                            AniListRelation(
                                id = media.id,
                                relationType = "DIRECTOR_CUT",
                                title = media.title,
                                coverImage = media.coverImage,
                                format = "TV",
                                episodes = media.episodes,
                                animeav1Slug = slug,
                            )
                        )
                    }
                }
            }
            specialRelations
        }.getOrDefault(emptyList())
    }

    /**
     * Resuelve el slug óptimo contrastando títulos en japonés (romaji) y en inglés.
     */
    suspend fun resolveSlug(romajiTitle: String, englishTitle: String?): String {
        val cacheKey = "$romajiTitle|$englishTitle"
        slugCache[cacheKey]?.let { return it }

        val candidates = searchCatalog(romajiTitle).ifEmpty {
            englishTitle?.let { searchCatalog(it) }.orEmpty()
        }

        val resolved = if (candidates.isNotEmpty()) {
            val romajiClean = romajiTitle.lowercase().trim()
            val exactMatch = candidates.firstOrNull { it.title.equals(romajiClean, ignoreCase = true) }
            if (exactMatch != null) exactMatch.slug
            else {
                val englishClean = englishTitle?.lowercase()?.trim()
                val exactEnglish = if (englishClean != null) candidates.firstOrNull { it.title.equals(englishClean, ignoreCase = true) } else null
                if (exactEnglish != null) exactEnglish.slug
                else {
                    val partialMatch = candidates.firstOrNull {
                        it.title.contains(romajiClean, ignoreCase = true) ||
                                (englishClean != null && it.title.contains(englishClean, ignoreCase = true))
                    }
                    partialMatch?.slug ?: candidates.first().slug
                }
            }
        } else {
            titleToSlug(romajiTitle)
        }

        slugCache[cacheKey] = resolved
        return resolved
    }

    /**
     * Extrae los servidores de reproducción (embeds) para un episodio determinado.
     * Prioriza el servidor MP4Upload (el más rápido y estable), seguido de UPNShare.
     */
    suspend fun getEpisodeSources(slug: String, episodeNumber: Int): List<AnimeAV1Source> = withContext(Dispatchers.IO) {
        val cacheKey = "$slug:$episodeNumber"
        episodeSourcesCache[cacheKey]?.let { return@withContext it }

        runSuspendCatching {
            val url = "$BASE_URL/media/$slug/$episodeNumber"
            val response = httpClient.get(url) {
                header("User-Agent", DEFAULT_USER_AGENT)
                header("Referer", "$BASE_URL/media/$slug")
            }

            if (!response.status.isSuccess()) {
                logger.warn("AnimeAV1 episodio devolvió código: ${response.status}")
                return@runSuspendCatching emptyList()
            }

            val html = response.bodyAsText()
            val sources = mutableListOf<AnimeAV1Source>()

            val serverRegex = Regex("""\{server:\s*"([^"]+)",\s*url:\s*"([^"]+)"\}""")

            // 1. Extraer fuentes Subtituladas (SUB)
            val subBlock = Regex("""SUB:\s*\[(.*?)\]""", RegexOption.DOT_MATCHES_ALL).find(html)?.groupValues?.get(1)
            if (!subBlock.isNullOrBlank()) {
                serverRegex.findAll(subBlock).forEach { match ->
                    val server = match.groupValues[1]
                    val embedUrl = match.groupValues[2]
                    sources.add(AnimeAV1Source(server = server, embedUrl = embedUrl, isDub = false))
                }
            }

            // 2. Extraer fuentes Dobladas (DUB)
            val dubBlock = Regex("""DUB:\s*\[(.*?)\]""", RegexOption.DOT_MATCHES_ALL).find(html)?.groupValues?.get(1)
            if (!dubBlock.isNullOrBlank()) {
                serverRegex.findAll(dubBlock).forEach { match ->
                    val server = match.groupValues[1]
                    val embedUrl = match.groupValues[2]
                    sources.add(AnimeAV1Source(server = server, embedUrl = embedUrl, isDub = true))
                }
            }

            // Filtrar solo servidores con extractor directo soportado y ordenar por prioridad (SUB antes que DUB):
            // 1. MP4Upload primero si el espejo responde (si cuelga como a3, la sonda lo descarta en ~3.5s)
            // 2. Voe (HLS estándar .m3u8 + .ts)
            // 3. UPNShare / TurboEmbed (HLS fMP4)
            // 4. YourUpload
            // 5. VidHide
            // 6. StreamTape
            val sortedSources = sources
                .filter { it.isSupportedExtractor }
                .sortedWith(
                    compareBy<AnimeAV1Source> { it.isDub }
                        .thenByDescending { it.isMp4Upload }
                        .thenByDescending { it.isVoe }
                        .thenByDescending { it.isUpn }
                        .thenByDescending { it.server.equals("YourUpload", ignoreCase = true) }
                        .thenByDescending { it.isVidHide }
                        .thenByDescending { it.isStreamTape }
                )
            if (sortedSources.isNotEmpty()) {
                episodeSourcesCache[cacheKey] = sortedSources
            }
            sortedSources
        }.getOrElse { error ->
            logger.error("Error al obtener fuentes del episodio $episodeNumber de '$slug': ${error.message}", error)
            emptyList()
        }
    }

    /**
     * Resuelve el enlace directo (.m3u8 o .mp4) para una fuente concreta.
     * Solo cachea el resultado si supera la verificación HTTP Range (o si ya venía en caché).
     */
    suspend fun resolveStream(
        source: AnimeAV1Source,
        forceRefresh: Boolean = false,
    ): Result<WebStreamSource> = withContext(Dispatchers.IO) {
        if (!forceRefresh) {
            resolvedStreamCache[source.embedUrl]?.let { return@withContext Result.success(it) }
        } else {
            resolvedStreamCache[source.embedUrl]?.let { invalidateStreamCache(it) }
        }

        val result = extractStreamUnverified(source)
        result.onSuccess { stream ->
            if (probeStream(stream.streamUrl, stream.headers, STREAM_PROBE_TIMEOUT_MS) == ProbeResult.ALIVE) {
                resolvedStreamCache[source.embedUrl] = stream
            }
        }
        result
    }

    private suspend fun extractStreamUnverified(source: AnimeAV1Source): Result<WebStreamSource> =
        runSuspendCatching {
            val resolvedServerName = source.server.ifBlank { source.canonicalServerName }
            val webStream = when {
                source.isUpn -> extractUpnStream(source, resolvedServerName)

                source.isMp4Upload -> {
                    val response = httpClient.get(source.embedUrl) {
                        header("User-Agent", DEFAULT_USER_AGENT)
                        header("Referer", "$BASE_URL/")
                    }
                    val html = response.bodyAsText()
                    val unpacked = unpackJsEvalIfPresent(html)
                    val combined = if (unpacked.isNotEmpty()) "$html\n$unpacked" else html
                    val mp4Match = Regex("""src:\s*["'](https?://[^"']+\.mp4[^"']*)["']""").find(combined)?.groupValues?.get(1)
                        ?: Regex("""player\.src\(\s*\{[^\}]*src:\s*["'](https?://[^"']+\.mp4[^"']*)["']""").find(combined)?.groupValues?.get(1)
                        ?: throw IllegalStateException("No se encontró URL de video en MP4Upload")

                    WebStreamSource(
                        title = source.displayName,
                        serverName = resolvedServerName,
                        streamUrl = mp4Match,
                        headers = mapOf(
                            "Referer" to "https://www.mp4upload.com/",
                            "User-Agent" to DEFAULT_USER_AGENT,
                        ),
                        quality = "1080p",
                        isHls = false,
                        isDub = source.isDub,
                    )
                }

                source.server.equals("YourUpload", ignoreCase = true) -> {
                    val response = httpClient.get(source.embedUrl) {
                        header("User-Agent", DEFAULT_USER_AGENT)
                        header("Referer", "$BASE_URL/")
                    }
                    val html = response.bodyAsText()
                    val mp4Match = Regex("""file:\s*["']([^"']+\.mp4[^"']*)["']""").find(html)?.groupValues?.get(1)
                        ?: throw IllegalStateException("No se encontró URL de video en YourUpload")

                    WebStreamSource(
                        title = source.displayName,
                        serverName = resolvedServerName,
                        streamUrl = mp4Match,
                        headers = mapOf(
                            "Referer" to source.embedUrl,
                            "User-Agent" to DEFAULT_USER_AGENT,
                        ),
                        quality = "1080p",
                        isHls = false,
                        isDub = source.isDub,
                    )
                }

                source.isVoe -> extractVoeStream(source)

                source.isVidHide -> extractVidHideStream(source)

                source.isStreamTape -> extractStreamTapeStream(source)

                else -> throw UnsupportedOperationException("Servidor web sin extractor directo soportado: ${source.server}")
            }
            webStream
        }.onFailure { err ->
            Log.w(LOG_TAG, "Fallo extrayendo ${source.canonicalServerName} (${source.embedUrl}): ${err.message}")
        }

    private suspend fun extractUpnStream(
        source: AnimeAV1Source,
        resolvedServerName: String,
    ): WebStreamSource {
        if (source.embedUrl.contains("turboembed.", ignoreCase = true)) {
            return extractTurboEmbedStream(source.embedUrl, source, resolvedServerName)
        }

        val hash = Regex("""#([a-zA-Z0-9_-]+)""").find(source.embedUrl)?.groupValues?.get(1)
            ?: throw IllegalArgumentException("No se encontró el hash en la URL de UPN: ${source.embedUrl}")

        // 1. Nuevo flujo UPNShare -> TurboEmbed (/api/public/resolve?file1=<hash>)
        val resolveResp = runCatching {
            httpClient.get("$UNS_BIO_BASE/api/public/resolve?file1=$hash") {
                header("Referer", "$UNS_BIO_BASE/")
                header("Origin", UNS_BIO_BASE)
                header("User-Agent", DEFAULT_USER_AGENT)
                header("Accept", "application/json, text/plain, */*")
            }
        }.getOrNull()

        if (resolveResp != null && resolveResp.status.isSuccess()) {
            val resolvedUrl = runCatching {
                Json.parseToJsonElement(resolveResp.bodyAsText())
                    .jsonObject["url"]?.jsonPrimitive?.contentOrNull
            }.getOrNull()
            if (!resolvedUrl.isNullOrBlank() && resolvedUrl.startsWith("http", ignoreCase = true)) {
                return extractTurboEmbedStream(resolvedUrl, source, resolvedServerName)
            }
        }

        // 2. Respaldo legado /api/v1/video por si uns.bio reactiva la API anterior
        val apiUrl = "$UNS_BIO_BASE/api/v1/video?id=$hash&w=1920&h=1080&r="
        val response = httpClient.get(apiUrl) {
            header("Referer", "$UNS_BIO_BASE/")
            header("Origin", UNS_BIO_BASE)
            header("User-Agent", DEFAULT_USER_AGENT)
            header("Accept", "*/*")
        }

        if (!response.status.isSuccess()) {
            throw IllegalStateException("UPNShare API devolvió código: ${response.status}")
        }

        val hexPayload = response.bodyAsText().trim()
        val decryptedJson = AnimeAV1Cipher.decryptHex(hexPayload)
        val tokenParams = extractPkToken(decryptedJson)

        val cfNative = Regex(""""cfNative":"([^"]+)"""").find(decryptedJson)?.groupValues?.get(1)?.replace("\\/", "/")
        val cf = Regex(""""cf":"([^"]+)"""").find(decryptedJson)?.groupValues?.get(1)?.replace("\\/", "/")
        val sourceUrl = Regex(""""source":"([^"]+)"""").find(decryptedJson)?.groupValues?.get(1)?.replace("\\/", "/")
        val hlsTiktok = Regex(""""hlsVideoTiktok":"([^"]+)"""").find(decryptedJson)?.groupValues?.get(1)?.replace("\\/", "/")

        val candidates = buildList {
            cfNative?.let { add("cfNative" to it) }
            cf?.let { add("cf" to it) }
            sourceUrl?.let { add("source" to it) }
            hlsTiktok?.let {
                add("hlsVideoTiktok" to if (it.startsWith("/")) "$UNS_BIO_BASE$it" else it)
            }
        }
        if (candidates.isEmpty()) {
            throw IllegalStateException("No se encontró URL de stream en la respuesta descifrada de UPN")
        }

        val (_, rawUrl) = candidates.first()
        val streamUrl = appendQueryParams(rawUrl, tokenParams)

        return WebStreamSource(
            title = source.displayName,
            serverName = resolvedServerName,
            streamUrl = streamUrl,
            headers = mapOf(
                "Referer" to "$UNS_BIO_BASE/",
                "Origin" to UNS_BIO_BASE,
                "User-Agent" to DEFAULT_USER_AGENT,
                "Accept" to "*/*",
            ),
            quality = "1080p",
            isHls = true,
            isDub = source.isDub,
            queryParams = tokenParams,
        )
    }

    private suspend fun extractTurboEmbedStream(
        targetUrl: String,
        source: AnimeAV1Source,
        resolvedServerName: String,
    ): WebStreamSource {
        val uri = URI(targetUrl)
        val scheme = uri.scheme?.takeIf { it.isNotBlank() } ?: "https"
        val host = uri.host?.takeIf { it.isNotBlank() }
            ?: throw IllegalStateException("Host inválido en TurboEmbed: $targetUrl")
        val videoId = uri.path?.trim('/')?.substringAfterLast('/')?.takeIf { it.isNotBlank() }
            ?: throw IllegalStateException("ID de video no encontrado en TurboEmbed: $targetUrl")

        val dayHex = (System.currentTimeMillis() / 86_400_000L).toString(16)
        val keyHex = (TURBO_KEY_CODEPOINTS + dayHex).take(32).padEnd(32, '0')
        val hostHex = host.toByteArray(StandardCharsets.UTF_8).joinToString("") { "%02x".format(it) }
        val ivHex = (TURBO_IV_SALT + hostHex).take(32).padEnd(32, '0')

        val keySpec = SecretKeySpec(hexToBytes(keyHex), "AES")
        val ivSpec = IvParameterSpec(hexToBytes(ivHex))

        val encCipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        encCipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val encryptedIdHex = bytesToHex(encCipher.doFinal(videoId.toByteArray(StandardCharsets.UTF_8)))

        val trackingResponse = httpClient.get("$scheme://$host/api/stream/tracking?c=$encryptedIdHex") {
            header("User-Agent", DEFAULT_USER_AGENT)
            header("Referer", targetUrl)
            header("Origin", "$scheme://$host")
            header("Accept", "application/json, text/plain, */*")
        }

        val xTrackingHex = trackingResponse.headers["X-Tracking"]?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?: throw IllegalStateException("TurboEmbed no devolvió cabecera X-Tracking (HTTP ${trackingResponse.status.value})")

        val decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        decCipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val decryptedJson = String(decCipher.doFinal(hexToBytes(xTrackingHex)), StandardCharsets.UTF_8)

        val streamObj = Json.parseToJsonElement(decryptedJson).jsonObject["stream"]?.jsonObject
            ?: throw IllegalStateException("TurboEmbed sin objeto 'stream'")

        // OJO: No usar 'tiktok' porque sus segmentos (.image) anteponen cabeceras PNG falsas (\x89PNG)
        // que ExoPlayer no puede demuxear; 'hls' y 'direct' sirven fMP4/TS puros.
        val urlCandidates = listOfNotNull(
            streamObj["hls"]?.jsonPrimitive?.contentOrNull?.takeIf { it.startsWith("http", ignoreCase = true) },
            streamObj["direct"]?.jsonPrimitive?.contentOrNull?.takeIf { it.startsWith("http", ignoreCase = true) },
        ).distinct()

        if (urlCandidates.isEmpty()) {
            throw IllegalStateException("TurboEmbed sin URL HLS/directa compatible")
        }

        val headers = mapOf(
            "Referer" to "$scheme://$host/",
            "Origin" to "$scheme://$host",
            "User-Agent" to DEFAULT_USER_AGENT,
            "Accept" to "*/*",
        )

        val chosenUrl = if (urlCandidates.size > 1) {
            urlCandidates.firstOrNull { url ->
                probeStream(url, headers, STREAM_PROBE_TIMEOUT_MS) == ProbeResult.ALIVE
            } ?: urlCandidates.first()
        } else {
            urlCandidates.first()
        }

        return WebStreamSource(
            title = source.displayName,
            serverName = resolvedServerName,
            streamUrl = chosenUrl,
            headers = headers,
            quality = "1080p",
            isHls = true,
            isDub = source.isDub,
        )
    }

    private fun hexToBytes(hex: String): ByteArray {
        val len = hex.length
        val out = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            out[i / 2] = ((Character.digit(hex[i], 16) shl 4) + Character.digit(hex[i + 1], 16)).toByte()
            i += 2
        }
        return out
    }

    private fun bytesToHex(bytes: ByteArray): String =
        bytes.joinToString("") { "%02x".format(it) }

    private suspend fun extractVoeStream(source: AnimeAV1Source): WebStreamSource {
        var currentUrl = source.embedUrl
        var html = httpClient.get(currentUrl) {
            header("User-Agent", DEFAULT_USER_AGENT)
            header("Referer", "$BASE_URL/")
        }.bodyAsText()

        // Voe frecuentemente redirige por JS: window.location.href = 'https://...'
        val jsRedirect = Regex("""window\.location(?:\.href)?\s*=\s*['"](https?://[^'"]+)['"]""")
            .find(html)?.groupValues?.get(1)
        if (!jsRedirect.isNullOrBlank()) {
            currentUrl = jsRedirect
            html = httpClient.get(currentUrl) {
                header("User-Agent", DEFAULT_USER_AGENT)
                header("Referer", source.embedUrl)
            }.bodyAsText()
        }

        // 1. Nuevo formato Voe: <script type="application/json">["<ofuscado>"]</script>
        val jsonPayloadStream = extractVoeFromObfuscatedJsonScript(html)

        // 2. Fallback formato anterior ('hls': Base64 o URL .m3u8 directa)
        val hlsRaw = Regex("""['"]hls['"]\s*:\s*['"]([^'"]+)['"]""").find(html)?.groupValues?.get(1)
        val decodedHls = hlsRaw?.let { raw ->
            if (raw.startsWith("http", ignoreCase = true)) {
                raw
            } else {
                runCatching {
                    String(java.util.Base64.getDecoder().decode(padBase64(raw)), StandardCharsets.UTF_8)
                }.getOrNull()?.takeIf { it.startsWith("http", ignoreCase = true) }
            }
        }

        val streamUrl = jsonPayloadStream
            ?: decodedHls
            ?: Regex("""(https?://[^\s"'<>]+?\.m3u8[^\s"'<>]*)""").find(html)?.groupValues?.get(1)
            ?: throw IllegalStateException("No se encontró stream HLS en Voe")

        val originDomain = Regex("""^(https?://[^/]+)""").find(currentUrl)?.groupValues?.get(1) ?: "https://voe.sx"
        return WebStreamSource(
            title = source.displayName,
            serverName = source.server.ifBlank { source.canonicalServerName },
            streamUrl = streamUrl.replace("\\/", "/"),
            headers = mapOf(
                "Referer" to "$originDomain/",
                "Origin" to originDomain,
                "User-Agent" to DEFAULT_USER_AGENT,
                "Accept" to "*/*",
            ),
            quality = "720p",
            isHls = true,
            isDub = source.isDub,
        )
    }

    private fun extractVoeFromObfuscatedJsonScript(html: String): String? {
        val matches = Regex(
            """<script[^>]*type=["']application/json["'][^>]*>\s*\[\s*"([^"]+)"\s*\]\s*</script>""",
            RegexOption.IGNORE_CASE,
        ).findAll(html)

        for (match in matches) {
            val encoded = match.groupValues[1]
            val jsonText = decodeVoeJsonPayload(encoded) ?: continue
            val parsedUrl = runCatching {
                val obj = Json.parseToJsonElement(jsonText).jsonObject
                obj["source"]?.jsonPrimitive?.contentOrNull?.takeIf { it.startsWith("http", ignoreCase = true) }
                    ?: obj["fallback"]?.jsonPrimitive?.contentOrNull?.takeIf { it.startsWith("http", ignoreCase = true) }
            }.getOrNull()
            if (!parsedUrl.isNullOrBlank()) {
                return parsedUrl
            }
        }
        return null
    }

    private fun decodeVoeJsonPayload(encoded: String): String? = runCatching {
        // 1. ROT13 sobre letras ASCII
        val rot13 = buildString(encoded.length) {
            for (ch in encoded) {
                append(
                    when (ch) {
                        in 'a'..'z' -> ('a'.code + (ch.code - 'a'.code + 13) % 26).toChar()
                        in 'A'..'Z' -> ('A'.code + (ch.code - 'A'.code + 13) % 26).toChar()
                        else -> ch
                    }
                )
            }
        }
        // 2. Eliminar marcadores de basura que Voe inyecta antes de Base64
        val cleaned = rot13.replace(Regex("""(@\$|\^\^|~@|%\?|\*~|!!|#&)"""), "")
        // 3. Base64 -> desplazar bytes (-3) -> invertir
        val step1Bytes = java.util.Base64.getDecoder().decode(padBase64(cleaned))
        val shiftedReversed = buildString(step1Bytes.size) {
            for (i in step1Bytes.indices.reversed()) {
                append(((step1Bytes[i].toInt() and 0xFF) - 3).toChar())
            }
        }
        // 4. Base64 final -> JSON UTF-8 con "source"/"fallback"
        val jsonBytes = java.util.Base64.getDecoder().decode(padBase64(shiftedReversed))
        String(jsonBytes, StandardCharsets.UTF_8)
    }.getOrNull()

    private fun padBase64(input: String): String {
        val trimmed = input.trim()
        val rem = trimmed.length % 4
        return if (rem == 0) trimmed else trimmed + "=".repeat(4 - rem)
    }

    private suspend fun extractVidHideStream(source: AnimeAV1Source): WebStreamSource {
        val response = httpClient.get(source.embedUrl) {
            header("User-Agent", DEFAULT_USER_AGENT)
            header("Referer", "$BASE_URL/")
        }
        val html = response.bodyAsText()
        val unpacked = unpackJsEvalIfPresent(html)
        val combined = "$unpacked\n$html"

        val m3u8Match = Regex("""file\s*:\s*["'](https?://[^"']+\.m3u8[^"']*)["']""").find(combined)?.groupValues?.get(1)
            ?: Regex("""(https?://[^\s"'<>]+?\.m3u8[^\s"'<>]*)""").find(combined)?.groupValues?.get(1)
            ?: throw IllegalStateException("No se encontró URL m3u8 en VidHide")

        val originDomain = Regex("""^(https?://[^/]+)""").find(source.embedUrl)?.groupValues?.get(1) ?: BASE_URL
        return WebStreamSource(
            title = source.displayName,
            serverName = source.server.ifBlank { source.canonicalServerName },
            streamUrl = m3u8Match.replace("\\/", "/"),
            headers = mapOf(
                "Referer" to "$originDomain/",
                "Origin" to originDomain,
                "User-Agent" to DEFAULT_USER_AGENT,
            ),
            quality = "1080p",
            isHls = true,
            isDub = source.isDub,
        )
    }

    private suspend fun extractStreamTapeStream(source: AnimeAV1Source): WebStreamSource {
        val response = httpClient.get(source.embedUrl) {
            header("User-Agent", DEFAULT_USER_AGENT)
            header("Referer", "$BASE_URL/")
        }
        val html = response.bodyAsText()

        // StreamTape construye el enlace mediante innerHTML = '//streamtape.../get_video?...' + ('xcd...').substring(...)
        val robotRegex = Regex("""getElementById\(['"](?:norobotlink|ideoolink|robotlink)['"]\)\.innerHTML\s*=\s*['"]([^'"]+)['"]\s*\+\s*\(['"]([^'"]+)['"]\)\.substring\((\d+)\)(?:\.substring\((\d+)\))?""")
        val match = robotRegex.find(html)
        val finalUrl = if (match != null) {
            val prefix = match.groupValues[1]
            var tokenPart = match.groupValues[2].substring(match.groupValues[3].toInt())
            val secondSub = match.groupValues[4].toIntOrNull()
            if (secondSub != null) {
                tokenPart = tokenPart.substring(secondSub)
            }
            val rawUrl = if (prefix.startsWith("//")) "https:$prefix$tokenPart" else "https://streamtape.com$prefix$tokenPart"
            if (rawUrl.contains("&stream=")) rawUrl else "$rawUrl&stream=1"
        } else {
            val fallbackMatch = Regex("""['"](https?://[^'"]*?/get_video\?id=[^'"]+)['"]""").find(html)?.groupValues?.get(1)
                ?: throw IllegalStateException("No se encontró enlace de video en StreamTape")
            if (fallbackMatch.contains("&stream=")) fallbackMatch else "$fallbackMatch&stream=1"
        }

        return WebStreamSource(
            title = source.displayName,
            serverName = source.server.ifBlank { source.canonicalServerName },
            streamUrl = finalUrl,
            headers = mapOf(
                "Referer" to source.embedUrl,
                "User-Agent" to DEFAULT_USER_AGENT,
            ),
            quality = "720p",
            isHls = false,
            isDub = source.isDub,
        )
    }

    /**
     * Desempaca bloques estándar Dean Edwards Packer: eval(function(p,a,c,k,e,d){...}('payload',radix,count,'symtab'.split('|')))
     */
    private fun unpackJsEvalIfPresent(html: String): String {
        val packedRegex = Regex(
            """eval\(function\(p,a,c,k,e,[dr]\)\{.*?\}\(\s*'((?:\\.|[^'])*)'\s*,\s*(\d+)\s*,\s*(\d+)\s*,\s*'((?:\\.|[^'])*)'\.split\('\|'\)""",
            RegexOption.DOT_MATCHES_ALL
        )
        val match = packedRegex.find(html) ?: return ""
        val payload = match.groupValues[1]
        val radix = match.groupValues[2].toIntOrNull() ?: 36
        val symtab = match.groupValues[4].split("|")

        fun encodeBase(num: Int, base: Int): String {
            val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            if (num < base) return chars[num].toString()
            return encodeBase(num / base, base) + chars[num % base]
        }

        var result = payload
        for (i in symtab.indices.reversed()) {
            val replacement = symtab[i]
            if (replacement.isNotEmpty()) {
                val token = encodeBase(i, radix)
                result = result.replace(Regex("\\b${Regex.escape(token)}\\b"), replacement)
            }
        }
        return result
    }

    /**
     * Comprueba de forma ultrarrápida si el enlace de video está disponible (no 404, no caído, sin cuelgue TLS).
     */
    suspend fun verifyStreamUrlAlive(
        url: String,
        headers: Map<String, String> = emptyMap(),
        timeoutMillis: Long = STREAM_PROBE_TIMEOUT_MS,
    ): Boolean = probeStream(url, headers, timeoutMillis) == ProbeResult.ALIVE

    private suspend fun probeStream(
        url: String,
        headers: Map<String, String> = emptyMap(),
        timeoutMillis: Long = STREAM_PROBE_TIMEOUT_MS,
    ): ProbeResult = withContext(Dispatchers.IO) {
        val result = withTimeoutOrNull(timeoutMillis) {
            try {
                val response = httpClient.get(url) {
                    headers.forEach { (k, v) -> header(k, v) }
                    if (headers.keys.none { it.equals("User-Agent", ignoreCase = true) }) {
                        header("User-Agent", DEFAULT_USER_AGENT)
                    }
                    header("Range", "bytes=0-10")
                }
                if (response.status.value in 200..399) ProbeResult.ALIVE else ProbeResult.DEAD
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                val msg = e.message.orEmpty()
                if (msg.contains("timeout", ignoreCase = true) || msg.contains("timed out", ignoreCase = true)) {
                    ProbeResult.TIMEOUT
                } else {
                    ProbeResult.DEAD
                }
            }
        }
        result ?: ProbeResult.TIMEOUT
    }

    /**
     * Extrae el token de acceso del CDN (`pk` -> `k` / `kx`) de la respuesta descifrada de UPN.
     *
     * El CDN rechaza con **403** cualquier manifest o segmento que no incluya estos parámetros,
     * por eso son obligatorios tanto en la URL inicial como en cada petición posterior.
     */
    private fun extractPkToken(decryptedJson: String): Map<String, String> {
        val pkBlock = Regex(""""pk"\s*:\s*\{([^}]*)}""").find(decryptedJson)?.groupValues?.get(1)
            ?: return emptyMap()
        val k = Regex(""""k"\s*:\s*"([^"]+)"""").find(pkBlock)?.groupValues?.get(1)
            ?: return emptyMap()
        val kx = Regex(""""kx"\s*:\s*(\d+)""").find(pkBlock)?.groupValues?.get(1)
            ?: return emptyMap()
        return mapOf("k" to k, "kx" to kx)
    }

    /** Añade los parámetros de query a una URL conservando los que ya tuviera. */
    private fun appendQueryParams(url: String, params: Map<String, String>): String {
        if (params.isEmpty()) return url
        val separator = if (url.contains("?")) "&" else "?"
        val query = params.entries.joinToString("&") { (key, value) ->
            "$key=${URLEncoder.encode(value, StandardCharsets.UTF_8.name())}"
        }
        return url + separator + query
    }

    /**
     * Devuelve un par (hasSub, hasDub) indicando qué pistas de idioma están disponibles en AnimeAV1.
     */
    suspend fun getAvailableLanguages(
        romajiTitle: String,
        englishTitle: String?,
        episodeNumber: Int = 1,
    ): Pair<Boolean, Boolean> = withContext(Dispatchers.IO) {
        runSuspendCatching {
            val slug = resolveSlug(romajiTitle, englishTitle)
            val sources = getEpisodeSources(slug, episodeNumber)
            val hasSub = sources.any { !it.isDub }
            val hasDub = sources.any { it.isDub }
            hasSub to hasDub
        }.getOrDefault(false to false)
    }

    /**
     * Resuelve la mejor fuente de streaming rápido para el episodio:
     * Intenta en cascada en orden de prioridad respetando el idioma preferido ([preferDub]);
     * si un servidor está caído (404/500/timeout), salta automáticamente al siguiente espejo.
     */
    private suspend fun resolveBestInternal(
        romajiTitle: String,
        englishTitle: String?,
        episodeNumber: Int,
        preferDub: Boolean,
    ): WebStreamSource? {
        val slug = resolveSlug(romajiTitle, englishTitle)
        val sources = getEpisodeSources(slug, episodeNumber)
        if (sources.isEmpty()) {
            Log.w(LOG_TAG, "AnimeAV1 sin fuentes para slug '$slug' ep $episodeNumber")
            logger.info("No se encontraron fuentes en AnimeAV1 para slug '$slug' ep $episodeNumber")
            return null
        }

        // Filtrar preferentemente el idioma elegido (SUB o LAT); si no existe en ese idioma, caer al otro
        val preferredSources = sources.filter { it.isDub == preferDub }.ifEmpty { sources }
        Log.i(
            LOG_TAG,
            "Resolviendo ep $episodeNumber ('$slug', preferDub=$preferDub) entre: ${preferredSources.joinToString { "${it.canonicalServerName}:${if (it.isDub) "LAT" else "SUB"}" }}",
        )

        // Resolver y sondear en paralelo hasta 5 candidatos: descarta hosts colgados como a3.mp4upload.com:183
        val stream = resolveFirstSuccessful(preferredSources.take(5))
        if (stream != null) {
            Log.i(LOG_TAG, "Fuente elegida: ${stream.serverName} (dub=${stream.isDub}) -> ${stream.streamUrl.take(90)}")
            logger.info("Fuente resuelta: ${stream.serverName} (dub=${stream.isDub}) -> ${stream.streamUrl}")
        } else {
            Log.w(LOG_TAG, "Ningún candidato respondió para '$slug' ep $episodeNumber")
        }
        return stream
    }

    suspend fun resolveBestStream(
        romajiTitle: String,
        englishTitle: String?,
        episodeNumber: Int,
        preferDub: Boolean = false,
    ): Result<WebStreamSource?> = withContext(Dispatchers.IO) {
        runSuspendCatching {
            resolveBestInternal(romajiTitle, englishTitle, episodeNumber, preferDub)
        }
    }

    /**
     * Resuelve y verifica varios candidatos **en paralelo**, devolviendo el primero que responde vivo.
     *
     * Concede una pequeña ventana de gracia a los servidores de mayor prioridad que también respondan vivos,
     * descartando inmediatamente enlaces muertos (403/404) o que cuelgan en handshake TLS (p.ej. a3.mp4upload.com).
     */
    private suspend fun resolveFirstSuccessful(
        sources: List<AnimeAV1Source>,
        timeoutMillis: Long = RESOLVE_TIMEOUT_MS,
    ): WebStreamSource? {
        if (sources.isEmpty()) return null

        // Solo se cachean URLs que ya pasaron ProbeResult.ALIVE.
        sources.firstNotNullOfOrNull { resolvedStreamCache[it.embedUrl] }?.let { cached ->
            Log.i(LOG_TAG, "Usando stream precargado: ${cached.serverName} -> ${cached.streamUrl.take(90)}")
            logger.info("Usando stream precargado: ${cached.serverName} -> ${cached.streamUrl}")
            return cached
        }

        return coroutineScope {
            val deferred = sources.mapIndexed { index, src ->
                async {
                    withTimeoutOrNull(timeoutMillis) {
                        val stream = extractStreamUnverified(src).getOrNull() ?: return@withTimeoutOrNull null
                        val probe = probeStream(stream.streamUrl, stream.headers, STREAM_PROBE_TIMEOUT_MS)
                        Log.i(
                            LOG_TAG,
                            "Sonda [${src.canonicalServerName}/${if (src.isDub) "LAT" else "SUB"}] -> $probe (${stream.streamUrl.take(80)})",
                        )
                        if (probe == ProbeResult.ALIVE) {
                            resolvedStreamCache[src.embedUrl] = stream
                        }
                        ResolvedCandidate(index = index, stream = stream, probe = probe)
                    }
                }
            }
            try {
                var pending = deferred.mapIndexed { index, job -> index to job }
                var chosen: WebStreamSource? = null
                var chosenIndex = Int.MAX_VALUE
                var fallbackTimeout: WebStreamSource? = null

                while (pending.isNotEmpty()) {
                    val (index, candidate) = select {
                        pending.forEach { (i, d) -> d.onAwait { i to it } }
                    }
                    pending = pending.filterNot { it.first == index }
                    if (candidate == null) continue
                    if (candidate.probe != ProbeResult.ALIVE) {
                        if (candidate.probe == ProbeResult.TIMEOUT && fallbackTimeout == null) {
                            fallbackTimeout = candidate.stream
                        }
                        continue
                    }

                    chosen = candidate.stream
                    chosenIndex = candidate.index

                    // Ventana de gracia: permitir que un servidor de MAYOR prioridad que también esté vivo gane.
                    val graceDeadline = System.currentTimeMillis() + PRIORITY_GRACE_MS
                    while (true) {
                        val remaining = graceDeadline - System.currentTimeMillis()
                        if (remaining <= 0L) break
                        val higherPriority = pending.filter { it.first < chosenIndex }
                        if (higherPriority.isEmpty()) break

                        val graceResult = withTimeoutOrNull<Pair<Int, ResolvedCandidate?>>(remaining) {
                            select<Pair<Int, ResolvedCandidate?>> {
                                higherPriority.forEach { (i, d) -> d.onAwait { i to it } }
                            }
                        }
                        if (graceResult == null) break

                        val (gIdx, gCandidate) = graceResult
                        pending = pending.filterNot { it.first == gIdx }
                        if (gCandidate != null && gCandidate.probe == ProbeResult.ALIVE && gCandidate.index < chosenIndex) {
                            chosen = gCandidate.stream
                            chosenIndex = gCandidate.index
                            if (chosenIndex == 0) break
                        }
                    }
                    break
                }

                if (chosen == null && fallbackTimeout != null) {
                    Log.w(LOG_TAG, "Ningún candidato respondió al probe; usando último recurso timeout: ${fallbackTimeout.serverName}")
                }
                chosen ?: fallbackTimeout
            } finally {
                deferred.forEach { it.cancel() }
            }
        }
    }

    /**
     * Fallback automático: devuelve el siguiente servidor del idioma elegido que resuelva bien, saltando los ya probados.
     * Invalida en caché el stream que falló para que no se vuelva a servir en la próxima reproducción.
     */
    suspend fun resolveNextStream(
        romajiTitle: String,
        englishTitle: String?,
        episodeNumber: Int,
        triedServers: Set<String>,
        failed: WebStreamSource? = null,
        preferDub: Boolean = failed?.isDub ?: false,
    ): WebStreamSource? = withContext(Dispatchers.IO) {
        failed?.let { invalidateStreamCache(it) }
        runSuspendCatching {
            val slug = resolveSlug(romajiTitle, englishTitle)
            val sources = getEpisodeSources(slug, episodeNumber)
            val candidates = sources.filter { it.isDub == preferDub }.ifEmpty { sources }
                .filter { src ->
                    triedServers.none { tried ->
                        tried.equals(src.server, ignoreCase = true) ||
                            tried.equals(src.canonicalServerName, ignoreCase = true)
                    }
                }
            Log.i(
                LOG_TAG,
                "Fallback ep $episodeNumber saltando $triedServers -> candidatos: ${candidates.joinToString { it.canonicalServerName }}",
            )
            // En paralelo: antes se probaban uno a uno, encadenando una petición HTTP por servidor.
            val stream = resolveFirstSuccessful(candidates)
            if (stream != null) {
                Log.i(LOG_TAG, "Fallback -> ${stream.serverName} (dub=${stream.isDub}): ${stream.streamUrl.take(90)}")
                logger.info("Fallback -> ${stream.serverName} (dub=${stream.isDub}): ${stream.streamUrl}")
            } else {
                Log.w(LOG_TAG, "Fallback agotado para ep $episodeNumber (saltados=$triedServers)")
            }
            stream
        }.getOrNull()
    }

    /**
     * Precarga en segundo plano el slug y las fuentes del episodio para que al pulsar 'Reproducir' esté en RAM.
     */
    suspend fun prefetchEpisode(
        romajiTitle: String,
        englishTitle: String?,
        episodeNumber: Int,
        preferDub: Boolean = false,
    ) = withContext(Dispatchers.IO) {
        runSuspendCatching {
            resolveBestStream(romajiTitle, englishTitle, episodeNumber, preferDub = preferDub)
        }
    }
}
