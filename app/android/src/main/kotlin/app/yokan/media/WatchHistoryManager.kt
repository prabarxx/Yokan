package app.yokan.media

import android.content.Context
import android.util.Log
import app.yokan.anilist.model.AniListCoverImage
import app.yokan.anilist.model.AniListMedia
import app.yokan.anilist.model.AniListTitle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.abs

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

    fun toFallbackAniListMedia(): AniListMedia = AniListMedia(
        id = animeId,
        title = AniListTitle(
            romaji = animeRomaji.ifBlank { animeTitle },
            english = animeEnglish ?: animeTitle,
        ),
        coverImage = coverUrl?.takeIf { it.isNotBlank() }?.let {
            AniListCoverImage(extraLarge = it, large = it, medium = it)
        },
        episodes = totalEpisodes.takeIf { it > 0 },
        status = if (animeId < 0) "FINISHED" else null,
    )

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
    private val ioScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var saveHistoryJob: Job? = null
    @Volatile
    private var lastPersistedHistoryJson: String? = null

    private val _history = MutableStateFlow<List<WatchProgress>>(emptyList())
    val allHistory: StateFlow<List<WatchProgress>> = _history.asStateFlow()

    /** Episodios vistos (>= 92 %) por anime. Independiente de "Continuar viendo": quitar una tarjeta no los borra. */
    private val _watched = MutableStateFlow<Map<Int, Set<Int>>>(emptyMap())
    val watchedEpisodes: StateFlow<Map<Int, Set<Int>>> = _watched.asStateFlow()

    /** Preferencia de idioma de reproducción: false = Subtitulado (SUB), true = Español Latino (LAT). */
    private val _preferDub = MutableStateFlow(false)
    val preferDub: StateFlow<Boolean> = _preferDub.asStateFlow()

    init {
        loadHistory()
        loadWatched()
        _preferDub.value = prefs.getBoolean(PREF_PREFER_DUB_KEY, false)
    }

    fun setPreferDub(dub: Boolean) {
        if (_preferDub.value == dub) return
        _preferDub.value = dub
        ioScope.launch {
            prefs.edit().putBoolean(PREF_PREFER_DUB_KEY, dub).apply()
        }
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
            lastPersistedHistoryJson = raw
            Log.i(TAG, "Historial cargado desde disco: ${list.size} entradas")
        }.onFailure {
            Log.e(TAG, "No se pudo leer el historial (${raw.length} chars)", it)
        }
    }

    private fun saveHistory(items: List<WatchProgress>, debounceMillis: Long = 400L) {
        _history.value = items
        saveHistoryJob?.cancel()
        saveHistoryJob = ioScope.launch {
            if (debounceMillis > 0L) {
                delay(debounceMillis)
            }
            runCatching {
                val arr = JSONArray()
                items.forEach { arr.put(it.toJson()) }
                val serialized = arr.toString()
                if (serialized == lastPersistedHistoryJson) return@launch
                prefs.edit().putString(PREF_KEY, serialized).apply()
                lastPersistedHistoryJson = serialized
                Log.i(TAG, "Historial guardado en background: ${items.size} entradas")
            }.onFailure {
                Log.e(TAG, "No se pudo guardar el historial", it)
            }
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
        latestAiredEpisode: Int = 0,
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

        if (watched.isFinished) markEpisodeWatched(animeId, episode)

        // Episodio terminado (>= 92 %): "Continuar viendo" avanza al siguiente (N+1, desde 0:00)
        // en lugar de desaparecer. Solo se oculta si era el último episodio o si el N+1 aún no se emitió.
        val hasNext = (totalEpisodes == 0 || episode < totalEpisodes) &&
            (latestAiredEpisode == 0 || episode < latestAiredEpisode)
        val progress = if (watched.isFinished && hasNext) {
            watched.copy(episode = episode + 1, positionMillis = 0L, durationMillis = 0L)
        } else {
            watched
        }

        val current = _history.value.toMutableList()
        val existingIndex = current.indexOfFirst { it.animeId == animeId }
        if (existingIndex >= 0) {
            val existing = current[existingIndex]
            // Evitar reescrituras innecesarias si el episodio y la posición apenas cambiaron (< 1s)
            if (existing.episode == progress.episode &&
                abs(existing.positionMillis - progress.positionMillis) < 1_000L &&
                existing.durationMillis == progress.durationMillis
            ) {
                return
            }
            current[existingIndex] = progress
        } else {
            current.add(0, progress)
        }

        // Keep only the last 30 entries
        val trimmed = current.sortedByDescending { it.lastWatchedTimestamp }.take(30)
        saveHistory(trimmed, debounceMillis = 500L)
    }

    private fun loadWatched() {
        val raw = prefs.getString(PREF_WATCHED_KEY, null) ?: return
        runCatching {
            val obj = JSONObject(raw)
            val map = mutableMapOf<Int, Set<Int>>()
            obj.keys().forEach { key ->
                val arr = obj.getJSONArray(key)
                map[key.toInt()] = (0 until arr.length()).map { arr.getInt(it) }.toSet()
            }
            _watched.value = map
        }.onFailure {
            Log.e(TAG, "No se pudieron leer los episodios vistos", it)
        }
    }

    private fun markEpisodeWatched(animeId: Int, episode: Int) {
        val current = _watched.value[animeId].orEmpty()
        if (episode in current) return
        val updated = _watched.value + (animeId to (current + episode))
        _watched.value = updated
        ioScope.launch {
            runCatching {
                val obj = JSONObject()
                updated.forEach { (id, eps) -> obj.put(id.toString(), JSONArray(eps.toList())) }
                prefs.edit().putString(PREF_WATCHED_KEY, obj.toString()).apply()
            }.onFailure {
                Log.e(TAG, "No se pudieron guardar los episodios vistos", it)
            }
        }
    }

    /** Devuelve una entrada al historial tal cual estaba (para "Deshacer" al quitarla). */
    fun restoreProgress(progress: WatchProgress) {
        val current = _history.value.filter { it.animeId != progress.animeId } + progress
        saveHistory(current.sortedByDescending { it.lastWatchedTimestamp }.take(30), debounceMillis = 0L)
    }

    /** Returns the saved progress for an anime, or null if not found. */
    fun getProgress(animeId: Int): WatchProgress? {
        return _history.value.firstOrNull { it.animeId == animeId }
    }

    /** Remove all progress for an anime. */
    fun removeProgress(animeId: Int) {
        val updated = _history.value.filter { it.animeId != animeId }
        saveHistory(updated, debounceMillis = 0L)
    }

    companion object {
        private const val TAG = "YokanHistory"
        private const val PREF_KEY = "watch_history_json"
        private const val PREF_WATCHED_KEY = "watched_episodes_json"
        private const val PREF_PREFER_DUB_KEY = "prefer_dub"

        @Volatile
        private var instance: WatchHistoryManager? = null

        fun getInstance(context: Context): WatchHistoryManager {
            return instance ?: synchronized(this) {
                instance ?: WatchHistoryManager(context).also { instance = it }
            }
        }
    }
}
