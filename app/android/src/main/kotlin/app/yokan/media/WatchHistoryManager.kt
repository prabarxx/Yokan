package app.yokan.media

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

data class WatchProgress(
    val animeId: Int,
    val animeTitle: String,
    val animeRomaji: String = "",
    val animeEnglish: String? = null,
    val coverUrl: String? = null,
    val episode: Int = 1,
    val totalEpisodes: Int = 0,
    val positionMillis: Long = 0L,
    val durationMillis: Long = 0L,
    val lastWatchedTimestamp: Long = System.currentTimeMillis(),
) {
    val progressFraction: Float
        get() = if (durationMillis > 0) (positionMillis.toFloat() / durationMillis.toFloat()).coerceIn(0f, 1f) else 0f

    /** Returns true if episode is considered finished (>=92% watched) */
    val isFinished: Boolean
        get() = progressFraction >= 0.92f

    fun toJson(): JSONObject = JSONObject().apply {
        put("animeId", animeId)
        put("animeTitle", animeTitle)
        put("animeRomaji", animeRomaji)
        put("animeEnglish", animeEnglish ?: JSONObject.NULL)
        put("coverUrl", coverUrl ?: JSONObject.NULL)
        put("episode", episode)
        put("totalEpisodes", totalEpisodes)
        put("positionMillis", positionMillis)
        put("durationMillis", durationMillis)
        put("lastWatchedTimestamp", lastWatchedTimestamp)
    }

    companion object {
        fun fromJson(o: JSONObject): WatchProgress = WatchProgress(
            animeId = o.getInt("animeId"),
            animeTitle = o.optString("animeTitle", ""),
            animeRomaji = o.optString("animeRomaji", ""),
            animeEnglish = if (o.isNull("animeEnglish")) null else o.optString("animeEnglish"),
            coverUrl = if (o.isNull("coverUrl")) null else o.optString("coverUrl"),
            episode = o.optInt("episode", 1),
            totalEpisodes = o.optInt("totalEpisodes", 0),
            positionMillis = o.optLong("positionMillis", 0L),
            durationMillis = o.optLong("durationMillis", 0L),
            lastWatchedTimestamp = o.optLong("lastWatchedTimestamp", System.currentTimeMillis()),
        )
    }
}

/**
 * Historial de "Continuar viendo".
 *
 * Usa org.json (incluido en Android) en vez de kotlinx.serialization: en builds release con R8
 * la serialización puede fallar en silencio, y el logger de la app no tiene backend en Android,
 * así que el fallo pasaba desapercibido (el historial aparecía en sesión pero no se persistía).
 * Los logs van por android.util.Log con el tag "YokanHistory":
 *   adb logcat -s YokanHistory
 */
class WatchHistoryManager private constructor(context: Context) {

    private val appContext = context.applicationContext
    private val prefs = appContext.getSharedPreferences("yokan_watch_history", Context.MODE_PRIVATE)

    private val _history = MutableStateFlow<List<WatchProgress>>(emptyList())
    val allHistory: StateFlow<List<WatchProgress>> = _history.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        val raw = prefs.getString(PREF_KEY, null)
        if (raw == null) {
            Log.i(TAG, "Sin historial previo en disco")
            return
        }
        runCatching {
            val arr = JSONArray(raw)
            val list = (0 until arr.length()).map { WatchProgress.fromJson(arr.getJSONObject(it)) }
            _history.value = list.sortedByDescending { it.lastWatchedTimestamp }
            Log.i(TAG, "Historial cargado desde disco: ${list.size} entradas")
        }.onFailure {
            Log.e(TAG, "No se pudo leer el historial (${raw.length} chars)", it)
        }
    }

    private fun saveHistory(items: List<WatchProgress>) {
        _history.value = items
        runCatching {
            val arr = JSONArray()
            items.forEach { arr.put(it.toJson()) }
            val ok = prefs.edit().putString(PREF_KEY, arr.toString()).commit()
            if (ok) {
                Log.i(TAG, "Historial guardado: ${items.size} entradas")
            } else {
                Log.e(TAG, "commit() devolvió false al guardar ${items.size} entradas")
            }
        }.onFailure {
            Log.e(TAG, "No se pudo guardar el historial", it)
        }
    }

    /**
     * Save or update progress for an anime episode.
     * Skips if positionMillis < 10_000 (less than 10 seconds watched).
     */
    fun saveProgress(
        animeId: Int,
        animeTitle: String,
        animeRomaji: String,
        animeEnglish: String?,
        coverUrl: String?,
        episode: Int,
        totalEpisodes: Int,
        positionMillis: Long,
        durationMillis: Long,
    ) {
        if (positionMillis < 10_000L) return

        val watched = WatchProgress(
            animeId = animeId,
            animeTitle = animeTitle,
            animeRomaji = animeRomaji,
            animeEnglish = animeEnglish,
            coverUrl = coverUrl,
            episode = episode,
            totalEpisodes = totalEpisodes,
            positionMillis = positionMillis,
            durationMillis = durationMillis,
            lastWatchedTimestamp = System.currentTimeMillis(),
        )

        // Episodio terminado (>= 92 %): "Continuar viendo" avanza al siguiente (N+1, desde 0:00)
        // en lugar de desaparecer. Solo se oculta cuando era el último episodio.
        val progress = if (watched.isFinished && (totalEpisodes == 0 || episode < totalEpisodes)) {
            watched.copy(episode = episode + 1, positionMillis = 0L, durationMillis = 0L)
        } else {
            watched
        }

        val current = _history.value.toMutableList()
        val existingIndex = current.indexOfFirst { it.animeId == animeId }
        if (existingIndex >= 0) {
            current[existingIndex] = progress
        } else {
            current.add(0, progress)
        }

        // Keep only the last 30 entries
        val trimmed = current.sortedByDescending { it.lastWatchedTimestamp }.take(30)
        saveHistory(trimmed)
    }

    /** Returns the saved progress for an anime, or null if not found. */
    fun getProgress(animeId: Int): WatchProgress? {
        return _history.value.firstOrNull { it.animeId == animeId }
    }

    /** Remove all progress for an anime. */
    fun removeProgress(animeId: Int) {
        val updated = _history.value.filter { it.animeId != animeId }
        saveHistory(updated)
    }

    companion object {
        private const val TAG = "YokanHistory"
        private const val PREF_KEY = "watch_history_json"

        @Volatile
        private var instance: WatchHistoryManager? = null

        fun getInstance(context: Context): WatchHistoryManager {
            return instance ?: synchronized(this) {
                instance ?: WatchHistoryManager(context).also { instance = it }
            }
        }
    }
}
