@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package app.yokan.ui.screens

import android.content.pm.ActivityInfo
import android.os.Build
import android.util.Log
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.snap
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FastForward
import androidx.compose.material.icons.rounded.PictureInPictureAlt
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
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
import android.widget.Toast
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.VideoFrameProcessingException
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import app.yokan.datasource.animeav1.WebStreamSource
import app.yokan.datasource.aniskip.AniSkipClient
import app.yokan.datasource.aniskip.SkipSegment
import app.yokan.datasource.aniskip.mergeSkipSegments
import app.yokan.datasource.nyaa.EpisodeNumberMatcher
import app.yokan.datasource.nyaa.NyaaTorrent
import app.yokan.media.TorrentManager
import app.yokan.media.TorrentMediaData
import app.yokan.media.WatchHistoryManager
import app.yokan.settings.YokanSettingsManager
import app.yokan.ui.LocalPipController
import app.yokan.anilist.model.AniListMedia
import org.openani.mediamp.MediaStatus
import me.him188.ani.app.videoplayer.ui.progress.PlayerProgressSliderState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import me.him188.ani.app.data.models.preference.DarkMode
import me.him188.ani.app.data.models.preference.PlayerKernelConfig
import me.him188.ani.app.domain.media.player.ChunkState
import me.him188.ani.app.domain.media.player.MediaCacheProgressInfo
import me.him188.ani.app.platform.features.StreamType
import me.him188.ani.app.platform.features.getComponentAccessorsImpl
import me.him188.ani.app.platform.findActivity
import me.him188.ani.app.torrent.api.files.FilePriority
import me.him188.ani.app.torrent.api.files.TorrentFileEntry
import me.him188.ani.app.torrent.api.files.TorrentFileHandle
import me.him188.ani.app.torrent.api.pieces.PieceState
import me.him188.ani.app.torrent.api.pieces.forEach
import me.him188.ani.app.torrent.api.pieces.isEmpty
import me.him188.ani.app.torrent.api.pieces.sumOf
import me.him188.ani.app.ui.foundation.TextWithBorder
import me.him188.ani.app.ui.foundation.animation.AniAnimatedVisibility
import me.him188.ani.app.ui.foundation.animation.LocalAniMotionScheme
import me.him188.ani.app.ui.foundation.effects.DarkStatusBarAppearance
import me.him188.ani.app.ui.foundation.effects.ScreenOnEffect
import me.him188.ani.app.ui.foundation.icons.AniIcons
import me.him188.ani.app.ui.foundation.icons.Forward80
import me.him188.ani.app.ui.foundation.icons.Forward85
import me.him188.ani.app.ui.foundation.icons.Forward90
import me.him188.ani.app.ui.foundation.theme.AniTheme
import me.him188.ani.app.videoplayer.media.LibassExoPlayerMediampPlayer
import me.him188.ani.app.videoplayer.videoenhancement.VideoEnhancementMode
import me.him188.ani.app.videoplayer.videoenhancement.createVideoEnhancementController
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
import org.openani.mediamp.features.chapters
import org.openani.mediamp.features.subtitleTracks
import org.openani.mediamp.togglePlayWhenReady
import kotlin.math.abs
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

private const val LOG_TAG = "YokanPlayer"

/**
 * Tiempo que se espera un stream web antes de darlo por muerto y probar el siguiente servidor.
 */
private const val WEB_WATCHDOG_MS = 8_000L

@Composable
fun VideoPlayerScreen(
    torrent: NyaaTorrent? = null,
    webStream: WebStreamSource? = null,
    sourceSelectionVersion: Int = 0,
    episodeNumber: Int? = null,
    absoluteEpisodeNumber: Int? = null,
    initialPositionMillis: Long = 0L,
    animeForHistory: AniListMedia? = null,
    episodeTitle: String? = null,
    hasBothLanguages: Boolean = false,
    aniSkipClient: AniSkipClient? = null,
    isFullscreen: Boolean? = null,
    onFullscreenChange: ((Boolean) -> Unit)? = null,
    maintainAspectRatio: Boolean? = null,
    windowInsets: WindowInsets? = null,
    externalLoadingStatus: String? = null,
    externalErrorMessage: String? = null,
    onOpenEpisodeList: (() -> Unit)? = null,
    onActiveStreamResolved: ((WebStreamSource) -> Unit)? = null,
    onToggleAudioLanguage: (suspend (targetDub: Boolean) -> WebStreamSource?)? = null,
    onChangeSource: (() -> Unit)? = null,
    onNextEpisode: (() -> Unit)? = null,
    onResolveNextWebSource: (suspend (failed: WebStreamSource, triedServers: Set<String>) -> WebStreamSource?)? = null,
    rhsSheet: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val activity = remember(context) { context.findActivity() }

    val watchHistoryManager = remember { WatchHistoryManager.getInstance(context) }
    val settingsManager = remember { YokanSettingsManager.getInstance(context) }
    val appSettings by settingsManager.settings.collectAsState()
    val currentAutoPlayNext by rememberUpdatedState(appSettings.autoPlayNextEpisode)
    val pipController = LocalPipController.current
    val isInPipMode = pipController.isInPipMode
    val lifecycleOwner = LocalLifecycleOwner.current

    var errorMessage by remember { mutableStateOf<String?>(null) }
    var loadingStatus by remember { mutableStateOf<String?>(if (torrent != null || webStream != null) "Cargando..." else null) }
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

    val videoEnhancementController = remember(player, playerCoroutineContext) {
        runCatching {
            createVideoEnhancementController(
                player = player,
                playerKernelConfig = flowOf(PlayerKernelConfig(exoPlayerInitEffectGraphInAdvance = true)),
                parentCoroutineContext = playerCoroutineContext,
            )?.also { controller ->
                controller.setMode(settingsManager.settings.value.videoEnhancementMode)
            }
        }.onFailure { err ->
            Log.w(LOG_TAG, "No se pudo inicializar VideoEnhancementController (Anime4K): ${err.message}", err)
        }.getOrNull()
    }
    val fallbackEnhancementFlow = remember { MutableStateFlow(VideoEnhancementMode.OFF) }
    val videoEnhancementMode by (videoEnhancementController?.mode ?: fallbackEnhancementFlow).collectAsState()

    LaunchedEffect(videoEnhancementController, appSettings.videoEnhancementMode) {
        videoEnhancementController?.setMode(appSettings.videoEnhancementMode)
    }

    // --- Fallback automático entre servidores web ---
    var activeWebStream by remember { mutableStateOf(webStream) }
    var lastHandledSelectionVersion by remember { mutableIntStateOf(sourceSelectionVersion) }
    var webReloadNonce by remember { mutableIntStateOf(0) }
    val triedWebServers = remember { mutableSetOf<String>() }
    var webSourceReady by remember { mutableStateOf(false) }
    var isSwitchingWebSource by remember { mutableStateOf(false) }
    var resumeAfterFallbackMs by remember { mutableLongStateOf(-1L) }
    val currentResolveNext by rememberUpdatedState(onResolveNextWebSource)
    val currentToggleLanguage by rememberUpdatedState(onToggleAudioLanguage)
    val currentOnActiveStreamResolved by rememberUpdatedState(onActiveStreamResolved)

    // Sincroniza cambios externos de webStream/torrent (incluyendo re-seleccionar la misma fuente o cambiar SUB/LAT)
    LaunchedEffect(webStream?.streamUrl, torrent?.magnetUrl, torrent?.torrentUrl, sourceSelectionVersion) {
        val selectionChanged = sourceSelectionVersion != lastHandledSelectionVersion
        lastHandledSelectionVersion = sourceSelectionVersion
        if (torrent == null && webStream != null && (webStream.streamUrl != activeWebStream?.streamUrl || selectionChanged)) {
            val isLanguageSwitch = activeWebStream != null && activeWebStream?.isDub != webStream.isDub
            if (isLanguageSwitch) {
                val pos = exoPlayer?.currentPosition ?: 0L
                if (pos > 2_000L) resumeAfterFallbackMs = pos
            }
            Log.i(LOG_TAG, "Nueva fuente web seleccionada: ${webStream.serverName} (selectionChanged=$selectionChanged, langSwitch=$isLanguageSwitch)")
            triedWebServers.clear()
            errorMessage = null
            loadingStatus = if (isLanguageSwitch) {
                if (webStream.isDub) "Cambiando a Español Latino..." else "Cambiando a Subtitulado..."
            } else {
                "Cargando..."
            }
            activeWebStream = webStream
            webReloadNonce++
        }
    }

    LaunchedEffect(activeWebStream) {
        activeWebStream?.let { currentOnActiveStreamResolved?.invoke(it) }
    }

    /** Pasa al siguiente servidor. Devuelve true si inició el cambio (o ya hay uno en curso). */
    fun switchToNextWebSource(reason: String): Boolean {
        val failed = activeWebStream ?: return false
        val resolver = currentResolveNext ?: return false
        if (isSwitchingWebSource) return true
        Log.w(LOG_TAG, "Cambiando servidor tras fallo de ${failed.serverName} ($reason): ${failed.streamUrl.take(80)}")
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
                Log.i(LOG_TAG, "Cambiado automáticamente a ${next.serverName}: ${next.streamUrl.take(80)}")
                activeWebStream = next
                webReloadNonce++
            } else {
                Log.e(LOG_TAG, "Sin servidores alternativos tras fallo de ${failed.serverName} (tried=$triedWebServers)")
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
                triedWebServers.clear()
                activeWebStream = switched
            } else {
                loadingStatus = null
                resumeAfterFallbackMs = -1L
                val targetLabel = if (targetDub) "Español Latino" else "Subtitulado"
                Toast.makeText(
                    context,
                    "No hay servidores disponibles en $targetLabel para este episodio.",
                    Toast.LENGTH_SHORT,
                ).show()
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
    fun seekToEffective(targetMs: Long) {
        val clamped = targetMs.coerceAtLeast(0L)
        if (currentMediaData == null && exoPlayer != null) {
            exoPlayer.seekTo(clamped)
            exoPosition = clamped
        } else {
            player.seekTo(clamped)
        }
    }
    fun skipEffective(deltaMs: Long) {
        val current = effectivePositionMillis()
        val effectiveDur = effectiveDurationMillis()
        val target = (current + deltaMs).coerceIn(0L, if (effectiveDur > 0L) effectiveDur else Long.MAX_VALUE)
        seekToEffective(target)
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
        if (currentOnNextEpisode != null && errorMessage == null && currentAutoPlayNext) {
            nextEpisodeCountdown = AUTO_NEXT_SECONDS
        }
    }

    var internalFullscreen by remember { mutableStateOf(animeForHistory == null) }
    val effectiveFullscreen = isFullscreen ?: internalFullscreen
    val currentOnFullscreenChange by rememberUpdatedState(onFullscreenChange)
    var isLocked by remember { mutableStateOf(false) }
    var showPlayerStats by remember { mutableStateOf(false) }

    val fullscreenState = rememberPlayerFullscreenState(
        isFullscreen = { effectiveFullscreen },
        onRequest = { requestedFullscreen ->
            if (!requestedFullscreen) {
                isLocked = false
            }
            if (currentOnFullscreenChange != null) {
                currentOnFullscreenChange?.invoke(requestedFullscreen)
            } else {
                internalFullscreen = requestedFullscreen
            }
        }
    )

    // Audio Focus + pausa automática al desconectar auriculares Bluetooth/cable
    LaunchedEffect(exoPlayer) {
        val exo = exoPlayer ?: return@LaunchedEffect
        runCatching {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(C.USAGE_MEDIA)
                .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                .build()
            exo.setAudioAttributes(audioAttributes, true)
            exo.setHandleAudioBecomingNoisy(true)
        }
    }

    // Sincroniza orientación horizontal / sistema bars exclusivamente con `effectiveFullscreen` (excepto en PiP)
    LaunchedEffect(effectiveFullscreen, isInPipMode, activity) {
        val act = activity ?: return@LaunchedEffect
        if (isInPipMode) return@LaunchedEffect
        val window = act.window
        val insetsController = window?.let { WindowCompat.getInsetsController(it, it.decorView) }
        if (effectiveFullscreen) {
            act.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            insetsController?.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            insetsController?.hide(WindowInsetsCompat.Type.systemBars())
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                @Suppress("DEPRECATION")
                window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        } else {
            act.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            insetsController?.show(WindowInsetsCompat.Type.systemBars())
            insetsController?.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                @Suppress("DEPRECATION")
                window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }
    }

    // Configura cutout al montar y restaura orientación / guarda progreso al cerrar el episodio
    DisposableEffect(activity) {
        val window = activity?.window
        val insetsController = window?.let { WindowCompat.getInsetsController(it, it.decorView) }
        val originalSystemBarsBehavior = insetsController?.systemBarsBehavior

        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val params = window.attributes
            params.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = params
        }

        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            if (window != null) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
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
            runCatching { videoEnhancementController?.close() }
            runCatching { player.close() }
            runCatching { currentMediaData?.close() }
        }
    }

    BackHandler {
        if (effectiveFullscreen && animeForHistory != null) {
            fullscreenState.request(false)
        } else {
            onBack()
        }
    }

    val playWhenReady by remember(player) { player.state.map { it.playWhenReady } }
        .collectAsStateWithLifecycle(false)

    val isBuffering by remember(player) { player.state.map { it.isBuffering } }
        .collectAsStateWithLifecycle(false)

    var exoPlayWhenReady by remember { mutableStateOf(true) }
    var exoIsBuffering by remember { mutableStateOf(false) }

    // Pausar cuando la actividad pasa a segundo plano sin PiP (ON_STOP no se dispara mientras PiP flota)
    DisposableEffect(lifecycleOwner, exoPlayer, player) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                if (currentMediaData == null && exoPlayer != null) {
                    if (exoPlayer.playWhenReady) {
                        exoPlayer.playWhenReady = false
                        exoPlayWhenReady = false
                    }
                } else if (player.state.value.playWhenReady) {
                    runCatching { player.pause() }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

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
                Log.e(LOG_TAG, "ExoPlayer error code=${error.errorCode} server=${activeWebStream?.serverName}: ${error.message}", error)
                logger.error("ExoPlayer error: ${error.message} (code=${error.errorCode})", error)
                if (videoEnhancementController != null &&
                    videoEnhancementController.mode.value != VideoEnhancementMode.OFF &&
                    isVideoFrameProcessingError(error)
                ) {
                    Log.w(LOG_TAG, "Fallo GPU/shader en Anime4K; desactivando mejora de video y reintentando", error)
                    videoEnhancementController.setMode(VideoEnhancementMode.OFF)
                    settingsManager.setVideoEnhancementMode(VideoEnhancementMode.OFF)
                    val pos = exoPlayer.currentPosition
                    if (pos > 2_000L) resumeAfterFallbackMs = pos
                    Toast.makeText(
                        context,
                        "Anime4K no es compatible con este dispositivo; se desactivó automáticamente.",
                        Toast.LENGTH_LONG,
                    ).show()
                    errorMessage = null
                    loadingStatus = "Cargando..."
                    if (activeWebStream != null) {
                        webReloadNonce++
                    } else {
                        runCatching {
                            exoPlayer.prepare()
                            exoPlayer.playWhenReady = true
                        }
                    }
                    return
                }
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
                    Log.i(LOG_TAG, "ExoPlayer READY server=${activeWebStream?.serverName} duration=${exoPlayer.duration}")
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
                    currentPos > 10_000L && abs(currentPos - lastSavedPositionMs) > 5_000L) {
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

    LaunchedEffect(activeWebStream?.streamUrl, webReloadNonce) {
        val stream = activeWebStream ?: return@LaunchedEffect
        currentMediaData?.let { prev ->
            currentMediaData = null
            runCatching { prev.close() }
        }
        webSourceReady = false
        exoBufferedPosition = 0L
        exoPosition = 0L
        exoDuration = 0L
        cacheProgressInfo = null
        cachePercentText = null
        loadingStatus = "Cargando..."
        errorMessage = null
        try {
            Log.i(LOG_TAG, "Iniciando ExoPlayer [${stream.serverName}] isHls=${stream.isHls} -> ${stream.streamUrl.take(90)}")
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

            val isExplicitMp4 = uri.path?.endsWith(".mp4", ignoreCase = true) == true ||
                stream.streamUrl.contains(".mp4", ignoreCase = true)
            val isHlsStream = stream.isHls ||
                stream.streamUrl.contains(".m3u8", ignoreCase = true) ||
                (!isExplicitMp4 && (
                    stream.streamUrl.contains("/pl/", ignoreCase = true) ||
                        Regex("""/master(?:\.m3u8)?(?:$|[?#])""", RegexOption.IGNORE_CASE)
                            .containsMatchIn(stream.streamUrl)
                    ))

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
            Log.e(LOG_TAG, "Error preparando MediaSource [${stream.serverName}]: ${e.message}", e)
            logger.error("Error al reproducir stream web: ${e.message}", e)
            if (!switchToNextWebSource("error preparar")) {
                errorMessage = "Error al reproducir stream: ${e.localizedMessage ?: e::class.simpleName}"
                loadingStatus = null
            }
        }
    }

    // Watchdog: si el servidor no llega a READY y no da señales de vida, pasar al siguiente.
    // No dispara si el reproductor ya está descargando (hay búfer): un CDN lento no es un fallo.
    LaunchedEffect(activeWebStream?.streamUrl, webReloadNonce) {
        if (activeWebStream == null) return@LaunchedEffect
        delay(WEB_WATCHDOG_MS)
        if (!webSourceReady && errorMessage == null && !isSwitchingWebSource && exoBufferedPosition <= 0L) {
            switchToNextWebSource("sin respuesta")
        }
    }

    LaunchedEffect(torrent?.magnetUrl, torrent?.torrentUrl) {
        val currentTorrent = torrent ?: return@LaunchedEffect
        activeWebStream = null
        exoDuration = 0L
        exoPosition = 0L
        exoBufferedPosition = 0L
        runCatching { exoPlayer?.clearMediaItems() }
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
            val hasValidMagnet = currentTorrent.magnetUrl.startsWith("magnet:", ignoreCase = true)
            val encodedInfo = if (directTorrentUrl != null) {
                runSuspendCatching {
                    downloader.fetchTorrent(directTorrentUrl)
                }.getOrElse { error ->
                    if (!hasValidMagnet) throw error
                    logger.warn("Falló descarga directa de .torrent HTTP (${error.message}), recurriendo a magnet...")
                    downloader.fetchTorrent(currentTorrent.magnetUrl)
                }
            } else {
                if (!hasValidMagnet && !currentTorrent.magnetUrl.startsWith("http", ignoreCase = true)) {
                    throw IllegalArgumentException("El torrent no tiene enlace magnet ni URL HTTP válida.")
                }
                downloader.fetchTorrent(currentTorrent.magnetUrl)
            }

            loadingStatus = "Conectando con peers..."
            val session = downloader.startDownload(encodedInfo)
            var sessionTransferredToMediaData = false
            var createdHandle: TorrentFileHandle? = null
            try {
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
                createdHandle = handle

                val mediaData = TorrentMediaData(
                    handle = handle,
                    onClose = {
                        coroutineScope.launch(NonCancellable) {
                            runCatching { handle.close() }
                            runCatching { session.closeIfNotInUse() }
                        }
                    }
                )

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
                currentMediaData = mediaData
                sessionTransferredToMediaData = true
                runCatching { player.play() }
                loadingStatus = null
            } finally {
                if (!sessionTransferredToMediaData) {
                    withContext(NonCancellable) {
                        runCatching { createdHandle?.close() }
                        runCatching { session.closeIfNotInUse() }
                    }
                }
            }
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
    LaunchedEffect(controllerState.visibility, effectiveFullscreen) {
        if (effectiveFullscreen && controllerState.visibility == ControllerVisibility.Invisible) {
            val window = activity?.window
            if (window != null) {
                val insetsController = WindowCompat.getInsetsController(window, window.decorView)
                insetsController.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                insetsController.hide(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    LaunchedEffect(isEffectivelyPlaying, effectiveFullscreen) {
        if (effectiveFullscreen && isEffectivelyPlaying) {
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

    // --- AniSkip (Opening / Ending / Recap) + capítulos embebidos en MKV ---
    var aniSkipSegments by remember(player) { mutableStateOf<List<SkipSegment>>(emptyList()) }
    val autoSkippedKeys = remember(player) { mutableSetOf<String>() }
    val embeddedChaptersState = remember(player) {
        player.chapters ?: flowOf(emptyList())
    }.collectAsState(initial = emptyList())
    val videoPropertiesState by player.mediaProperties.collectAsState(null)
    val playerPositionMs by player.currentPositionMillis.collectAsState()
    val resolvedDurationMillis by remember(player) {
        derivedStateOf {
            if (exoDuration > 0L) exoDuration else (videoPropertiesState?.durationMillis ?: 0L)
        }
    }
    // Agrupa la duración en intervalos de 2s para consultar AniSkip exactamente con el corte real
    // de este video y re-consultar automáticamente si cambia la duración al cambiar servidor o SUB/LAT.
    val aniSkipDurationBucket = if (resolvedDurationMillis >= 30_000L) {
        (resolvedDurationMillis / 2_000L).toInt()
    } else {
        0
    }

    LaunchedEffect(aniSkipClient, animeForHistory?.idMal, episodeNumber, aniSkipDurationBucket) {
        aniSkipSegments = emptyList()
        autoSkippedKeys.clear()
        val client = aniSkipClient ?: return@LaunchedEffect
        val malId = animeForHistory?.idMal ?: return@LaunchedEffect
        val ep = episodeNumber ?: return@LaunchedEffect
        val durationMs = resolvedDurationMillis
        if (malId > 0 && ep > 0 && aniSkipDurationBucket > 0 && durationMs >= 30_000L) {
            aniSkipSegments = client.getSkipSegments(
                malId = malId,
                episodeNumber = ep,
                episodeLengthMillis = durationMs,
            )
        }
    }

    val effectiveSkipSegments by remember(player) {
        derivedStateOf {
            mergeSkipSegments(
                embeddedChapters = embeddedChaptersState.value,
                aniSkipSegments = aniSkipSegments,
                totalDurationMillis = resolvedDurationMillis,
            )
        }
    }

    val activeSkipSegment by remember(player) {
        derivedStateOf {
            if (resolvedDurationMillis <= 0L) return@derivedStateOf null
            val pos = if (exoPosition > 0L) exoPosition else playerPositionMs
            if (pos <= 0L) return@derivedStateOf null
            effectiveSkipSegments.firstOrNull { seg ->
                pos >= seg.startMillis && pos < (seg.endMillis - 1_500L)
            }
        }
    }

    LaunchedEffect(activeSkipSegment?.key, appSettings.autoSkipIntro, appSettings.autoSkipOutro) {
        val seg = activeSkipSegment ?: return@LaunchedEffect
        val shouldAutoSkip = (seg.isAutoSkippableOpening && appSettings.autoSkipIntro) ||
            (seg.isAutoSkippableEnding && appSettings.autoSkipOutro)
        if (shouldAutoSkip && autoSkippedKeys.add(seg.key)) {
            seekToEffective(seg.endMillis)
            Toast.makeText(context, "${seg.type.label} saltado", Toast.LENGTH_SHORT).show()
        }
    }

    val progressSliderState = remember(player) {
        PlayerProgressSliderState(
            currentPositionMillis = {
                if (exoPosition > 0L) exoPosition else player.currentPositionMillis.value
            },
            totalDurationMillis = {
                if (exoDuration > 0L) exoDuration else (player.mediaProperties.value?.durationMillis ?: 0L)
            },
            chapters = {
                val embedded = embeddedChaptersState.value
                if (embedded.isNotEmpty()) {
                    embedded
                } else {
                    effectiveSkipSegments.map { it.toChapter() }
                }
            },
            onPreview = {},
            onPreviewFinished = { targetMs ->
                seekToEffective(targetMs)
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

    val resolvedMaintainAspectRatio = maintainAspectRatio ?: !effectiveFullscreen
    val resolvedWindowInsets = windowInsets ?: if (effectiveFullscreen) {
        WindowInsets.displayCutout
    } else {
        WindowInsets(0)
    }

    val renderProgressSlider: @Composable (Modifier) -> Unit = { sliderModifier ->
        MediaProgressSlider(
            state = progressSliderState,
            cacheProgressInfoFlow = { cacheProgressInfo },
            showPreviewTimeTextOnThumb = effectiveFullscreen,
            framePreview = null,
            showFramePreviewInPopup = effectiveFullscreen,
            touchSeekState = touchSeekState,
            modifier = sliderModifier,
        )
    }

    val activeLoadingStatus = loadingStatus
        ?: if (activeWebStream == null && torrent == null) externalLoadingStatus else null
    val activeErrorMessage = errorMessage
        ?: if (activeWebStream == null && torrent == null) externalErrorMessage else null

    val pipPlaybackActive = isEffectivelyPlaying && activeErrorMessage == null
    LaunchedEffect(pipPlaybackActive, appSettings.enablePip) {
        pipController.onUpdatePlaybackActive(pipPlaybackActive && appSettings.enablePip)
    }
    DisposableEffect(Unit) {
        onDispose {
            pipController.onUpdatePlaybackActive(false)
        }
    }
    LaunchedEffect(isInPipMode) {
        if (isInPipMode) {
            showSettingsSheet = false
            controllerState.toggleFullVisible(false)
        }
    }

    val outerModifier = modifier
        .fillMaxWidth()
        .background(Color.Black)
        .then(if (effectiveFullscreen || isInPipMode) Modifier.fillMaxSize() else Modifier.statusBarsPadding())

    AniTheme(darkModeOverride = DarkMode.DARK) {
        Box(modifier = outerModifier) {
            VideoScaffold(
                expanded = effectiveFullscreen || isInPipMode,
                modifier = if (effectiveFullscreen || isInPipMode) Modifier.fillMaxSize() else Modifier.fillMaxWidth(),
                contentWindowInsets = if (isInPipMode) WindowInsets(0) else resolvedWindowInsets,
                maintainAspectRatio = if (isInPipMode) false else resolvedMaintainAspectRatio,
                controllerState = controllerState,
                gestureLocked = isLocked || isInPipMode,
                topBar = {
                    if (!isInPipMode) {
                    PlayerTopBar(
                        title = {
                            if (effectiveFullscreen && animeForHistory != null) {
                                Column(horizontalAlignment = Alignment.Start) {
                                    ProvideTextStyle(MaterialTheme.typography.titleMedium) {
                                        TextWithBorder(
                                            animeForHistory.title.displayTitle,
                                            softWrap = false,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                    }
                                    val epLabel = buildString {
                                        episodeNumber?.let { append(String.format("%02d", it)) }
                                        val titleText = episodeTitle?.takeIf { it.isNotBlank() }
                                            ?: episodeNumber?.let { "Episodio $it" }
                                        if (!titleText.isNullOrBlank()) {
                                            if (isNotEmpty()) append("  ")
                                            append(titleText)
                                        }
                                    }
                                    if (epLabel.isNotBlank()) {
                                        ProvideTextStyle(MaterialTheme.typography.titleSmall) {
                                            TextWithBorder(
                                                epLabel,
                                                softWrap = false,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                            )
                                        }
                                    }
                                }
                            }
                        },
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
                            if (pipController.isSupported && appSettings.enablePip && activeErrorMessage == null) {
                                IconButton(onClick = { pipController.onEnterPip() }) {
                                    Icon(
                                        Icons.Rounded.PictureInPictureAlt,
                                        contentDescription = "Imagen en imagen (PiP)",
                                        tint = Color.White,
                                    )
                                }
                            }
                            if (onChangeSource != null && (effectiveFullscreen || activeErrorMessage != null)) {
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
                        },
                        windowInsets = WindowInsets(0),
                    )
                    }
                },
                centerOverlay = {},
                video = {
                    VideoPlayer(
                        player = player,
                        modifier = Modifier
                            .fillMaxSize()
                            .onSizeChanged { size ->
                                if (size.width > 0 && size.height > 0) {
                                    videoEnhancementController?.setViewportSize(size.width, size.height)
                                }
                            },
                    )
                },
                gestureHost = {
                    val swipeSeekerState = rememberSwipeSeekerState(
                        screenWidthPx = constraints.maxWidth,
                        swipeSeekerConfig = swipeSeekerConfig,
                    ) { offsetSeconds ->
                        skipEffective(offsetSeconds * 1000L)
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
                    if (effectiveFullscreen && !isInPipMode) {
                        GestureLock(
                            isLocked = isLocked,
                            onClick = { isLocked = !isLocked },
                        )
                    }
                },
                playerStatsOverlay = {
                    if (showPlayerStats && !isInPipMode) {
                        PlayerStatsOverlay(playerStats)
                    }
                },
                floatingMessage = {
                    val status = activeLoadingStatus
                    val showBuffering = (activeWebStream != null || torrent != null) && isEffectivelyBuffering
                    if (!isInPipMode && activeErrorMessage == null && (showBuffering || status != null)) {
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
                floatingBottomEnd = {
                    if (!effectiveFullscreen && !isInPipMode) {
                        var showFloatingSwitch by remember { mutableStateOf(true) }
                        LaunchedEffect(Unit) {
                            delay(5.seconds)
                            showFloatingSwitch = false
                        }
                        AniAnimatedVisibility(
                            visible = showFloatingSwitch,
                            enter = fadeIn(snap()),
                            exit = LocalAniMotionScheme.current.animatedVisibility.standardExit,
                        ) {
                            PlayerControllerDefaults.FullscreenIcon(fullscreenState)
                        }
                    }
                },
                rhsSheet = { if (!isInPipMode) rhsSheet() },
                bottomBar = {
                    if (!isInPipMode) {
                    val sliderOnly = controllerState.visibility == ControllerVisibility.InlineSliderOnly
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(remember { MutableInteractionSource() }, null, onClick = {})
                            .padding(
                                start = if (effectiveFullscreen) 16.dp else 8.dp,
                                end = if (effectiveFullscreen) 16.dp else 8.dp,
                                bottom = if (effectiveFullscreen) 4.dp else 2.dp,
                            ),
                    ) {
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
                                    if (effectiveFullscreen && onNextEpisode != null) {
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

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(if (effectiveFullscreen) 8.dp else 4.dp),
                                ) {
                                    if (effectiveFullscreen && onOpenEpisodeList != null) {
                                        TextButton(
                                            onClick = onOpenEpisodeList,
                                            colors = ButtonDefaults.textButtonColors(
                                                contentColor = Color.White,
                                            ),
                                        ) {
                                            Text("Episodios", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                        }
                                    }

                                    val currentSpeed = playbackSpeedControllerState?.currentSpeed ?: 1.0f
                                    if (effectiveFullscreen && currentSpeed != 1.0f) {
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
                                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                            )
                                        }
                                    }

                                    if (effectiveFullscreen && videoEnhancementMode != VideoEnhancementMode.OFF) {
                                        Surface(
                                            onClick = { showSettingsSheet = true },
                                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.28f),
                                            shape = RoundedCornerShape(50),
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Rounded.AutoAwesome,
                                                    contentDescription = "Anime4K activo",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(14.dp),
                                                )
                                                Text(
                                                    text = if (videoEnhancementMode == VideoEnhancementMode.QUALITY) "4K+" else "4K",
                                                    color = Color.White,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                )
                                            }
                                        }
                                    }

                                    if (effectiveFullscreen) {
                                        val activeSkip = activeSkipSegment
                                        val skipSeconds = appSettings.skipIntroSeconds
                                        val skipIcon = if (activeSkip != null) {
                                            Icons.Rounded.FastForward
                                        } else {
                                            when (skipSeconds) {
                                                80 -> AniIcons.Forward80
                                                85 -> AniIcons.Forward85
                                                90 -> AniIcons.Forward90
                                                else -> Icons.Rounded.FastForward
                                            }
                                        }
                                        Surface(
                                            onClick = {
                                                if (activeSkip != null) {
                                                    autoSkippedKeys.add(activeSkip.key)
                                                    seekToEffective(activeSkip.endMillis)
                                                } else {
                                                    skipEffective(skipSeconds * 1_000L)
                                                }
                                            },
                                            color = if (activeSkip != null) {
                                                MaterialTheme.colorScheme.primary.copy(alpha = 0.34f)
                                            } else {
                                                Color.White.copy(alpha = 0.16f)
                                            },
                                            shape = RoundedCornerShape(50),
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                            ) {
                                                Icon(
                                                    skipIcon,
                                                    contentDescription = activeSkip?.type?.buttonLabel ?: "Adelantar +${skipSeconds}s",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(18.dp),
                                                )
                                                Text(
                                                    text = activeSkip?.type?.buttonLabel ?: "+${skipSeconds}s",
                                                    color = Color.White,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                )
                                            }
                                        }
                                    }

                                    PlayerControllerDefaults.FullscreenIcon(fullscreenState)
                                }
                            }
                        }

                        renderProgressSlider(Modifier)
                    }
                    }
                },
                detachedProgressSlider = {
                    if (!isInPipMode) {
                        renderProgressSlider(Modifier.padding(horizontal = 12.dp))
                    }
                },
            )

            // Panel compacto de ajustes del reproductor (Velocidad, Pantalla, Anime4K, Pistas, Estadísticas)
            if (showSettingsSheet && !isInPipMode) {
                PlayerSettingsOverlay(
                    playbackSpeedControllerState = playbackSpeedControllerState,
                    videoAspectRatioControllerState = videoAspectRatioControllerState,
                    videoEnhancementMode = if (videoEnhancementController != null) videoEnhancementMode else null,
                    onSelectVideoEnhancementMode = { mode ->
                        videoEnhancementController?.setMode(mode)
                        settingsManager.setVideoEnhancementMode(mode)
                    },
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

            // Botón flotante inteligente de AniSkip (Opening / Ending / Resumen)
            val currentSkip = activeSkipSegment
            if (!isInPipMode && currentSkip != null && nextEpisodeCountdown == null && activeErrorMessage == null) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(
                            end = if (effectiveFullscreen) 24.dp else 12.dp,
                            bottom = if (effectiveFullscreen) 84.dp else 52.dp,
                        ),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    Surface(
                        onClick = {
                            autoSkippedKeys.add(currentSkip.key)
                            seekToEffective(currentSkip.endMillis)
                        },
                        shape = RoundedCornerShape(50),
                        color = Color(0xDD14141A),
                        contentColor = Color.White,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)),
                        shadowElevation = 6.dp,
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.FastForward,
                                contentDescription = currentSkip.type.buttonLabel,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp),
                            )
                            Text(
                                text = currentSkip.type.buttonLabel,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                    }
                }
            }

            // Cuenta regresiva para el siguiente episodio
            if (!isInPipMode) nextEpisodeCountdown?.let { secondsLeft ->
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(end = if (effectiveFullscreen) 24.dp else 12.dp, bottom = if (effectiveFullscreen) 96.dp else 56.dp),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    androidx.compose.material3.Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color.Black.copy(alpha = 0.85f),
                        contentColor = Color.White,
                    ) {
                        Column(
                            modifier = Modifier.padding(if (effectiveFullscreen) 16.dp else 12.dp),
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

            // Diálogo de error dentro del marco del reproductor
            if (activeErrorMessage != null) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.88f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(if (effectiveFullscreen) 32.dp else 16.dp)
                    ) {
                        Text(
                            text = "No se pudo reproducir",
                            color = Color(0xFFFF5252),
                            fontWeight = FontWeight.Bold,
                            style = if (effectiveFullscreen) MaterialTheme.typography.titleLarge else MaterialTheme.typography.titleMedium,
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = activeErrorMessage,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (onChangeSource != null) {
                                Button(
                                    onClick = onChangeSource,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    ),
                                ) {
                                    Text("Elegir fuente")
                                }
                            }
                            if (effectiveFullscreen && onNextEpisode != null) {
                                OutlinedButton(
                                    onClick = onNextEpisode,
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                                ) {
                                    Text("Siguiente ep.", color = Color.White)
                                }
                            }
                            if (effectiveFullscreen || animeForHistory == null) {
                                OutlinedButton(
                                    onClick = {
                                        if (effectiveFullscreen && animeForHistory != null) {
                                            fullscreenState.request(false)
                                        } else {
                                            onBack()
                                        }
                                    },
                                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.4f)),
                                ) {
                                    Text(if (effectiveFullscreen && animeForHistory != null) "Salir pantalla completa" else "Volver", color = Color.White)
                                }
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
    videoEnhancementMode: VideoEnhancementMode?,
    onSelectVideoEnhancementMode: (VideoEnhancementMode) -> Unit,
    onApplyExoSpeed: (Float) -> Unit,
    audioTracksContent: @Composable () -> Unit,
    subtitleTracksContent: @Composable () -> Unit,
    showPlayerStats: Boolean,
    onTogglePlayerStats: (Boolean) -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
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
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 18.dp, vertical = 14.dp),
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

                if (videoEnhancementMode != null) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.AutoAwesome,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(15.dp),
                            )
                            Text(
                                text = "Mejora de video (Anime4K)",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White.copy(alpha = 0.7f),
                            )
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            listOf(
                                VideoEnhancementMode.OFF to "Off",
                                VideoEnhancementMode.PERFORMANCE to "Rendimiento",
                                VideoEnhancementMode.QUALITY to "Calidad",
                            ).forEach { (mode, label) ->
                                val selected = videoEnhancementMode == mode
                                Surface(
                                    onClick = { onSelectVideoEnhancementMode(mode) },
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (selected) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        Color.White.copy(alpha = 0.08f)
                                    },
                                ) {
                                    Text(
                                        text = label,
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
        val matched = EpisodeNumberMatcher.findBestMatch(files, ep) { it.fileName }
        if (matched != null) return matched
    }
    return null
}

private fun isVideoFrameProcessingError(error: PlaybackException): Boolean {
    if (error.errorCode == PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSOR_INIT_FAILED ||
        error.errorCode == PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED
    ) {
        return true
    }
    var cause: Throwable? = error
    while (cause != null) {
        if (cause is VideoFrameProcessingException) return true
        cause = cause.cause
    }
    return false
}
