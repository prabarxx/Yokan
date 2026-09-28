package app.yokan.media

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.him188.ani.utils.logging.error
import me.him188.ani.utils.logging.logger

@Serializable
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
}

class WatchHistoryManager private constructor(context: Context) {

    private val logger = logger("WatchHistoryManager")
    private val appContext = context.applicationContext
    private val prefs = appContext.getSharedPreferences("yokan_watch_history", Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    private val _history = MutableStateFlow<List<WatchProgress>>(emptyList())
    val allHistory: StateFlow<List<WatchProgress>> = _history.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        val rawJson = prefs.getString(PREF_KEY, null)
        if (rawJson == null) {
            logger.info("Sin historial previo en disco (PREF_KEY ausente)")
            return
        }
        runCatching {
            val list = json.decodeFromString<List<WatchProgress>>(rawJson)
            _history.value = list.sortedByDescending { it.lastWatchedTimestamp }
            logger.info("Historial cargado desde disco: ${list.size} entradas")
        }.onFailure {
            logger.error("Failed to load watch history", it)
        }
    }

    private fun saveHistory(items: List<WatchProgress>) {
        _history.value = items
        // Escritura SÍNCRONA con commit(): el JSON es diminuto (máx. 30 entradas)
        // y así el dato queda en disco antes de que el proceso pueda morir.
        // Con apply() + scope.launch() el guardado quedaba encolado y se perdía
        // al cerrar la app (back + swipe), que es por lo que "Continuar viendo"
        // aparecía en sesión pero desaparecía al reiniciar.
        runCatching {
            val encoded = json.encodeToString(items)
            val ok = prefs.edit().putString(PREF_KEY, encoded).commit()
            if (!ok) {
                logger.error("commit() devolvió false al guardar historial (${items.size} entradas)")
                return@runCatching
            }
            // Verificación: releer y comprobar que lo escrito coincide.
            val raw = prefs.getString(PREF_KEY, null)
            if (raw == null) {
                logger.error("Verificación falló: PREF_KEY ausente tras commit, reintentando...")
                prefs.edit().putString(PREF_KEY, encoded).commit()
            } else {
                val count = runCatching { json.decodeFromString<List<WatchProgress>>(raw).size }.getOrNull()
                logger.info("Historial guardado y verificado: ${items.size} entradas (leídas: $count)")
            }
        }.onFailure {
            logger.error("Failed to save watch history", it)
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
