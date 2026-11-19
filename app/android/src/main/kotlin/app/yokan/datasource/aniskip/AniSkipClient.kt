package app.yokan.datasource.aniskip

import android.util.Log
import app.yokan.datasource.animeav1.TtlCache
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import java.util.Locale
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import me.him188.ani.utils.coroutines.runSuspendCatching
import org.json.JSONObject
import org.openani.mediamp.InternalMediampApi
import org.openani.mediamp.metadata.Chapter

enum class SkipSegmentType(
    val apiKey: String,
    val label: String,
    val buttonLabel: String,
) {
    OPENING("op", "Opening", "Saltar Opening"),
    MIXED_OPENING("mixed-op", "Opening", "Saltar Opening"),
    ENDING("ed", "Ending", "Saltar Ending"),
    MIXED_ENDING("mixed-ed", "Ending", "Saltar Ending"),
    RECAP("recap", "Resumen", "Saltar Resumen");

    val isOpening: Boolean get() = this == OPENING || this == MIXED_OPENING
    val isEnding: Boolean get() = this == ENDING || this == MIXED_ENDING
    val isPureOpening: Boolean get() = this == OPENING
    val isPureEnding: Boolean get() = this == ENDING

    companion object {
        fun fromApiKey(raw: String): SkipSegmentType? = when (raw.lowercase(Locale.US).trim()) {
            "op" -> OPENING
            "mixed-op" -> MIXED_OPENING
            "ed" -> ENDING
            "mixed-ed" -> MIXED_ENDING
            "recap" -> RECAP
            else -> null
        }
    }
}

data class SkipSegment(
    val type: SkipSegmentType,
    val startMillis: Long,
    val endMillis: Long,
    val fromEmbeddedChapter: Boolean = false,
) {
    val durationMillis: Long get() = (endMillis - startMillis).coerceAtLeast(0L)
    val isOpening: Boolean get() = type.isOpening
    val isEnding: Boolean get() = type.isEnding
    val isAutoSkippableOpening: Boolean get() = type.isPureOpening
    val isAutoSkippableEnding: Boolean get() = type.isPureEnding
    val key: String get() = "${type.apiKey}:$startMillis:$endMillis"

    @OptIn(InternalMediampApi::class)
    fun toChapter(): Chapter = Chapter(
        name = type.label,
        durationMillis = durationMillis,
        offsetMillis = startMillis,
    )
}

private val CHAPTER_OPENING_REGEX = Regex(
    """\b(?:op|op\s*\d+|opening|opening\s+theme|opening\s+credits|opening\s+song|intro)\b""",
    RegexOption.IGNORE_CASE,
)
private val CHAPTER_ENDING_REGEX = Regex(
    """\b(?:ed|ed\s*\d+|ending|ending\s+theme|ending\s+credits|ending\s+song|outro|credits)\b""",
    RegexOption.IGNORE_CASE,
)
private val CHAPTER_RECAP_REGEX = Regex(
    """\b(?:recap|resumen|previously)\b""",
    RegexOption.IGNORE_CASE,
)

/**
 * Extrae segmentos saltables (OP / ED / Recap) directamente de los capítulos embebidos del archivo
 * (p. ej. MKV en torrents), que son 100% exactos respecto al video reproducido.
 */
fun extractSkipSegmentsFromChapters(
    chapters: List<Chapter>,
    totalDurationMillis: Long,
): List<SkipSegment> {
    if (chapters.isEmpty()) return emptyList()

    var opening: SkipSegment? = null
    var ending: SkipSegment? = null
    var recap: SkipSegment? = null

    for (chapter in chapters) {
        val rawName = chapter.name.trim()
        if (rawName.isEmpty()) continue
        val startMs = chapter.offsetMillis.coerceAtLeast(0L)
        val rawEndMs = startMs + chapter.durationMillis.coerceAtLeast(0L)
        val endMs = if (totalDurationMillis > 0L) {
            rawEndMs.coerceAtMost(totalDurationMillis)
        } else {
            rawEndMs
        }
        val durMs = endMs - startMs
        if (durMs < 10_000L) continue

        val type = when {
            CHAPTER_OPENING_REGEX.containsMatchIn(rawName) -> SkipSegmentType.OPENING
            CHAPTER_ENDING_REGEX.containsMatchIn(rawName) -> SkipSegmentType.ENDING
            CHAPTER_RECAP_REGEX.containsMatchIn(rawName) -> SkipSegmentType.RECAP
            else -> null
        } ?: continue

        when (type) {
            SkipSegmentType.OPENING -> {
                if (opening == null && durMs <= 210_000L) {
                    opening = SkipSegment(
                        type = type,
                        startMillis = startMs,
                        endMillis = endMs,
                        fromEmbeddedChapter = true,
                    )
                }
            }
            SkipSegmentType.ENDING -> {
                val minStartMs = if (totalDurationMillis >= 300_000L) (totalDurationMillis * 0.45).toLong() else 0L
                if (ending == null && durMs <= 210_000L && startMs >= minStartMs) {
                    ending = SkipSegment(
                        type = type,
                        startMillis = startMs,
                        endMillis = endMs,
                        fromEmbeddedChapter = true,
                    )
                }
            }
            SkipSegmentType.RECAP -> {
                val maxStartMs = if (totalDurationMillis >= 300_000L) (totalDurationMillis * 0.50).toLong() else Long.MAX_VALUE
                if (recap == null && durMs <= 300_000L && startMs < maxStartMs) {
                    recap = SkipSegment(
                        type = type,
                        startMillis = startMs,
                        endMillis = endMs,
                        fromEmbeddedChapter = true,
                    )
                }
            }
            else -> Unit
        }
    }

    return listOfNotNull(opening, ending, recap).sortedBy { it.startMillis }
}

/**
 * Combina capítulos embebidos (prioridad máxima, exactos al archivo) con tiempos de AniSkip
 * filtrados para la duración real del video.
 */
fun mergeSkipSegments(
    embeddedChapters: List<Chapter>,
    aniSkipSegments: List<SkipSegment>,
    totalDurationMillis: Long,
): List<SkipSegment> {
    val embedded = extractSkipSegmentsFromChapters(embeddedChapters, totalDurationMillis)
    if (embedded.isEmpty()) return aniSkipSegments
    if (aniSkipSegments.isEmpty()) return embedded

    val hasEmbeddedOpening = embedded.any { it.isOpening }
    val hasEmbeddedEnding = embedded.any { it.isEnding }
    val hasEmbeddedRecap = embedded.any { it.type == SkipSegmentType.RECAP }

    val merged = embedded.toMutableList()
    for (seg in aniSkipSegments) {
        val covered = when {
            seg.isOpening -> hasEmbeddedOpening
            seg.isEnding -> hasEmbeddedEnding
            seg.type == SkipSegmentType.RECAP -> hasEmbeddedRecap
            else -> false
        }
        if (!covered && merged.none { segmentsOverlapHeavily(it, seg) }) {
            merged.add(seg)
        }
    }
    return merged.sortedBy { it.startMillis }
}

private fun segmentsOverlapHeavily(a: SkipSegment, b: SkipSegment): Boolean {
    val overlap = min(a.endMillis, b.endMillis) - max(a.startMillis, b.startMillis)
    if (overlap <= 0L) return false
    val shorter = min(a.durationMillis, b.durationMillis).coerceAtLeast(1L)
    return overlap.toDouble() / shorter.toDouble() >= 0.5
}

/**
 * Cliente ligero para la API pública AniSkip v2 (tiempos crowdsourced de Opening, Ending y Recap).
 * Consulta usando la duración real del video (`episodeLength`) y valida cada segmento contra dicha
 * duración para evitar desfases entre distintas ediciones/cortes.
 * Usa org.json para evitar cualquier incidencia con R8 en builds release.
 */
class AniSkipClient(
    private val httpClient: HttpClient,
) {
    private data class ParsedCandidate(
        val segment: SkipSegment,
        val reportedLengthSec: Double,
        val lengthDiffSec: Double,
    )

    private val cache = TtlCache<String, List<SkipSegment>>(
        ttlMillis = CACHE_TTL_MS,
        maxSize = 256,
    )

    suspend fun getSkipSegments(
        malId: Int,
        episodeNumber: Int,
        episodeLengthMillis: Long,
    ): List<SkipSegment> = withContext(Dispatchers.IO) {
        if (malId <= 0 || episodeNumber <= 0 || episodeLengthMillis < MIN_EPISODE_DURATION_MS) {
            return@withContext emptyList()
        }
        val lengthBucket = episodeLengthMillis / 2_000L
        val cacheKey = "$malId:$episodeNumber:$lengthBucket"
        cache[cacheKey]?.let { return@withContext it }

        val actualLengthSec = episodeLengthMillis / 1000.0
        val segments = withTimeoutOrNull(REQUEST_TIMEOUT_MS) {
            runSuspendCatching {
                val primary = fetchRawSkipTimes(
                    malId = malId,
                    episodeNumber = episodeNumber,
                    episodeLengthParam = String.format(Locale.US, "%.3f", actualLengthSec),
                )
                val body = when {
                    primary.body != null -> primary.body
                    primary.notFound -> {
                        // Fallback solo para entradas antiguas guardadas con episodeLength=0 en AniSkip;
                        // parseSkipTimes seguirá rechazando cualquier corte cuya duración real difiera >10s.
                        fetchRawSkipTimes(
                            malId = malId,
                            episodeNumber = episodeNumber,
                            episodeLengthParam = "0",
                        ).body
                    }
                    else -> return@runSuspendCatching null
                } ?: return@runSuspendCatching emptyList()

                parseSkipTimes(
                    rawJson = body,
                    actualDurationMillis = episodeLengthMillis,
                    malId = malId,
                    episodeNumber = episodeNumber,
                )
            }.getOrNull()
        }

        if (segments != null) {
            cache[cacheKey] = segments
            segments
        } else {
            emptyList()
        }
    }

    private data class FetchResult(
        val body: String? = null,
        val notFound: Boolean = false,
    )

    private suspend fun fetchRawSkipTimes(
        malId: Int,
        episodeNumber: Int,
        episodeLengthParam: String,
    ): FetchResult {
        val response = httpClient.get("$BASE_URL/skip-times/$malId/$episodeNumber") {
            header("User-Agent", "Yokan/2.7.1 (Android)")
            parameter("types", "op")
            parameter("types", "ed")
            parameter("types", "mixed-op")
            parameter("types", "mixed-ed")
            parameter("types", "recap")
            parameter("episodeLength", episodeLengthParam)
        }

        if (response.status == HttpStatusCode.NotFound) {
            return FetchResult(notFound = true)
        }
        if (!response.status.isSuccess()) {
            Log.w(TAG, "AniSkip HTTP ${response.status.value} para malId=$malId ep=$episodeNumber len=$episodeLengthParam")
            return FetchResult()
        }
        return FetchResult(body = response.bodyAsText())
    }

    private fun parseSkipTimes(
        rawJson: String,
        actualDurationMillis: Long,
        malId: Int,
        episodeNumber: Int,
    ): List<SkipSegment> {
        val root = JSONObject(rawJson)
        if (!root.optBoolean("found", false)) return emptyList()
        val results = root.optJSONArray("results") ?: return emptyList()

        val actualLengthSec = actualDurationMillis / 1000.0
        val candidates = mutableListOf<ParsedCandidate>()

        for (i in 0 until results.length()) {
            val item = results.optJSONObject(i) ?: continue
            val type = SkipSegmentType.fromApiKey(item.optString("skipType", "")) ?: continue
            val interval = item.optJSONObject("interval") ?: continue
            val startSec = interval.optDouble("startTime", Double.NaN)
            val endSec = interval.optDouble("endTime", Double.NaN)
            if (startSec.isNaN() || endSec.isNaN() || startSec < 0.0 || endSec <= startSec) continue

            val reportedLengthSec = item.optDouble("episodeLength", 0.0)
                .takeIf { !it.isNaN() && it > 0.0 } ?: 0.0
            val lengthDiffSec = if (reportedLengthSec > 0.0) {
                abs(reportedLengthSec - actualLengthSec)
            } else {
                UNKNOWN_LENGTH_DIFF_SEC
            }

            // Rechazar cortes cuya duración grabada en AniSkip difiera demasiado de este video
            // (evita desfases de 15–190s entre ediciones TV/Crunchyroll/Prime/BD).
            if (reportedLengthSec > 0.0 && lengthDiffSec > MAX_EPISODE_LENGTH_DIFF_SECONDS) {
                Log.d(
                    TAG,
                    "Descartado ${type.apiKey} mal=$malId ep=$episodeNumber: " +
                        "epLen=${String.format(Locale.US, "%.1f", reportedLengthSec)}s " +
                        "vs real=${String.format(Locale.US, "%.1f", actualLengthSec)}s " +
                        "(diff=${String.format(Locale.US, "%.1f", lengthDiffSec)}s)",
                )
                continue
            }

            val startMs = (startSec * 1000.0).toLong().coerceAtLeast(0L)
            if (startMs >= actualDurationMillis) continue
            val endMs = (endSec * 1000.0).toLong().coerceAtMost(actualDurationMillis)
            val segDurationMs = endMs - startMs

            val maxSegDurationMs = if (type == SkipSegmentType.RECAP) MAX_RECAP_DURATION_MS else MAX_OP_ED_DURATION_MS
            if (segDurationMs < MIN_SEGMENT_DURATION_MS || segDurationMs > maxSegDurationMs) continue

            // Descartar Endings absurdos ubicados en la primera mitad del episodio (entradas erróneas/troll)
            if (type.isEnding && actualDurationMillis >= 300_000L && startMs < (actualDurationMillis * 0.45).toLong()) {
                continue
            }
            // Los resúmenes siempre van en la primera mitad del episodio
            if (type == SkipSegmentType.RECAP && actualDurationMillis >= 300_000L && startMs >= (actualDurationMillis * 0.50).toLong()) {
                continue
            }

            candidates.add(
                ParsedCandidate(
                    segment = SkipSegment(
                        type = type,
                        startMillis = startMs,
                        endMillis = endMs,
                    ),
                    reportedLengthSec = reportedLengthSec,
                    lengthDiffSec = lengthDiffSec,
                ),
            )
        }

        val selectedOpening = selectBestForCategory(
            pure = candidates.filter { it.segment.type == SkipSegmentType.OPENING },
            mixed = candidates.filter { it.segment.type == SkipSegmentType.MIXED_OPENING },
        )
        val selectedEnding = selectBestForCategory(
            pure = candidates.filter { it.segment.type == SkipSegmentType.ENDING },
            mixed = candidates.filter { it.segment.type == SkipSegmentType.MIXED_ENDING },
        )
        val selectedRecap = candidates
            .filter { it.segment.type == SkipSegmentType.RECAP }
            .minByOrNull { it.lengthDiffSec }
            ?.takeIf { recap ->
                val conflictsWithOp = selectedOpening?.let { segmentsOverlapHeavily(it.segment, recap.segment) } == true
                val conflictsWithEd = selectedEnding?.let { segmentsOverlapHeavily(it.segment, recap.segment) } == true
                !conflictsWithOp && !conflictsWithEd
            }

        val finalList = listOfNotNull(selectedOpening, selectedEnding, selectedRecap)
            .sortedBy { it.segment.startMillis }

        Log.i(
            TAG,
            "AniSkip mal=$malId ep=$episodeNumber realLen=${String.format(Locale.US, "%.1f", actualLengthSec)}s -> " +
                finalList.joinToString { c ->
                    "${c.segment.type.apiKey}[${c.segment.startMillis / 1000}s-${c.segment.endMillis / 1000}s, srcLen=${String.format(Locale.US, "%.1f", c.reportedLengthSec)}s]"
                }.ifEmpty { "sin coincidencias exactas" },
        )

        return finalList.map { it.segment }
    }

    /**
     * Elige un único segmento por categoría (OP o ED) para evitar que coexistan `op` y `mixed-op`
     * de cortes distintos. Prioriza el tipo puro (`op`/`ed`) salvo que la versión `mixed` tenga una
     * duración sensiblemente más cercana a la del video actual.
     */
    private fun selectBestForCategory(
        pure: List<ParsedCandidate>,
        mixed: List<ParsedCandidate>,
    ): ParsedCandidate? {
        val bestPure = pure.minByOrNull { it.lengthDiffSec }
        val bestMixed = mixed.minByOrNull { it.lengthDiffSec }
        if (bestPure == null) return bestMixed
        if (bestMixed == null) return bestPure
        return if (bestPure.lengthDiffSec <= bestMixed.lengthDiffSec + PURE_TYPE_PREFERENCE_MARGIN_SEC) {
            bestPure
        } else {
            bestMixed
        }
    }

    companion object {
        private const val TAG = "YokanAniSkip"
        private const val BASE_URL = "https://api.aniskip.com/v2"
        private const val CACHE_TTL_MS = 6 * 60 * 60_000L
        private const val REQUEST_TIMEOUT_MS = 6_000L
        private const val MIN_EPISODE_DURATION_MS = 30_000L
        private const val MIN_SEGMENT_DURATION_MS = 10_000L
        private const val MAX_OP_ED_DURATION_MS = 210_000L
        private const val MAX_RECAP_DURATION_MS = 300_000L
        private const val MAX_EPISODE_LENGTH_DIFF_SECONDS = 10.0
        private const val PURE_TYPE_PREFERENCE_MARGIN_SEC = 4.0
        private const val UNKNOWN_LENGTH_DIFF_SEC = 50.0
    }
}
