package app.yokan.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import app.yokan.ui.screens.HomeScreen
import app.yokan.ui.screens.TorrentSelectionModal
import app.yokan.ui.screens.VideoPlayerScreen
import io.ktor.client.HttpClient
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

    BackHandler(enabled = currentScreen !is Screen.Home || torrentModalState != null) {
        if (torrentModalState != null) {
            torrentModalState = null
        } else {
            when (val screen = currentScreen) {
                is Screen.Home -> Unit
                is Screen.Details -> currentScreen = Screen.Home
                is Screen.Player -> currentScreen = screen.previousScreen
            }
        }
    }

    when (val screen = currentScreen) {
        is Screen.Home -> {
            HomeScreen(
                aniListClient = aniListClient,
                onAnimeClick = { anime ->
                    currentScreen = Screen.Details(anime)
                },
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
        is Screen.Player -> {
            VideoPlayerScreen(
                torrent = screen.torrent,
                onBack = {
                    currentScreen = screen.previousScreen
                },
            )
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
