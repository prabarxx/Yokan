package app.yokan.ui.screens

import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.yokan.datasource.nyaa.NyaaTorrent
import app.yokan.media.TorrentManager
import app.yokan.media.TorrentMediaData
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import me.him188.ani.app.platform.features.StreamType
import me.him188.ani.app.platform.features.getComponentAccessorsImpl
import me.him188.ani.app.platform.findActivity
import me.him188.ani.app.torrent.api.files.FilePriority
import me.him188.ani.app.videoplayer.media.LibassExoPlayerMediampPlayer
import me.him188.ani.app.videoplayer.ui.VideoPlayer
import me.him188.ani.app.videoplayer.ui.VideoScaffold
import me.him188.ani.app.videoplayer.ui.gesture.GestureLock
import me.him188.ani.app.videoplayer.ui.gesture.LockableVideoGestureHost
import me.him188.ani.app.videoplayer.ui.gesture.NoOpLevelController
import me.him188.ani.app.videoplayer.ui.gesture.asLevelController
import me.him188.ani.app.videoplayer.ui.gesture.rememberSwipeSeekerState
import me.him188.ani.app.videoplayer.ui.progress.MediaProgressIndicatorText
import me.him188.ani.app.videoplayer.ui.progress.MediaProgressSlider
import me.him188.ani.app.videoplayer.ui.progress.PlayerControllerBar
import me.him188.ani.app.videoplayer.ui.progress.PlayerControllerDefaults
import me.him188.ani.app.videoplayer.ui.progress.rememberMediaProgressSliderState
import me.him188.ani.app.videoplayer.ui.rememberPlayerFullscreenState
import me.him188.ani.app.videoplayer.ui.rememberVideoControllerState
import me.him188.ani.app.videoplayer.ui.top.PlayerTopBar
import me.him188.ani.app.videoplayer.ui.top.SystemTime
import me.him188.ani.utils.logging.error
import me.him188.ani.utils.logging.info
import me.him188.ani.utils.logging.logger
import kotlin.time.Duration.Companion.seconds

private val logger = logger("VideoPlayerScreen")

@Composable
fun VideoPlayerScreen(
    torrent: NyaaTorrent,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val activity = remember(context) { context.findActivity() }

    var errorMessage by remember { mutableStateOf<String?>(null) }
    var loadingStatus by remember { mutableStateOf<String?>("Conectando con la red BitTorrent...") }
    var currentMediaData by remember { mutableStateOf<TorrentMediaData?>(null) }

    val coroutineExceptionHandler = remember {
        CoroutineExceptionHandler { _, throwable ->
            logger.error(throwable) { "Error inesperado en corrutina del reproductor" }
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

    val fullscreenState = rememberPlayerFullscreenState(
        isFullscreen = { isFullscreen },
        onRequest = { requestedFullscreen ->
            isFullscreen = requestedFullscreen
            activity?.requestedOrientation = if (requestedFullscreen) {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_USER
            }
        }
    )

    // Force landscape on start, restore on exit
    DisposableEffect(activity) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
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

    LaunchedEffect(torrent.magnetUrl) {
        loadingStatus = "Conectando con la red BitTorrent..."
        errorMessage = null
        try {
            logger.info("Iniciando descarga torrent para: ${torrent.title}")
            val torrentManager = TorrentManager.getInstance(context)
            val downloader = torrentManager.downloader

            loadingStatus = "Obteniendo metadatos del torrent..."
            val encodedInfo = downloader.fetchTorrent(torrent.magnetUrl)

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
            val targetFile = videoFiles.maxByOrNull { it.length } ?: files.maxByOrNull { it.length }

            if (targetFile == null || targetFile.length <= 0) {
                errorMessage = "No se encontró ningún archivo de video en este torrent."
                loadingStatus = null
                return@LaunchedEffect
            }

            loadingStatus = "Preparando streaming: ${targetFile.fileName}..."
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

            loadingStatus = "Iniciando búfer de video..."
            player.setMediaData(mediaData, playWhenReady = true)
            loadingStatus = null
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            logger.error("Error al inicializar el torrent: ${e.message}", e)
            errorMessage = "Error al reproducir torrent: ${e.localizedMessage ?: e::class.simpleName}"
            loadingStatus = null
        }
    }

    val controllerState = rememberVideoControllerState()
    val platformComponents = remember(context) { getComponentAccessorsImpl(context) }
    val audioController = remember(platformComponents) {
        platformComponents.audioManager?.asLevelController(StreamType.MUSIC) ?: NoOpLevelController
    }
    val brightnessController = remember(platformComponents) {
        platformComponents.brightnessManager?.asLevelController() ?: NoOpLevelController
    }

    val progressSliderState = rememberMediaProgressSliderState(
        player = player,
        onPreview = {},
        onPreviewFinished = { player.seekTo(it) },
    )

    val playWhenReady by remember(player) { player.state.map { it.playWhenReady } }
        .collectAsState(false)

    val isBuffering by remember(player) { player.state.map { it.isBuffering } }
        .collectAsState(false)

    Box(modifier = Modifier.fillMaxSize()) {
        VideoScaffold(
            expanded = isFullscreen,
            modifier = Modifier.fillMaxSize(),
            maintainAspectRatio = false,
            controllerState = controllerState,
            gestureLocked = isLocked,
            topBar = {
                PlayerTopBar(
                    title = {
                        Text(
                            text = torrent.title,
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    },
                )
            },
            centerOverlay = {
                SystemTime()
            },
            video = {
                VideoPlayer(
                    player = player,
                    modifier = Modifier.fillMaxSize(),
                )
            },
            gestureHost = {
                val swipeSeekerState = rememberSwipeSeekerState(
                    screenWidthPx = constraints.maxWidth,
                ) { offsetSeconds ->
                    player.skip(offsetSeconds * 1000L)
                }

                LockableVideoGestureHost(
                    controllerState = controllerState,
                    seekerState = swipeSeekerState,
                    progressSliderState = progressSliderState,
                    playerState = player,
                    locked = isLocked,
                    enableSwipeToSeek = true,
                    audioController = audioController,
                    brightnessController = brightnessController,
                    playbackSpeedControllerState = null,
                    fullscreenState = fullscreenState,
                    modifier = Modifier.fillMaxSize(),
                    onTogglePauseResume = {
                        if (player.state.value.playWhenReady) {
                            player.pause()
                        } else {
                            player.play()
                        }
                    },
                )
            },
            gestureLock = {
                GestureLock(
                    isLocked = isLocked,
                    onClick = { isLocked = !isLocked },
                )
            },
            floatingMessage = {
                val status = loadingStatus
                if (isBuffering || status != null) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(24.dp)
                        ) {
                            CircularProgressIndicator(color = Color.White)
                            if (status != null) {
                                Text(
                                    text = status,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                PlayerControllerBar(
                    startActions = {
                        PlayerControllerDefaults.PlaybackIcon(
                            isPlaying = { playWhenReady },
                            onClick = {
                                if (playWhenReady) player.pause() else player.play()
                            },
                        )
                    },
                    progressIndicator = {
                        MediaProgressIndicatorText(
                            state = progressSliderState,
                        )
                    },
                    progressSlider = {
                        MediaProgressSlider(
                            state = progressSliderState,
                            cacheProgressInfoFlow = { null },
                        )
                    },
                    danmakuEditor = {},
                    endActions = {
                        PlayerControllerDefaults.FullscreenIcon(
                            fullscreenState = fullscreenState,
                        )
                    },
                    expanded = isFullscreen,
                )
            }
        )

        // Diálogo / Pantalla de error amigable
        if (errorMessage != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.92f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(
                        text = "Error al reproducir",
                        color = Color(0xFFFF5252),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = errorMessage ?: "",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )
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
