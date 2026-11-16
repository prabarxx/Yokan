package app.yokan.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.CloudDone
import androidx.compose.material.icons.rounded.SyncAlt
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.yokan.anilist.client.AniListClient
import app.yokan.anilist.model.AniListMedia
import app.yokan.anilist.model.AniListRelation
import app.yokan.datasource.animeav1.AnimeAV1Client
import app.yokan.datasource.animeav1.WebStreamSource
import app.yokan.datasource.nyaa.NyaaSearchEngine
import app.yokan.datasource.nyaa.NyaaTorrent
import app.yokan.media.WatchHistoryManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import me.him188.ani.app.ui.foundation.LongClickProgressFill
import me.him188.ani.app.ui.foundation.OutlinedTag
import me.him188.ani.app.ui.foundation.icons.PlayingIcon
import me.him188.ani.app.ui.foundation.input.touchHorizontalScrollOnly
import me.him188.ani.app.ui.foundation.layout.currentWindowAdaptiveInfo1
import me.him188.ani.app.ui.foundation.layout.isWidthAtLeastMedium
import me.him188.ani.app.ui.foundation.pagerTabIndicatorOffset
import me.him188.ani.app.ui.foundation.theme.weaken
import me.him188.ani.app.ui.foundation.widgets.ModalSideSheet
import me.him188.ani.app.ui.foundation.widgets.rememberModalSideSheetState

private const val EPISODE_AUTO_RESOLVE_TIMEOUT_MS = 15_000L

private sealed interface EpisodeSourceSummary {
    data object AutoSelecting : EpisodeSourceSummary
    data class Web(val stream: WebStreamSource) : EpisodeSourceSummary
    data class Torrent(val torrent: NyaaTorrent) : EpisodeSourceSummary
    data class ManualRequired(val notice: String) : EpisodeSourceSummary
}

private data class PlayerHostParams(
    val modifier: Modifier,
    val maintainAspectRatio: Boolean,
    val insets: WindowInsets,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeScreen(
    initialAnime: AniListMedia,
    initialEpisode: Int,
    aniListClient: AniListClient,
    nyaaSearchEngine: NyaaSearchEngine,
    animeAV1Client: AnimeAV1Client,
    onNavigateAnime: (AniListMedia) -> Unit,
    onBack: () -> Unit,
    initialTorrent: NyaaTorrent? = null,
    initialWebStream: WebStreamSource? = null,
) {
    val context = LocalContext.current
    val watchHistoryManager = remember { WatchHistoryManager.getInstance(context) }
    val preferDub by watchHistoryManager.preferDub.collectAsState()
    val watchedEpisodesMap by watchHistoryManager.watchedEpisodes.collectAsState()
    val watchHistory by watchHistoryManager.allHistory.collectAsState()

    var anime by remember(initialAnime.id) { mutableStateOf(initialAnime) }
    var specialVersions by remember(initialAnime.id) { mutableStateOf<List<AniListRelation>>(emptyList()) }
    var currentEpisode by rememberSaveable(initialAnime.id) { mutableIntStateOf(initialEpisode) }

    var isFullscreen by rememberSaveable { mutableStateOf(false) }
    var activeWebStream by remember { mutableStateOf<WebStreamSource?>(initialWebStream) }
    var activeTorrent by remember { mutableStateOf<NyaaTorrent?>(initialTorrent) }
    var isAutoResolving by remember { mutableStateOf(initialWebStream == null && initialTorrent == null) }
    var manualSelectionNotice by remember { mutableStateOf<String?>(null) }
    var hasBothLanguages by remember { mutableStateOf(false) }

    var showSourceSelector by remember { mutableStateOf(false) }
    var showEpisodeSheet by remember { mutableStateOf(false) }
    var showSubjectDetailsSheet by remember { mutableStateOf(false) }

    // Enriquece detalles de AniList si la navegación vino desde "Continuar viendo" con modelo reducido
    LaunchedEffect(initialAnime.id) {
        if (initialAnime.id > 0 && (anime.description.isNullOrBlank() || anime.recommendations.isEmpty() || anime.studios.isEmpty())) {
            aniListClient.getAnimeDetails(initialAnime.id).onSuccess { details ->
                anime = details
            }
        }
        val specials = animeAV1Client.findSpecialVersions(
            romajiTitle = anime.title.romaji,
            englishTitle = anime.title.english,
            existingRelationIds = anime.relations.map { it.id }.toSet(),
        )
        specialVersions = specials
    }

    // Detecta idiomas disponibles, precarga episodio siguiente y resuelve fuente web automática para currentEpisode
    LaunchedEffect(anime.id, currentEpisode) {
        launch {
            val (hasSub, hasDub) = animeAV1Client.getAvailableLanguages(
                romajiTitle = anime.title.romaji,
                englishTitle = anime.title.english,
                episodeNumber = currentEpisode,
            )
            hasBothLanguages = hasSub && hasDub
            animeAV1Client.prefetchEpisode(
                romajiTitle = anime.title.romaji,
                englishTitle = anime.title.english,
                episodeNumber = currentEpisode + 1,
                preferDub = preferDub,
            )
        }

        if (activeWebStream == null && activeTorrent == null) {
            isAutoResolving = true
            manualSelectionNotice = null
            val res = withTimeoutOrNull(EPISODE_AUTO_RESOLVE_TIMEOUT_MS) {
                animeAV1Client.resolveBestStream(
                    romajiTitle = anime.title.romaji,
                    englishTitle = anime.title.english,
                    episodeNumber = currentEpisode,
                    preferDub = preferDub,
                )
            }
            isAutoResolving = false
            if (res == null) {
                manualSelectionNotice = "La búsqueda automática tardó demasiado. Toca para elegir servidor o torrent."
            } else {
                val best = res.getOrNull()
                if (best != null) {
                    activeWebStream = best
                } else {
                    manualSelectionNotice = "No se encontró stream automático. Toca para elegir fuente manualmente."
                }
            }
        } else {
            isAutoResolving = false
        }
    }

    fun selectEpisode(ep: Int) {
        if (ep == currentEpisode) return
        activeWebStream = null
        activeTorrent = null
        manualSelectionNotice = null
        isAutoResolving = true
        currentEpisode = ep
    }

    val totalEpisodes = anime.effectiveEpisodesCount
    val latestAired = anime.latestAiredEpisode
    val hasNextEpisode = (totalEpisodes == 0 || currentEpisode < totalEpisodes) &&
        (latestAired == 0 || currentEpisode < latestAired)

    val currentEpisodeSubtitle = remember(anime.streamingEpisodes, currentEpisode) {
        anime.streamingEpisodes.firstOrNull { it.episodeNumber == currentEpisode }
            ?.title
            ?.replace(Regex("^(?:Episode|Ep|Capítulo|Cap)\\s*\\d+\\s*[-:]?\\s*", RegexOption.IGNORE_CASE), "")
            ?.trim()
            ?.takeIf { it.isNotBlank() }
    }

    val savedInitialPositionMillis = remember(anime.id, currentEpisode) {
        val progress = watchHistoryManager.getProgress(anime.id)
        if (progress?.episode == currentEpisode && progress.progressFraction < 0.92f) {
            progress.positionMillis
        } else {
            0L
        }
    }

    val sourceSummary: EpisodeSourceSummary = when {
        activeWebStream != null -> EpisodeSourceSummary.Web(activeWebStream!!)
        activeTorrent != null -> EpisodeSourceSummary.Torrent(activeTorrent!!)
        isAutoResolving -> EpisodeSourceSummary.AutoSelecting
        else -> EpisodeSourceSummary.ManualRequired(
            manualSelectionNotice ?: "Toca para seleccionar un servidor o torrent",
        )
    }

    val watchedEpisodes = remember(watchedEpisodesMap, anime.id) {
        watchedEpisodesMap[anime.id].orEmpty()
    }
    val allRelations = remember(anime.relations, specialVersions) {
        (anime.relations + specialVersions).distinctBy { it.id }
    }

    // Usamos movableContentOf sobre el reproductor por (anime.id, currentEpisode) para que alternar
    // pantalla completa o rotar no recree ni reinicie ExoPlayer / TorrentMediaData.
    val playerHost = remember(anime.id, currentEpisode) {
        movableContentOf<PlayerHostParams> { params ->
            VideoPlayerScreen(
                torrent = activeTorrent,
                webStream = activeWebStream,
                episodeNumber = currentEpisode,
                absoluteEpisodeNumber = anime.calculateAbsoluteEpisode(currentEpisode).takeIf { it != currentEpisode },
                episodeTitle = currentEpisodeSubtitle,
                initialPositionMillis = savedInitialPositionMillis,
                animeForHistory = anime,
                hasBothLanguages = hasBothLanguages,
                isFullscreen = isFullscreen,
                onFullscreenChange = { isFullscreen = it },
                maintainAspectRatio = params.maintainAspectRatio,
                windowInsets = params.insets,
                externalLoadingStatus = if (isAutoResolving) "Buscando la mejor fuente..." else null,
                externalErrorMessage = if (!isAutoResolving) manualSelectionNotice else null,
                onOpenEpisodeList = { showEpisodeSheet = true },
                onActiveStreamResolved = { stream ->
                    activeWebStream = stream
                    manualSelectionNotice = null
                },
                onToggleAudioLanguage = { targetDub ->
                    val resolved = animeAV1Client.resolveBestStream(
                        romajiTitle = anime.title.romaji,
                        englishTitle = anime.title.english,
                        episodeNumber = currentEpisode,
                        preferDub = targetDub,
                    ).getOrNull()
                    if (resolved != null && resolved.isDub == targetDub) {
                        watchHistoryManager.setPreferDub(targetDub)
                        resolved
                    } else {
                        null
                    }
                },
                onChangeSource = { showSourceSelector = true },
                onNextEpisode = if (hasNextEpisode) {
                    { selectEpisode(currentEpisode + 1) }
                } else null,
                onResolveNextWebSource = { failed, tried ->
                    animeAV1Client.resolveNextStream(
                        romajiTitle = anime.title.romaji,
                        englishTitle = anime.title.english,
                        episodeNumber = currentEpisode,
                        triedServers = tried,
                        failed = failed,
                        preferDub = failed.isDub,
                    )
                },
                rhsSheet = {
                    if (isFullscreen && showSourceSelector) {
                        val sideSheetState = rememberModalSideSheetState()
                        ModalSideSheet(
                            onDismiss = { showSourceSelector = false },
                            state = sideSheetState,
                            containerColor = BottomSheetDefaults.ContainerColor,
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .widthIn(320.dp, 420.dp)
                                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical + WindowInsetsSides.End)),
                            ) {
                                TopAppBar(
                                    title = { Text("Seleccionar fuente") },
                                    actions = {
                                        IconButton(onClick = { sideSheetState.close() }) {
                                            Icon(Icons.Outlined.Close, contentDescription = "Cerrar")
                                        }
                                    },
                                    colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = BottomSheetDefaults.ContainerColor,
                                    ),
                                    windowInsets = WindowInsets(0),
                                )
                                TorrentSelectionContent(
                                    anime = anime,
                                    episodeNumber = currentEpisode,
                                    searchEngine = nyaaSearchEngine,
                                    animeAV1Client = animeAV1Client,
                                    onTorrentSelect = { torrent ->
                                        activeWebStream = null
                                        activeTorrent = torrent
                                        manualSelectionNotice = null
                                        isAutoResolving = false
                                        showSourceSelector = false
                                    },
                                    onWebStreamSelect = { webStream ->
                                        activeTorrent = null
                                        activeWebStream = webStream
                                        manualSelectionNotice = null
                                        isAutoResolving = false
                                        showSourceSelector = false
                                    },
                                    onDismiss = { sideSheetState.close() },
                                    notice = manualSelectionNotice,
                                    showHeader = false,
                                    fillListHeight = true,
                                )
                            }
                        }
                    } else if (isFullscreen && showEpisodeSheet) {
                        val sideSheetState = rememberModalSideSheetState()
                        ModalSideSheet(
                            onDismiss = { showEpisodeSheet = false },
                            state = sideSheetState,
                            containerColor = BottomSheetDefaults.ContainerColor,
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .widthIn(300.dp, 400.dp)
                                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical + WindowInsetsSides.End)),
                            ) {
                                TopAppBar(
                                    title = { Text("Seleccionar episodio") },
                                    actions = {
                                        IconButton(onClick = { sideSheetState.close() }) {
                                            Icon(Icons.Outlined.Close, contentDescription = "Cerrar")
                                        }
                                    },
                                    colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = BottomSheetDefaults.ContainerColor,
                                    ),
                                    windowInsets = WindowInsets(0),
                                )
                                Box(
                                    Modifier
                                        .weight(1f)
                                        .verticalScroll(rememberScrollState())
                                        .padding(16.dp),
                                ) {
                                    AnimeEpisodeFlowGrid(
                                        totalEpisodes = totalEpisodes,
                                        latestAiredEpisode = latestAired,
                                        currentEpisode = currentEpisode,
                                        watchedEpisodes = watchedEpisodes,
                                        onEpisodeClick = { ep ->
                                            selectEpisode(ep)
                                            showEpisodeSheet = false
                                        },
                                        onEpisodeLongClick = { ep ->
                                            watchHistoryManager.toggleEpisodeWatched(anime.id, ep)
                                        },
                                    )
                                }
                            }
                        }
                    }
                },
                modifier = params.modifier,
                onBack = onBack,
            )
        }
    }

    val isWideScreen = currentWindowAdaptiveInfo1().isWidthAtLeastMedium

    when {
        isFullscreen -> {
            playerHost(
                PlayerHostParams(
                    modifier = Modifier.fillMaxSize(),
                    maintainAspectRatio = false,
                    insets = WindowInsets.displayCutout,
                ),
            )
        }

        isWideScreen -> {
            BoxWithConstraints(Modifier.fillMaxSize()) {
                val sidebarWidth = (maxWidth * 0.3f).coerceIn(340.dp, 440.dp)
                Row(Modifier.fillMaxSize()) {
                    playerHost(
                        PlayerHostParams(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            maintainAspectRatio = false,
                            insets = WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical + WindowInsetsSides.Start),
                        ),
                    )
                    EpisodeBodyPane(
                        anime = anime,
                        currentEpisode = currentEpisode,
                        currentEpisodeSubtitle = currentEpisodeSubtitle,
                        totalEpisodes = totalEpisodes,
                        latestAired = latestAired,
                        watchedEpisodes = watchedEpisodes,
                        allRelations = allRelations,
                        sourceSummary = sourceSummary,
                        hasBothLanguages = hasBothLanguages,
                        isDub = activeWebStream?.isDub ?: preferDub,
                        onExpandSubject = { showSubjectDetailsSheet = true },
                        onOpenSourceSelector = { showSourceSelector = true },
                        onOpenEpisodeGrid = { showEpisodeSheet = true },
                        onSelectEpisode = { selectEpisode(it) },
                        onToggleEpisodeWatched = { ep -> watchHistoryManager.toggleEpisodeWatched(anime.id, ep) },
                        onTogglePreferDub = { watchHistoryManager.setPreferDub(it) },
                        onNavigateAnime = onNavigateAnime,
                        modifier = Modifier
                            .width(sidebarWidth)
                            .fillMaxHeight()
                            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top + WindowInsetsSides.End)),
                    )
                }
            }
        }

        else -> {
            // Modo teléfono retrato estilo Animeko: Video 16:9 arriba + TabRow + EpisodeDetails abajo
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                playerHost(
                    PlayerHostParams(
                        modifier = Modifier.fillMaxWidth(),
                        maintainAspectRatio = true,
                        insets = WindowInsets(0),
                    ),
                )
                EpisodeBodyPane(
                    anime = anime,
                    currentEpisode = currentEpisode,
                    currentEpisodeSubtitle = currentEpisodeSubtitle,
                    totalEpisodes = totalEpisodes,
                    latestAired = latestAired,
                    watchedEpisodes = watchedEpisodes,
                    allRelations = allRelations,
                    sourceSummary = sourceSummary,
                    hasBothLanguages = hasBothLanguages,
                    isDub = activeWebStream?.isDub ?: preferDub,
                    onExpandSubject = { showSubjectDetailsSheet = true },
                    onOpenSourceSelector = { showSourceSelector = true },
                    onOpenEpisodeGrid = { showEpisodeSheet = true },
                    onSelectEpisode = { selectEpisode(it) },
                    onToggleEpisodeWatched = { ep -> watchHistoryManager.toggleEpisodeWatched(anime.id, ep) },
                    onTogglePreferDub = { watchHistoryManager.setPreferDub(it) },
                    onNavigateAnime = onNavigateAnime,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                )
            }
        }
    }

    // Hoja inferior de selección de fuente (retrato / pantalla dividida)
    if (!isFullscreen && showSourceSelector) {
        TorrentSelectionModal(
            anime = anime,
            episodeNumber = currentEpisode,
            notice = manualSelectionNotice,
            searchEngine = nyaaSearchEngine,
            animeAV1Client = animeAV1Client,
            onTorrentSelect = { torrent ->
                activeWebStream = null
                activeTorrent = torrent
                manualSelectionNotice = null
                isAutoResolving = false
                showSourceSelector = false
            },
            onWebStreamSelect = { webStream ->
                activeTorrent = null
                activeWebStream = webStream
                manualSelectionNotice = null
                isAutoResolving = false
                showSourceSelector = false
            },
            onDismiss = { showSourceSelector = false },
        )
    }

    // Hoja inferior de cuadrícula completa de episodios (desde '>' de Lista de episodios)
    if (!isFullscreen && showEpisodeSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = { showEpisodeSheet = false },
            sheetState = sheetState,
            contentWindowInsets = {
                BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)
            },
        ) {
            Column {
                TopAppBar(
                    title = { Text("Seleccionar episodio") },
                    windowInsets = WindowInsets(0),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BottomSheetDefaults.ContainerColor,
                    ),
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 460.dp)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    AnimeEpisodeFlowGrid(
                        totalEpisodes = totalEpisodes,
                        latestAiredEpisode = latestAired,
                        currentEpisode = currentEpisode,
                        watchedEpisodes = watchedEpisodes,
                        onEpisodeClick = { ep ->
                            selectEpisode(ep)
                            showEpisodeSheet = false
                        },
                        onEpisodeLongClick = { ep ->
                            watchHistoryManager.toggleEpisodeWatched(anime.id, ep)
                        },
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Lightbulb,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp),
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Mantén pulsado para marcar como visto",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }

    // Hoja inferior con la ficha completa del anime al pulsar el título (igual que Animeko onExpandSubject)
    if (showSubjectDetailsSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val watchProgress = remember(watchHistory, anime.id) {
            watchHistory.firstOrNull { it.animeId == anime.id }
        }
        ModalBottomSheet(
            onDismissRequest = { showSubjectDetailsSheet = false },
            sheetState = sheetState,
            modifier = Modifier.statusBarsPadding(),
            contentWindowInsets = {
                BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)
            },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.92f),
            ) {
                AnimeDetailsPageContent(
                    anime = anime,
                    allRelations = allRelations,
                    watchProgress = watchProgress,
                    watchedEpisodes = watchedEpisodes,
                    preferDub = preferDub,
                    hasDubAvailable = hasBothLanguages,
                    onTogglePreferDub = { watchHistoryManager.setPreferDub(it) },
                    onToggleEpisodeWatched = { ep -> watchHistoryManager.toggleEpisodeWatched(anime.id, ep) },
                    onEpisodeClick = { ep ->
                        showSubjectDetailsSheet = false
                        selectEpisode(ep)
                    },
                    onAnimeClick = { relatedAnime ->
                        showSubjectDetailsSheet = false
                        onNavigateAnime(relatedAnime)
                    },
                    onBack = { showSubjectDetailsSheet = false },
                    showTopBar = false,
                    showBlurredBackground = false,
                )
            }
        }
    }
}

@Composable
private fun EpisodeBodyPane(
    anime: AniListMedia,
    currentEpisode: Int,
    currentEpisodeSubtitle: String?,
    totalEpisodes: Int,
    latestAired: Int,
    watchedEpisodes: Set<Int>,
    allRelations: List<AniListRelation>,
    sourceSummary: EpisodeSourceSummary,
    hasBothLanguages: Boolean,
    isDub: Boolean,
    onExpandSubject: () -> Unit,
    onOpenSourceSelector: () -> Unit,
    onOpenEpisodeGrid: () -> Unit,
    onSelectEpisode: (Int) -> Unit,
    onToggleEpisodeWatched: (Int) -> Unit,
    onTogglePreferDub: (Boolean) -> Unit,
    onNavigateAnime: (AniListMedia) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    val scope = rememberCoroutineScope()

    Column(modifier) {
        Surface(color = MaterialTheme.colorScheme.surfaceContainerLow) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                EpisodeDetailsTabRow(
                    pagerState = pagerState,
                    onSelectTab = { idx -> scope.launch { pagerState.animateScrollToPage(idx) } },
                    modifier = Modifier.weight(1f),
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                )
                Box(
                    modifier = Modifier
                        .height(44.dp)
                        .padding(vertical = 6.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        shape = RoundedCornerShape(50),
                    ) {
                        Text(
                            text = buildString {
                                append("Cap. ${String.format("%02d", currentEpisode)}")
                                if (hasBothLanguages) {
                                    append(if (isDub) " · LAT" else " · SUB")
                                }
                            },
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        )
                    }
                }
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.weaken())

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .touchHorizontalScrollOnly(),
        ) { pageIndex ->
            when (pageIndex) {
                0 -> {
                    EpisodeDetailsTab(
                        anime = anime,
                        currentEpisode = currentEpisode,
                        currentEpisodeSubtitle = currentEpisodeSubtitle,
                        totalEpisodes = totalEpisodes,
                        latestAired = latestAired,
                        watchedEpisodes = watchedEpisodes,
                        allRelations = allRelations,
                        sourceSummary = sourceSummary,
                        onExpandSubject = onExpandSubject,
                        onOpenSourceSelector = onOpenSourceSelector,
                        onOpenEpisodeGrid = onOpenEpisodeGrid,
                        onSelectEpisode = onSelectEpisode,
                        onToggleEpisodeWatched = onToggleEpisodeWatched,
                        onNavigateAnime = onNavigateAnime,
                    )
                }

                1 -> {
                    EpisodeInfoTab(
                        anime = anime,
                        hasBothLanguages = hasBothLanguages,
                        isDub = isDub,
                        onTogglePreferDub = onTogglePreferDub,
                    )
                }
            }
        }
    }
}

@Composable
private fun EpisodeDetailsTabRow(
    pagerState: PagerState,
    onSelectTab: (Int) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
) {
    val tabs = listOf("Detalles", "Información")
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = modifier,
        indicator = @Composable { tabPositions ->
            TabRowDefaults.PrimaryIndicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
            )
        },
        containerColor = containerColor,
        contentColor = MaterialTheme.colorScheme.contentColorFor(containerColor),
        edgePadding = 8.dp,
        divider = {},
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { onSelectTab(index) },
                modifier = Modifier.height(44.dp),
                text = { Text(title, softWrap = false) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
private fun EpisodeDetailsTab(
    anime: AniListMedia,
    currentEpisode: Int,
    currentEpisodeSubtitle: String?,
    totalEpisodes: Int,
    latestAired: Int,
    watchedEpisodes: Set<Int>,
    allRelations: List<AniListRelation>,
    sourceSummary: EpisodeSourceSummary,
    onExpandSubject: () -> Unit,
    onOpenSourceSelector: () -> Unit,
    onOpenEpisodeGrid: () -> Unit,
    onSelectEpisode: (Int) -> Unit,
    onToggleEpisodeWatched: (Int) -> Unit,
    onNavigateAnime: (AniListMedia) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
    ) {
        // 1. Cabecera: Título clicable del anime (abre ficha completa en bottom sheet) + info botón
        item("episode_header") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = onExpandSubject)
                        .padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = anime.title.displayTitle,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = buildString {
                            append("Episodio ${String.format("%02d", currentEpisode)}")
                            currentEpisodeSubtitle?.let { append(" · $it") }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                IconButton(onClick = onExpandSubject) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Detalles del anime",
                    )
                }
            }
        }

        // 2. Banner de estado de fuente estilo Animeko (MediaSelectorSummaryBanner)
        item("source_banner") {
            MediaSelectorSummaryBanner(
                sourceSummary = sourceSummary,
                onClick = onOpenSourceSelector,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp),
            )
        }

        // 3. Lista horizontal de episodios estilo Animeko (NarrowEpisodeListSection + EpisodeCard)
        item("episode_list_section") {
            NarrowEpisodeListSection(
                anime = anime,
                totalEpisodes = totalEpisodes,
                latestAired = latestAired,
                currentEpisode = currentEpisode,
                watchedEpisodes = watchedEpisodes,
                onSelectEpisode = onSelectEpisode,
                onToggleEpisodeWatched = onToggleEpisodeWatched,
                onOpenEpisodeGrid = onOpenEpisodeGrid,
                modifier = Modifier.padding(top = 12.dp),
            )
        }

        // 4. Relacionados & Recomendaciones estilo Animeko
        if (allRelations.isNotEmpty()) {
            item("related_section") {
                RelatedSubjectsSection(
                    title = "Relacionados",
                    relations = allRelations,
                    onAnimeClick = onNavigateAnime,
                    horizontalPadding = 16.dp,
                    modifier = Modifier.padding(top = 20.dp),
                )
            }
        }

        if (anime.recommendations.isNotEmpty()) {
            item("recommendations_section") {
                RelatedSubjectsSection(
                    title = "Recomendaciones",
                    relations = anime.recommendations,
                    onAnimeClick = onNavigateAnime,
                    horizontalPadding = 16.dp,
                    modifier = Modifier.padding(top = 20.dp),
                )
            }
        }

        item("nav_bar_spacer") {
            Spacer(
                Modifier
                    .windowInsetsBottomHeight(WindowInsets.safeDrawing)
                    .heightIn(min = 12.dp),
            )
        }
    }
}

@Composable
private fun MediaSelectorSummaryBanner(
    sourceSummary: EpisodeSourceSummary,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 68.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(Modifier.weight(1f)) {
                AnimatedContent(
                    targetState = sourceSummary,
                    label = "source_summary_transition",
                ) { state ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        when (state) {
                            is EpisodeSourceSummary.AutoSelecting -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 2.dp,
                                )
                                Column {
                                    Text(
                                        text = "Buscando la mejor fuente...",
                                        style = MaterialTheme.typography.titleSmall,
                                    )
                                    Text(
                                        text = "Toca aquí para seleccionar servidor o torrent manualmente",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }

                            is EpisodeSourceSummary.ManualRequired -> {
                                Icon(
                                    imageVector = Icons.Rounded.Warning,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(22.dp),
                                )
                                Column {
                                    Text(
                                        text = "Selección manual requerida",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.error,
                                    )
                                    Text(
                                        text = state.notice,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }

                            is EpisodeSourceSummary.Web -> {
                                Icon(
                                    imageVector = Icons.Rounded.CloudDone,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(22.dp),
                                )
                                Column {
                                    Text(
                                        text = "${state.stream.serverName} · ${if (state.stream.isDub) "Español Latino" else "Subtitulado"}",
                                        style = MaterialTheme.typography.titleSmall,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Text(
                                        text = "Servidor web · Toca para cambiar de fuente",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }

                            is EpisodeSourceSummary.Torrent -> {
                                Icon(
                                    imageVector = Icons.Rounded.Bolt,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(22.dp),
                                )
                                Column {
                                    Text(
                                        text = state.torrent.title,
                                        style = MaterialTheme.typography.titleSmall,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Text(
                                        text = "Torrent P2P · ${state.torrent.quality}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Icon(
                imageVector = Icons.Rounded.SyncAlt,
                contentDescription = "Cambiar fuente",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NarrowEpisodeListSection(
    anime: AniListMedia,
    totalEpisodes: Int,
    latestAired: Int,
    currentEpisode: Int,
    watchedEpisodes: Set<Int>,
    onSelectEpisode: (Int) -> Unit,
    onToggleEpisodeWatched: (Int) -> Unit,
    onOpenEpisodeGrid: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val density = LocalDensity.current
    var hasScrolledInitially by remember(anime.id) { mutableStateOf(false) }

    LaunchedEffect(anime.id, currentEpisode, totalEpisodes) {
        if (totalEpisodes > 0) {
            val targetIdx = (currentEpisode - 1).coerceIn(0, totalEpisodes - 1)
            val offsetPx = with(density) { -48.dp.roundToPx() }
            if (!hasScrolledInitially) {
                listState.scrollToItem(targetIdx, offsetPx)
                hasScrolledInitially = true
            } else {
                listState.animateScrollToItem(targetIdx, offsetPx)
            }
        }
    }

    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Lista de episodios",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.combinedClickable(
                    onClick = {},
                    onDoubleClick = {
                        val idx = (currentEpisode - 1).coerceIn(0, (totalEpisodes - 1).coerceAtLeast(0))
                        coroutineScope.launch { listState.animateScrollToItem(idx) }
                    },
                ),
            )

            Row(
                modifier = Modifier.combinedClickable(onClick = onOpenEpisodeGrid),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val airingSummary = if (latestAired > 0) {
                    "Ep. $latestAired de $totalEpisodes"
                } else {
                    "$totalEpisodes episodios"
                }
                Text(
                    text = airingSummary,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                )
                Spacer(Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "Ver más episodios",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(totalEpisodes, key = { it + 1 }) { idx ->
                val ep = idx + 1
                val isPlaying = ep == currentEpisode
                val isWatched = ep in watchedEpisodes
                val isAired = latestAired == 0 || ep <= latestAired
                val epName = anime.streamingEpisodes.firstOrNull { it.episodeNumber == ep }
                    ?.title
                    ?.replace(Regex("^(?:Episode|Ep|Capítulo|Cap)\\s*\\d+\\s*[-:]?\\s*", RegexOption.IGNORE_CASE), "")
                    ?.trim()
                    ?.takeIf { it.isNotBlank() }
                    ?: if (isAired) "Episodio $ep" else "Próximamente"

                EpisodeHorizontalCard(
                    episodeNumber = ep,
                    title = epName,
                    isPlaying = isPlaying,
                    isWatched = isWatched,
                    isAired = isAired,
                    onClick = { if (isAired) onSelectEpisode(ep) },
                    onLongClick = { if (isAired) onToggleEpisodeWatched(ep) },
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun EpisodeHorizontalCard(
    episodeNumber: Int,
    title: String,
    isPlaying: Boolean,
    isWatched: Boolean,
    isAired: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = when {
                isPlaying -> MaterialTheme.colorScheme.secondaryContainer
                isWatched || !isAired -> MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.6f)
                else -> MaterialTheme.colorScheme.surfaceContainer
            },
        ),
        modifier = Modifier
            .height(64.dp)
            .aspectRatio(16f / 10)
            .combinedClickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                enabled = isAired,
                onClick = onClick,
                onLongClick = if (isAired) onLongClick else null,
            ),
    ) {
        Box(Modifier.fillMaxSize()) {
            LongClickProgressFill(
                interactionSource = interactionSource,
                enabled = isAired,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.14f),
                modifier = Modifier.matchParentSize(),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (isPlaying) {
                            PlayingIcon(
                                contentDescription = "Reproduciendo",
                                width = 20.dp,
                                height = 12.dp,
                            )
                            Spacer(Modifier.width(4.dp))
                        }
                        Text(
                            text = String.format("%02d", episodeNumber),
                            style = MaterialTheme.typography.labelLarge,
                            color = when {
                                isPlaying -> MaterialTheme.colorScheme.primary
                                isWatched || !isAired -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                                else -> LocalContentColor.current
                            },
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = if (isWatched || !isAired) {
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EpisodeInfoTab(
    anime: AniListMedia,
    hasBothLanguages: Boolean,
    isDub: Boolean,
    onTogglePreferDub: (Boolean) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val cleanSynopsis = anime.cleanDescription
        if (cleanSynopsis.isNotBlank()) {
            item("synopsis") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Sinopsis", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = cleanSynopsis,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        if (anime.genres.isNotEmpty()) {
            item("genres") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Etiquetas", style = MaterialTheme.typography.titleMedium)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        anime.genres.forEach { genre ->
                            OutlinedTag { Text(genre) }
                        }
                    }
                }
            }
        }

        if (hasBothLanguages) {
            item("lang_pref") {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text("Idioma predeterminado", style = MaterialTheme.typography.titleSmall)
                            Text(
                                text = if (isDub) "Español Latino (LAT)" else "Japonés Subtitulado (SUB)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        Surface(
                            onClick = { onTogglePreferDub(!isDub) },
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                        ) {
                            Text(
                                text = if (isDub) "Cambiar a SUB" else "Cambiar a LAT",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
