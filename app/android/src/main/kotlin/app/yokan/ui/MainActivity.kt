package app.yokan.ui

import android.app.PictureInPictureParams
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DownloadDone
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import app.yokan.anilist.client.AniListClient
import app.yokan.anilist.model.AniListMedia
import app.yokan.datasource.animeav1.AnimeAV1Client
import app.yokan.datasource.aniskip.AniSkipClient
import app.yokan.datasource.nyaa.NyaaSearchEngine
import app.yokan.datasource.nyaa.NyaaTorrent
import app.yokan.media.WatchHistoryManager
import app.yokan.settings.YokanSettingsManager
import app.yokan.ui.screens.AnimeDetailsScreen
import app.yokan.ui.screens.CacheManagementScreen
import app.yokan.ui.screens.EpisodeScreen
import app.yokan.ui.screens.HomeScreen
import app.yokan.ui.screens.ScheduleScreen
import app.yokan.ui.screens.SettingsScreen
import app.yokan.ui.screens.VideoPlayerScreen
import app.yokan.ui.state.HomeCache
import io.ktor.client.HttpClient
import me.him188.ani.app.ui.adaptive.navigation.AniNavigationSuite
import me.him188.ani.app.ui.adaptive.navigation.AniNavigationSuiteLayout
import me.him188.ani.app.ui.foundation.ImageViewer
import me.him188.ani.app.ui.foundation.LocalImageViewerHandler
import me.him188.ani.app.ui.foundation.LocalSketch
import me.him188.ani.app.ui.foundation.rememberAniSketchInstance
import me.him188.ani.utils.ktor.asScopedHttpClient
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val aniListClient: AniListClient by inject()
    private val nyaaSearchEngine: NyaaSearchEngine by inject()
    private val animeAV1Client: AnimeAV1Client by inject()
    private val aniSkipClient: AniSkipClient by inject()
    private val httpClient: HttpClient by inject()

    private val isInPipModeState = mutableStateOf(false)

    @Volatile
    private var isVideoPlaybackActive: Boolean = false

    private val isPipSupported: Boolean by lazy {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buildPipParams(autoEnter: Boolean): PictureInPictureParams {
        val builder = PictureInPictureParams.Builder()
            .setAspectRatio(Rational(16, 9))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            builder.setAutoEnterEnabled(autoEnter)
            builder.setSeamlessResizeEnabled(true)
        }
        return builder.build()
    }

    private fun updatePipAutoEnter(active: Boolean) {
        isVideoPlaybackActive = active
        if (isPipSupported && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            runCatching {
                setPictureInPictureParams(buildPipParams(autoEnter = active))
            }
        }
    }

    private fun enterPipModeIfSupported(): Boolean {
        if (!isPipSupported || Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return false
        return runCatching {
            enterPictureInPictureMode(buildPipParams(autoEnter = isVideoPlaybackActive))
        }.getOrDefault(false)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (isVideoPlaybackActive &&
            YokanSettingsManager.getInstance(this).settings.value.enablePip
        ) {
            enterPipModeIfSupported()
        }
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration,
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        isInPipModeState.value = isInPictureInPictureMode
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val scopedClient = remember { httpClient.asScopedHttpClient() }
            val sketch = rememberAniSketchInstance(scopedClient)
            val isInPipMode = isInPipModeState.value
            val pipController = remember(isPipSupported, isInPipMode) {
                PipController(
                    isSupported = isPipSupported,
                    isInPipMode = isInPipMode,
                    onUpdatePlaybackActive = { updatePipAutoEnter(it) },
                    onEnterPip = { enterPipModeIfSupported() },
                )
            }
            CompositionLocalProvider(
                LocalSketch provides sketch,
                LocalPipController provides pipController,
            ) {
                YokanTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        YokanApp(
                            aniListClient = aniListClient,
                            nyaaSearchEngine = nyaaSearchEngine,
                            animeAV1Client = animeAV1Client,
                            aniSkipClient = aniSkipClient,
                        )
                    }
                }
            }
        }
    }
}

private sealed interface Screen {
    data object Home : Screen
    data object Cache : Screen
    data object Settings : Screen
    data class Schedule(val previousScreen: Screen = Home) : Screen
    data class Details(val anime: AniListMedia, val previousScreen: Screen? = null) : Screen
    data class Episode(
        val anime: AniListMedia,
        val episode: Int,
        val previousScreen: Screen,
    ) : Screen
    data class StandaloneTorrent(
        val torrent: NyaaTorrent,
        val previousScreen: Screen,
    ) : Screen
}

@Composable
private fun YokanApp(
    aniListClient: AniListClient,
    nyaaSearchEngine: NyaaSearchEngine,
    animeAV1Client: AnimeAV1Client,
    aniSkipClient: AniSkipClient,
) {
    val context = LocalContext.current
    val watchHistoryManager = remember { WatchHistoryManager.getInstance(context) }
    val preferDub by watchHistoryManager.preferDub.collectAsState()
    val imageViewer = LocalImageViewerHandler.current

    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var selectedNavTab by remember { mutableStateOf(0) }

    BackHandler(enabled = imageViewer.viewing.value) {
        imageViewer.clear()
    }

    BackHandler(
        enabled = !imageViewer.viewing.value && (
            currentScreen !is Screen.Home ||
                (currentScreen is Screen.Home && (selectedNavTab == 1 || HomeCache.isSearching))
            ),
    ) {
        when (val screen = currentScreen) {
            is Screen.Home -> {
                if (selectedNavTab == 1 || HomeCache.isSearching) {
                    selectedNavTab = 0
                    HomeCache.isSearching = false
                }
            }
            is Screen.Cache -> {
                selectedNavTab = 0
                currentScreen = Screen.Home
            }
            is Screen.Settings -> {
                selectedNavTab = 0
                currentScreen = Screen.Home
            }
            is Screen.Schedule -> currentScreen = screen.previousScreen
            is Screen.Details -> currentScreen = screen.previousScreen ?: Screen.Home
            is Screen.Episode -> currentScreen = screen.previousScreen
            is Screen.StandaloneTorrent -> currentScreen = screen.previousScreen
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val screen = currentScreen) {
            is Screen.Episode -> {
                key(screen.anime.id) {
                    EpisodeScreen(
                        initialAnime = screen.anime,
                        initialEpisode = screen.episode,
                        aniListClient = aniListClient,
                        nyaaSearchEngine = nyaaSearchEngine,
                        animeAV1Client = animeAV1Client,
                        aniSkipClient = aniSkipClient,
                        onNavigateAnime = { relatedAnime ->
                            currentScreen = Screen.Details(anime = relatedAnime, previousScreen = screen)
                        },
                        onBack = {
                            currentScreen = screen.previousScreen
                        },
                    )
                }
            }

            is Screen.Schedule -> {
                ScheduleScreen(
                    aniListClient = aniListClient,
                    onAnimeClick = { anime ->
                        currentScreen = Screen.Details(anime = anime, previousScreen = screen)
                    },
                    onEpisodeClick = { anime, episode ->
                        currentScreen = Screen.Episode(
                            anime = anime,
                            episode = episode,
                            previousScreen = screen,
                        )
                    },
                    onBack = { currentScreen = screen.previousScreen },
                )
            }

            is Screen.StandaloneTorrent -> {
                VideoPlayerScreen(
                    torrent = screen.torrent,
                    isFullscreen = true,
                    onBack = { currentScreen = screen.previousScreen },
                )
            }

            is Screen.Details -> {
                key(screen.anime.id) {
                    LaunchedEffect(screen.anime.id, preferDub) {
                        val episodeToPrefetch = watchHistoryManager.getProgress(screen.anime.id)
                            ?.episode
                            ?.takeIf { it > 0 }
                            ?: 1
                        animeAV1Client.prefetchEpisode(
                            romajiTitle = screen.anime.title.romaji,
                            englishTitle = screen.anime.title.english,
                            episodeNumber = episodeToPrefetch,
                            preferDub = preferDub,
                        )
                    }
                    AnimeDetailsScreen(
                        animeId = screen.anime.id,
                        initialAnime = screen.anime,
                        aniListClient = aniListClient,
                        animeAV1Client = animeAV1Client,
                        onAnimeClick = { relatedAnime ->
                            currentScreen = Screen.Details(anime = relatedAnime, previousScreen = screen)
                        },
                        onEpisodeClick = { anime, episode ->
                            currentScreen = Screen.Episode(
                                anime = anime,
                                episode = episode,
                                previousScreen = screen,
                            )
                        },
                        onBack = {
                            currentScreen = screen.previousScreen ?: Screen.Home
                        },
                    )
                }
            }

            is Screen.Home, is Screen.Cache, is Screen.Settings -> {
                AniNavigationSuiteLayout(
                    navigationSuite = {
                        AniNavigationSuite {
                            item(
                                selected = selectedNavTab == 0 && currentScreen is Screen.Home && !HomeCache.isSearching,
                                onClick = {
                                    selectedNavTab = 0
                                    HomeCache.isSearching = false
                                    currentScreen = Screen.Home
                                },
                                icon = { Icon(Icons.Rounded.Explore, contentDescription = "Explorar") },
                                label = { Text("Explorar") },
                            )
                            item(
                                selected = (selectedNavTab == 1 && currentScreen is Screen.Home) || (currentScreen is Screen.Home && HomeCache.isSearching),
                                onClick = {
                                    selectedNavTab = 1
                                    HomeCache.isSearching = true
                                    currentScreen = Screen.Home
                                },
                                icon = { Icon(Icons.Rounded.Search, contentDescription = "Buscar") },
                                label = { Text("Buscar") },
                            )
                            item(
                                selected = selectedNavTab == 2 && currentScreen is Screen.Cache,
                                onClick = {
                                    selectedNavTab = 2
                                    currentScreen = Screen.Cache
                                },
                                icon = { Icon(Icons.Rounded.DownloadDone, contentDescription = "Caché") },
                                label = { Text("Caché") },
                            )
                            item(
                                selected = selectedNavTab == 3 && currentScreen is Screen.Settings,
                                onClick = {
                                    selectedNavTab = 3
                                    currentScreen = Screen.Settings
                                },
                                icon = { Icon(Icons.Rounded.Settings, contentDescription = "Ajustes") },
                                label = { Text("Ajustes") },
                            )
                        }
                    }
                ) {
                    when (screen) {
                        is Screen.Home -> {
                            HomeScreen(
                                aniListClient = aniListClient,
                                onAnimeClick = { anime ->
                                    currentScreen = Screen.Details(anime = anime, previousScreen = Screen.Home)
                                },
                                onEpisodeClick = { anime, episode ->
                                    currentScreen = Screen.Episode(
                                        anime = anime,
                                        episode = episode,
                                        previousScreen = Screen.Home,
                                    )
                                },
                                onOpenSchedule = {
                                    currentScreen = Screen.Schedule(previousScreen = Screen.Home)
                                },
                                isSearchTab = selectedNavTab == 1 || HomeCache.isSearching,
                                onSearchModeChange = { active ->
                                    selectedNavTab = if (active) 1 else 0
                                },
                            )
                        }

                        is Screen.Cache -> {
                            CacheManagementScreen(
                                onPlayTorrent = { torrent ->
                                    currentScreen = Screen.StandaloneTorrent(
                                        torrent = torrent,
                                        previousScreen = Screen.Cache,
                                    )
                                }
                            )
                        }

                        is Screen.Settings -> {
                            SettingsScreen()
                        }

                        else -> Unit
                    }
                }
            }
        }

        ImageViewer(handler = imageViewer) { imageViewer.clear() }
    }
}
