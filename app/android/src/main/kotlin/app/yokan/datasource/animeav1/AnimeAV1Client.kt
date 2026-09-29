package app.yokan.datasource.animeav1

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import me.him188.ani.utils.logging.logger
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class AnimeAV1Client(
    private val httpClient: HttpClient = HttpClient(),
) {
    private val logger = logger("AnimeAV1Client")

    // Caché en memoria para evitar repetir peticiones de red (0ms tras la primera resolución)
    private val slugCache = java.util.concurrent.ConcurrentHashMap<String, String>()
    private val episodeSourcesCache = java.util.concurrent.ConcurrentHashMap<String, List<AnimeAV1Source>>()
    private val resolvedStreamCache = java.util.concurrent.ConcurrentHashMap<String, WebStreamSource>()

    companion object {
        private const val BASE_URL = "https://animeav1.com"
        private const val UNS_BIO_BASE = "https://animeav1.uns.bio"
        private const val DEFAULT_USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
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
        runCatching {
            val encodedQuery = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8.name())
            val url = "$BASE_URL/catalogo?search=$encodedQuery"
            val response = httpClient.get(url) {
                header("User-Agent", DEFAULT_USER_AGENT)
                header("Referer", "$BASE_URL/")
            }

            if (!response.status.isSuccess()) {
                logger.warn("AnimeAV1 catalogo devolvió estado: ${response.status}")
                return@runCatching emptyList()
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
     * Prioriza estrictamente el servidor UPN (UPNShare / uns.bio) tal como se solicitó.
     */
    suspend fun getEpisodeSources(slug: String, episodeNumber: Int): List<AnimeAV1Source> = withContext(Dispatchers.IO) {
        val cacheKey = "$slug:$episodeNumber"
        episodeSourcesCache[cacheKey]?.let { return@withContext it }

        runCatching {
            val url = "$BASE_URL/media/$slug/$episodeNumber"
            val response = httpClient.get(url) {
                header("User-Agent", DEFAULT_USER_AGENT)
                header("Referer", "$BASE_URL/media/$slug")
            }

            if (!response.status.isSuccess()) {
                logger.warn("AnimeAV1 episodio devolvió código: ${response.status}")
                return@runCatching emptyList()
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

            // Ordenamiento prioritario:
            // 1. UPNShare / uns.bio primero (el más estable por defecto)
            // 2. MP4Upload
            // 3. YourUpload
            // 4. Voe
            // 5. Otros
            val sortedSources = sources.sortedWith(
                compareByDescending<AnimeAV1Source> { it.isUpn }
                    .thenByDescending { it.server.equals("MP4Upload", ignoreCase = true) }
                    .thenByDescending { it.server.equals("YourUpload", ignoreCase = true) }
                    .thenByDescending { it.server.equals("Voe", ignoreCase = true) }
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
     * En el caso de UPNShare, aplica el descifrado AES-128-CBC sin anuncios.
     */
    suspend fun resolveStream(source: AnimeAV1Source): Result<WebStreamSource> = withContext(Dispatchers.IO) {
        resolvedStreamCache[source.embedUrl]?.let { return@withContext Result.success(it) }

        runCatching {
            val webStream = if (source.isUpn) {
                val hash = Regex("""#([a-zA-Z0-9_-]+)""").find(source.embedUrl)?.groupValues?.get(1)
                    ?: throw IllegalArgumentException("No se encontró el hash en la URL de UPN: ${source.embedUrl}")

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

                // Extraer cfNative primero (el master m3u8 con proxy en Cloudflare), o cf, o source
                val cfNative = Regex(""""cfNative":"([^"]+)"""").find(decryptedJson)?.groupValues?.get(1)
                    ?.replace("\\/", "/")
                val cf = Regex(""""cf":"([^"]+)"""").find(decryptedJson)?.groupValues?.get(1)
                    ?.replace("\\/", "/")
                val sourceUrl = Regex(""""source":"([^"]+)"""").find(decryptedJson)?.groupValues?.get(1)
                    ?.replace("\\/", "/")

                val targetStream = cfNative ?: cf ?: sourceUrl
                ?: throw IllegalStateException("No se encontró URL de stream en la respuesta descifrada de UPN")

                WebStreamSource(
                    title = "AnimeAV1 (UPN - HLS Directo)",
                    serverName = "UPNShare",
                    streamUrl = targetStream,
                    headers = mapOf(
                        "Referer" to "$UNS_BIO_BASE/",
                        "Origin" to UNS_BIO_BASE,
                        "User-Agent" to DEFAULT_USER_AGENT,
                        "Accept" to "*/*",
                    ),
                    quality = "1080p",
                    isHls = true,
                )
            } else if (source.server.equals("MP4Upload", ignoreCase = true)) {
                val response = httpClient.get(source.embedUrl) {
                    header("User-Agent", DEFAULT_USER_AGENT)
                    header("Referer", "$BASE_URL/")
                }
                val html = response.bodyAsText()
                val mp4Match = Regex("""src:\s*"(https://[^"]+\.mp4)"""").find(html)?.groupValues?.get(1)
                    ?: Regex("""player\.src\(\s*\{[^\}]*src:\s*"(https://[^"]+\.mp4)"""").find(html)?.groupValues?.get(1)
                    ?: throw IllegalStateException("No se encontró URL de video en MP4Upload")

                WebStreamSource(
                    title = "AnimeAV1 (MP4Upload - 1080p)",
                    serverName = "MP4Upload",
                    streamUrl = mp4Match,
                    headers = mapOf(
                        "Referer" to "https://www.mp4upload.com/",
                        "User-Agent" to DEFAULT_USER_AGENT,
                    ),
                    quality = "1080p",
                    isHls = false,
                )
            } else if (source.server.equals("YourUpload", ignoreCase = true)) {
                val response = httpClient.get(source.embedUrl) {
                    header("User-Agent", DEFAULT_USER_AGENT)
                    header("Referer", "$BASE_URL/")
                }
                val html = response.bodyAsText()
                val mp4Match = Regex("""file:\s*["']([^"']+\.mp4[^"']*)["']""").find(html)?.groupValues?.get(1)
                    ?: throw IllegalStateException("No se encontró URL de video en YourUpload")

                WebStreamSource(
                    title = "AnimeAV1 (YourUpload - 1080p)",
                    serverName = "YourUpload",
                    streamUrl = mp4Match,
                    headers = mapOf(
                        "Referer" to source.embedUrl,
                        "User-Agent" to DEFAULT_USER_AGENT,
                    ),
                    quality = "1080p",
                    isHls = false,
                )
            } else {
                // Fallback para otros servidores embebidos
                WebStreamSource(
                    title = "AnimeAV1 (${source.server})",
                    serverName = source.server,
                    streamUrl = source.embedUrl,
                    headers = mapOf("Referer" to "$BASE_URL/"),
                    quality = source.quality,
                    isHls = false,
                )
            }
            resolvedStreamCache[source.embedUrl] = webStream
            webStream
        }
    }

    /**
     * Comprueba de forma ultrarrápida si el enlace de video está disponible (no 404, no caído).
     */
    suspend fun verifyStreamUrlAlive(url: String, headers: Map<String, String> = emptyMap()): Boolean = withContext(Dispatchers.IO) {
        runCatching {
            val response = httpClient.get(url) {
                headers.forEach { (k, v) -> header(k, v) }
                header("Range", "bytes=0-10")
            }
            response.status.value in 200..399
        }.getOrDefault(false)
    }

    /**
     * Resuelve la mejor fuente de streaming rápido para el episodio:
     * Intenta en cascada en orden de prioridad y verifica que el enlace responda;
     * si un servidor está caído (404/500/timeout), salta automáticamente al siguiente espejo.
     */
    suspend fun resolveBestStream(
        romajiTitle: String,
        englishTitle: String?,
        episodeNumber: Int,
    ): Result<WebStreamSource?> = withContext(Dispatchers.IO) {
        runCatching {
            val slug = resolveSlug(romajiTitle, englishTitle)
            val sources = getEpisodeSources(slug, episodeNumber)
            if (sources.isEmpty()) {
                logger.info("No se encontraron fuentes en AnimeAV1 para slug '$slug' ep $episodeNumber")
                return@runCatching null
            }

            // Filtrar preferentemente pistas SUB
            val subSources = sources.filter { !it.isDub }.ifEmpty { sources }

            // Resolver en PARALELO los 3 mejores servidores y devolver el primero (por prioridad)
            // que resuelva bien. Ya no se hace verifyStreamUrlAlive en el camino crítico:
            // ExoPlayer falla rápido si el enlace está caído y el usuario puede cambiar de fuente.
            coroutineScope {
                val deferred = subSources.take(3).map { src -> async { resolveStream(src) } }
                try {
                    for (d in deferred) {
                        val stream = d.await().getOrNull()
                        if (stream != null) {
                            logger.info("Fuente resuelta: ${stream.serverName} -> ${stream.streamUrl}")
                            return@coroutineScope stream
                        }
                    }
                    null
                } finally {
                    deferred.forEach { it.cancel() }
                }
            }
        }
    }

    /**
     * Fallback automático: devuelve el siguiente servidor SUB que resuelva bien, saltando los ya probados.
     * Invalida en caché el stream que falló para que no se vuelva a servir en la próxima reproducción.
     */
    suspend fun resolveNextStream(
        romajiTitle: String,
        englishTitle: String?,
        episodeNumber: Int,
        triedServers: Set<String>,
        failed: WebStreamSource? = null,
    ): WebStreamSource? = withContext(Dispatchers.IO) {
        failed?.let { f -> resolvedStreamCache.entries.removeAll { it.value.streamUrl == f.streamUrl } }
        runCatching {
            val slug = resolveSlug(romajiTitle, englishTitle)
            val sources = getEpisodeSources(slug, episodeNumber)
            val subs = sources.filter { !it.isDub }.ifEmpty { sources }
            for (src in subs) {
                if (triedServers.any { it.equals(src.server, ignoreCase = true) }) continue
                val stream = resolveStream(src).getOrNull() ?: continue
                logger.info("Fallback -> ${stream.serverName}: ${stream.streamUrl}")
                return@runCatching stream
            }
            null
        }.getOrNull()
    }

    /**
     * Precarga en segundo plano el slug y las fuentes del episodio para que al pulsar 'Reproducir' esté en RAM.
     */
    suspend fun prefetchEpisode(
        romajiTitle: String,
        englishTitle: String?,
        episodeNumber: Int,
    ) = withContext(Dispatchers.IO) {
        runCatching {
            resolveBestStream(romajiTitle, englishTitle, episodeNumber)
        }
    }
}
