package app.yokan.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DownloadDone
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import app.yokan.anilist.client.AniListClient
import app.yokan.anilist.model.AniListMedia
import app.yokan.datasource.nyaa.NyaaSearchEngine
import app.yokan.datasource.nyaa.NyaaTorrent
import app.yokan.ui.screens.AnimeDetailsScreen
import app.yokan.ui.screens.CacheManagementScreen
import app.yokan.ui.screens.HomeScreen
import app.yokan.ui.screens.TorrentSelectionModal
import app.yokan.ui.screens.VideoPlayerScreen
import io.ktor.client.HttpClient
import me.him188.ani.app.ui.adaptive.navigation.AniNavigationSuite
import me.him188.ani.app.ui.adaptive.navigation.AniNavigationSuiteLayout
import me.him188.ani.app.ui.foundation.LocalSketch
import me.him188.ani.app.ui.foundation.rememberAniSketchInstance
import me.him188.ani.utils.ktor.asScopedHttpClient
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val aniListClient: AniListClient by inject()
    private val nyaaSearchEngine: NyaaSearchEngine by inject()
    private val httpClient: HttpClient by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val scopedClient = remember { httpClient.asScopedHttpClient() }
            val sketch = rememberAniSketchInstance(scopedClient)
            CompositionLocalProvider(
                LocalSketch provides sketch,
            ) {
                YokanTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        YokanApp(
                            aniListClient = aniListClient,
                            nyaaSearchEngine = nyaaSearchEngine,
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
    data class Details(val anime: AniListMedia) : Screen
    data class Player(val torrent: NyaaTorrent, val previousScreen: Screen) : Screen
}

private data class TorrentModalState(
    val anime: AniListMedia,
    val episode: Int,
)

@Composable
private fun YokanApp(
    aniListClient: AniListClient,
    nyaaSearchEngine: NyaaSearchEngine,
) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var torrentModalState by remember { mutableStateOf<TorrentModalState?>(null) }
    var selectedNavTab by remember { mutableStateOf(0) }

    BackHandler(enabled = currentScreen !is Screen.Home || torrentModalState != null) {
        if (torrentModalState != null) {
            torrentModalState = null
        } else {
            when (val screen = currentScreen) {
                is Screen.Home -> Unit
                is Screen.Cache -> {
                    selectedNavTab = 0
                    currentScreen = Screen.Home
                }
                is Screen.Details -> currentScreen = Screen.Home
                is Screen.Player -> currentScreen = screen.previousScreen
            }
        }
    }

    if (currentScreen is Screen.Player) {
        val playerScreen = currentScreen as Screen.Player
        VideoPlayerScreen(
            torrent = playerScreen.torrent,
            onBack = {
                currentScreen = playerScreen.previousScreen
            },
        )
    } else {
        AniNavigationSuiteLayout(
            navigationSuite = {
                AniNavigationSuite {
                    item(
                        selected = selectedNavTab == 0 && currentScreen is Screen.Home,
                        onClick = {
                            selectedNavTab = 0
                            currentScreen = Screen.Home
                        },
                        icon = { Icon(Icons.Rounded.Explore, contentDescription = "Explorar") },
                        label = { Text("Explorar") },
                    )
                    item(
                        selected = selectedNavTab == 1,
                        onClick = {
                            selectedNavTab = 1
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
                }
            }
        ) {
            when (val screen = currentScreen) {
                is Screen.Home -> {
                    HomeScreen(
                        aniListClient = aniListClient,
                        onAnimeClick = { anime ->
                            currentScreen = Screen.Details(anime)
                        },
                    )
                }
                is Screen.Cache -> {
                    CacheManagementScreen(
                        onPlayTorrent = { torrent ->
                            currentScreen = Screen.Player(torrent = torrent, previousScreen = Screen.Cache)
                        }
                    )
                }
                is Screen.Details -> {
                    AnimeDetailsScreen(
                        animeId = screen.anime.id,
                        initialAnime = screen.anime,
                        aniListClient = aniListClient,
                        onEpisodeClick = { anime, episode ->
                            torrentModalState = TorrentModalState(anime, episode)
                        },
                        onBack = {
                            currentScreen = Screen.Home
                        },
                    )
                }
                is Screen.Player -> Unit
            }
        }
    }

    torrentModalState?.let { modal ->
        TorrentSelectionModal(
            anime = modal.anime,
            episodeNumber = modal.episode,
            searchEngine = nyaaSearchEngine,
            onTorrentSelect = { torrent ->
                val prev = currentScreen
                torrentModalState = null
                currentScreen = Screen.Player(torrent = torrent, previousScreen = prev)
            },
            onDismiss = {
                torrentModalState = null
            },
        )
    }
}
