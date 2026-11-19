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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import me.him188.ani.utils.coroutines.runSuspendCatching
import org.json.JSONObject
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

    companion object {
        fun fromApiKey(raw: String): SkipSegmentType? = when (raw.lowercase().trim()) {
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
) {
    val durationMillis: Long get() = (endMillis - startMillis).coerceAtLeast(0L)
    val isOpening: Boolean get() = type == SkipSegmentType.OPENING || type == SkipSegmentType.MIXED_OPENING
    val isEnding: Boolean get() = type == SkipSegmentType.ENDING || type == SkipSegmentType.MIXED_ENDING
    val key: String get() = "${type.apiKey}:$startMillis:$endMillis"

    fun toChapter(): Chapter = Chapter(
        name = type.label,
        durationMillis = durationMillis,
        offsetMillis = startMillis,
    )
}

/**
 * Cliente ligero para la API pública AniSkip v2 (tiempos crowdsourced de Opening, Ending y Recap).
 * Usa org.json para evitar cualquier incidencia con R8 en builds release.
 */
class AniSkipClient(
    private val httpClient: HttpClient,
) {
    private val cache = TtlCache<String, List<SkipSegment>>(
        ttlMillis = CACHE_TTL_MS,
        maxSize = 256,
    )

    suspend fun getSkipSegments(
        malId: Int,
        episodeNumber: Int,
    ): List<SkipSegment> = withContext(Dispatchers.IO) {
        if (malId <= 0 || episodeNumber <= 0) return@withContext emptyList()
        val cacheKey = "$malId:$episodeNumber"
        cache[cacheKey]?.let { return@withContext it }

        val segments = withTimeoutOrNull(REQUEST_TIMEOUT_MS) {
            runSuspendCatching {
                val response = httpClient.get("$BASE_URL/skip-times/$malId/$episodeNumber") {
                    header("User-Agent", "Yokan/2.7 (Android)")
                    parameter("types", "op")
                    parameter("types", "ed")
                    parameter("types", "mixed-op")
                    parameter("types", "mixed-ed")
                    parameter("types", "recap")
                    parameter("episodeLength", 0)
                }

                if (response.status == HttpStatusCode.NotFound) {
                    return@runSuspendCatching emptyList()
                }
                if (!response.status.isSuccess()) {
                    Log.w(TAG, "AniSkip HTTP ${response.status.value} para malId=$malId ep=$episodeNumber")
                    return@runSuspendCatching null
                }

                parseSkipTimes(response.bodyAsText())
            }.getOrNull()
        }

        if (segments != null) {
            cache[cacheKey] = segments
            segments
        } else {
            emptyList()
        }
    }

    private fun parseSkipTimes(rawJson: String): List<SkipSegment> {
        val root = JSONObject(rawJson)
        if (!root.optBoolean("found", false)) return emptyList()
        val results = root.optJSONArray("results") ?: return emptyList()

        val parsed = mutableListOf<SkipSegment>()
        for (i in 0 until results.length()) {
            val item = results.optJSONObject(i) ?: continue
            val type = SkipSegmentType.fromApiKey(item.optString("skipType", "")) ?: continue
            val interval = item.optJSONObject("interval") ?: continue
            val startSec = interval.optDouble("startTime", Double.NaN)
            val endSec = interval.optDouble("endTime", Double.NaN)
            if (startSec.isNaN() || endSec.isNaN()) continue
            val startMs = (startSec * 1000.0).toLong().coerceAtLeast(0L)
            val endMs = (endSec * 1000.0).toLong().coerceAtLeast(0L)
            if (endMs - startMs < MIN_SEGMENT_DURATION_MS) continue
            parsed.add(SkipSegment(type = type, startMillis = startMs, endMillis = endMs))
        }

        return parsed
            .distinctBy { it.type to (it.startMillis / 2000L) }
            .sortedBy { it.startMillis }
    }

    companion object {
        private const val TAG = "YokanAniSkip"
        private const val BASE_URL = "https://api.aniskip.com/v2"
        private const val CACHE_TTL_MS = 6 * 60 * 60_000L
        private const val REQUEST_TIMEOUT_MS = 6_000L
        private const val MIN_SEGMENT_DURATION_MS = 5_000L
    }
}
