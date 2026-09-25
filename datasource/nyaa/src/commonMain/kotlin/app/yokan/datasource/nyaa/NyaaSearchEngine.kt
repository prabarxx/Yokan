package app.yokan.datasource.nyaa

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import me.him188.ani.utils.coroutines.IO_

class NyaaSearchEngine(
    private val httpClient: HttpClient = HttpClient(),
) {
    private val spanishTokens = arrayOf(
        "Español", "Espanol", "Spanish", "SPA", "Castellano", "Latino", "Castilian",
        "Multi-Sub", "Multi-Subs", "Multisubs", "MultiSub", "MultiSubs",
        "Multiple Subtitle", "Multiple Subtitles",
        "Multi-Audio", "Multi-Dub", "MULTi", "Multi",
        "SubsPlease", "Erai-raws", "PuyaSubs!", "PuyaSubs", "Judas",
        "Anime Time", "Dual Audio", "Dual-Audio",
    )

    private val spanishWordRegex = Regex("""(?i)(?:^|[^a-zA-Z0-9])(?:SPA|ESP|LAT|ES|ESP-LAT|SPA-LAT)(?:$|[^a-zA-Z0-9])""")

    private val fastTrackers = listOf(
        "http://nyaa.tracker.wf:7777/announce",
        "udp://open.stealth.si:80/announce",
        "udp://tracker.opentrackr.org:1337/announce",
        "udp://tracker.torrent.eu.org:451/announce",
        "udp://tracker.moeking.me:6969/announce",
        "udp://explodie.org:6969/announce",
        "udp://tracker.dler.org:6969/announce",
        "udp://open.demonii.com:1337/announce",
        "udp://exodus.desync.com:6969/announce",
        "udp://tracker.openbittorrent.com:6969/announce",
        "wss://tracker.openwebtorrent.com:443/announce",
        "wss://tracker.btorrent.xyz:443/announce",
    )

    /**
     * Búsqueda inteligente multi-candidato para maximizar la aparición de fansubs de calidad (Erai-raws, SubsPlease)
     * y asegurar que todas las series encuentren torrents aunque los títulos de AniList contengan puntuación compleja.
     */
    suspend fun search(
        romajiTitle: String,
        englishTitle: String? = null,
        episodeNumber: Int? = null,
    ): Result<List<NyaaTorrent>> = withContext(Dispatchers.IO_) {
        runCatching {
            val queries = generateSearchQueries(romajiTitle, englishTitle, episodeNumber)
            val allTorrents = coroutineScope {
                queries.map { q ->
                    async { executeNyaaQuery(q) }
                }.awaitAll().flatten()
            }

            var deduplicated = allTorrents.distinctBy {
                it.infoHash.ifBlank { it.magnetUrl }.ifBlank { it.title }
            }

            // Fallback: Si no se encuentra nada para el episodio específico (películas, OVAs, batches),
            // buscar sin el número de episodio para que el usuario siempre tenga opciones disponibles.
            if (deduplicated.isEmpty() && episodeNumber != null) {
                val fallbackQueries = generateFallbackQueries(romajiTitle, englishTitle)
                val fallbackTorrents = coroutineScope {
                    fallbackQueries.map { q ->
                        async { executeNyaaQuery(q) }
                    }.awaitAll().flatten()
                }
                deduplicated = fallbackTorrents.distinctBy {
                    it.infoHash.ifBlank { it.magnetUrl }.ifBlank { it.title }
                }
            }

            deduplicated.sortedWith(
                compareByDescending<NyaaTorrent> { it.isSpanishOrMulti }
                    .thenByDescending { it.seeders }
            )
        }
    }

    suspend fun search(
        animeTitle: String,
        episodeNumber: Int? = null,
    ): Result<List<NyaaTorrent>> = search(
        romajiTitle = animeTitle,
        englishTitle = null,
        episodeNumber = episodeNumber,
    )

    private fun generateSearchQueries(
        romajiTitle: String,
        englishTitle: String?,
        episodeNumber: Int?,
    ): List<String> {
        val epFormatted = episodeNumber?.let { " " + it.toString().padStart(2, '0') }.orEmpty()
        val queries = mutableListOf<String>()

        val cleanRomaji = sanitizeTitle(romajiTitle)
        val baseRomaji = extractBaseTitle(cleanRomaji)
        val cleanEnglish = englishTitle?.let { sanitizeTitle(it) }?.takeIf { it.isNotBlank() }
        val baseEnglish = cleanEnglish?.let { extractBaseTitle(it) }?.takeIf { it.isNotBlank() }

        // 1. Erai-raws con base Romaji (garantiza que aparezcan los lanzamientos multi-sub con español)
        if (baseRomaji.isNotBlank()) {
            queries.add("Erai-raws $baseRomaji$epFormatted")
        }

        // 2. Búsqueda principal abierta con Romaji base + episodio
        if (baseRomaji.isNotBlank()) {
            queries.add("$baseRomaji$epFormatted")
        }

        // 3. Romaji completo si difiere del base (para animes con subtítulos de temporada específicos)
        if (cleanRomaji.isNotBlank() && cleanRomaji != baseRomaji) {
            queries.add("$cleanRomaji$epFormatted")
        }

        // 4. Si hay título en inglés y es diferente: Erai-raws y búsqueda general
        if (baseEnglish != null && baseEnglish.isNotBlank() && !baseEnglish.equals(baseRomaji, ignoreCase = true)) {
            queries.add("Erai-raws $baseEnglish$epFormatted")
            queries.add("$baseEnglish$epFormatted")
        }

        // 5. SubsPlease con base Romaji
        if (baseRomaji.isNotBlank()) {
            queries.add("SubsPlease $baseRomaji$epFormatted")
        }

        return queries.filter { it.isNotBlank() }.distinct()
    }

    private fun generateFallbackQueries(
        romajiTitle: String,
        englishTitle: String?,
    ): List<String> {
        val queries = mutableListOf<String>()
        val baseRomaji = extractBaseTitle(sanitizeTitle(romajiTitle))
        val baseEnglish = englishTitle?.let { extractBaseTitle(sanitizeTitle(it)) }

        if (baseRomaji.isNotBlank()) {
            queries.add("Erai-raws $baseRomaji")
            queries.add(baseRomaji)
        }
        if (baseEnglish != null && baseEnglish.isNotBlank() && !baseEnglish.equals(baseRomaji, ignoreCase = true)) {
            queries.add("Erai-raws $baseEnglish")
            queries.add(baseEnglish)
        }
        return queries.filter { it.isNotBlank() }.distinct()
    }

    private fun sanitizeTitle(title: String): String {
        return title
            .replace(Regex("""[:\-_~!?/,.]"""), " ")
            .replace(Regex("""\((?:TV|Batch|BD|Movie|\d{4})\)""", RegexOption.IGNORE_CASE), " ")
            .replace(Regex("""\s+"""), " ")
            .trim()
    }

    private fun extractBaseTitle(cleanTitle: String): String {
        val seasonCut = cleanTitle.split(
            Regex("""(?i)\s+(?:Season|\d+(?:st|nd|rd|th)\s+Season|Part|Cour|Hen|Gekijouban)\b""")
        ).firstOrNull()?.trim()
        return (seasonCut ?: cleanTitle).ifBlank { cleanTitle }
    }

    private suspend fun executeNyaaQuery(queryParam: String): List<NyaaTorrent> {
        return runCatching {
            val response = httpClient.get("https://nyaa.si/") {
                parameter("page", "rss")
                parameter("c", "1_0")
                parameter("s", "seeders")
                parameter("o", "desc")
                parameter("q", queryParam)
            }
            if (!response.status.isSuccess()) {
                emptyList()
            } else {
                parseNyaaRss(response.bodyAsText())
            }
        }.getOrDefault(emptyList())
    }

    private fun parseNyaaRss(xml: String): List<NyaaTorrent> {
        val items = mutableListOf<NyaaTorrent>()
        val itemPattern = Regex("""<item>([\s\S]*?)</item>""", RegexOption.IGNORE_CASE)
        val titlePattern = Regex("""<title>([\s\S]*?)</title>""", RegexOption.IGNORE_CASE)
        val linkPattern = Regex("""<link>([\s\S]*?)</link>""", RegexOption.IGNORE_CASE)
        val seedersPattern = Regex("""<nyaa:seeders>(\d+)</nyaa:seeders>""", RegexOption.IGNORE_CASE)
        val leechersPattern = Regex("""<nyaa:leechers>(\d+)</nyaa:leechers>""", RegexOption.IGNORE_CASE)
        val sizePattern = Regex("""<nyaa:size>([\s\S]*?)</nyaa:size>""", RegexOption.IGNORE_CASE)
        val pubDatePattern = Regex("""<pubDate>([\s\S]*?)</pubDate>""", RegexOption.IGNORE_CASE)
        val infoHashPattern = Regex("""<nyaa:infoHash>([\s\S]*?)</nyaa:infoHash>""", RegexOption.IGNORE_CASE)

        for (match in itemPattern.findAll(xml)) {
            val itemXml = match.groupValues[1]

            val rawTitle = titlePattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty()
            val cleanTitle = cleanCdata(rawTitle)
            if (cleanTitle.isBlank()) continue

            val rawLink = linkPattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty()
            val downloadLink = cleanCdata(rawLink)

            val rawInfoHash = infoHashPattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty()
            val infoHash = cleanCdata(rawInfoHash)

            val seeders = seedersPattern.find(itemXml)?.groupValues?.get(1)?.toIntOrNull() ?: 0
            val leechers = leechersPattern.find(itemXml)?.groupValues?.get(1)?.toIntOrNull() ?: 0
            val size = cleanCdata(sizePattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty()).ifBlank { "N/A" }
            val pubDate = cleanCdata(pubDatePattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty())

            val quality = detectQuality(cleanTitle)
            val isSpanish = isSpanishOrMulti(cleanTitle)

            // Construir magnet enriquecido con los mejores trackers públicos de alta velocidad
            val magnetUrl = if (infoHash.isNotBlank()) {
                buildString {
                    append("magnet:?xt=urn:btih:").append(infoHash)
                    append("&dn=").append(cleanTitle)
                    for (tr in fastTrackers) {
                        append("&tr=").append(tr)
                    }
                }
            } else {
                downloadLink
            }

            items.add(
                NyaaTorrent(
                    title = cleanTitle,
                    magnetUrl = magnetUrl,
                    seeders = seeders,
                    leechers = leechers,
                    size = size,
                    quality = quality,
                    isSpanishOrMulti = isSpanish,
                    publishDate = pubDate,
                    torrentUrl = downloadLink,
                    infoHash = infoHash,
                )
            )
        }

        return items
    }

    private fun cleanCdata(str: String): String {
        return str.removePrefix("<![CDATA[").removeSuffix("]]>").trim()
    }

    private fun detectQuality(title: String): String {
        return when {
            title.contains("2160p", ignoreCase = true) || title.contains("4K", ignoreCase = true) -> "4K"
            title.contains("1080p", ignoreCase = true) -> "1080p"
            title.contains("720p", ignoreCase = true) -> "720p"
            title.contains("480p", ignoreCase = true) -> "480p"
            else -> "HD"
        }
    }

    private fun isSpanishOrMulti(title: String): Boolean {
        if (spanishTokens.any { title.contains(it, ignoreCase = true) }) return true
        return spanishWordRegex.containsMatchIn(title)
    }
}
