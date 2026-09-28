package app.yokan.media

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.him188.ani.utils.logging.error
import me.him188.ani.utils.logging.logger

@Serializable
data class WatchProgress(
    val animeId: Int,
    val animeTitle: String,
    val animeRomaji: String,
    val animeEnglish: String?,
    val coverUrl: String?,
    val episode: Int,
    val totalEpisodes: Int,
    val positionMillis: Long,
    val durationMillis: Long,
    val lastWatchedTimestamp: Long = System.currentTimeMillis(),
) {
    val progressFraction: Float
        get() = if (durationMillis > 0) (positionMillis.toFloat() / durationMillis.toFloat()).coerceIn(0f, 1f) else 0f

    /** Returns true if episode is considered finished (>=92% watched) */
    val isFinished: Boolean
        get() = progressFraction >= 0.92f
}

class WatchHistoryManager private constructor(context: Context) {

    private val logger = logger("WatchHistoryManager")
    private val appContext = context.applicationContext
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val prefs = appContext.getSharedPreferences("yokan_watch_history", Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    private val _history = MutableStateFlow<List<WatchProgress>>(emptyList())
    val allHistory: StateFlow<List<WatchProgress>> = _history.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        val rawJson = prefs.getString(PREF_KEY, null) ?: return
        runCatching {
            val list = json.decodeFromString<List<WatchProgress>>(rawJson)
            _history.value = list.sortedByDescending { it.lastWatchedTimestamp }
        }.onFailure {
            logger.error("Failed to load watch history", it)
        }
    }

    private fun saveHistory(items: List<WatchProgress>) {
        _history.value = items
        scope.launch {
            runCatching {
                val encoded = json.encodeToString(items)
                prefs.edit().putString(PREF_KEY, encoded).apply()
            }.onFailure {
                logger.error("Failed to save watch history", it)
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
    ) {
        if (positionMillis < 10_000L) return

        val progress = WatchProgress(
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
