package app.yokan.datasource.animeav1

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.him188.ani.utils.logging.logger
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class AnimeAV1Client(
    private val httpClient: HttpClient = HttpClient(),
) {
    private val logger = logger("AnimeAV1Client")

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
        val candidates = searchCatalog(romajiTitle).ifEmpty {
            englishTitle?.let { searchCatalog(it) }.orEmpty()
        }

        if (candidates.isNotEmpty()) {
            val romajiClean = romajiTitle.lowercase().trim()
            val exactMatch = candidates.firstOrNull { it.title.equals(romajiClean, ignoreCase = true) }
            if (exactMatch != null) return exactMatch.slug

            val englishClean = englishTitle?.lowercase()?.trim()
            if (englishClean != null) {
                val exactEnglish = candidates.firstOrNull { it.title.equals(englishClean, ignoreCase = true) }
                if (exactEnglish != null) return exactEnglish.slug
            }

            val partialMatch = candidates.firstOrNull {
                it.title.contains(romajiClean, ignoreCase = true) ||
                        (englishClean != null && it.title.contains(englishClean, ignoreCase = true))
            }
            if (partialMatch != null) return partialMatch.slug

            return candidates.first().slug
        }

        return titleToSlug(romajiTitle)
    }

    /**
     * Extrae los servidores de reproducción (embeds) para un episodio determinado.
     * Prioriza estrictamente el servidor UPN (UPNShare / uns.bio) tal como se solicitó.
     */
    suspend fun getEpisodeSources(slug: String, episodeNumber: Int): List<AnimeAV1Source> = withContext(Dispatchers.IO) {
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

            val serverRegex = Regex("""\{server:"([^"]+)",url:"([^"]+)"\}""")

            // 1. Extraer fuentes Subtituladas (SUB)
            val subBlock = Regex("""embeds:\{SUB:\[(.*?)\]""").find(html)?.groupValues?.get(1)
            if (!subBlock.isNullOrBlank()) {
                serverRegex.findAll(subBlock).forEach { match ->
                    val server = match.groupValues[1]
                    val embedUrl = match.groupValues[2]
                    sources.add(AnimeAV1Source(server = server, embedUrl = embedUrl, isDub = false))
                }
            }

            // 2. Extraer fuentes Dobladas (DUB)
            val dubBlock = Regex("""DUB:\[(.*?)\]""").find(html)?.groupValues?.get(1)
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
            // 3. Voe
            // 4. Otros
            sources.sortedWith(
                compareByDescending<AnimeAV1Source> { it.isUpn }
                    .thenByDescending { it.server.equals("MP4Upload", ignoreCase = true) }
                    .thenByDescending { it.server.equals("Voe", ignoreCase = true) }
            )
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
        runCatching {
            if (source.isUpn) {
                val hash = Regex("""#([a-zA-Z0-9]+)""").find(source.embedUrl)?.groupValues?.get(1)
                    ?: throw IllegalArgumentException("No se encontró el hash en la URL de UPN: ${source.embedUrl}")

                val apiUrl = "$UNS_BIO_BASE/api/v1/video?id=$hash&w=1920&h=1080&r="
                val response = httpClient.get(apiUrl) {
                    header("Referer", "$UNS_BIO_BASE/")
                    header("Origin", UNS_BIO_BASE)
                    header("User-Agent", DEFAULT_USER_AGENT)
                }

                if (!response.status.isSuccess()) {
                    throw IllegalStateException("UPNShare API devolvió código: ${response.status}")
                }

                val hexPayload = response.bodyAsText().trim()
                val decryptedJson = AnimeAV1Cipher.decryptHex(hexPayload)

                // Extraer cfNative o source (HLS master playlist)
                val cfNative = Regex(""""cfNative":"([^"]+)"""").find(decryptedJson)?.groupValues?.get(1)
                    ?.replace("\\/", "/")
                val sourceUrl = Regex(""""source":"([^"]+)"""").find(decryptedJson)?.groupValues?.get(1)
                    ?.replace("\\/", "/")

                val targetStream = cfNative ?: sourceUrl
                ?: throw IllegalStateException("No se encontró URL de stream en la respuesta descifrada de UPN")

                WebStreamSource(
                    title = "AnimeAV1 (UPN - HLS Directo)",
                    serverName = "UPNShare",
                    streamUrl = targetStream,
                    headers = mapOf(
                        "Referer" to "$UNS_BIO_BASE/",
                        "Origin" to UNS_BIO_BASE,
                        "User-Agent" to DEFAULT_USER_AGENT,
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
        }
    }

    /**
     * Resuelve la mejor fuente de streaming rápido para el episodio:
     * Intenta primero con UPN (por defecto); si falla, recurre en cascada a los otros servidores.
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

            // 1. Intentar UPN primero (prioridad máxima)
            val upnSource = subSources.firstOrNull { it.isUpn }
            if (upnSource != null) {
                val upnResult = resolveStream(upnSource)
                if (upnResult.isSuccess) {
                    logger.info("Resolución exitosa de stream UPN: ${upnResult.getOrNull()?.streamUrl}")
                    return@runCatching upnResult.getOrNull()
                } else {
                    logger.warn("Falló resolución UPN: ${upnResult.exceptionOrNull()?.message}, probando alternativas...")
                }
            }

            // 2. Intentar alternativas en orden
            for (source in subSources) {
                if (source.isUpn) continue // ya intentado
                val res = resolveStream(source)
                if (res.isSuccess) {
                    return@runCatching res.getOrNull()
                }
            }

            null
        }
    }
}
