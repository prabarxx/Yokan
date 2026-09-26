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
import kotlin.math.ln

class NyaaSearchEngine(
    private val httpClient: HttpClient = HttpClient(),
) {
    private val multiSubRegex = Regex("""(?i)\b(?:multiple\s+subtitles?|multi-?subs?)\b""")
    private val explicitSpanishRegex = Regex("""(?i)(?:\[|\()(?:SPA|SPA-LA|Castellano|Latino|Spanish|ESP|ES|LAT|ESP-LAT|SPA-LAT)(?:\]|\))""")
    private val spanishWordRegex = Regex("""(?i)\b(?:Español|Espanol|Castellano|Latino|Sub\s*Español|Sub\s*Espanol|Audio\s*Latino|Audio\s*Castellano)\b""")

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
     * Búsqueda inteligente y completa según la arquitectura de selección automática de fuentes.
     * Implementa cadena de rescate de títulos, numeración relativa/absoluta, detección estricta de español,
     * gestión de paquetes (batches) y ordenamiento por función heurística de puntuación.
     */
    suspend fun search(
        romajiTitle: String,
        englishTitle: String? = null,
        synonyms: List<String> = emptyList(),
        episodeNumber: Int? = null,
        absoluteEpisodeNumber: Int? = null,
        totalEpisodes: Int? = null,
    ): Result<List<NyaaTorrent>> = withContext(Dispatchers.IO_) {
        runCatching {
            val queries = generateSearchQueries(
                romajiTitle = romajiTitle,
                englishTitle = englishTitle,
                synonyms = synonyms,
                episodeNumber = episodeNumber,
                absoluteEpisodeNumber = absoluteEpisodeNumber,
            )

            val allTorrents = coroutineScope {
                queries.map { q ->
                    async { executeNyaaQuery(q, episodeNumber, absoluteEpisodeNumber, totalEpisodes) }
                }.awaitAll().flatten()
            }

            var deduplicated = allTorrents.distinctBy {
                it.infoHash.ifBlank { it.magnetUrl }.ifBlank { it.title }
            }

            // Fallback de rescate: Si no se encuentra nada con número de episodio (series en batch, películas, OVAs),
            // consultar títulos base sin episodio.
            if (deduplicated.isEmpty() && episodeNumber != null) {
                val fallbackQueries = generateFallbackQueries(romajiTitle, englishTitle, synonyms)
                val fallbackTorrents = coroutineScope {
                    fallbackQueries.map { q ->
                        async { executeNyaaQuery(q, episodeNumber, absoluteEpisodeNumber, totalEpisodes) }
                    }.awaitAll().flatten()
                }
                deduplicated = fallbackTorrents.distinctBy {
                    it.infoHash.ifBlank { it.magnetUrl }.ifBlank { it.title }
                }
            }

            // Ordenar por puntuación heurística descendente (Auto-Resolver)
            deduplicated.sortedByDescending { it.score }
        }
    }

    suspend fun search(
        romajiTitle: String,
        englishTitle: String? = null,
        episodeNumber: Int? = null,
    ): Result<List<NyaaTorrent>> = search(
        romajiTitle = romajiTitle,
        englishTitle = englishTitle,
        synonyms = emptyList(),
        episodeNumber = episodeNumber,
        absoluteEpisodeNumber = null,
        totalEpisodes = null,
    )

    suspend fun search(
        animeTitle: String,
        episodeNumber: Int? = null,
    ): Result<List<NyaaTorrent>> = search(
        romajiTitle = animeTitle,
        englishTitle = null,
        synonyms = emptyList(),
        episodeNumber = episodeNumber,
        absoluteEpisodeNumber = null,
        totalEpisodes = null,
    )

    /**
     * Resuelve de forma automática la fuente con el puntaje más alto según la función heurística.
     */
    suspend fun resolveBestTorrent(
        romajiTitle: String,
        englishTitle: String? = null,
        synonyms: List<String> = emptyList(),
        episodeNumber: Int,
        absoluteEpisodeNumber: Int? = null,
        totalEpisodes: Int? = null,
    ): Result<NyaaTorrent?> = withContext(Dispatchers.IO_) {
        search(
            romajiTitle = romajiTitle,
            englishTitle = englishTitle,
            synonyms = synonyms,
            episodeNumber = episodeNumber,
            absoluteEpisodeNumber = absoluteEpisodeNumber,
            totalEpisodes = totalEpisodes,
        ).map { torrents ->
            torrents.maxByOrNull { it.score }
        }
    }

    /**
     * Genera la cadena de consultas secuenciales de rescate (fallback) con formateo estricto
     * y manejo de numeración relativa vs. absoluta.
     */
    private fun generateSearchQueries(
        romajiTitle: String,
        englishTitle: String?,
        synonyms: List<String>,
        episodeNumber: Int?,
        absoluteEpisodeNumber: Int?,
    ): List<String> {
        val queries = mutableListOf<String>()

        val cleanRomaji = sanitizeTitle(romajiTitle)
        val baseRomaji = extractBaseTitle(cleanRomaji)
        val cleanEnglish = englishTitle?.let { sanitizeTitle(it) }?.takeIf { it.isNotBlank() }
        val baseEnglish = cleanEnglish?.let { extractBaseTitle(it) }?.takeIf { it.isNotBlank() }

        // Formateo estricto del episodio (04 o 004, nunca 4 plano)
        val epFormatted = episodeNumber?.let { " " + formatEpisodeNumber(it) }.orEmpty()

        // 1. [Erai-raws] "Romaji Title" 04
        if (baseRomaji.isNotBlank()) {
            queries.add("Erai-raws $baseRomaji$epFormatted")
        }

        // 2. "Romaji Title" 04
        if (baseRomaji.isNotBlank()) {
            queries.add("$baseRomaji$epFormatted")
        }

        // Romaji completo si difiere del base
        if (cleanRomaji.isNotBlank() && cleanRomaji != baseRomaji) {
            queries.add("$cleanRomaji$epFormatted")
        }

        // 3. "English Title" 04
        if (baseEnglish != null && baseEnglish.isNotBlank() && !baseEnglish.equals(baseRomaji, ignoreCase = true)) {
            queries.add("Erai-raws $baseEnglish$epFormatted")
            queries.add("$baseEnglish$epFormatted")
        }

        // 4. Nombres alternativos del array synonyms
        val cleanSynonyms = synonyms.map { sanitizeTitle(it) }
            .filter { it.isNotBlank() && it.length >= 3 && it.length <= 40 }
            .distinct()
            .take(3)

        for (synonym in cleanSynonyms) {
            val baseSynonym = extractBaseTitle(synonym)
            if (baseSynonym.isNotBlank() && !baseSynonym.equals(baseRomaji, ignoreCase = true) && !baseSynonym.equals(baseEnglish, ignoreCase = true)) {
                queries.add("$baseSynonym$epFormatted")
            }
        }

        // Manejo de numeración absoluta si difiere de la relativa (ej. Attack on Titan Season 3 - 04 -> Episode 41)
        if (absoluteEpisodeNumber != null && absoluteEpisodeNumber != episodeNumber) {
            val absFormatted = " " + formatEpisodeNumber(absoluteEpisodeNumber)
            if (baseRomaji.isNotBlank()) {
                queries.add("Erai-raws $baseRomaji$absFormatted")
                queries.add("$baseRomaji$absFormatted")
            }
            if (baseEnglish != null && baseEnglish.isNotBlank() && !baseEnglish.equals(baseRomaji, ignoreCase = true)) {
                queries.add("Erai-raws $baseEnglish$absFormatted")
                queries.add("$baseEnglish$absFormatted")
            }
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
        synonyms: List<String>,
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

        for (synonym in synonyms.take(2)) {
            val clean = extractBaseTitle(sanitizeTitle(synonym))
            if (clean.isNotBlank()) {
                queries.add(clean)
            }
        }

        return queries.filter { it.isNotBlank() }.distinct()
    }

    fun formatEpisodeNumber(ep: Int): String {
        return if (ep >= 100) ep.toString() else ep.toString().padStart(2, '0')
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

    private suspend fun executeNyaaQuery(
        queryParam: String,
        episodeNumber: Int?,
        absoluteEpisodeNumber: Int?,
        totalEpisodes: Int?,
    ): List<NyaaTorrent> {
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
                parseNyaaRss(response.bodyAsText(), episodeNumber, absoluteEpisodeNumber, totalEpisodes)
            }
        }.getOrDefault(emptyList())
    }

    private fun parseNyaaRss(
        xml: String,
        episodeNumber: Int?,
        absoluteEpisodeNumber: Int?,
        totalEpisodes: Int?,
    ): List<NyaaTorrent> {
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
            val rawSize = cleanCdata(sizePattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty()).ifBlank { "N/A" }
            val pubDate = cleanCdata(pubDatePattern.find(itemXml)?.groupValues?.get(1)?.trim().orEmpty())

            val quality = detectQuality(cleanTitle)
            val isSpanish = isSpanishCompatible(cleanTitle)
            val isTrustedGroup = isTrustedSpanishGroup(cleanTitle)

            // Detección y corrección de tamaño para colecciones completas (Batches)
            val sizeBytes = parseSizeToBytes(rawSize)
            val isBatch = isBatchTorrent(cleanTitle, sizeBytes)
            val displaySize = if (isBatch && sizeBytes > 0) {
                val batchEpisodes = extractBatchEpisodeCount(cleanTitle, totalEpisodes ?: 12)
                val estimatedPerEpBytes = sizeBytes / batchEpisodes.coerceAtLeast(1)
                "~${formatBytesToSize(estimatedPerEpBytes)} (Capítulo)"
            } else {
                rawSize
            }

            // Cálculo de Puntuación según fórmula de Auto-Resolver
            val score = calculateScore(
                torrentTitle = cleanTitle,
                seeders = seeders,
                quality = quality,
                isSpanish = isSpanish,
                isTrustedGroup = isTrustedGroup,
                episodeNumber = episodeNumber,
                absoluteEpisodeNumber = absoluteEpisodeNumber,
            )

            // Magnet enriquecido con trackers de alto rendimiento
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
                    size = rawSize,
                    quality = quality,
                    isSpanishOrMulti = isSpanish,
                    publishDate = pubDate,
                    torrentUrl = downloadLink,
                    infoHash = infoHash,
                    isBatch = isBatch,
                    displaySize = displaySize,
                    score = score,
                    isTrustedSpanishGroup = isTrustedGroup,
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

    /**
     * Reglas estrictas de detección de subtítulos en español:
     * - MultiSub de Erai-raws: [Multiple Subtitle], MultiSub, Multi-Sub
     * - Etiquetas explícitas: [SPA], [SPA-LA], [Castellano], [Latino], [Spanish], etc.
     * - Excluye falsos positivos como [Dual Audio] o [Multi-Audio] que solo implican audio JP+EN.
     */
    fun isSpanishCompatible(title: String): Boolean {
        if (multiSubRegex.containsMatchIn(title)) return true
        if (explicitSpanishRegex.containsMatchIn(title)) return true
        if (spanishWordRegex.containsMatchIn(title)) return true
        return false
    }

    fun isTrustedSpanishGroup(title: String): Boolean {
        val trustedGroups = listOf("Erai-raws", "TatakaiFuniSubs", "PuyaSubs", "PuyaSubs!", "Anime Wave Fenix")
        return trustedGroups.any { title.contains(it, ignoreCase = true) }
    }

    private fun parseSizeToBytes(sizeStr: String): Long {
        val clean = sizeStr.trim()
        val parts = clean.split(Regex("""\s+"""))
        if (parts.size < 2) return 0L
        val value = parts[0].toDoubleOrNull() ?: return 0L
        val unit = parts[1].uppercase()
        return when {
            unit.startsWith("T") -> (value * 1024.0 * 1024.0 * 1024.0 * 1024.0).toLong()
            unit.startsWith("G") -> (value * 1024.0 * 1024.0 * 1024.0).toLong()
            unit.startsWith("M") -> (value * 1024.0 * 1024.0).toLong()
            unit.startsWith("K") -> (value * 1024.0).toLong()
            else -> value.toLong()
        }
    }

    private fun formatBytesToSize(bytes: Long): String {
        val gib = 1024.0 * 1024.0 * 1024.0
        val mib = 1024.0 * 1024.0
        return when {
            bytes >= gib -> {
                val v = (bytes / gib * 10.0).toLong() / 10.0
                "$v GiB"
            }
            bytes >= mib -> {
                val v = (bytes / mib * 10.0).toLong() / 10.0
                "$v MiB"
            }
            else -> "${bytes / 1024} KiB"
        }
    }

    private fun extractBatchEpisodeCount(title: String, defaultCount: Int): Int {
        val rangeMatch = Regex("""\b0*(\d{1,3})\s*[-~–]\s*0*(\d{1,3})\b""").find(title)
        if (rangeMatch != null) {
            val start = rangeMatch.groupValues[1].toIntOrNull() ?: 1
            val end = rangeMatch.groupValues[2].toIntOrNull() ?: 1
            if (end > start) {
                return (end - start + 1)
            }
        }
        return defaultCount.coerceAtLeast(1)
    }

    private fun isBatchTorrent(title: String, sizeBytes: Long): Boolean {
        if (Regex("""(?i)\b(?:batch|complete|seasons?|\d{1,2}\s*[-~–]\s*\d{1,2})\b""").containsMatchIn(title)) {
            return true
        }
        return sizeBytes > 4_800_000_000L
    }

    /**
     * Fórmula heurística Auto-Resolver:
     * Puntuación = (Coincidencia exacta x 1000)
     *            + (Subtítulos en español x 500)
     *            + (Resolución 1080p x 200)
     *            + (Semillas > 5 x 100)
     *            + log(Seeders)
     *            + (Bonificación grupo de confianza)
     */
    fun calculateScore(
        torrentTitle: String,
        seeders: Int,
        quality: String,
        isSpanish: Boolean,
        isTrustedGroup: Boolean,
        episodeNumber: Int?,
        absoluteEpisodeNumber: Int?,
    ): Double {
        var score = 0.0

        val exactMatchFactor = if (episodeNumber != null) {
            checkEpisodeMatch(torrentTitle, episodeNumber, absoluteEpisodeNumber)
        } else {
            0.5
        }
        score += exactMatchFactor * 1000.0

        if (isSpanish) {
            score += 500.0
            if (isTrustedGroup) {
                score += 50.0
            }
        }

        if (quality.contains("1080", ignoreCase = true) || torrentTitle.contains("1080p", ignoreCase = true)) {
            score += 200.0
        } else if (quality.contains("720", ignoreCase = true) || torrentTitle.contains("720p", ignoreCase = true)) {
            score += 100.0
        }

        if (seeders > 5) {
            score += 100.0
        }

        score += ln(seeders.coerceAtLeast(1).toDouble())

        return score
    }

    private fun checkEpisodeMatch(title: String, episodeNumber: Int, absoluteEpisodeNumber: Int?): Double {
        val targets = listOfNotNull(episodeNumber, absoluteEpisodeNumber).distinct()
        for (ep in targets) {
            val patterns = listOf(
                Regex("""(?i)[sS]\d+[eE]0*${ep}\b"""),
                Regex("""(?i)\b(?:ep|episode|cap|capitulo)\.?\s*0*${ep}\b"""),
                Regex("""(?i)[eE]0*${ep}\b"""),
                Regex("""[\[\(-]\s*0*${ep}\s*[\]\)-]"""),
                Regex("""(?:\s|_)0*${ep}(?:\s|_|\.)"""),
                Regex("""\b0*${ep}\b""")
            )
            if (patterns.any { it.containsMatchIn(title) }) {
                val isExplicitBatch = Regex("""\b0*(\d{1,3})\s*[-~–]\s*0*(\d{1,3})\b""").containsMatchIn(title)
                return if (isExplicitBatch) 0.6 else 1.0
            }
        }

        val rangeMatch = Regex("""\b0*(\d{1,3})\s*[-~–]\s*0*(\d{1,3})\b""").find(title)
        if (rangeMatch != null) {
            val start = rangeMatch.groupValues[1].toIntOrNull() ?: 1
            val end = rangeMatch.groupValues[2].toIntOrNull() ?: 1
            for (ep in targets) {
                if (ep in start..end) {
                    return 0.5
                }
            }
        }

        return 0.0
    }
}
