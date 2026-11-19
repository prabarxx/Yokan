package app.yokan.settings

import android.content.Context
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.him188.ani.app.data.models.preference.DarkMode
import me.him188.ani.app.data.models.preference.ThemeSettings
import me.him188.ani.app.videoplayer.videoenhancement.VideoEnhancementMode

data class SeedColorPreset(
    val name: String,
    val argb: Long,
) {
    val color: Color get() = Color(argb)
}

data class YokanSettings(
    val darkMode: DarkMode = DarkMode.DARK,
    val useBlackBackground: Boolean = true,
    val useDynamicTheme: Boolean = false,
    val enableFrostedGlassEffect: Boolean = true,
    val seedColorArgb: Long = DEFAULT_SEED_COLOR_ARGB,
    val videoEnhancementMode: VideoEnhancementMode = VideoEnhancementMode.OFF,
    val skipIntroSeconds: Int = DEFAULT_SKIP_INTRO_SECONDS,
    val autoPlayNextEpisode: Boolean = true,
    val torrentDownloadLimitBytes: Int = 0,
    val torrentUploadLimitBytes: Int = 0,
) {
    fun toThemeSettings(): ThemeSettings = ThemeSettings(
        darkMode = darkMode,
        useDynamicTheme = useDynamicTheme,
        useBlackBackground = useBlackBackground,
        enableFrostedGlassEffect = enableFrostedGlassEffect,
        seedColorValue = Color(seedColorArgb).value,
    )

    companion object {
        const val DEFAULT_SEED_COLOR_ARGB: Long = 0xFF6750A4L
        const val DEFAULT_SKIP_INTRO_SECONDS: Int = 85

        val SEED_COLOR_PRESETS: List<SeedColorPreset> = listOf(
            SeedColorPreset("Morado", 0xFF6750A4L),
            SeedColorPreset("Animeko", 0xFF7EBBEDL),
            SeedColorPreset("Índigo", 0xFF3F51B5L),
            SeedColorPreset("Esmeralda", 0xFF2E7D32L),
            SeedColorPreset("Sakura", 0xFFE91E63L),
            SeedColorPreset("Ámbar", 0xFFF57C00L),
            SeedColorPreset("Carmesí", 0xFFC62828L),
        )

        val SKIP_INTRO_OPTIONS: List<Int> = listOf(60, 80, 85, 90)

        val DOWNLOAD_LIMIT_OPTIONS_BYTES: List<Int> = listOf(
            0,
            2 * 1024 * 1024,
            5 * 1024 * 1024,
            10 * 1024 * 1024,
            20 * 1024 * 1024,
        )

        val UPLOAD_LIMIT_OPTIONS_BYTES: List<Int> = listOf(
            0,
            256 * 1024,
            512 * 1024,
            1024 * 1024,
            2 * 1024 * 1024,
        )
    }
}

/**
 * Preferencias globales de Yokan persistidas con primitivos de SharedPreferences
 * para ser completamente seguras bajo R8/ProGuard en builds de producción.
 */
class YokanSettingsManager private constructor(context: Context) {

    private val appContext = context.applicationContext
    private val prefs = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val ioScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _settings = MutableStateFlow(loadSettings())
    val settings: StateFlow<YokanSettings> = _settings.asStateFlow()

    private fun loadSettings(): YokanSettings {
        val darkMode = when (prefs.getString(KEY_DARK_MODE, "DARK")) {
            "AUTO" -> DarkMode.AUTO
            "LIGHT" -> DarkMode.LIGHT
            else -> DarkMode.DARK
        }

        val enhancementMode = when (prefs.getString(KEY_VIDEO_ENHANCEMENT, "OFF")) {
            "PERFORMANCE" -> VideoEnhancementMode.PERFORMANCE
            "QUALITY" -> VideoEnhancementMode.QUALITY
            else -> VideoEnhancementMode.OFF
        }

        val skipIntro = prefs.getInt(KEY_SKIP_INTRO_SECONDS, YokanSettings.DEFAULT_SKIP_INTRO_SECONDS)
            .takeIf { it in 10..300 }
            ?: YokanSettings.DEFAULT_SKIP_INTRO_SECONDS

        return YokanSettings(
            darkMode = darkMode,
            useBlackBackground = prefs.getBoolean(KEY_USE_BLACK_BACKGROUND, true),
            useDynamicTheme = prefs.getBoolean(KEY_USE_DYNAMIC_THEME, false),
            enableFrostedGlassEffect = prefs.getBoolean(KEY_ENABLE_FROSTED_GLASS, true),
            seedColorArgb = prefs.getLong(KEY_SEED_COLOR_ARGB, YokanSettings.DEFAULT_SEED_COLOR_ARGB),
            videoEnhancementMode = enhancementMode,
            skipIntroSeconds = skipIntro,
            autoPlayNextEpisode = prefs.getBoolean(KEY_AUTO_PLAY_NEXT, true),
            torrentDownloadLimitBytes = prefs.getInt(KEY_TORRENT_DOWNLOAD_LIMIT, 0).coerceAtLeast(0),
            torrentUploadLimitBytes = prefs.getInt(KEY_TORRENT_UPLOAD_LIMIT, 0).coerceAtLeast(0),
        )
    }

    private fun updateAndPersist(transform: (YokanSettings) -> YokanSettings) {
        var updated: YokanSettings? = null
        _settings.update { current ->
            val next = transform(current)
            updated = next
            next
        }
        val toSave = updated ?: return
        val darkModeToken = when (toSave.darkMode) {
            DarkMode.AUTO -> "AUTO"
            DarkMode.LIGHT -> "LIGHT"
            DarkMode.DARK -> "DARK"
        }
        val enhancementToken = when (toSave.videoEnhancementMode) {
            VideoEnhancementMode.OFF -> "OFF"
            VideoEnhancementMode.PERFORMANCE -> "PERFORMANCE"
            VideoEnhancementMode.QUALITY -> "QUALITY"
        }
        ioScope.launch {
            prefs.edit()
                .putString(KEY_DARK_MODE, darkModeToken)
                .putBoolean(KEY_USE_BLACK_BACKGROUND, toSave.useBlackBackground)
                .putBoolean(KEY_USE_DYNAMIC_THEME, toSave.useDynamicTheme)
                .putBoolean(KEY_ENABLE_FROSTED_GLASS, toSave.enableFrostedGlassEffect)
                .putLong(KEY_SEED_COLOR_ARGB, toSave.seedColorArgb)
                .putString(KEY_VIDEO_ENHANCEMENT, enhancementToken)
                .putInt(KEY_SKIP_INTRO_SECONDS, toSave.skipIntroSeconds)
                .putBoolean(KEY_AUTO_PLAY_NEXT, toSave.autoPlayNextEpisode)
                .putInt(KEY_TORRENT_DOWNLOAD_LIMIT, toSave.torrentDownloadLimitBytes)
                .putInt(KEY_TORRENT_UPLOAD_LIMIT, toSave.torrentUploadLimitBytes)
                .apply()
        }
    }

    fun setDarkMode(mode: DarkMode) = updateAndPersist { it.copy(darkMode = mode) }

    fun setUseBlackBackground(enabled: Boolean) = updateAndPersist { it.copy(useBlackBackground = enabled) }

    fun setUseDynamicTheme(enabled: Boolean) = updateAndPersist { it.copy(useDynamicTheme = enabled) }

    fun setEnableFrostedGlassEffect(enabled: Boolean) = updateAndPersist { it.copy(enableFrostedGlassEffect = enabled) }

    fun setSeedColorArgb(argb: Long) = updateAndPersist { it.copy(seedColorArgb = argb) }

    fun setVideoEnhancementMode(mode: VideoEnhancementMode) = updateAndPersist {
        it.copy(videoEnhancementMode = mode)
    }

    fun setSkipIntroSeconds(seconds: Int) = updateAndPersist {
        it.copy(skipIntroSeconds = seconds.coerceIn(10, 300))
    }

    fun setAutoPlayNextEpisode(enabled: Boolean) = updateAndPersist { it.copy(autoPlayNextEpisode = enabled) }

    fun setTorrentDownloadLimitBytes(bytesPerSec: Int) = updateAndPersist {
        it.copy(torrentDownloadLimitBytes = bytesPerSec.coerceAtLeast(0))
    }

    fun setTorrentUploadLimitBytes(bytesPerSec: Int) = updateAndPersist {
        it.copy(torrentUploadLimitBytes = bytesPerSec.coerceAtLeast(0))
    }

    fun resetToDefaults() = updateAndPersist { YokanSettings() }

    companion object {
        private const val PREFS_NAME = "yokan_settings"
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_USE_BLACK_BACKGROUND = "use_black_background"
        private const val KEY_USE_DYNAMIC_THEME = "use_dynamic_theme"
        private const val KEY_ENABLE_FROSTED_GLASS = "enable_frosted_glass"
        private const val KEY_SEED_COLOR_ARGB = "seed_color_argb"
        private const val KEY_VIDEO_ENHANCEMENT = "video_enhancement_mode"
        private const val KEY_SKIP_INTRO_SECONDS = "skip_intro_seconds"
        private const val KEY_AUTO_PLAY_NEXT = "auto_play_next_episode"
        private const val KEY_TORRENT_DOWNLOAD_LIMIT = "torrent_download_limit_bytes"
        private const val KEY_TORRENT_UPLOAD_LIMIT = "torrent_upload_limit_bytes"

        @Volatile
        private var instance: YokanSettingsManager? = null

        fun getInstance(context: Context): YokanSettingsManager {
            return instance ?: synchronized(this) {
                instance ?: YokanSettingsManager(context).also { instance = it }
            }
        }
    }
}
