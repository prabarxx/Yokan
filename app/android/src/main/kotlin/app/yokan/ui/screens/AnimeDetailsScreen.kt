package app.yokan.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Dataset
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import app.yokan.anilist.client.AniListClient
import app.yokan.anilist.model.AniListMedia
import app.yokan.anilist.model.AniListRelation
import app.yokan.datasource.animeav1.AnimeAV1Client
import app.yokan.media.WatchHistoryManager
import app.yokan.media.WatchProgress
import dev.chrisbanes.haze.rememberHazeState
import kotlinx.coroutines.launch
import me.him188.ani.app.ui.foundation.AsyncImage
import me.him188.ani.app.ui.foundation.FilledTonalCombinedClickButton
import me.him188.ani.app.ui.foundation.LocalImageViewerHandler
import me.him188.ani.app.ui.foundation.LongClickProgressFill
import me.him188.ani.app.ui.foundation.OutlinedTag
import me.him188.ani.app.ui.foundation.animation.AniAnimatedVisibility
import me.him188.ani.app.ui.foundation.backgroundWithGradient
import me.him188.ani.app.ui.foundation.icons.PlayingIcon
import me.him188.ani.app.ui.foundation.ifThen
import me.him188.ani.app.ui.foundation.input.touchHorizontalScrollOnly
import me.him188.ani.app.ui.foundation.layout.NestedScrollableColumn
import me.him188.ani.app.ui.foundation.layout.NestedScrollableScope
import me.him188.ani.app.ui.foundation.layout.PaddingValuesSides
import me.him188.ani.app.ui.foundation.layout.currentWindowAdaptiveInfo1
import me.him188.ani.app.ui.foundation.layout.isWidthAtLeastMedium
import me.him188.ani.app.ui.foundation.layout.isWidthCompact
import me.him188.ani.app.ui.foundation.layout.only
import me.him188.ani.app.ui.foundation.layout.paddingIfNotEmpty
import me.him188.ani.app.ui.foundation.layout.paneHorizontalPadding
import me.him188.ani.app.ui.foundation.layout.paneVerticalPadding
import me.him188.ani.app.ui.foundation.pagerTabIndicatorOffset
import me.him188.ani.app.ui.foundation.theme.AniThemeDefaults
import me.him188.ani.app.ui.foundation.theme.LocalAppChromeHazeState
import me.him188.ani.app.ui.foundation.theme.appChromeFrostedGlass
import me.him188.ani.app.ui.foundation.theme.appChromeHazeSource
import me.him188.ani.app.ui.foundation.theme.isAppChromeFrostedGlassActive
import me.him188.ani.app.ui.foundation.theme.stronglyWeaken
import me.him188.ani.app.ui.foundation.theme.weaken
import me.him188.ani.app.ui.foundation.widgets.BackNavigationIconButton

private const val COVER_WIDTH_TO_HEIGHT_RATIO = 849f / 1200f
private val MAXIMUM_CONTENT_WIDTH = 1300.dp
private val TAB_ROW_WIDTH = 104.dp * 3

private enum class DetailsTab(val label: String) {
    DETAILS("Detalles"),
    EPISODES("Episodios"),
    RELATED("Relacionados"),
}

@Composable
fun AnimeDetailsScreen(
    animeId: Int,
    initialAnime: AniListMedia?,
    aniListClient: AniListClient,
    animeAV1Client: AnimeAV1Client,
    onAnimeClick: (AniListMedia) -> Unit,
    onEpisodeClick: (AniListMedia, Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    showTopBar: Boolean = true,
    showBlurredBackground: Boolean = true,
) {
    val context = LocalContext.current
    val watchHistoryManager = remember { WatchHistoryManager.getInstance(context) }
    val watchHistory by watchHistoryManager.allHistory.collectAsState()
    val watchedEpisodesMap by watchHistoryManager.watchedEpisodes.collectAsState()
    val preferDub by watchHistoryManager.preferDub.collectAsState()

    var animeDetails by remember(animeId) { mutableStateOf<AniListMedia?>(initialAnime) }
    var isLoading by remember(animeId) { mutableStateOf(initialAnime == null) }
    var specialVersions by remember(animeId) { mutableStateOf<List<AniListRelation>>(emptyList()) }
    var hasDubAvailable by remember(animeId) { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(animeDetails?.id, animeDetails?.title?.romaji) {
        val current = animeDetails ?: return@LaunchedEffect
        val (_, hasDub) = animeAV1Client.getAvailableLanguages(
            romajiTitle = current.title.romaji,
            englishTitle = current.title.english,
            episodeNumber = 1,
        )
        hasDubAvailable = hasDub
    }

    LaunchedEffect(animeId) {
        if (animeId > 0) {
            val result = aniListClient.getAnimeDetails(animeId)
            result.onSuccess { details ->
                animeDetails = details
                isLoading = false

                val specials = animeAV1Client.findSpecialVersions(
                    romajiTitle = details.title.romaji,
                    englishTitle = details.title.english,
                    existingRelationIds = details.relations.map { it.id }.toSet(),
                )
                specialVersions = specials
            }.onFailure {
                isLoading = false
            }
        } else {
            val current = initialAnime
            if (current != null) {
                animeDetails = current
                isLoading = false
                if (current.description.isNullOrBlank()) {
                    val slug = animeAV1Client.resolveSlug(current.title.romaji, current.title.english)
                    animeAV1Client.getAnimeAV1Media(slug)?.let { enriched ->
                        animeDetails = enriched
                    }
                }
                val cleanFranchise = current.title.romaji
                    .replace(Regex("(?i):?\\s*(shin-?henshuu-?ban|director'?s?\\s*cut|recut|remake|sin\\s*censura|uncensored)"), "")
                    .trim()
                if (cleanFranchise.isNotBlank()) {
                    val searchResult = aniListClient.searchAnime(cleanFranchise, 1, 5)
                    searchResult.onSuccess { items ->
                        specialVersions = items.map { item ->
                            AniListRelation(
                                id = item.id,
                                relationType = "ALTERNATIVE",
                                title = item.title,
                                coverImage = item.coverImage,
                                format = item.format ?: "TV",
                                episodes = item.episodes,
                                seasonYear = item.seasonYear,
                            )
                        }
                    }
                }
            } else {
                isLoading = false
            }
        }
    }

    val anime = animeDetails
    if (anime == null) {
        Scaffold(
            topBar = {
                if (showTopBar) {
                    TopAppBar(
                        title = {},
                        navigationIcon = { BackNavigationIconButton(onBack) },
                        colors = AniThemeDefaults.transparentAppBarColors(),
                    )
                }
            },
            containerColor = AniThemeDefaults.pageContentBackgroundColor,
            modifier = modifier,
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center,
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Text(
                            text = "No se pudo cargar la información del anime",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        FilledTonalButton(onClick = onBack) {
                            Text("Volver")
                        }
                    }
                }
            }
        }
        return
    }

    val allRelations = remember(anime.relations, specialVersions) {
        (anime.relations + specialVersions).distinctBy { it.id }
    }
    val watchProgress = remember(watchHistory, anime.id) {
        watchHistory.firstOrNull { it.animeId == anime.id }
    }
    val watchedSet = remember(watchedEpisodesMap, anime.id) {
        watchedEpisodesMap[anime.id].orEmpty()
    }

    AnimeDetailsPageContent(
        anime = anime,
        allRelations = allRelations,
        watchProgress = watchProgress,
        watchedEpisodes = watchedSet,
        preferDub = preferDub,
        hasDubAvailable = hasDubAvailable == true,
        onTogglePreferDub = { watchHistoryManager.setPreferDub(it) },
        onToggleEpisodeWatched = { ep -> watchHistoryManager.toggleEpisodeWatched(anime.id, ep) },
        onEpisodeClick = { ep -> onEpisodeClick(anime, ep) },
        onAnimeClick = onAnimeClick,
        onBack = onBack,
        modifier = modifier,
        showTopBar = showTopBar,
        showBlurredBackground = showBlurredBackground,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AnimeDetailsPageContent(
    anime: AniListMedia,
    allRelations: List<AniListRelation>,
    watchProgress: WatchProgress?,
    watchedEpisodes: Set<Int>,
    preferDub: Boolean,
    hasDubAvailable: Boolean,
    onTogglePreferDub: (Boolean) -> Unit,
    onToggleEpisodeWatched: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
    onAnimeClick: (AniListMedia) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    showTopBar: Boolean = true,
    showBlurredBackground: Boolean = true,
) {
    val context = LocalContext.current
    val imageViewer = LocalImageViewerHandler.current
    val totalEpisodes = anime.effectiveEpisodesCount
    val latestAired = anime.latestAiredEpisode
    val isCompactWidth = currentWindowAdaptiveInfo1().isWidthCompact
    val windowSizeClass = currentWindowAdaptiveInfo1().windowSizeClass

    val nextEpisodeToPlay = remember(watchProgress, watchedEpisodes, totalEpisodes, latestAired) {
        val progressEp = watchProgress?.episode?.takeIf { it in 1..totalEpisodes }
        when {
            progressEp != null -> progressEp
            else -> (1..totalEpisodes).firstOrNull { it !in watchedEpisodes } ?: 1
        }
    }

    var showEpisodeListDialog by rememberSaveable { mutableStateOf(false) }

    if (showEpisodeListDialog) {
        AnimeEpisodeListDialog(
            subjectTitle = anime.title.displayTitle,
            totalEpisodes = totalEpisodes,
            latestAiredEpisode = latestAired,
            currentEpisode = nextEpisodeToPlay,
            watchedEpisodes = watchedEpisodes,
            onDismissRequest = { showEpisodeListDialog = false },
            onEpisodeClick = { ep ->
                showEpisodeListDialog = false
                onEpisodeClick(ep)
            },
            onEpisodeLongClick = onToggleEpisodeWatched,
        )
    }

    val pagerState = rememberPagerState(
        initialPage = DetailsTab.DETAILS.ordinal,
        pageCount = { DetailsTab.entries.size },
    )
    val scope = rememberCoroutineScope()
    val nestedScrollableColumnState = rememberNestedScrollableColumnState()

    val backgroundColor = AniThemeDefaults.pageContentBackgroundColor
    val stickyTopBarColor = AniThemeDefaults.navigationContainerColor
    val windowInsets = TopAppBarDefaults.windowInsets

    val onClickOpenExternal: () -> Unit = {
        val url = if (anime.id > 0) {
            "https://anilist.co/anime/${anime.id}"
        } else {
            "https://animeav1.com"
        }
        runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    val topAppBarActions: @Composable RowScope.() -> Unit = {
        IconButton(onClick = onClickOpenExternal) {
            Icon(Icons.AutoMirrored.Outlined.OpenInNew, contentDescription = "Abrir en AniList")
        }
    }

    val coverImageUrl = anime.coverImage?.bestQualityUrl?.takeIf { it.isNotBlank() }
    val onClickCover: (() -> Unit)? = coverImageUrl?.let { url -> { imageViewer.viewImage(url) } }

    CompositionLocalProvider(LocalAppChromeHazeState provides rememberHazeState()) {
        val frostedGlassActive = isAppChromeFrostedGlassActive()

        Scaffold(
            topBar = {
                if (showTopBar) {
                    TopAppBar(
                        title = {},
                        navigationIcon = { BackNavigationIconButton(onBack) },
                        actions = topAppBarActions,
                        colors = AniThemeDefaults.transparentAppBarColors(),
                        windowInsets = windowInsets.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top),
                    )
                }
            },
            modifier = modifier,
            contentWindowInsets = windowInsets.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
            containerColor = backgroundColor,
        ) { scaffoldPadding ->
            val headerContentPadding = scaffoldPadding.only(PaddingValuesSides.Horizontal + PaddingValuesSides.Top)
            val remainingContentPadding = scaffoldPadding.only(PaddingValuesSides.Horizontal)

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter,
            ) {
                var tabRowHeightPx by remember { mutableStateOf(0) }
                val density = LocalDensity.current
                val anchorHeightPx = rememberUpdatedState(
                    with(density) { scaffoldPadding.calculateTopPadding().roundToPx() },
                )
                val stickyPanelVisible by remember(nestedScrollableColumnState) {
                    derivedStateOf {
                        val state = nestedScrollableColumnState
                        val threshold = state.headerHeight - tabRowHeightPx - anchorHeightPx.value
                        state.headerHeight > 0 && state.scrolledOffset > 0f && state.scrolledOffset >= threshold
                    }
                }

                NestedScrollableColumn(
                    header = {
                        Column(Modifier.fillMaxWidth()) {
                            Box {
                                if (showBlurredBackground) {
                                    SubjectBlurredBackground(
                                        coverImageUrl = coverImageUrl,
                                        modifier = Modifier.matchParentSize(),
                                        backgroundColor = backgroundColor,
                                        blurRadius = if (isCompactWidth) 16.dp else 32.dp,
                                    )
                                }

                                Column(
                                    Modifier
                                        .padding(headerContentPadding)
                                        .consumeWindowInsets(headerContentPadding),
                                ) {
                                    SubjectDetailsHeaderLayout(
                                        anime = anime,
                                        coverImageUrl = coverImageUrl,
                                        watchProgress = watchProgress,
                                        watchedEpisodes = watchedEpisodes,
                                        nextEpisodeToPlay = nextEpisodeToPlay,
                                        preferDub = preferDub,
                                        hasDubAvailable = hasDubAvailable,
                                        onTogglePreferDub = onTogglePreferDub,
                                        onShowEpisodeList = { showEpisodeListDialog = true },
                                        onPlay = { onEpisodeClick(nextEpisodeToPlay) },
                                        onClickCover = onClickCover,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                                            .widthIn(max = MAXIMUM_CONTENT_WIDTH)
                                            .fillMaxWidth()
                                            .ifThen(!showTopBar) { padding(top = windowSizeClass.paneVerticalPadding) }
                                            .padding(horizontal = windowSizeClass.paneHorizontalPadding),
                                    )
                                }
                            }

                            Box(
                                Modifier
                                    .onSizeChanged { tabRowHeightPx = it.height }
                                    .padding(remainingContentPadding)
                                    .fillMaxWidth(),
                            ) {
                                SubjectDetailsContentTabRow(
                                    pagerState = pagerState,
                                    onSelectTab = { index ->
                                        scope.launch { pagerState.animateScrollToPage(index) }
                                    },
                                    visible = !stickyPanelVisible,
                                    isOverlay = false,
                                )
                            }
                        }
                    },
                    content = {
                        val contentModifier = Modifier
                            .padding(remainingContentPadding)
                            .consumeWindowInsets(remainingContentPadding)
                            .fillMaxSize()

                        HorizontalPager(
                            state = pagerState,
                            modifier = contentModifier.touchHorizontalScrollOnly(),
                            verticalAlignment = Alignment.Top,
                        ) { index ->
                            when (index) {
                                DetailsTab.DETAILS.ordinal -> {
                                    CompactDetailsTabContent(
                                        anime = anime,
                                        allRelations = allRelations,
                                        totalEpisodes = totalEpisodes,
                                        latestAired = latestAired,
                                        currentEpisode = nextEpisodeToPlay,
                                        watchedEpisodes = watchedEpisodes,
                                        onEpisodeClick = onEpisodeClick,
                                        onEpisodeLongClick = onToggleEpisodeWatched,
                                        onShowAllEpisodes = { showEpisodeListDialog = true },
                                        onAnimeClick = onAnimeClick,
                                        nestedScrollWorkaroundModifier = Modifier.nestedScrollWorkaround(),
                                    )
                                }

                                DetailsTab.EPISODES.ordinal -> {
                                    EpisodesTabContent(
                                        totalEpisodes = totalEpisodes,
                                        latestAired = latestAired,
                                        currentEpisode = nextEpisodeToPlay,
                                        watchedEpisodes = watchedEpisodes,
                                        onEpisodeClick = onEpisodeClick,
                                        onEpisodeLongClick = onToggleEpisodeWatched,
                                        nestedScrollWorkaroundModifier = Modifier.nestedScrollWorkaround(),
                                    )
                                }

                                DetailsTab.RELATED.ordinal -> {
                                    RelatedTabContent(
                                        relations = allRelations,
                                        recommendations = anime.recommendations,
                                        onAnimeClick = onAnimeClick,
                                        nestedScrollWorkaroundModifier = Modifier.nestedScrollWorkaround(),
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .appChromeHazeSource(backgroundColor = backgroundColor),
                    state = nestedScrollableColumnState,
                )

                // Sticky Frosted-Glass Overlay Panel cuando el TabRow alcanza el TopAppBar
                if (showTopBar) {
                    val panelBackgroundModifier = Modifier
                        .appChromeFrostedGlass(
                            enabled = frostedGlassActive,
                            containerColor = stickyTopBarColor,
                        )
                        .ifThen(!frostedGlassActive) { background(stickyTopBarColor) }

                    Column(
                        Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth(),
                    ) {
                        AniAnimatedVisibility(
                            visible = stickyPanelVisible,
                            enter = fadeIn(),
                            exit = fadeOut(),
                        ) {
                            TopAppBar(
                                title = {
                                    Text(
                                        anime.title.displayTitle,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                },
                                modifier = panelBackgroundModifier,
                                navigationIcon = { BackNavigationIconButton(onBack) },
                                actions = topAppBarActions,
                                colors = AniThemeDefaults.transparentAppBarColors(),
                                windowInsets = windowInsets.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top),
                            )
                        }

                        if (stickyPanelVisible) {
                            Box(
                                panelBackgroundModifier
                                    .padding(remainingContentPadding)
                                    .fillMaxWidth(),
                            ) {
                                SubjectDetailsContentTabRow(
                                    pagerState = pagerState,
                                    onSelectTab = { index ->
                                        scope.launch { pagerState.animateScrollToPage(index) }
                                    },
                                    visible = true,
                                    isOverlay = true,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SubjectBlurredBackground(
    coverImageUrl: String?,
    modifier: Modifier = Modifier,
    backgroundColor: Color = AniThemeDefaults.pageContentBackgroundColor,
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    blurRadius: Dp = 16.dp,
) {
    val brush = remember(surfaceColor, backgroundColor) {
        Brush.verticalGradient(
            0f to surfaceColor.copy(alpha = 0xA2 / 255f),
            0.4f to surfaceColor.copy(alpha = 0xA2 / 255f),
            1.0f to backgroundColor,
        )
    }
    Box(modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxSize()
                .blur(blurRadius)
                .backgroundWithGradient(
                    data = coverImageUrl,
                    fallbackColor = backgroundColor,
                    brush = brush,
                ),
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SubjectDetailsHeaderLayout(
    anime: AniListMedia,
    coverImageUrl: String?,
    watchProgress: WatchProgress?,
    watchedEpisodes: Set<Int>,
    nextEpisodeToPlay: Int,
    preferDub: Boolean,
    hasDubAvailable: Boolean,
    onTogglePreferDub: (Boolean) -> Unit,
    onShowEpisodeList: () -> Unit,
    onPlay: () -> Unit,
    onClickCover: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val subtitleText = remember(anime.title) {
        anime.title.native?.takeIf { it.isNotBlank() && it != anime.title.displayTitle }
            ?: anime.title.romaji.takeIf { it.isNotBlank() && it != anime.title.displayTitle }
            ?: anime.title.displayTitle
    }

    val seasonTagContent: @Composable () -> Unit = {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        ) {
            val tagLabel = anime.displaySeason ?: anime.displayFormat
            if (!tagLabel.isNullOrBlank()) {
                OutlinedTag { Text(tagLabel) }
            }
            if (anime.displaySeason != null && !anime.displayFormat.isNullOrBlank()) {
                OutlinedTag { Text(anime.displayFormat.orEmpty()) }
            }
            Text(
                text = formatAiringLabel(anime),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }

    val ratingContent: @Composable () -> Unit = {
        val score = anime.averageScore
        if (score != null && score > 0) {
            Column(horizontalAlignment = Alignment.End) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = String.format("%.1f", score / 10f),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(20.dp),
                    )
                }
                Text(
                    text = "AniList $score%",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }

    val collectionDataContent: @Composable () -> Unit = {
        val statsText = buildString {
            anime.popularity?.takeIf { it > 0 }?.let {
                append("${formatCompactNumber(it)} miembros")
            }
            anime.favourites?.takeIf { it > 0 }?.let {
                if (isNotEmpty()) append(" · ")
                append("${formatCompactNumber(it)} favs")
            }
            if (isEmpty()) {
                append("${anime.effectiveEpisodesCount} episodios")
            } else {
                append(" · ${anime.effectiveEpisodesCount} eps")
            }
        }
        Text(
            text = statsText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }

    val collectionActionContent: @Composable () -> Unit = {
        if (hasDubAvailable) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.7f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            ) {
                Row(
                    modifier = Modifier.padding(2.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    val subSelected = !preferDub
                    LanguageTogglePill(
                        label = "SUB",
                        selected = subSelected,
                        onClick = { onTogglePreferDub(false) },
                    )
                    LanguageTogglePill(
                        label = "LAT",
                        selected = preferDub,
                        onClick = { onTogglePreferDub(true) },
                    )
                }
            }
        }
    }

    val selectEpisodeButtonContent: @Composable BoxScope.() -> Unit = {
        SelectEpisodeButtonsRow(
            watchProgress = watchProgress,
            watchedEpisodes = watchedEpisodes,
            nextEpisodeToPlay = nextEpisodeToPlay,
            totalEpisodes = anime.effectiveEpisodesCount,
            onShowEpisodeList = onShowEpisodeList,
            onPlay = onPlay,
            modifier = Modifier.fillMaxWidth(),
        )
    }

    if (currentWindowAdaptiveInfo1().isWidthAtLeastMedium) {
        SubjectDetailsHeaderWide(
            coverImageUrl = coverImageUrl,
            title = { Text(anime.title.displayTitle) },
            subtitle = { Text(subtitleText) },
            seasonTags = seasonTagContent,
            collectionData = collectionDataContent,
            collectionAction = collectionActionContent,
            selectEpisodeButton = selectEpisodeButtonContent,
            rating = ratingContent,
            onClickCover = onClickCover,
            modifier = modifier,
        )
    } else {
        SubjectDetailsHeaderCompact(
            coverImageUrl = coverImageUrl,
            title = { Text(anime.title.displayTitle) },
            subtitle = { Text(subtitleText) },
            seasonTags = seasonTagContent,
            collectionData = collectionDataContent,
            collectionAction = collectionActionContent,
            selectEpisodeButton = selectEpisodeButtonContent,
            rating = ratingContent,
            onClickCover = onClickCover,
            modifier = modifier,
        )
    }
}

@Composable
private fun LanguageTogglePill(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
        color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
        contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        )
    }
}

@Composable
private fun SubjectDetailsHeaderCompact(
    coverImageUrl: String?,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    seasonTags: @Composable () -> Unit,
    collectionData: @Composable () -> Unit,
    collectionAction: @Composable () -> Unit,
    selectEpisodeButton: @Composable BoxScope.() -> Unit,
    rating: @Composable () -> Unit,
    onClickCover: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Row(
            Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.Top,
        ) {
            val imageWidth = 140.dp
            Box(
                Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .ifThen(onClickCover != null) { clickable(onClick = checkNotNull(onClickCover)) },
            ) {
                AsyncImage(
                    model = coverImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(imageWidth)
                        .height(imageWidth / COVER_WIDTH_TO_HEIGHT_RATIO),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                Modifier
                    .weight(1f, fill = true)
                    .padding(horizontal = 12.dp),
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    var showSubtitle by remember { mutableStateOf(false) }
                    Box(Modifier.clickable { showSubtitle = !showSubtitle }) {
                        ProvideTextStyle(MaterialTheme.typography.titleLarge) {
                            if (showSubtitle) subtitle() else title()
                        }
                    }

                    ProvideTextStyle(MaterialTheme.typography.labelLarge) {
                        seasonTags()
                    }

                    Spacer(Modifier.weight(1f))

                    Row(
                        Modifier
                            .requiredHeight(IntrinsicSize.Max)
                            .align(Alignment.End),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        rating()
                    }
                }
            }
        }

        Row(
            Modifier
                .padding(top = 16.dp)
                .align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                Modifier.weight(1f),
                verticalAlignment = Alignment.Bottom,
            ) {
                collectionData()
            }
            collectionAction()
        }

        Box(
            Modifier.paddingIfNotEmpty(top = 8.dp),
            contentAlignment = Alignment.CenterEnd,
        ) {
            selectEpisodeButton()
        }
    }
}

@Composable
private fun SubjectDetailsHeaderWide(
    coverImageUrl: String?,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    seasonTags: @Composable () -> Unit,
    collectionData: @Composable () -> Unit,
    collectionAction: @Composable () -> Unit,
    selectEpisodeButton: @Composable BoxScope.() -> Unit,
    rating: @Composable () -> Unit,
    onClickCover: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Row(
            Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.Top,
        ) {
            val imageWidth = 220.dp
            Box(
                Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .ifThen(onClickCover != null) { clickable(onClick = checkNotNull(onClickCover)) },
            ) {
                AsyncImage(
                    model = coverImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(imageWidth)
                        .height(imageWidth / COVER_WIDTH_TO_HEIGHT_RATIO),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                Modifier
                    .weight(1f, fill = true)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                var showSubtitle by remember { mutableStateOf(false) }
                Box(Modifier.clickable { showSubtitle = !showSubtitle }) {
                    ProvideTextStyle(MaterialTheme.typography.headlineMedium) {
                        if (showSubtitle) subtitle() else title()
                    }
                }

                ProvideTextStyle(MaterialTheme.typography.labelLarge) {
                    seasonTags()
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Box(Modifier.weight(1f)) { collectionData() }
                    collectionAction()
                }

                Spacer(Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Box(Modifier.weight(1f)) { selectEpisodeButton() }
                    rating()
                }
            }
        }
    }
}

@Composable
private fun SelectEpisodeButtonsRow(
    watchProgress: WatchProgress?,
    watchedEpisodes: Set<Int>,
    nextEpisodeToPlay: Int,
    totalEpisodes: Int,
    onShowEpisodeList: () -> Unit,
    onPlay: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val label = when {
        watchProgress != null && watchProgress.progressFraction < 0.92f && watchProgress.positionMillis > 0L ->
            "Continuar Ep. $nextEpisodeToPlay"
        watchedEpisodes.size >= totalEpisodes && totalEpisodes > 0 ->
            "Volver a ver Ep. $nextEpisodeToPlay"
        watchedEpisodes.isNotEmpty() || (watchProgress != null && watchProgress.episode > 1) ->
            "Continuar Ep. $nextEpisodeToPlay"
        else ->
            "Comenzar Ep. $nextEpisodeToPlay"
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onShowEpisodeList) {
            Icon(
                imageVector = Icons.Outlined.Dataset,
                contentDescription = "Todos los episodios",
            )
        }

        Box(Modifier.weight(1f)) {
            Button(
                onClick = onPlay,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 42.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            ) {
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.width(6.dp))
                Text(label, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
private fun SubjectDetailsContentTabRow(
    pagerState: PagerState,
    onSelectTab: (Int) -> Unit,
    visible: Boolean,
    isOverlay: Boolean,
    modifier: Modifier = Modifier,
) {
    val compact = currentWindowAdaptiveInfo1().isWidthCompact
    Column(
        modifier
            .fillMaxWidth()
            .alpha(if (visible) 1f else 0f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.ifThen(!compact) { widthIn(max = TAB_ROW_WIDTH) },
            containerColor = if (isOverlay) Color.Transparent else Color.Transparent,
            indicator = @Composable { tabPositions ->
                TabRowDefaults.PrimaryIndicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    width = 36.dp,
                )
            },
            divider = {},
        ) {
            DetailsTab.entries.forEachIndexed { index, tab ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { onSelectTab(index) },
                    modifier = Modifier.height(44.dp),
                    text = { Text(tab.label, softWrap = false) },
                )
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.weaken())
    }
}

@Composable
private fun CompactDetailsTabContent(
    anime: AniListMedia,
    allRelations: List<AniListRelation>,
    totalEpisodes: Int,
    latestAired: Int,
    currentEpisode: Int,
    watchedEpisodes: Set<Int>,
    onEpisodeClick: (Int) -> Unit,
    onEpisodeLongClick: (Int) -> Unit,
    onShowAllEpisodes: () -> Unit,
    onAnimeClick: (AniListMedia) -> Unit,
    nestedScrollWorkaroundModifier: Modifier,
) {
    val windowSizeClass = currentWindowAdaptiveInfo1().windowSizeClass
    val horizontalPadding = windowSizeClass.paneHorizontalPadding
    val verticalSpacing = if (currentWindowAdaptiveInfo1().isWidthCompact) 20.dp else 28.dp

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(nestedScrollWorkaroundModifier),
        contentPadding = PaddingValues(vertical = verticalSpacing),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
    ) {
        // 1. Episodios (tira horizontal estilo Animeko + '>' que abre EpisodeListDialog)
        item("episodes") {
            DetailsEpisodesSection(
                anime = anime,
                totalEpisodes = totalEpisodes,
                latestAired = latestAired,
                currentEpisode = currentEpisode,
                watchedEpisodes = watchedEpisodes,
                onEpisodeClick = onEpisodeClick,
                onEpisodeLongClick = onEpisodeLongClick,
                onShowAllEpisodes = onShowAllEpisodes,
                horizontalPadding = horizontalPadding,
            )
        }

        // 2. Sinopsis (5 líneas colapsables)
        val cleanSynopsis = anime.cleanDescription
        if (cleanSynopsis.isNotBlank()) {
            item("summary") {
                SubjectSummarySection(
                    summary = cleanSynopsis,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                )
            }
        }

        // 3. Géneros (OutlinedTag FlowRow)
        if (anime.genres.isNotEmpty()) {
            item("tags") {
                SubjectTagsSection(
                    genres = anime.genres,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                )
            }
        }

        // 4. Información (SubjectInfoTable 2 columnas)
        item("info") {
            SubjectInfoSection(
                anime = anime,
                modifier = Modifier.padding(horizontal = horizontalPadding),
            )
        }

        // 5. Temporadas y Relacionados
        if (allRelations.isNotEmpty()) {
            item("related") {
                RelatedSubjectsSection(
                    title = "Relacionados",
                    relations = allRelations,
                    onAnimeClick = onAnimeClick,
                    horizontalPadding = horizontalPadding,
                )
            }
        }

        // 6. Recomendaciones
        if (anime.recommendations.isNotEmpty()) {
            item("recommendations") {
                RelatedSubjectsSection(
                    title = "Recomendaciones",
                    relations = anime.recommendations,
                    onAnimeClick = onAnimeClick,
                    horizontalPadding = horizontalPadding,
                )
            }
        }
    }
}

@Composable
private fun EpisodesTabContent(
    totalEpisodes: Int,
    latestAired: Int,
    currentEpisode: Int,
    watchedEpisodes: Set<Int>,
    onEpisodeClick: (Int) -> Unit,
    onEpisodeLongClick: (Int) -> Unit,
    nestedScrollWorkaroundModifier: Modifier,
) {
    val horizontalPadding = currentWindowAdaptiveInfo1().windowSizeClass.paneHorizontalPadding
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(nestedScrollWorkaroundModifier),
        contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item("legend") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Lightbulb,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Mantén pulsado un episodio para marcarlo como visto",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        item("grid") {
            AnimeEpisodeFlowGrid(
                totalEpisodes = totalEpisodes,
                latestAiredEpisode = latestAired,
                currentEpisode = currentEpisode,
                watchedEpisodes = watchedEpisodes,
                onEpisodeClick = onEpisodeClick,
                onEpisodeLongClick = onEpisodeLongClick,
            )
        }
    }
}

@Composable
private fun RelatedTabContent(
    relations: List<AniListRelation>,
    recommendations: List<AniListRelation>,
    onAnimeClick: (AniListMedia) -> Unit,
    nestedScrollWorkaroundModifier: Modifier,
) {
    val horizontalPadding = currentWindowAdaptiveInfo1().windowSizeClass.paneHorizontalPadding
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(nestedScrollWorkaroundModifier),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        if (relations.isEmpty() && recommendations.isEmpty()) {
            item("empty") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding, vertical = 32.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "No hay entregas relacionadas ni recomendaciones disponibles.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        } else {
            if (relations.isNotEmpty()) {
                item("relations_header") {
                    Text(
                        text = "Relacionados y entregas (${relations.size})",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = horizontalPadding),
                    )
                }
                relationListItems(relations, horizontalPadding, onAnimeClick)
            }
            if (recommendations.isNotEmpty()) {
                item("rec_header") {
                    Text(
                        text = "Recomendaciones (${recommendations.size})",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = horizontalPadding),
                    )
                }
                relationListItems(recommendations, horizontalPadding, onAnimeClick)
            }
        }
    }
}

private fun LazyListScope.relationListItems(
    itemsList: List<AniListRelation>,
    horizontalPadding: Dp,
    onAnimeClick: (AniListMedia) -> Unit,
) {
    items(itemsList, key = { "${it.relationType}_${it.id}" }) { rel ->
        Surface(
            onClick = { onAnimeClick(rel.toAniListMedia()) },
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding),
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                AsyncImage(
                    model = rel.coverImage?.bestQualityUrl,
                    contentDescription = rel.title.displayTitle,
                    modifier = Modifier
                        .width(56.dp)
                        .height(78.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Text(
                            text = rel.displayBadge,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        )
                    }
                    Text(
                        text = rel.title.displayTitle,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    val meta = listOfNotNull(
                        rel.episodes?.takeIf { it > 0 }?.let { "$it episodios" },
                        rel.seasonYear?.toString(),
                    ).joinToString(" · ")
                    if (meta.isNotBlank()) {
                        Text(
                            text = meta,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailsSectionHeader(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    action: (@Composable RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProvideTextStyle(MaterialTheme.typography.titleMedium) {
            Box(Modifier.weight(1f)) { title() }
        }
        if (action != null) {
            Row(verticalAlignment = Alignment.CenterVertically, content = action)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DetailsEpisodesSection(
    anime: AniListMedia,
    totalEpisodes: Int,
    latestAired: Int,
    currentEpisode: Int,
    watchedEpisodes: Set<Int>,
    onEpisodeClick: (Int) -> Unit,
    onEpisodeLongClick: (Int) -> Unit,
    onShowAllEpisodes: () -> Unit,
    horizontalPadding: Dp,
) {
    val rowState = rememberLazyListState()
    var initialScrolled by remember(anime.id) { mutableStateOf(false) }

    LaunchedEffect(anime.id, currentEpisode, totalEpisodes) {
        if (!initialScrolled && totalEpisodes > 0) {
            val targetIdx = (currentEpisode - 1).coerceIn(0, totalEpisodes - 1)
            rowState.scrollToItem(targetIdx)
            initialScrolled = true
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        DetailsSectionHeader(
            title = { Text("Episodios") },
            modifier = Modifier.padding(horizontal = horizontalPadding),
            action = {
                Surface(
                    onClick = onShowAllEpisodes,
                    color = Color.Transparent,
                    shape = MaterialTheme.shapes.small,
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = formatAiringShort(anime, totalEpisodes),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Spacer(Modifier.width(2.dp))
                        Icon(
                            imageVector = Icons.Outlined.ChevronRight,
                            contentDescription = "Ver todos los episodios",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }
            },
        )

        LazyRow(
            state = rowState,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(totalEpisodes, key = { it + 1 }) { idx ->
                val ep = idx + 1
                val isPlaying = ep == currentEpisode
                val isWatched = ep in watchedEpisodes
                val isAired = latestAired == 0 || ep <= latestAired
                val epInfo = anime.streamingEpisodes.firstOrNull { it.episodeNumber == ep }
                val rawSubtitle = epInfo?.title
                    ?.replace(Regex("^(?:Episode|Ep|Capítulo|Cap)\\s*\\d+\\s*[-:]?\\s*", RegexOption.IGNORE_CASE), "")
                    ?.trim()
                    ?.takeIf { it.isNotBlank() }

                DetailsEpisodeGridCell(
                    episodeNumber = ep,
                    subtitle = rawSubtitle ?: if (isAired) "Episodio $ep" else "Próximamente",
                    isPlaying = isPlaying,
                    isWatched = isWatched,
                    isAired = isAired,
                    onClick = { onEpisodeClick(ep) },
                    onLongClick = { onEpisodeLongClick(ep) },
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DetailsEpisodeGridCell(
    episodeNumber: Int,
    subtitle: String,
    isPlaying: Boolean,
    isWatched: Boolean,
    isAired: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val shape = RoundedCornerShape(12.dp)
    val containerColor = when {
        !isAired -> MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.55f)
        isPlaying -> MaterialTheme.colorScheme.primaryContainer
        isWatched -> MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.65f)
        else -> MaterialTheme.colorScheme.surfaceContainerLow
    }
    val titleColor = when {
        !isAired -> MaterialTheme.colorScheme.onSurface.stronglyWeaken()
        isPlaying -> MaterialTheme.colorScheme.onPrimaryContainer
        isWatched -> MaterialTheme.colorScheme.onSurfaceVariant
        else -> MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = Modifier
            .width(108.dp)
            .height(64.dp)
            .clip(shape)
            .background(containerColor)
            .combinedClickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                enabled = isAired,
                onClick = onClick,
                onLongClick = if (isAired) onLongClick else null,
            ),
    ) {
        LongClickProgressFill(
            interactionSource = interactionSource,
            enabled = isAired,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
            modifier = Modifier.matchParentSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                if (isPlaying) {
                    PlayingIcon(
                        contentDescription = "Reproduciendo",
                        width = 16.dp,
                        height = 10.dp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                Text(
                    text = String.format("%02d", episodeNumber),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = if (isPlaying) FontWeight.Bold else FontWeight.SemiBold,
                    color = if (isPlaying) MaterialTheme.colorScheme.primary else titleColor,
                )
            }
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = titleColor.copy(alpha = 0.8f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun SubjectSummarySection(
    summary: String,
    modifier: Modifier = Modifier,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var canExpand by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        DetailsSectionHeader(title = { Text("Sinopsis") })
        SelectionContainer {
            Text(
                text = summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = if (isExpanded) Int.MAX_VALUE else 5,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { result ->
                    if (!isExpanded) {
                        canExpand = result.hasVisualOverflow
                    }
                },
                modifier = Modifier
                    .animateContentSize()
                    .clickable { if (canExpand || isExpanded) isExpanded = !isExpanded },
            )
        }
        if (canExpand || isExpanded) {
            TextButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier.align(Alignment.End),
            ) {
                Text(if (isExpanded) "Mostrar menos" else "Ver más")
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SubjectTagsSection(
    genres: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        DetailsSectionHeader(title = { Text("Etiquetas") })
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            genres.forEach { genre ->
                OutlinedTag {
                    Text(genre)
                }
            }
        }
    }
}

@Composable
private fun SubjectInfoSection(
    anime: AniListMedia,
    modifier: Modifier = Modifier,
) {
    val rows = remember(anime) {
        buildList {
            anime.title.native?.takeIf { it.isNotBlank() }?.let { add("Título original" to it) }
            anime.displayFormat?.let { add("Formato" to it) }
            anime.episodes?.takeIf { it > 0 }?.let { add("Episodios" to "$it episodios") }
            anime.duration?.takeIf { it > 0 }?.let { add("Duración" to "$it min/ep") }
            anime.displaySeason?.let { add("Estreno" to it) }
            add("Estado" to formatAiringLabel(anime))
            if (anime.studios.isNotEmpty()) {
                add("Estudio" to anime.studios.joinToString(", "))
            }
            anime.displaySource?.let { add("Origen" to it) }
            if (anime.synonyms.isNotEmpty()) {
                add("Alias" to anime.synonyms.take(3).joinToString(", "))
            }
        }
    }
    if (rows.isEmpty()) return

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        DetailsSectionHeader(title = { Text("Información") })
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            rows.forEach { (label, value) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.width(104.dp),
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
fun RelatedSubjectsSection(
    title: String,
    relations: List<AniListRelation>,
    onAnimeClick: (AniListMedia) -> Unit,
    horizontalPadding: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        DetailsSectionHeader(
            title = { Text(title) },
            modifier = Modifier.padding(horizontal = horizontalPadding),
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(relations, key = { "${title}_${it.id}" }) { rel ->
                RelatedAnimePosterCard(
                    relation = rel,
                    onClick = { onAnimeClick(rel.toAniListMedia()) },
                )
            }
        }
    }
}

@Composable
fun RelatedAnimePosterCard(
    relation: AniListRelation,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(104.dp)
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(COVER_WIDTH_TO_HEIGHT_RATIO)
                .clip(MaterialTheme.shapes.small),
        ) {
            AsyncImage(
                model = relation.coverImage?.bestQualityUrl,
                contentDescription = relation.title.displayTitle,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Surface(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
                shape = RoundedCornerShape(bottomEnd = 8.dp),
                modifier = Modifier.align(Alignment.TopStart),
            ) {
                Text(
                    text = relation.displayBadge,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                )
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = relation.title.displayTitle,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Cuadrícula interactiva de episodios compartida entre el diálogo de [AnimeDetailsScreen] y la hoja
 * [EpisodeGrid] de [EpisodeScreen], estilizada como Animeko (`EpisodeListFlowRow` + `EpisodeSortSquareButton`).
 */
@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun AnimeEpisodeFlowGrid(
    totalEpisodes: Int,
    latestAiredEpisode: Int,
    currentEpisode: Int?,
    watchedEpisodes: Set<Int>,
    onEpisodeClick: (Int) -> Unit,
    onEpisodeLongClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val actionColor = MaterialTheme.colorScheme.primary
    val watchedColor = actionColor.weaken()
    val disabledColor = MaterialTheme.colorScheme.onSurface.stronglyWeaken()

    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        for (ep in 1..totalEpisodes) {
            val isWatched = ep in watchedEpisodes
            val isAired = latestAiredEpisode == 0 || ep <= latestAiredEpisode
            val isCurrent = ep == currentEpisode
            val containerColor = when {
                isWatched -> watchedColor
                !isAired -> disabledColor
                else -> actionColor
            }
            FilledTonalCombinedClickButton(
                onClick = { if (isAired) onEpisodeClick(ep) },
                onLongClick = { if (isAired) onEpisodeLongClick(ep) },
                enabled = isAired,
                modifier = Modifier
                    .heightIn(min = 48.dp)
                    .widthIn(min = 48.dp),
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
                border = if (isCurrent) BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary) else null,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = containerColor,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = disabledColor,
                    disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            ) {
                if (isCurrent) {
                    PlayingIcon(
                        contentDescription = "Reproduciendo",
                        width = 16.dp,
                        height = 10.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(Modifier.width(4.dp))
                }
                Text(
                    text = String.format("%02d", ep),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
fun AnimeEpisodeListDialog(
    subjectTitle: String,
    totalEpisodes: Int,
    latestAiredEpisode: Int,
    currentEpisode: Int?,
    watchedEpisodes: Set<Int>,
    onDismissRequest: () -> Unit,
    onEpisodeClick: (Int) -> Unit,
    onEpisodeLongClick: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = "Seleccionar episodio",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = subjectTitle,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(16.dp))

                Box(
                    Modifier
                        .weight(1f, fill = false)
                        .heightIn(max = 360.dp),
                ) {
                    Column(Modifier.verticalScroll(scrollState)) {
                        AnimeEpisodeFlowGrid(
                            totalEpisodes = totalEpisodes,
                            latestAiredEpisode = latestAiredEpisode,
                            currentEpisode = currentEpisode,
                            watchedEpisodes = watchedEpisodes,
                            onEpisodeClick = onEpisodeClick,
                            onEpisodeLongClick = onEpisodeLongClick,
                        )
                        Spacer(Modifier.height(12.dp))
                    }
                }

                HorizontalDivider()
                Spacer(Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Lightbulb,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Mantén pulsado para marcar como visto",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .align(Alignment.End),
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cerrar")
                    }
                }
            }
        }
    }
}

private fun formatAiringLabel(anime: AniListMedia): String {
    return when (anime.status?.uppercase()) {
        "RELEASING" -> {
            val aired = anime.latestAiredEpisode
            if (aired > 0) "En emisión · Ep. $aired" else "En emisión"
        }
        "FINISHED" -> "Finalizado"
        "NOT_YET_RELEASED" -> "Próximamente"
        "CANCELLED" -> "Cancelado"
        "HIATUS" -> "En pausa"
        else -> anime.status ?: "Disponible"
    }
}

private fun formatAiringShort(anime: AniListMedia, totalEpisodes: Int): String {
    return when {
        anime.status.equals("RELEASING", ignoreCase = true) && anime.latestAiredEpisode > 0 ->
            "Ep. ${anime.latestAiredEpisode} de $totalEpisodes"
        anime.status.equals("FINISHED", ignoreCase = true) ->
            "Finalizado · $totalEpisodes eps"
        else ->
            "$totalEpisodes eps"
    }
}

private fun formatCompactNumber(value: Int): String {
    return when {
        value >= 1_000_000 -> String.format("%.1fM", value / 1_000_000f)
        value >= 1_000 -> String.format("%.1fK", value / 1_000f)
        else -> value.toString()
    }
}
