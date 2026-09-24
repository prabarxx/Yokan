package app.yokan.ui.screens

import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
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
import app.yokan.datasource.nyaa.NyaaTorrent
import kotlinx.coroutines.flow.map
import me.him188.ani.app.platform.features.StreamType
import me.him188.ani.app.platform.features.getComponentAccessorsImpl
import me.him188.ani.app.platform.findActivity
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
import org.openani.mediamp.source.UriMediaData

@Composable
fun VideoPlayerScreen(
    torrent: NyaaTorrent,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val activity = remember(context) { context.findActivity() }

    val player = remember {
        LibassExoPlayerMediampPlayer(context, coroutineScope.coroutineContext)
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
            player.close()
        }
    }

    BackHandler {
        if (isFullscreen) {
            fullscreenState.request(false)
        }
        onBack()
    }

    LaunchedEffect(torrent.magnetUrl) {
        player.setMediaData(UriMediaData(torrent.magnetUrl), playWhenReady = true)
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
                if (isBuffering) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color.White)
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
    }
}
