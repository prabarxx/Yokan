package app.yokan.datasource.nyaa

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
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
    )

    private val spanishWordRegex = Regex("""(?i)(?:^|[^a-zA-Z0-9])(?:SPA|ESP|LAT|ES|ESP-LAT|SPA-LAT)(?:$|[^a-zA-Z0-9])""")

    suspend fun search(
        animeTitle: String,
        episodeNumber: Int? = null,
    ): Result<List<NyaaTorrent>> = withContext(Dispatchers.IO_) {
        runCatching {
            val queryParam = buildString {
                append(animeTitle.trim())
                if (episodeNumber != null && episodeNumber > 0) {
                    append(" ")
                    append(episodeNumber.toString().padStart(2, '0'))
                }
            }

            val response = httpClient.get("https://nyaa.si/") {
                parameter("page", "rss")
                parameter("c", "1_0")
                parameter("s", "seeders")
                parameter("o", "desc")
                parameter("q", queryParam)
            }
            if (!response.status.isSuccess()) {
                throw IllegalStateException("Nyaa request failed with code ${response.status}")
            }

            val xmlText = response.bodyAsText()
            parseNyaaRss(xmlText)
        }
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

        for (match in itemPattern.findAll(xml)) {
            val itemXml = match.groupValues[1]

            val rawTitle = titlePattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty()
            val cleanTitle = cleanCdata(rawTitle)
            if (cleanTitle.isBlank()) continue

            val rawLink = linkPattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty()
            val magnetUrl = cleanCdata(rawLink)
            if (magnetUrl.isBlank()) continue

            val seeders = seedersPattern.find(itemXml)?.groupValues?.get(1)?.toIntOrNull() ?: 0
            val leechers = leechersPattern.find(itemXml)?.groupValues?.get(1)?.toIntOrNull() ?: 0
            val size = cleanCdata(sizePattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty()).ifBlank { "N/A" }
            val pubDate = cleanCdata(pubDatePattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty())

            val quality = detectQuality(cleanTitle)
            val isSpanish = isSpanishOrMulti(cleanTitle)

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
                )
            )
        }

        // Ordenar: primero los torrents en español/multi-sub, y luego por sembradores descendente
        return items.sortedWith(
            compareByDescending<NyaaTorrent> { it.isSpanishOrMulti }
                .thenByDescending { it.seeders }
        )
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
