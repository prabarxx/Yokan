package app.yokan.ui.screens

import android.content.pm.ActivityInfo
import android.os.Build
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import app.yokan.datasource.animeav1.WebStreamSource
import app.yokan.datasource.nyaa.NyaaTorrent
import app.yokan.media.TorrentManager
import app.yokan.media.TorrentMediaData
import app.yokan.media.WatchHistoryManager
import app.yokan.anilist.model.AniListMedia
import org.openani.mediamp.MediaStatus
import me.him188.ani.app.videoplayer.ui.progress.PlayerProgressSliderState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import me.him188.ani.app.data.models.preference.DarkMode
import me.him188.ani.app.domain.media.player.ChunkState
import me.him188.ani.app.domain.media.player.MediaCacheProgressInfo
import me.him188.ani.app.platform.features.StreamType
import me.him188.ani.app.platform.features.getComponentAccessorsImpl
import me.him188.ani.app.platform.findActivity
import me.him188.ani.app.torrent.api.files.FilePriority
import me.him188.ani.app.torrent.api.files.TorrentFileEntry
import me.him188.ani.app.torrent.api.pieces.PieceState
import me.him188.ani.app.torrent.api.pieces.forEach
import me.him188.ani.app.torrent.api.pieces.isEmpty
import me.him188.ani.app.torrent.api.pieces.sumOf
import me.him188.ani.app.ui.foundation.effects.DarkStatusBarAppearance
import me.him188.ani.app.ui.foundation.effects.ScreenOnEffect
import me.him188.ani.app.ui.foundation.icons.AniIcons
import me.him188.ani.app.ui.foundation.icons.Forward85
import me.him188.ani.app.ui.foundation.icons.Forward90
import me.him188.ani.app.ui.foundation.theme.AniTheme
import me.him188.ani.app.videoplayer.media.LibassExoPlayerMediampPlayer
import me.him188.ani.app.videoplayer.ui.ControllerVisibility
import me.him188.ani.app.videoplayer.ui.PlaybackSpeedControllerState
import me.him188.ani.app.videoplayer.ui.PlayerControllerState
import me.him188.ani.app.videoplayer.ui.PlayerStatsOverlay
import me.him188.ani.app.videoplayer.ui.VideoAspectRatioControllerState
import me.him188.ani.app.videoplayer.ui.VideoLoadingIndicator
import me.him188.ani.app.videoplayer.ui.VideoPlayer
import me.him188.ani.app.videoplayer.ui.VideoScaffold
import me.him188.ani.app.videoplayer.ui.gesture.GestureIndicatorState
import me.him188.ani.app.videoplayer.ui.gesture.GestureLock
import me.him188.ani.app.videoplayer.ui.gesture.LockableVideoGestureHost
import me.him188.ani.app.videoplayer.ui.gesture.NoOpLevelController
import me.him188.ani.app.videoplayer.ui.gesture.SwipeSeekerConfig
import me.him188.ani.app.videoplayer.ui.gesture.asLevelController
import me.him188.ani.app.videoplayer.ui.gesture.rememberGestureIndicatorState
import me.him188.ani.app.videoplayer.ui.gesture.rememberSwipeSeekerState
import me.him188.ani.app.videoplayer.ui.progress.AudioSwitcher
import me.him188.ani.app.videoplayer.ui.progress.MediaProgressIndicatorText
import me.him188.ani.app.videoplayer.ui.progress.MediaProgressSlider
import me.him188.ani.app.videoplayer.ui.progress.PlayerControllerBar
import me.him188.ani.app.videoplayer.ui.progress.PlayerControllerDefaults
import me.him188.ani.app.videoplayer.ui.progress.SubtitleSwitcher
import me.him188.ani.app.videoplayer.ui.progress.TouchSeekState
import me.him188.ani.app.videoplayer.ui.progress.rememberMediaProgressSliderState
import me.him188.ani.app.videoplayer.ui.rememberAlwaysOnRequester
import me.him188.ani.app.videoplayer.ui.rememberPlayerFullscreenState
import me.him188.ani.app.videoplayer.ui.rememberPlayerStatsState
import me.him188.ani.app.videoplayer.ui.rememberVideoControllerState
import me.him188.ani.app.videoplayer.ui.renderAspectRatioMode
import me.him188.ani.app.videoplayer.ui.top.PlayerTopBar
import me.him188.ani.utils.coroutines.runSuspendCatching
import me.him188.ani.utils.io.absolutePath
import me.him188.ani.utils.logging.error
import me.him188.ani.utils.logging.info
import me.him188.ani.utils.logging.logger
import me.him188.ani.utils.logging.warn
import org.openani.mediamp.features.AspectRatioMode
import org.openani.mediamp.features.PlaybackSpeed
import org.openani.mediamp.features.VideoAspectRatio
import org.openani.mediamp.features.audioTracks
import org.openani.mediamp.features.subtitleTracks
import org.openani.mediamp.togglePlayWhenReady
import kotlin.time.Duration.Companion.seconds

private val logger = logger("VideoPlayerScreen")

@Composable
private fun rememberPlayerTouchSeekState(
    controllerState: PlayerControllerState,
    indicatorState: GestureIndicatorState,
    swipeSeekerConfig: SwipeSeekerConfig = SwipeSeekerConfig.Default,
): TouchSeekState {
    val density = LocalDensity.current
    return remember(controllerState, indicatorState, swipeSeekerConfig, density) {
        val controllerRequester = Any()
        var indicatorTicket: Int? = null
        fun stopCancellationIndicator() {
            indicatorTicket?.let(indicatorState::stopSeekCancellation)
            indicatorTicket = null
        }
        TouchSeekState(
            swipeSeekerConfig = swipeSeekerConfig,
            density = density,
            onStateChanged = { state ->
                when (state) {
                    TouchSeekState.State.Idle -> {
                        controllerState.cancelRequestInlineProgressSlider(controllerRequester)
                        stopCancellationIndicator()
                    }
                    TouchSeekState.State.Seeking -> {
                        controllerState.setRequestInlineProgressSlider(controllerRequester)
                        stopCancellationIndicator()
                    }
                    TouchSeekState.State.Cancelling -> {
                        indicatorTicket = indicatorState.startSeekCancellation()
                    }
                }
            },
        )
    }
}

/** Segundos de cuenta regresiva antes de pasar solo al siguiente episodio. */
private const val AUTO_NEXT_SECONDS = 5

/**
 * Tiempo que se espera un stream web antes de darlo por muerto y probar el siguiente servidor.
 * Antes eran 15 s (con lo que el usuario se quedaba 15 s viendo "Cargando...").
 */
private const val WEB_WATCHDOG_MS = 10_000L

@Composable
fun VideoPlayerScreen(
    torrent: NyaaTorrent? = null,
    webStream: WebStreamSource? = null,
    episodeNumber: Int? = null,
    absoluteEpisodeNumber: Int? = null,
    initialPositionMillis: Long = 0L,
    animeForHistory: AniListMedia? = null,
    hasBothLanguages: Boolean = false,
    onToggleAudioLanguage: (suspend (targetDub: Boolean) -> WebStreamSource?)? = null,
    onChangeSource: (() -> Unit)? = null,
    onNextEpisode: (() -> Unit)? = null,
    onResolveNextWebSource: (suspend (failed: WebStreamSource, triedServers: Set<String>) -> WebStreamSource?)? = null,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val activity = remember(context) { context.findActivity() }

    val watchHistoryManager = remember { WatchHistoryManager.getInstance(context) }

    var errorMessage by remember { mutableStateOf<String?>(null) }
    var loadingStatus by remember { mutableStateOf<String?>("Cargando...") }
    var currentMediaData by remember { mutableStateOf<TorrentMediaData?>(null) }
    var cacheProgressInfo by remember { mutableStateOf<MediaCacheProgressInfo?>(null) }
    var cachePercentText by remember { mutableStateOf<String?>(null) }
    var exoDuration by remember { mutableLongStateOf(0L) }
    var exoPosition by remember { mutableLongStateOf(0L) }
    var exoBufferedPosition by remember { mutableLongStateOf(0L) }
    var hasSeekedToInitial by remember { mutableStateOf(initialPositionMillis <= 0L) }
    var resumeToastShown by remember { mutableStateOf(false) }
    var lastSavedPositionMs by remember { mutableLongStateOf(0L) }
    // Segundos restantes para el siguiente episodio; null = sin cuenta regresiva activa
    var nextEpisodeCountdown by remember { mutableStateOf<Int?>(null) }

    val coroutineExceptionHandler = remember {
        CoroutineExceptionHandler { _, throwable ->
            logger.error("Error inesperado en corrutina del reproductor: ${throwable.message}", throwable)
            errorMessage = throwable.localizedMessage ?: "Error desconocido en el reproductor"
            loadingStatus = null
        }
    }

    val playerCoroutineContext = remember(coroutineScope, coroutineExceptionHandler) {
        coroutineScope.coroutineContext + coroutineExceptionHandler
    }

    val player = remember {
        LibassExoPlayerMediampPlayer(context, playerCoroutineContext)
    }

    val exoPlayer = remember(player) { (player as? LibassExoPlayerMediampPlayer)?.exoPlayer }

    // --- Fallback automático entre servidores web ---
    var activeWebStream by remember { mutableStateOf(webStream) }
    val triedWebServers = remember { mutableSetOf<String>() }
    var webSourceReady by remember { mutableStateOf(false) }
    var isSwitchingWebSource by remember { mutableStateOf(false) }
    var resumeAfterFallbackMs by remember { mutableLongStateOf(-1L) }
    val currentResolveNext by rememberUpdatedState(onResolveNextWebSource)
    val currentToggleLanguage by rememberUpdatedState(onToggleAudioLanguage)

    /** Pasa al siguiente servidor. Devuelve true si inició el cambio (o ya hay uno en curso). */
    fun switchToNextWebSource(reason: String): Boolean {
        val failed = activeWebStream ?: return false
        val resolver = currentResolveNext ?: return false
        if (isSwitchingWebSource) return true
        isSwitchingWebSource = true
        triedWebServers.add(failed.serverName)
        val pos = exoPlayer?.currentPosition ?: 0L
        if (pos > 5_000L) resumeAfterFallbackMs = pos
        errorMessage = null
        loadingStatus = "Cargando..."
        coroutineScope.launch {
            val next = try {
                resolver(failed, triedWebServers.toSet())
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                null
            }
            isSwitchingWebSource = false
            if (next != null) {
                activeWebStream = next
            } else {
                loadingStatus = null
                errorMessage = "No hay más servidores disponibles para este episodio."
            }
        }
        return true
    }

    /** Alterna al vuelo entre Subtitulado (SUB) y Español Latino (LAT) conservando la posición de reproducción. */
    fun toggleWebAudioLanguage() {
        val current = activeWebStream ?: return
        val toggler = currentToggleLanguage ?: return
        if (isSwitchingWebSource) return
        val targetDub = !current.isDub
        isSwitchingWebSource = true
        triedWebServers.clear()
        val pos = exoPlayer?.currentPosition ?: 0L
        if (pos > 2_000L) resumeAfterFallbackMs = pos
        errorMessage = null
        loadingStatus = if (targetDub) "Cambiando a Español Latino..." else "Cambiando a Subtitulado..."
        coroutineScope.launch {
            val switched = try {
                toggler(targetDub)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                null
            }
            isSwitchingWebSource = false
            if (switched != null) {
                activeWebStream = switched
            } else {
                loadingStatus = null
            }
        }
    }

    // Posición/duración efectivas: en vía web (AnimeAV1) van por exoPlayer,
    // en vía torrent van por el reproductor mediamp. El slider ya usa este
    // fallback (PlayerProgressSliderState); el guardado del historial debe
    // usarlo también o en vía torrent nunca se guarda nada.
    // (Declaradas aquí arriba porque onDispose las usa y Kotlin solo ve
    //  funciones locales declaradas antes de su punto de uso.)
    fun effectivePositionMillis(): Long {
        val exoPos = exoPlayer?.currentPosition?.coerceAtLeast(0L) ?: 0L
        return if (exoPos > 0L) exoPos else player.currentPositionMillis.value.coerceAtLeast(0L)
    }
    fun effectiveDurationMillis(): Long {
        val exoDur = exoPlayer?.duration?.let {
            if (it > 0 && it != androidx.media3.common.C.TIME_UNSET) it else 0L
        } ?: 0L
        return if (exoDur > 0L) exoDur else (player.mediaProperties.value?.durationMillis ?: 0L)
    }

    val currentOnNextEpisode by rememberUpdatedState(onNextEpisode)

    /** Al llegar al final: deja el episodio N+1 en "Continuar viendo" y arranca la cuenta regresiva. */
    fun handleEpisodeEnded() {
        val dur = effectiveDurationMillis()
        if (animeForHistory != null && episodeNumber != null && dur > 0L) {
            // posición == duración => el manager lo registra como episodio siguiente desde 0:00
            watchHistoryManager.saveProgress(
                animeId = animeForHistory.id,
                animeTitle = animeForHistory.title.displayTitle,
                animeRomaji = animeForHistory.title.romaji,
                animeEnglish = animeForHistory.title.english,
                coverUrl = animeForHistory.coverImage?.bestQualityUrl,
                episode = episodeNumber,
                totalEpisodes = animeForHistory.effectiveEpisodesCount,
                latestAiredEpisode = animeForHistory.latestAiredEpisode,
                positionMillis = dur,
                durationMillis = dur,
            )
        }
        if (currentOnNextEpisode != null && errorMessage == null) {
            nextEpisodeCountdown = AUTO_NEXT_SECONDS
        }
    }

    var isFullscreen by remember { mutableStateOf(true) }
    var isLocked by remember { mutableStateOf(false) }
    var showPlayerStats by remember { mutableStateOf(false) }

    val fullscreenState = rememberPlayerFullscreenState(
        isFullscreen = { isFullscreen },
        onRequest = { requestedFullscreen ->
            isFullscreen = requestedFullscreen
            activity?.requestedOrientation = if (requestedFullscreen) {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_USER
            }
            val window = activity?.window
            if (window != null) {
                val insetsController = WindowCompat.getInsetsController(window, window.decorView)
                if (requestedFullscreen) {
                    insetsController.systemBarsBehavior =
                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    insetsController.hide(WindowInsetsCompat.Type.systemBars())
                } else {
                    insetsController.show(WindowInsetsCompat.Type.systemBars())
                    insetsController.systemBarsBehavior =
                        WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                }
            }
        }
    )

    // Force landscape on start, hide system bars in immersive mode, restore on exit
    DisposableEffect(activity) {
        val window = activity?.window
        val insetsController = window?.let { WindowCompat.getInsetsController(it, it.decorView) }
        val originalSystemBarsBehavior = insetsController?.systemBarsBehavior

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

        if (window != null) {
            insetsController?.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            insetsController?.hide(WindowInsetsCompat.Type.systemBars())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val params = window.attributes
                params.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                window.attributes = params
            }
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                @Suppress("DEPRECATION")
                window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
            if (window != null) {
                insetsController?.show(WindowInsetsCompat.Type.systemBars())
                insetsController?.systemBarsBehavior =
                    originalSystemBarsBehavior ?: WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    @Suppress("DEPRECATION")
                    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                }
            }
            // Save final position before closing (posición efectiva: exo o mediamp)
            if (animeForHistory != null && episodeNumber != null) {
                val finalPos = effectivePositionMillis()
                val finalDur = effectiveDurationMillis()
                if (finalPos > 10_000L && finalDur > 0L) {
                    watchHistoryManager.saveProgress(
                        animeId = animeForHistory.id,
                        animeTitle = animeForHistory.title.displayTitle,
                        animeRomaji = animeForHistory.title.romaji,
                        animeEnglish = animeForHistory.title.english,
                        coverUrl = animeForHistory.coverImage?.bestQualityUrl,
                        episode = episodeNumber,
                        totalEpisodes = animeForHistory.effectiveEpisodesCount,
                        latestAiredEpisode = animeForHistory.latestAiredEpisode,
                        positionMillis = finalPos,
                        durationMillis = finalDur,
                    )
                }
            }
            runCatching { player.close() }
            runCatching { currentMediaData?.close() }
        }
    }

    BackHandler {
        if (isFullscreen) {
            fullscreenState.request(false)
        }
        onBack()
    }

    val playWhenReady by remember(player) { player.state.map { it.playWhenReady } }
        .collectAsStateWithLifecycle(false)

    val isBuffering by remember(player) { player.state.map { it.isBuffering } }
        .collectAsStateWithLifecycle(false)

    var exoPlayWhenReady by remember { mutableStateOf(true) }
    var exoIsBuffering by remember { mutableStateOf(false) }

    val isEffectivelyPlaying by remember {
        derivedStateOf {
            if (currentMediaData == null) {
                exoPlayWhenReady && (exoPlayer?.playbackState != Player.STATE_ENDED)
            } else {
                playWhenReady
            }
        }
    }

    val isEffectivelyBuffering by remember {
        derivedStateOf {
            if (currentMediaData == null) {
                exoIsBuffering
            } else {
                isBuffering
            }
        }
    }

    if (isEffectivelyPlaying) {
        ScreenOnEffect()
    }

    DarkStatusBarAppearance()

    DisposableEffect(exoPlayer) {
        if (exoPlayer == null) return@DisposableEffect onDispose {}
        val listener = object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                logger.error("ExoPlayer error: ${error.message} (code=${error.errorCode})", error)
                if (activeWebStream != null && switchToNextWebSource("error ${error.errorCode}")) return
                errorMessage = "Error de reproducción: ${error.localizedMessage ?: error.message}"
                loadingStatus = null
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                logger.info("ExoPlayer playbackState changed: $playbackState (READY=3, BUFFERING=2, ENDED=4, IDLE=1)")
                exoIsBuffering = playbackState == Player.STATE_BUFFERING
                if (playbackState == Player.STATE_ENDED) {
                    handleEpisodeEnded()
                } else {
                    // Si el usuario retrocede o reinicia, se cancela la cuenta regresiva
                    nextEpisodeCountdown = null
                }
                if (playbackState == Player.STATE_READY) {
                    loadingStatus = null
                    errorMessage = null
                    val dur = exoPlayer.duration
                    if (dur > 0 && dur != androidx.media3.common.C.TIME_UNSET) {
                        exoDuration = dur
                    }
                    // Nota: antes aquí se liberaba el tope de 720p para subir a 1080p, lo que
                    // obligaba a ExoPlayer a re-seleccionar la pista y provocaba un SEGUNDO
                    // buffering justo después de empezar. Ahora se reproduce la calidad máxima
                    // desde el principio.
                    webSourceReady = true
                    // Tras un fallback, retomar donde iba el video anterior
                    if (resumeAfterFallbackMs > 0L) {
                        exoPlayer.seekTo(resumeAfterFallbackMs)
                        resumeAfterFallbackMs = -1L
                        hasSeekedToInitial = true
                    }
                    // Seek to saved position (continue watching)
                    if (!hasSeekedToInitial && initialPositionMillis > 0L) {
                        hasSeekedToInitial = true
                        exoPlayer.seekTo(initialPositionMillis)
                    }
                }
            }

            override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
                exoPlayWhenReady = playWhenReady
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (currentMediaData == null) {
                    exoPlayWhenReady = isPlaying || (exoPlayer?.playWhenReady == true)
                }
            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            exoPlayer.removeListener(listener)
        }
    }

    // Actualización continua de posición, duración, búfer y caché
    LaunchedEffect(player) {
        val exo = (player as? LibassExoPlayerMediampPlayer)?.exoPlayer
        while (isActive) {
            if (exo != null) {
                val dur = exo.duration
                val validDur = if (dur > 0 && dur != androidx.media3.common.C.TIME_UNSET) dur else 0L
                if (validDur > 0L) {
                    exoDuration = validDur
                }
                exoPosition = exo.currentPosition.coerceAtLeast(0L)
                val buf = exo.bufferedPosition.coerceAtLeast(0L)
                exoBufferedPosition = buf

                if (currentMediaData == null && validDur > 0L) {
                    val pct = ((buf.toFloat() / validDur.toFloat()) * 100f).toInt().coerceIn(0, 100)
                    cachePercentText = "Caché: $pct%"
                    val ratio = (buf.toFloat() / validDur.toFloat()).coerceIn(0f, 1f)
                    if (ratio > 0f) {
                        cacheProgressInfo = MediaCacheProgressInfo(
                            chunkWeights = listOf(ratio, 1f - ratio),
                            chunkStates = listOf(ChunkState.DONE, ChunkState.NONE),
                        )
                    }
                }

                // Save progress every 5s for "continue watching"
                // (posición efectiva: vía web sale de exo, vía torrent del mediamp player)
                val currentPos = effectivePositionMillis()
                val effectiveDur = effectiveDurationMillis()
                if (animeForHistory != null && episodeNumber != null && effectiveDur > 0L &&
                    currentPos > 10_000L && (currentPos - lastSavedPositionMs > 5_000L)) {
                    lastSavedPositionMs = currentPos
                    watchHistoryManager.saveProgress(
                        animeId = animeForHistory.id,
                        animeTitle = animeForHistory.title.displayTitle,
                        animeRomaji = animeForHistory.title.romaji,
                        animeEnglish = animeForHistory.title.english,
                        coverUrl = animeForHistory.coverImage?.bestQualityUrl,
                        episode = episodeNumber,
                        totalEpisodes = animeForHistory.effectiveEpisodesCount,
                        latestAiredEpisode = animeForHistory.latestAiredEpisode,
                        positionMillis = currentPos,
                        durationMillis = effectiveDur,
                    )
                }
            }
            delay(250)
        }
    }

    LaunchedEffect(player) {
        player.state.collect { state ->
            val status = state.mediaStatus
            if (status is MediaStatus.Error) {
                logger.error("Player MediaStatus.Error: ${status.error.message}", status.error)
                errorMessage = "Error en el reproductor: ${status.error.localizedMessage ?: "Error de decodificación"}"
                loadingStatus = null
            }
        }
    }

    // Cuenta regresiva de "Siguiente episodio" (se cancela poniendo nextEpisodeCountdown = null)
    LaunchedEffect(nextEpisodeCountdown != null) {
        while (nextEpisodeCountdown != null) {
            delay(1_000)
            val left = (nextEpisodeCountdown ?: break) - 1
            if (left <= 0) {
                nextEpisodeCountdown = null
                currentOnNextEpisode?.invoke()
            } else {
                nextEpisodeCountdown = left
            }
        }
    }

    LaunchedEffect(activeWebStream?.streamUrl) {
        val stream = activeWebStream ?: return@LaunchedEffect
        currentMediaData?.let { prev ->
            currentMediaData = null
            runCatching { prev.close() }
        }
        webSourceReady = false
        loadingStatus = "Cargando..."
        errorMessage = null
        try {
            logger.info("Iniciando reproducción de stream web: ${stream.streamUrl}")
            val uri = Uri.parse(stream.streamUrl)

            val headers = mutableMapOf<String, String>()
            headers.putAll(stream.headers)
            if (!headers.containsKey("User-Agent")) {
                headers["User-Agent"] = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
            }
            if (!headers.containsKey("Referer")) {
                headers["Referer"] = "https://animeav1.uns.bio/"
                if (!headers.containsKey("Origin")) {
                    headers["Origin"] = "https://animeav1.uns.bio"
                }
            }
            headers["Accept"] = "*/*"

            // OkHttp compartido: HTTP/2, reutilización de conexiones y pool (mucho mejor para HLS)
            val dataSourceFactory = androidx.media3.datasource.okhttp.OkHttpDataSource.Factory(
                app.yokan.media.PlayerHttp.client
            )
                .setUserAgent(headers["User-Agent"]!!)
                .setDefaultRequestProperties(headers)

            // Algunos CDN exigen un token de acceso en todas las peticiones de la secuencia
            // (manifest, playlists y segmentos). Como los segmentos se referencian con URLs
            // relativas, el reproductor los pediría sin el query y el CDN respondería 403.
            val effectiveDataSourceFactory: androidx.media3.datasource.DataSource.Factory =
                if (stream.queryParams.isNotEmpty()) {
                    val streamHost = uri.host
                    if (streamHost != null) {
                        app.yokan.media.QueryParamDataSourceFactory(
                            delegate = dataSourceFactory,
                            params = stream.queryParams,
                            hosts = setOf(streamHost),
                        )
                    } else {
                        dataSourceFactory
                    }
                } else {
                    dataSourceFactory
                }

            val isHlsStream = stream.isHls ||
                stream.streamUrl.contains(".m3u8", ignoreCase = true) ||
                stream.streamUrl.contains("/pl/", ignoreCase = true) ||
                stream.streamUrl.contains("master", ignoreCase = true)

            val mediaItem = MediaItem.Builder()
                .setUri(uri)
                .setMimeType(if (isHlsStream) MimeTypes.APPLICATION_M3U8 else MimeTypes.VIDEO_MP4)
                .build()

            val mediaSource = if (isHlsStream) {
                HlsMediaSource.Factory(effectiveDataSourceFactory)
                    .setAllowChunklessPreparation(true)
                    .createMediaSource(mediaItem)
            } else {
                ProgressiveMediaSource.Factory(effectiveDataSourceFactory)
                    .createMediaSource(mediaItem)
            }

            // Sin stop()/clearMediaItems(): setMediaSource ya reemplaza el item y stop() reinicia
            // los renderers (re-inicializa el decoder) en cada reproducción.
            exoPlayer?.setMediaSource(mediaSource)
            exoPlayer?.prepare()
            exoPlayer?.playWhenReady = true
            exoPlayWhenReady = true
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            logger.error("Error al reproducir stream web: ${e.message}", e)
            errorMessage = "Error al reproducir stream: ${e.localizedMessage ?: e::class.simpleName}"
            loadingStatus = null
        }
    }

    // Watchdog: si el servidor no llega a READY y no da señales de vida, pasar al siguiente.
    // No dispara si el reproductor ya está descargando (hay búfer): un CDN lento no es un fallo.
    LaunchedEffect(activeWebStream?.streamUrl) {
        if (activeWebStream == null) return@LaunchedEffect
        delay(WEB_WATCHDOG_MS)
        if (!webSourceReady && errorMessage == null && !isSwitchingWebSource && exoBufferedPosition <= 0L) {
            switchToNextWebSource("sin respuesta")
        }
    }

    LaunchedEffect(torrent?.magnetUrl) {
        val currentTorrent = torrent ?: return@LaunchedEffect
        activeWebStream = null
        currentMediaData?.let { prev ->
            currentMediaData = null
            runCatching { prev.close() }
        }
        loadingStatus = "Conectando con la red BitTorrent..."
        errorMessage = null
        try {
            logger.info("Iniciando descarga torrent para: ${currentTorrent.title}")
            val torrentManager = TorrentManager.getInstance(context)
            val downloader = torrentManager.downloader

            loadingStatus = "Obteniendo metadatos del torrent..."
            val directTorrentUrl = currentTorrent.torrentUrl.takeIf { it.startsWith("http", ignoreCase = true) }
            val encodedInfo = if (directTorrentUrl != null) {
                runSuspendCatching {
                    downloader.fetchTorrent(directTorrentUrl)
                }.getOrElse { error ->
                    logger.warn("Falló descarga directa de .torrent HTTP (${error.message}), recurriendo a magnet...")
                    downloader.fetchTorrent(currentTorrent.magnetUrl)
                }
            } else {
                downloader.fetchTorrent(currentTorrent.magnetUrl)
            }

            loadingStatus = "Conectando con peers..."
            val session = downloader.startDownload(encodedInfo)

            loadingStatus = "Obteniendo lista de archivos..."
            val files = withTimeoutOrNull(60.seconds) {
                session.getFiles()
            }

            if (files.isNullOrEmpty()) {
                errorMessage = "Tiempo de espera agotado buscando fuentes para este torrent."
                loadingStatus = null
                return@LaunchedEffect
            }

            val videoExtensions = listOf(".mkv", ".mp4", ".avi", ".webm", ".mov", ".flv", ".wmv")
            val videoFiles = files.filter { file ->
                videoExtensions.any { ext -> file.fileName.endsWith(ext, ignoreCase = true) }
            }
            val targetFile = findMatchingEpisodeFile(videoFiles, episodeNumber, absoluteEpisodeNumber)
                ?: videoFiles.maxByOrNull { it.length }
                ?: files.maxByOrNull { it.length }

            if (targetFile == null || targetFile.length <= 0) {
                errorMessage = "No se encontró ningún archivo de video en este torrent."
                loadingStatus = null
                return@LaunchedEffect
            }

            loadingStatus = "Preparando streaming: ${targetFile.fileName}..."
            session.prioritizeSingleFile(targetFile)
            val handle = targetFile.createHandle().apply {
                resume(FilePriority.HIGH)
            }

            val mediaData = TorrentMediaData(
                handle = handle,
                onClose = {
                    coroutineScope.launch(NonCancellable) {
                        runCatching { handle.close() }
                        runCatching { session.closeIfNotInUse() }
                    }
                }
            )
            currentMediaData = mediaData

            runCatching {
                val exactSaveDir = downloader.getSaveDirForTorrent(encodedInfo).absolutePath
                app.yokan.media.CacheStorageManager.getInstance(context).recordCachedTorrent(
                    title = targetFile.fileName.ifBlank { currentTorrent.title },
                    magnetUrl = currentTorrent.magnetUrl,
                    torrentUrl = currentTorrent.torrentUrl,
                    fileSizeBytes = targetFile.length,
                    infoHash = currentTorrent.infoHash,
                    saveDirPath = exactSaveDir,
                )
            }

            loadingStatus = "Iniciando búfer de video..."
            player.setMediaData(
                mediaData,
                playWhenReady = true,
                startPositionMillis = initialPositionMillis.coerceAtLeast(0L),
            )
            runCatching { player.play() }
            loadingStatus = null
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            logger.error("Error al inicializar el torrent: ${e.message}", e)
            errorMessage = "Error al reproducir torrent: ${e.localizedMessage ?: e::class.simpleName}"
            loadingStatus = null
        }
    }

    // Monitorización de fragmentos / pieces para visualización en tiempo real en la barra de reproducción
    LaunchedEffect(currentMediaData) {
        val mediaData = currentMediaData ?: return@LaunchedEffect
        val pieces = mediaData.handle.entry.pieces
        if (pieces.isEmpty()) return@LaunchedEffect

        val totalSize = pieces.totalSize.takeIf { it > 0 } ?: pieces.sumOf { it.size }
        if (totalSize <= 0) return@LaunchedEffect

        val weights = mutableListOf<Float>()
        pieces.forEach { piece ->
            weights.add(piece.size.toFloat() / totalSize.toFloat())
        }

        while (isActive) {
            val states = mutableListOf<ChunkState>()
            pieces.forEach { piece ->
                states.add(
                    when (piece.state) {
                        PieceState.READY -> ChunkState.NONE
                        PieceState.DOWNLOADING -> ChunkState.DOWNLOADING
                        PieceState.FINISHED -> ChunkState.DONE
                        PieceState.NOT_AVAILABLE -> ChunkState.NOT_AVAILABLE
                    }
                )
            }

            cacheProgressInfo = MediaCacheProgressInfo(
                chunkWeights = weights,
                chunkStates = states,
            )
            val doneCount = states.count { it == ChunkState.DONE }
            cachePercentText = "Caché: ${(doneCount * 100) / states.size.coerceAtLeast(1)}%"

            if (states.all { it == ChunkState.DONE }) {
                break
            }
            delay(500)
        }
    }

    val controllerState = rememberVideoControllerState()

    // Ensure system bars (notification/battery bar) stay hidden when player controls auto-hide or during playback
    LaunchedEffect(controllerState.visibility, isFullscreen) {
        if (isFullscreen && controllerState.visibility == ControllerVisibility.Invisible) {
            val window = activity?.window
            if (window != null) {
                val insetsController = WindowCompat.getInsetsController(window, window.decorView)
                insetsController.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                insetsController.hide(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    LaunchedEffect(isEffectivelyPlaying, isFullscreen) {
        if (isFullscreen && isEffectivelyPlaying) {
            val window = activity?.window
            if (window != null) {
                val insetsController = WindowCompat.getInsetsController(window, window.decorView)
                insetsController.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                insetsController.hide(WindowInsetsCompat.Type.systemBars())
            }
        }
    }
    val platformComponents = remember(context) { getComponentAccessorsImpl(context) }
    val audioController = remember(platformComponents) {
        platformComponents.audioManager?.asLevelController(StreamType.MUSIC) ?: NoOpLevelController
    }
    val brightnessController = remember(platformComponents) {
        platformComponents.brightnessManager?.asLevelController() ?: NoOpLevelController
    }

    val progressSliderState = remember(player) {
        PlayerProgressSliderState(
            currentPositionMillis = {
                if (exoPosition > 0L) exoPosition else player.currentPositionMillis.value
            },
            totalDurationMillis = {
                if (exoDuration > 0L) exoDuration else (player.mediaProperties.value?.durationMillis ?: 0L)
            },
            chapters = { emptyList() },
            onPreview = {},
            onPreviewFinished = { targetMs ->
                exoPlayer?.seekTo(targetMs)
                player.seekTo(targetMs)
            },
        )
    }

    val indicatorState = rememberGestureIndicatorState()
    val swipeSeekerConfig = SwipeSeekerConfig.Default
    val touchSeekState = rememberPlayerTouchSeekState(
        controllerState = controllerState,
        indicatorState = indicatorState,
        swipeSeekerConfig = swipeSeekerConfig,
    )

    val playbackSpeed = remember(player) { player.features[PlaybackSpeed] }
    val playbackSpeedControllerState = remember(playbackSpeed, coroutineScope) {
        playbackSpeed?.let {
            PlaybackSpeedControllerState(it, scope = coroutineScope)
        }
    }

    val videoAspectRatio = remember(player) { player.features[VideoAspectRatio] }
    val videoAspectRatioControllerState = remember(videoAspectRatio, coroutineScope) {
        videoAspectRatio?.let {
            VideoAspectRatioControllerState(it, scope = coroutineScope)
        }
    }

    val playerStats by rememberPlayerStatsState(player)

    val togglePlayPause: () -> Unit = {
        if (currentMediaData == null && exoPlayer != null) {
            val exo = exoPlayer
            if (exo.playbackState == Player.STATE_ENDED) {
                exo.seekTo(0)
                exo.playWhenReady = true
                exoPlayWhenReady = true
                coroutineScope.launch {
                    indicatorState.showResumedLong()
                }
            } else {
                val nextState = !exo.playWhenReady
                exo.playWhenReady = nextState
                exoPlayWhenReady = nextState
                coroutineScope.launch {
                    if (nextState) {
                        indicatorState.showResumedLong()
                    } else {
                        indicatorState.showPausedLong()
                    }
                }
            }
        } else {
            if (player.state.value.playWhenReady) {
                coroutineScope.launch {
                    indicatorState.showPausedLong()
                }
            } else {
                coroutineScope.launch {
                    indicatorState.showResumedLong()
                }
            }
            player.togglePlayWhenReady()
        }
    }

    var showSettingsSheet by remember { mutableStateOf(false) }

    AniTheme(darkModeOverride = DarkMode.DARK) {
        Box(modifier = Modifier.fillMaxSize()) {
            VideoScaffold(
                expanded = isFullscreen,
                modifier = Modifier.fillMaxSize(),
                maintainAspectRatio = false,
                controllerState = controllerState,
                gestureLocked = isLocked,
                topBar = {
                    PlayerTopBar(
                        title = {},
                        actions = {
                            if (activeWebStream != null && hasBothLanguages && onToggleAudioLanguage != null) {
                                val isCurrentlyDub = activeWebStream?.isDub == true
                                Surface(
                                    onClick = { toggleWebAudioLanguage() },
                                    color = Color.White.copy(alpha = 0.16f),
                                    shape = RoundedCornerShape(50),
                                    modifier = Modifier.padding(end = 4.dp),
                                ) {
                                    Text(
                                        text = if (isCurrentlyDub) "LAT ⇄ SUB" else "SUB ⇄ LAT",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    )
                                }
                            }
                            if (onChangeSource != null) {
                                IconButton(onClick = onChangeSource) {
                                    Icon(
                                        Icons.Rounded.SwapHoriz,
                                        contentDescription = "Cambiar fuente",
                                        tint = Color.White,
                                    )
                                }
                            }
                            IconButton(onClick = { showSettingsSheet = true }) {
                                Icon(
                                    Icons.Rounded.Settings,
                                    contentDescription = "Ajustes del reproductor",
                                    tint = Color.White,
                                )
                            }
                        }
                    )
                },
                centerOverlay = {},
                video = {
                    VideoPlayer(
                        player = player,
                        modifier = Modifier.fillMaxSize(),
                    )
                },
                gestureHost = {
                    val videoPropertiesState by player.mediaProperties.collectAsState(null)
                    val effectiveDuration = if (exoDuration > 0L) exoDuration else (videoPropertiesState?.durationMillis ?: 0L)
                    val swipeSeekerState = rememberSwipeSeekerState(
                        screenWidthPx = constraints.maxWidth,
                        swipeSeekerConfig = swipeSeekerConfig,
                    ) { offsetSeconds ->
                        val current = if (exoPosition > 0L) exoPosition else player.currentPositionMillis.value
                        val target = (current + offsetSeconds * 1000L).coerceIn(0L, if (effectiveDuration > 0L) effectiveDuration else Long.MAX_VALUE)
                        exoPlayer?.seekTo(target)
                        player.skip(offsetSeconds * 1000L)
                    }

                    val enableSwipeToSeek by remember {
                        derivedStateOf {
                            (videoPropertiesState?.let { it.durationMillis != 0L } == true) || exoDuration > 0L
                        }
                    }

                    LockableVideoGestureHost(
                        controllerState = controllerState,
                        seekerState = swipeSeekerState,
                        progressSliderState = progressSliderState,
                        playerState = player,
                        locked = isLocked,
                        enableSwipeToSeek = enableSwipeToSeek,
                        audioController = audioController,
                        brightnessController = brightnessController,
                        playbackSpeedControllerState = playbackSpeedControllerState,
                        fullscreenState = fullscreenState,
                        modifier = Modifier.fillMaxSize(),
                        onTogglePauseResume = togglePlayPause,
                        onToggleDanmaku = {},
                        onTogglePlayerStats = {
                            showPlayerStats = !showPlayerStats
                        },
                        gestureIndicatorState = indicatorState,
                        fastForwardSpeed = 3f,
                    )
                },
                gestureLock = {
                    if (isFullscreen) {
                        GestureLock(
                            isLocked = isLocked,
                            onClick = { isLocked = !isLocked },
                        )
                    }
                },
                playerStatsOverlay = {
                    if (showPlayerStats) {
                        PlayerStatsOverlay(playerStats)
                    }
                },
                floatingMessage = {
                    val status = loadingStatus
                    if (isEffectivelyBuffering || status != null) {
                        VideoLoadingIndicator(
                            showProgress = true,
                            text = {
                                Text(
                                    text = status ?: "Cargando búfer de video...",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                )
                            },
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                },
                bottomBar = {
                    val sliderOnly = controllerState.visibility == ControllerVisibility.InlineSliderOnly
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(remember { MutableInteractionSource() }, null, onClick = {})
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                    ) {
                        if (!sliderOnly) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Surface(
                                    onClick = {
                                        val current = if (exoPosition > 0L) exoPosition else player.currentPositionMillis.value
                                        val target = (current + 85_000L).coerceIn(
                                            0L,
                                            if (exoDuration > 0L) exoDuration else Long.MAX_VALUE
                                        )
                                        exoPlayer?.seekTo(target)
                                        player.skip(85_000L)
                                    },
                                    color = Color.White.copy(alpha = 0.16f),
                                    shape = RoundedCornerShape(50),
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    ) {
                                        Icon(
                                            AniIcons.Forward85,
                                            contentDescription = "Saltar intro (+85s)",
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp),
                                        )
                                        Text(
                                            text = "Saltar intro",
                                            color = Color.White,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                        }

                        MediaProgressSlider(
                            state = progressSliderState,
                            cacheProgressInfoFlow = { cacheProgressInfo },
                            showPreviewTimeTextOnThumb = isFullscreen,
                            framePreview = null,
                            showFramePreviewInPopup = isFullscreen,
                            touchSeekState = touchSeekState,
                        )

                        if (!sliderOnly) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                ) {
                                    PlayerControllerDefaults.PlaybackIcon(
                                        isPlaying = { isEffectivelyPlaying },
                                        onClick = togglePlayPause,
                                    )
                                    if (onNextEpisode != null) {
                                        IconButton(onClick = onNextEpisode) {
                                            Icon(
                                                Icons.Rounded.SkipNext,
                                                contentDescription = "Siguiente episodio",
                                                tint = Color.White,
                                                modifier = Modifier.size(30.dp),
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(4.dp))
                                    ProvideTextStyle(MaterialTheme.typography.labelMedium) {
                                        MediaProgressIndicatorText(
                                            state = progressSliderState,
                                            playbackSpeedState = playbackSpeedControllerState,
                                        )
                                    }
                                }

                                val currentSpeed = playbackSpeedControllerState?.currentSpeed ?: 1.0f
                                if (currentSpeed != 1.0f) {
                                    Surface(
                                        onClick = { showSettingsSheet = true },
                                        color = Color.White.copy(alpha = 0.14f),
                                        shape = RoundedCornerShape(50),
                                    ) {
                                        Text(
                                            text = "${currentSpeed}x",
                                            color = Color.White,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            )

            // Panel compacto de ajustes del reproductor (Velocidad, Pantalla, Pistas, Estadísticas)
            if (showSettingsSheet) {
                PlayerSettingsOverlay(
                    playbackSpeedControllerState = playbackSpeedControllerState,
                    videoAspectRatioControllerState = videoAspectRatioControllerState,
                    onApplyExoSpeed = { speed -> exoPlayer?.setPlaybackSpeed(speed) },
                    audioTracksContent = {
                        player.audioTracks?.let {
                            PlayerControllerDefaults.AudioSwitcher(it)
                        }
                    },
                    subtitleTracksContent = {
                        player.subtitleTracks?.let {
                            PlayerControllerDefaults.SubtitleSwitcher(it)
                        }
                    },
                    showPlayerStats = showPlayerStats,
                    onTogglePlayerStats = { showPlayerStats = it },
                    onDismiss = { showSettingsSheet = false },
                )
            }

            // Cuenta regresiva para el siguiente episodio
            nextEpisodeCountdown?.let { secondsLeft ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 24.dp, bottom = 96.dp),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    androidx.compose.material3.Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color.Black.copy(alpha = 0.85f),
                        contentColor = Color.White,
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = "Siguiente episodio en $secondsLeft s",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleSmall,
                            )
                            if (episodeNumber != null) {
                                Text(
                                    text = "Episodio ${episodeNumber + 1}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.7f),
                                )
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedButton(
                                    onClick = { nextEpisodeCountdown = null },
                                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.5f)),
                                ) {
                                    Text("Cancelar", color = Color.White)
                                }
                                Button(
                                    onClick = {
                                        nextEpisodeCountdown = null
                                        currentOnNextEpisode?.invoke()
                                    },
                                ) {
                                    Text("Ver ahora")
                                }
                            }
                        }
                    }
                }
            }

            // Diálogo de error amigable
            if (errorMessage != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.92f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(32.dp)
                    ) {
                        Text(
                            text = "Error al reproducir",
                            color = Color(0xFFFF5252),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = errorMessage ?: "",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            if (onNextEpisode != null) {
                                OutlinedButton(
                                    onClick = onNextEpisode,
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                                ) {
                                    Text("Siguiente ep.", color = Color.White)
                                }
                            }
                            if (onChangeSource != null) {
                                OutlinedButton(
                                    onClick = onChangeSource,
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                                ) {
                                    Text("Cambiar fuente", color = Color.White)
                                }
                            }
                            Button(
                                onClick = onBack,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text("Volver")
                            }
                        }
                    }
                }
            }
        }
    }
}

private val SPEED_PRESETS = listOf(0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f)

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PlayerSettingsOverlay(
    playbackSpeedControllerState: PlaybackSpeedControllerState?,
    videoAspectRatioControllerState: VideoAspectRatioControllerState?,
    onApplyExoSpeed: (Float) -> Unit,
    audioTracksContent: @Composable () -> Unit,
    subtitleTracksContent: @Composable () -> Unit,
    showPlayerStats: Boolean,
    onTogglePlayerStats: (Boolean) -> Unit,
    onDismiss: () -> Unit,
) {
    BackHandler(onBack = onDismiss)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.45f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onDismiss,
            )
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Surface(
            modifier = Modifier
                .widthIn(min = 280.dp, max = 340.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {},
                ),
            shape = RoundedCornerShape(18.dp),
            color = Color(0xFF16161C).copy(alpha = 0.96f),
            contentColor = Color.White,
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Ajustes",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(28.dp),
                    ) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = "Cerrar ajustes",
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }

                if (playbackSpeedControllerState != null) {
                    val currentSpeed = playbackSpeedControllerState.currentSpeed
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Velocidad",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White.copy(alpha = 0.7f),
                        )
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            SPEED_PRESETS.forEach { preset ->
                                val selected = kotlin.math.abs(currentSpeed - preset) < 0.03f
                                Surface(
                                    onClick = {
                                        playbackSpeedControllerState.commitSpeed(preset)
                                        onApplyExoSpeed(preset)
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (selected) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        Color.White.copy(alpha = 0.08f)
                                    },
                                ) {
                                    Text(
                                        text = if (preset == 1.0f) "Normal" else "${preset}x",
                                        fontSize = 12.sp,
                                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (selected) {
                                            MaterialTheme.colorScheme.onPrimary
                                        } else {
                                            Color.White.copy(alpha = 0.9f)
                                        },
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    )
                                }
                            }
                        }
                    }
                }

                if (videoAspectRatioControllerState != null) {
                    val currentMode = videoAspectRatioControllerState.currentMode
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Pantalla",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White.copy(alpha = 0.7f),
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            VideoAspectRatioControllerState.Entries.forEach { mode ->
                                val selected = currentMode == mode
                                Surface(
                                    onClick = { videoAspectRatioControllerState.setMode(mode) },
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (selected) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        Color.White.copy(alpha = 0.08f)
                                    },
                                ) {
                                    Text(
                                        text = when (mode) {
                                            AspectRatioMode.FIT -> "Ajustar"
                                            AspectRatioMode.CROP -> "Rellenar"
                                            AspectRatioMode.STRETCH -> "Estirar"
                                        },
                                        fontSize = 12.sp,
                                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (selected) {
                                            MaterialTheme.colorScheme.onPrimary
                                        } else {
                                            Color.White.copy(alpha = 0.9f)
                                        },
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    )
                                }
                            }
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    audioTracksContent()
                    subtitleTracksContent()
                }

                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTogglePlayerStats(!showPlayerStats) }
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Icon(
                            Icons.Outlined.Analytics,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(18.dp),
                        )
                        Text(
                            text = "Estadísticas técnicas",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.9f),
                        )
                    }
                    Switch(
                        checked = showPlayerStats,
                        onCheckedChange = onTogglePlayerStats,
                    )
                }
            }
        }
    }
}

/**
 * Identifica el archivo interno cuyo nombre coincida con el episodio solicitado
 * (ej. ...S03E04... o ... - 04.mkv), con soporte para numeración relativa y absoluta.
 */
private fun findMatchingEpisodeFile(
    files: List<TorrentFileEntry>,
    episodeNumber: Int?,
    absoluteEpisodeNumber: Int?,
): TorrentFileEntry? {
    if (files.isEmpty()) return null
    if (files.size == 1) return files.first()
    if (episodeNumber == null && absoluteEpisodeNumber == null) return null

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
        for (pattern in patterns) {
            val matched = files.firstOrNull { pattern.containsMatchIn(it.fileName) }
            if (matched != null) return matched
        }
    }
    return null
}
