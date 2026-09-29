package app.yokan.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DownloadDone
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.yokan.anilist.client.AniListClient
import app.yokan.anilist.model.AniListMedia
import app.yokan.datasource.animeav1.AnimeAV1Client
import app.yokan.datasource.animeav1.WebStreamSource
import app.yokan.datasource.nyaa.NyaaSearchEngine
import app.yokan.datasource.nyaa.NyaaTorrent
import app.yokan.ui.screens.AnimeDetailsScreen
import app.yokan.ui.screens.CacheManagementScreen
import app.yokan.ui.screens.HomeScreen
import app.yokan.ui.screens.TorrentSelectionModal
import app.yokan.ui.screens.VideoPlayerScreen
import app.yokan.ui.state.HomeCache
import app.yokan.media.WatchHistoryManager
import androidx.compose.ui.platform.LocalContext
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
    private val animeAV1Client: AnimeAV1Client by inject()
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
                            animeAV1Client = animeAV1Client,
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
    data class Player(
        val torrent: NyaaTorrent? = null,
        val webStream: WebStreamSource? = null,
        val anime: AniListMedia? = null,
        val episode: Int? = null,
        val initialPositionMillis: Long = 0L,
        val previousScreen: Screen,
    ) : Screen
}

private data class TorrentModalState(
    val anime: AniListMedia,
    val episode: Int,
)

private data class ResolvingEpisodeState(
    val anime: AniListMedia,
    val episode: Int,
)

@Composable
private fun YokanApp(
    aniListClient: AniListClient,
    nyaaSearchEngine: NyaaSearchEngine,
    animeAV1Client: AnimeAV1Client,
) {
    val context = LocalContext.current
    val watchHistoryManager = remember { WatchHistoryManager.getInstance(context) }

    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var torrentModalState by remember { mutableStateOf<TorrentModalState?>(null) }
    var resolvingEpisodeState by remember { mutableStateOf<ResolvingEpisodeState?>(null) }
    var selectedNavTab by remember { mutableStateOf(0) }

    // Auto-Resolver Híbrido:
    // 1. Intento prioritario por AnimeAV1 (servidor MP4Upload por defecto: el más rápido y estable)
    // 2. Fallback a la red BitTorrent (Nyaa / Erai-raws) si no está disponible en la web
    LaunchedEffect(resolvingEpisodeState) {
        val state = resolvingEpisodeState ?: return@LaunchedEffect
        // Si se pide desde el reproductor (siguiente episodio), "atrás" debe volver a donde estaba antes
        val prev = (currentScreen as? Screen.Player)?.previousScreen ?: currentScreen

        // Look up saved progress for "continuar viendo"
        val savedProgress = watchHistoryManager.getProgress(state.anime.id)
        val savedPositionMillis = if (savedProgress?.episode == state.episode &&
            savedProgress.progressFraction < 0.92f) {
            savedProgress.positionMillis
        } else 0L

        // Intento 1: AnimeAV1 (MP4Upload primero)
        val webStreamResult = animeAV1Client.resolveBestStream(
            romajiTitle = state.anime.title.romaji,
            englishTitle = state.anime.title.english,
            episodeNumber = state.episode,
        )

        val bestWebStream = webStreamResult.getOrNull()
        if (bestWebStream != null) {
            resolvingEpisodeState = null
            currentScreen = Screen.Player(
                webStream = bestWebStream,
                anime = state.anime,
                episode = state.episode,
                initialPositionMillis = savedPositionMillis,
                previousScreen = prev,
            )
            return@LaunchedEffect
        }

        // Intento 2: Nyaa Torrents
        val absEpisode = state.anime.calculateAbsoluteEpisode(state.episode).takeIf { it != state.episode }
        val bestTorrentResult = nyaaSearchEngine.resolveBestTorrent(
            romajiTitle = state.anime.title.romaji,
            englishTitle = state.anime.title.english,
            synonyms = state.anime.synonyms,
            episodeNumber = state.episode,
            absoluteEpisodeNumber = absEpisode,
            totalEpisodes = state.anime.effectiveEpisodesCount,
        )

        bestTorrentResult.fold(
            onSuccess = { bestTorrent ->
                resolvingEpisodeState = null
                if (bestTorrent != null) {
                    currentScreen = Screen.Player(
                        torrent = bestTorrent,
                        anime = state.anime,
                        episode = state.episode,
                        initialPositionMillis = savedPositionMillis,
                        previousScreen = prev,
                    )
                } else {
                    torrentModalState = TorrentModalState(state.anime, state.episode)
                }
            },
            onFailure = {
                resolvingEpisodeState = null
                torrentModalState = TorrentModalState(state.anime, state.episode)
            }
        )
    }

    BackHandler(enabled = currentScreen !is Screen.Home || torrentModalState != null || resolvingEpisodeState != null || (currentScreen is Screen.Home && (selectedNavTab == 1 || HomeCache.isSearching))) {
        if (resolvingEpisodeState != null) {
            resolvingEpisodeState = null
        } else if (torrentModalState != null) {
            torrentModalState = null
        } else {
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
                is Screen.Details -> currentScreen = Screen.Home
                is Screen.Player -> currentScreen = screen.previousScreen
            }
        }
    }

    if (currentScreen is Screen.Player) {
        val playerScreen = currentScreen as Screen.Player
        // Precarga el episodio siguiente mientras se ve el actual
        LaunchedEffect(playerScreen.anime?.id, playerScreen.episode) {
            val anime = playerScreen.anime ?: return@LaunchedEffect
            val ep = playerScreen.episode ?: return@LaunchedEffect
            animeAV1Client.prefetchEpisode(
                romajiTitle = anime.title.romaji,
                englishTitle = anime.title.english,
                episodeNumber = ep + 1,
            )
        }
        // key: recrea el reproductor limpio al cambiar de episodio o de fuente
        key(
            playerScreen.anime?.id,
            playerScreen.episode,
            playerScreen.webStream?.streamUrl,
            playerScreen.torrent?.magnetUrl,
        ) {
        VideoPlayerScreen(
            torrent = playerScreen.torrent,
            webStream = playerScreen.webStream,
            episodeNumber = playerScreen.episode,
            absoluteEpisodeNumber = playerScreen.anime?.let {
                playerScreen.episode?.let { ep -> it.calculateAbsoluteEpisode(ep).takeIf { abs -> abs != ep } }
            },
            initialPositionMillis = playerScreen.initialPositionMillis,
            animeForHistory = playerScreen.anime,
            onChangeSource = if (playerScreen.anime != null && playerScreen.episode != null) {
                {
                    torrentModalState = TorrentModalState(playerScreen.anime, playerScreen.episode)
                }
            } else null,
            onNextEpisode = if (
                playerScreen.anime != null && playerScreen.episode != null &&
                (playerScreen.anime.effectiveEpisodesCount == 0 ||
                    playerScreen.episode < playerScreen.anime.effectiveEpisodesCount)
            ) {
                {
                    resolvingEpisodeState =
                        ResolvingEpisodeState(playerScreen.anime, playerScreen.episode + 1)
                }
            } else null,
            onResolveNextWebSource = if (playerScreen.anime != null && playerScreen.episode != null) {
                { failed, tried ->
                    animeAV1Client.resolveNextStream(
                        romajiTitle = playerScreen.anime.title.romaji,
                        englishTitle = playerScreen.anime.title.english,
                        episodeNumber = playerScreen.episode,
                        triedServers = tried,
                        failed = failed,
                    )
                }
            } else null,
            onBack = {
                currentScreen = playerScreen.previousScreen
            },
        )
        }
    } else {
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
                        isSearchTab = selectedNavTab == 1 || HomeCache.isSearching,
                        onSearchModeChange = { active ->
                            selectedNavTab = if (active) 1 else 0
                        },
                    )
                }
                is Screen.Cache -> {
                    CacheManagementScreen(
                        onPlayTorrent = { torrent ->
                            currentScreen = Screen.Player(
                                torrent = torrent,
                                webStream = null,
                                anime = null,
                                episode = null,
                                previousScreen = Screen.Cache,
                            )
                        }
                    )
                }
                is Screen.Details -> {
                    LaunchedEffect(screen.anime.id) {
                        // Precarga en segundo plano mientras el usuario visualiza los detalles del anime
                        animeAV1Client.prefetchEpisode(
                            romajiTitle = screen.anime.title.romaji,
                            englishTitle = screen.anime.title.english,
                            episodeNumber = 1,
                        )
                    }
                    AnimeDetailsScreen(
                        animeId = screen.anime.id,
                        initialAnime = screen.anime,
                        aniListClient = aniListClient,
                        onEpisodeClick = { anime, episode ->
                            resolvingEpisodeState = ResolvingEpisodeState(anime, episode)
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

    // Modal de resolución automática en progreso
    resolvingEpisodeState?.let { state ->
        Dialog(onDismissRequest = { resolvingEpisodeState = null }) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Seleccionando la mejor fuente...",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "Episodio ${state.episode} • ${state.anime.title.displayTitle}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                    OutlinedButton(
                        onClick = {
                            val anime = state.anime
                            val ep = state.episode
                            resolvingEpisodeState = null
                            torrentModalState = TorrentModalState(anime, ep)
                        }
                    ) {
                        Text("Elegir fuente manualmente")
                    }
                }
            }
        }
    }

    torrentModalState?.let { modal ->
        TorrentSelectionModal(
            anime = modal.anime,
            episodeNumber = modal.episode,
            searchEngine = nyaaSearchEngine,
            animeAV1Client = animeAV1Client,
            onTorrentSelect = { torrent ->
                val prev = if (currentScreen is Screen.Player) {
                    (currentScreen as Screen.Player).previousScreen
                } else {
                    currentScreen
                }
                torrentModalState = null
                currentScreen = Screen.Player(
                    torrent = torrent,
                    webStream = null,
                    anime = modal.anime,
                    episode = modal.episode,
                    previousScreen = prev,
                )
            },
            onWebStreamSelect = { webStream ->
                val prev = if (currentScreen is Screen.Player) {
                    (currentScreen as Screen.Player).previousScreen
                } else {
                    currentScreen
                }
                torrentModalState = null
                currentScreen = Screen.Player(
                    torrent = null,
                    webStream = webStream,
                    anime = modal.anime,
                    episode = modal.episode,
                    previousScreen = prev,
                )
            },
            onDismiss = {
                torrentModalState = null
            },
        )
    }
}
