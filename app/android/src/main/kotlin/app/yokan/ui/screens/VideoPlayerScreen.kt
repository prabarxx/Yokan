package app.yokan.ui.screens

import android.content.pm.ActivityInfo
import android.os.Build
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.rounded.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import app.yokan.datasource.animeav1.WebStreamSource
import app.yokan.datasource.nyaa.NyaaTorrent
import app.yokan.media.TorrentManager
import app.yokan.media.TorrentMediaData
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
import me.him188.ani.app.videoplayer.ui.top.PlayerTopBar
import me.him188.ani.app.videoplayer.ui.top.SystemTime
import me.him188.ani.utils.logging.error
import me.him188.ani.utils.logging.info
import me.him188.ani.utils.logging.logger
import me.him188.ani.utils.logging.warn
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

@Composable
fun VideoPlayerScreen(
    torrent: NyaaTorrent? = null,
    webStream: WebStreamSource? = null,
    episodeNumber: Int? = null,
    absoluteEpisodeNumber: Int? = null,
    onChangeSource: (() -> Unit)? = null,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val activity = remember(context) { context.findActivity() }

    var errorMessage by remember { mutableStateOf<String?>(null) }
    var loadingStatus by remember { mutableStateOf<String?>("Conectando con la red...") }
    var currentMediaData by remember { mutableStateOf<TorrentMediaData?>(null) }
    var cacheProgressInfo by remember { mutableStateOf<MediaCacheProgressInfo?>(null) }
    var cachePercentText by remember { mutableStateOf<String?>(null) }
    var exoDuration by remember { mutableLongStateOf(0L) }
    var exoPosition by remember { mutableLongStateOf(0L) }
    var exoBufferedPosition by remember { mutableLongStateOf(0L) }

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

    if (playWhenReady) {
        ScreenOnEffect()
    }

    DarkStatusBarAppearance()

    val exoPlayer = remember(player) { (player as? LibassExoPlayerMediampPlayer)?.exoPlayer }
    DisposableEffect(exoPlayer) {
        if (exoPlayer == null) return@DisposableEffect onDispose {}
        val listener = object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                logger.error("ExoPlayer error: ${error.message} (code=${error.errorCode})", error)
                errorMessage = "Error de reproducción: ${error.localizedMessage ?: error.message}"
                loadingStatus = null
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                logger.info("ExoPlayer playbackState changed: $playbackState (READY=3, BUFFERING=2, ENDED=4, IDLE=1)")
                if (playbackState == Player.STATE_READY) {
                    loadingStatus = null
                    errorMessage = null
                    val dur = exoPlayer.duration
                    if (dur > 0 && dur != androidx.media3.common.C.TIME_UNSET) {
                        exoDuration = dur
                    }
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

    LaunchedEffect(webStream?.streamUrl) {
        val stream = webStream ?: return@LaunchedEffect
        loadingStatus = "Cargando stream web de AnimeAV1 (${stream.serverName})..."
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
            }
            if (!headers.containsKey("Origin")) {
                headers["Origin"] = "https://animeav1.uns.bio"
            }
            headers["Accept"] = "*/*"

            val dataSourceFactory = DefaultHttpDataSource.Factory()
                .setUserAgent(headers["User-Agent"]!!)
                .setDefaultRequestProperties(headers)
                .setAllowCrossProtocolRedirects(true)
                .setConnectTimeoutMs(30_000)
                .setReadTimeoutMs(30_000)

            val isHlsStream = stream.isHls ||
                stream.streamUrl.contains(".m3u8", ignoreCase = true) ||
                stream.streamUrl.contains("/pl/", ignoreCase = true) ||
                stream.streamUrl.contains("master", ignoreCase = true)

            val mediaItem = MediaItem.Builder()
                .setUri(uri)
                .setMimeType(if (isHlsStream) MimeTypes.APPLICATION_M3U8 else MimeTypes.VIDEO_MP4)
                .build()

            val mediaSource = if (isHlsStream) {
                HlsMediaSource.Factory(dataSourceFactory)
                    .setAllowChunklessPreparation(false)
                    .createMediaSource(mediaItem)
            } else {
                ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaItem)
            }

            exoPlayer?.stop()
            exoPlayer?.clearMediaItems()
            exoPlayer?.setMediaSource(mediaSource)
            exoPlayer?.prepare()
            exoPlayer?.playWhenReady = true
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            logger.error("Error al reproducir stream web: ${e.message}", e)
            errorMessage = "Error al reproducir stream: ${e.localizedMessage ?: e::class.simpleName}"
            loadingStatus = null
        }
    }

    LaunchedEffect(torrent?.magnetUrl) {
        val currentTorrent = torrent ?: return@LaunchedEffect
        loadingStatus = "Conectando con la red BitTorrent..."
        errorMessage = null
        try {
            logger.info("Iniciando descarga torrent para: ${currentTorrent.title}")
            val torrentManager = TorrentManager.getInstance(context)
            val downloader = torrentManager.downloader

            loadingStatus = "Obteniendo metadatos del torrent..."
            val directTorrentUrl = currentTorrent.torrentUrl.takeIf { it.startsWith("http", ignoreCase = true) }
            val encodedInfo = if (directTorrentUrl != null) {
                runCatching {
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
                app.yokan.media.CacheStorageManager.getInstance(context).recordCachedTorrent(
                    title = targetFile.fileName.ifBlank { currentTorrent.title },
                    magnetUrl = currentTorrent.magnetUrl,
                    torrentUrl = currentTorrent.torrentUrl,
                    fileSizeBytes = targetFile.length,
                )
            }

            loadingStatus = "Iniciando búfer de video..."
            player.setMediaData(mediaData, playWhenReady = true)
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

    LaunchedEffect(playWhenReady, isFullscreen) {
        if (isFullscreen && playWhenReady) {
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
                            if (onChangeSource != null) {
                                OutlinedButton(
                                    onClick = onChangeSource,
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = Color.White,
                                        containerColor = Color.Black.copy(alpha = 0.5f)
                                    ),
                                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.7f)),
                                    shape = RoundedCornerShape(16.dp),
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                                    modifier = Modifier.padding(end = 4.dp)
                                ) {
                                    Icon(
                                        Icons.Rounded.SwapHoriz,
                                        contentDescription = "Cambiar fuente",
                                        modifier = Modifier.size(16.dp),
                                        tint = Color.White,
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Cambiar fuente",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White,
                                    )
                                }
                            }
                            IconButton(onClick = {
                                val current = if (exoPosition > 0L) exoPosition else player.currentPositionMillis.value
                                val target = (current + 85_000L).coerceIn(0L, if (exoDuration > 0L) exoDuration else Long.MAX_VALUE)
                                exoPlayer?.seekTo(target)
                                player.skip(85_000L)
                            }) {
                                Icon(AniIcons.Forward85, contentDescription = "+85s", tint = Color.White)
                            }
                            IconButton(onClick = {
                                val current = if (exoPosition > 0L) exoPosition else player.currentPositionMillis.value
                                val target = (current + 90_000L).coerceIn(0L, if (exoDuration > 0L) exoDuration else Long.MAX_VALUE)
                                exoPlayer?.seekTo(target)
                                player.skip(90_000L)
                            }) {
                                Icon(AniIcons.Forward90, contentDescription = "+90s", tint = Color.White)
                            }
                            IconButton(onClick = { showPlayerStats = !showPlayerStats }) {
                                Icon(
                                    Icons.Outlined.Analytics,
                                    contentDescription = "Estadísticas",
                                    tint = if (showPlayerStats) MaterialTheme.colorScheme.primary else Color.White,
                                )
                            }
                        }
                    )
                },
                centerOverlay = {
                    if (isFullscreen) {
                        SystemTime()
                    }
                },
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
                        onTogglePauseResume = {
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
                        },
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
                    if (isBuffering || status != null) {
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
                    PlayerControllerBar(
                        startActions = {
                            PlayerControllerDefaults.PlaybackIcon(
                                isPlaying = { playWhenReady },
                                onClick = { player.togglePlayWhenReady() },
                            )
                        },
                        progressIndicator = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                MediaProgressIndicatorText(
                                    state = progressSliderState,
                                    playbackSpeedState = playbackSpeedControllerState,
                                )
                                cachePercentText?.let { cacheText ->
                                    Text(
                                        text = cacheText,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.White.copy(alpha = 0.9f),
                                            fontSize = 11.sp,
                                        ),
                                        modifier = Modifier
                                            .background(
                                                color = Color.Black.copy(alpha = 0.5f),
                                                shape = RoundedCornerShape(4.dp),
                                            )
                                            .padding(horizontal = 6.dp, vertical = 2.dp),
                                    )
                                }
                            }
                        },
                        progressSlider = {
                            MediaProgressSlider(
                                state = progressSliderState,
                                cacheProgressInfoFlow = { cacheProgressInfo },
                                showPreviewTimeTextOnThumb = isFullscreen,
                                framePreview = null,
                                showFramePreviewInPopup = isFullscreen,
                                touchSeekState = touchSeekState,
                            )
                        },
                        danmakuEditor = {},
                        endActions = {
                            if (isFullscreen) {
                                // Selector de pistas de audio estilo Animeko
                                player.audioTracks?.let {
                                    PlayerControllerDefaults.AudioSwitcher(it)
                                }

                                // Selector de subtítulos estilo Animeko
                                player.subtitleTracks?.let {
                                    PlayerControllerDefaults.SubtitleSwitcher(it)
                                }

                                // Selector de proporción de aspecto (Fit, Stretch, Crop)
                                val videoAspectRatioAlwaysOnRequester =
                                    rememberAlwaysOnRequester(controllerState, "videoAspectRatioSelector")
                                videoAspectRatioControllerState?.also { controller ->
                                    PlayerControllerDefaults.VideoAspectRatioSelector(controller) {
                                        if (it) {
                                            videoAspectRatioAlwaysOnRequester.request()
                                        } else {
                                            videoAspectRatioAlwaysOnRequester.cancelRequest()
                                        }
                                    }
                                }

                                // Selector de velocidad de reproducción (0.5x .. 2.5x)
                                val playbackSpeedAlwaysOnRequester =
                                    rememberAlwaysOnRequester(controllerState, "speedSwitcher")
                                playbackSpeedControllerState?.also { controller ->
                                    PlayerControllerDefaults.SpeedSwitcher(controller) {
                                        if (it) {
                                            playbackSpeedAlwaysOnRequester.request()
                                        } else {
                                            playbackSpeedAlwaysOnRequester.cancelRequest()
                                        }
                                    }
                                }
                            }

                            PlayerControllerDefaults.FullscreenIcon(
                                fullscreenState = fullscreenState,
                            )
                        },
                        expanded = isFullscreen,
                        sliderOnly = controllerState.visibility == ControllerVisibility.InlineSliderOnly,
                    )
                }
            )

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
