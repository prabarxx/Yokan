package app.yokan.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CloudOff
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.yokan.anilist.client.AniListClient
import app.yokan.anilist.model.AniListMedia
import app.yokan.media.WatchHistoryManager
import app.yokan.media.WatchProgress
import app.yokan.settings.YokanSettingsManager
import app.yokan.ui.components.AnimePosterCard
import app.yokan.ui.components.TrendingCarousel
import app.yokan.ui.state.HomeCache
import java.util.Calendar
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.him188.ani.app.ui.adaptive.AniTopAppBar
import me.him188.ani.app.ui.adaptive.navigation.AniNavigationSuiteDefaults
import me.him188.ani.app.ui.foundation.AsyncImage
import me.him188.ani.app.ui.foundation.layout.currentWindowAdaptiveInfo1
import me.him188.ani.app.ui.foundation.theme.LocalAppChromeOverlayInsets
import me.him188.ani.app.ui.foundation.theme.appChromeHazeSource
import me.him188.ani.app.ui.subject.SubjectGridDefaults

private val GENRE_OPTIONS: List<Pair<String, String>> = listOf(
    "Action" to "Acción",
    "Adventure" to "Aventura",
    "Comedy" to "Comedia",
    "Drama" to "Drama",
    "Fantasy" to "Fantasía",
    "Horror" to "Horror",
    "Mecha" to "Mecha",
    "Mystery" to "Misterio",
    "Psychological" to "Psicológico",
    "Romance" to "Romance",
    "Sci-Fi" to "Ciencia Ficción",
    "Slice of Life" to "Slice of Life",
    "Sports" to "Deportes",
    "Supernatural" to "Sobrenatural",
    "Thriller" to "Suspenso",
)

private fun currentAnimeSeasonAndYear(): Pair<String, Int> {
    val cal = Calendar.getInstance()
    val month = cal.get(Calendar.MONTH) // 0 = Jan
    val year = cal.get(Calendar.YEAR)
    val season = when (month) {
        in 0..2 -> "WINTER"
        in 3..5 -> "SPRING"
        in 6..8 -> "SUMMER"
        else -> "FALL"
    }
    return season to year
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    aniListClient: AniListClient,
    onAnimeClick: (AniListMedia) -> Unit,
    onEpisodeClick: ((AniListMedia, Int) -> Unit)? = null,
    onOpenSchedule: (() -> Unit)? = null,
    isSearchTab: Boolean = false,
    onSearchModeChange: ((Boolean) -> Unit)? = null,
) {
    val context = LocalContext.current
    val watchHistoryManager = remember { WatchHistoryManager.getInstance(context) }
    val settingsManager = remember { YokanSettingsManager.getInstance(context) }
    val watchHistory by watchHistoryManager.allHistory.collectAsState()
    val recentSearches by settingsManager.recentSearches.collectAsState()

    var trendingList by remember { mutableStateOf(HomeCache.trendingList) }
    var popularList by remember { mutableStateOf(HomeCache.popularList) }
    var popularPage by remember { mutableStateOf(HomeCache.popularPage) }
    var hasMorePopular by remember { mutableStateOf(HomeCache.hasMorePopular) }
    var isLoadingMorePopular by remember { mutableStateOf(false) }

    var searchResults by remember { mutableStateOf(HomeCache.searchResults) }
    var searchPage by remember { mutableStateOf(HomeCache.searchPage) }
    var hasMoreSearch by remember { mutableStateOf(HomeCache.hasMoreSearch) }
    var isLoadingMoreSearch by remember { mutableStateOf(false) }

    var isSearching by remember { mutableStateOf(isSearchTab || HomeCache.isSearching) }
    var searchQuery by remember { mutableStateOf(HomeCache.searchQuery) }
    var selectedGenre by remember { mutableStateOf(HomeCache.searchGenre) }
    var selectedFormat by remember { mutableStateOf(HomeCache.searchFormat) }
    var selectedStatus by remember { mutableStateOf(HomeCache.searchStatus) }
    var selectedSort by remember { mutableStateOf(HomeCache.searchSort) }
    var currentSeasonOnly by remember { mutableStateOf(HomeCache.searchCurrentSeasonOnly) }
    var isLoading by remember { mutableStateOf(!HomeCache.isLoaded) }
    var isSearchLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }

    var loadError by remember { mutableStateOf(false) }
    var isRefreshing by remember { mutableStateOf(false) }
    var openingAnimeId by remember { mutableStateOf<Int?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val haptics = LocalHapticFeedback.current

    // Recuerda el scroll de la grilla principal al abrir un detalle y volver
    val gridState = rememberLazyGridState(
        initialFirstVisibleItemIndex = HomeCache.gridScrollIndex,
        initialFirstVisibleItemScrollOffset = HomeCache.gridScrollOffset,
    )
    DisposableEffect(Unit) {
        onDispose {
            HomeCache.gridScrollIndex = gridState.firstVisibleItemIndex
            HomeCache.gridScrollOffset = gridState.firstVisibleItemScrollOffset
        }
    }

    // Recuerda el scroll de la grilla de búsqueda al abrir un detalle y volver
    val searchGridState = rememberLazyGridState(
        initialFirstVisibleItemIndex = HomeCache.searchGridScrollIndex,
        initialFirstVisibleItemScrollOffset = HomeCache.searchGridScrollOffset,
    )
    DisposableEffect(Unit) {
        onDispose {
            HomeCache.searchGridScrollIndex = searchGridState.firstVisibleItemIndex
            HomeCache.searchGridScrollOffset = searchGridState.firstVisibleItemScrollOffset
        }
    }

    val layoutParams = SubjectGridDefaults.coverLayoutParameters()

    /** Carga la siguiente página del catálogo de populares al hacer scroll infinito */
    fun loadNextPopularPage() {
        if (isLoadingMorePopular || !hasMorePopular || isRefreshing || isLoading) return
        isLoadingMorePopular = true
        coroutineScope.launch {
            val nextPage = popularPage + 1
            val res = aniListClient.fetchPopular(nextPage, 30)
            res.onSuccess { newItems ->
                if (newItems.isNotEmpty()) {
                    val existingIds = popularList.map { it.id }.toSet()
                    val filtered = newItems.filter { it.id !in existingIds }
                    popularList = popularList + filtered
                    popularPage = nextPage
                    hasMorePopular = newItems.size >= 30
                    HomeCache.popularList = popularList
                    HomeCache.popularPage = popularPage
                    HomeCache.hasMorePopular = hasMorePopular
                } else {
                    hasMorePopular = false
                    HomeCache.hasMorePopular = false
                }
            }
            isLoadingMorePopular = false
        }
    }

    val hasActiveFilters = selectedGenre != null ||
        selectedFormat != null ||
        selectedStatus != null ||
        selectedSort != null ||
        currentSeasonOnly
    val hasActiveSearch = searchQuery.isNotBlank() || hasActiveFilters

    /** Ejecuta búsqueda o exploración filtrada en AniList con debounce opcional */
    fun executeDiscoverySearch(
        query: String = searchQuery,
        genre: String? = selectedGenre,
        format: String? = selectedFormat,
        status: String? = selectedStatus,
        sort: String? = selectedSort,
        seasonOnly: Boolean = currentSeasonOnly,
        debounceMs: Long = 0L,
    ) {
        searchQuery = query
        selectedGenre = genre
        selectedFormat = format
        selectedStatus = status
        selectedSort = sort
        currentSeasonOnly = seasonOnly

        HomeCache.searchQuery = query
        HomeCache.searchGenre = genre
        HomeCache.searchFormat = format
        HomeCache.searchStatus = status
        HomeCache.searchSort = sort
        HomeCache.searchCurrentSeasonOnly = seasonOnly

        searchJob?.cancel()
        val trimmed = query.trim()
        val anyActive = trimmed.isNotBlank() || genre != null || format != null || status != null || sort != null || seasonOnly
        if (!anyActive) {
            searchResults = emptyList()
            searchPage = 1
            hasMoreSearch = true
            HomeCache.searchResults = emptyList()
            HomeCache.searchPage = 1
            HomeCache.hasMoreSearch = true
            isSearchLoading = false
            return
        }

        val (seasonName, seasonYear) = if (seasonOnly) currentAnimeSeasonAndYear() else (null to null)
        isSearchLoading = true
        searchJob = coroutineScope.launch {
            if (debounceMs > 0L) delay(debounceMs)
            val res = aniListClient.discoverAnime(
                searchQuery = trimmed.takeIf { it.isNotBlank() },
                genre = genre,
                format = format,
                status = status,
                season = seasonName,
                seasonYear = seasonYear,
                sort = sort,
                page = 1,
                perPage = 30,
            )
            res.onSuccess {
                searchResults = it
                searchPage = 1
                hasMoreSearch = it.size >= 30
                HomeCache.searchResults = it
                HomeCache.searchPage = 1
                HomeCache.hasMoreSearch = hasMoreSearch
                isSearchLoading = false
            }.onFailure {
                if (isActive) {
                    isSearchLoading = false
                    snackbarHostState.showSnackbar("No se pudo buscar. Revisa tu conexión.")
                }
            }
        }
    }

    /** Carga la siguiente página de resultados de búsqueda/filtros al hacer scroll infinito */
    fun loadNextSearchPage() {
        val query = searchQuery.trim()
        if (isLoadingMoreSearch || !hasMoreSearch || isSearchLoading || !hasActiveSearch) return
        isLoadingMoreSearch = true
        val (seasonName, seasonYear) = if (currentSeasonOnly) currentAnimeSeasonAndYear() else (null to null)
        coroutineScope.launch {
            val nextPage = searchPage + 1
            val res = aniListClient.discoverAnime(
                searchQuery = query.takeIf { it.isNotBlank() },
                genre = selectedGenre,
                format = selectedFormat,
                status = selectedStatus,
                season = seasonName,
                seasonYear = seasonYear,
                sort = selectedSort,
                page = nextPage,
                perPage = 30,
            )
            res.onSuccess { newItems ->
                if (newItems.isNotEmpty()) {
                    val existingIds = searchResults.map { it.id }.toSet()
                    val filtered = newItems.filter { it.id !in existingIds }
                    searchResults = searchResults + filtered
                    searchPage = nextPage
                    hasMoreSearch = newItems.size >= 30
                    HomeCache.searchResults = searchResults
                    HomeCache.searchPage = searchPage
                    HomeCache.hasMoreSearch = hasMoreSearch
                } else {
                    hasMoreSearch = false
                    HomeCache.hasMoreSearch = false
                }
            }
            isLoadingMoreSearch = false
        }
    }

    // Detección de scroll para paginación de populares (4-6 elementos antes del final)
    val shouldLoadMorePopular = remember {
        derivedStateOf {
            val totalItems = gridState.layoutInfo.totalItemsCount
            val lastVisibleIndex = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            totalItems > 0 && lastVisibleIndex >= totalItems - 6
        }
    }

    LaunchedEffect(shouldLoadMorePopular.value) {
        if (shouldLoadMorePopular.value && !isLoadingMorePopular && hasMorePopular && !isLoading && !isRefreshing) {
            loadNextPopularPage()
        }
    }

    // Detección de scroll para paginación de búsqueda (4-6 elementos antes del final)
    val shouldLoadMoreSearch = remember {
        derivedStateOf {
            val totalItems = searchGridState.layoutInfo.totalItemsCount
            val lastVisibleIndex = searchGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            totalItems > 0 && lastVisibleIndex >= totalItems - 6
        }
    }

    LaunchedEffect(shouldLoadMoreSearch.value) {
        if (shouldLoadMoreSearch.value && !isLoadingMoreSearch && hasMoreSearch && !isSearchLoading && hasActiveSearch) {
            loadNextSearchPage()
        }
    }

    LaunchedEffect(isSearchTab) {
        if (isSearchTab != isSearching) {
            isSearching = isSearchTab
            HomeCache.isSearching = isSearchTab
        }
    }

    val setSearching: (Boolean) -> Unit = { active ->
        isSearching = active
        HomeCache.isSearching = active
        onSearchModeChange?.invoke(active)
    }

    /** Descarga trending y populares en paralelo. Devuelve true solo si ambas peticiones tuvieron éxito. */
    suspend fun loadHome(): Boolean {
        val trending = coroutineScope.async { aniListClient.fetchTrending(1, 10) }
        val popular = coroutineScope.async { aniListClient.fetchPopular(1, 30) }
        val trendingRes = trending.await()
        val popularRes = popular.await()

        trendingRes.onSuccess {
            trendingList = it
            HomeCache.trendingList = it
        }
        popularRes.onSuccess {
            popularList = it
            popularPage = 1
            hasMorePopular = it.size >= 30
            HomeCache.popularList = it
            HomeCache.popularPage = 1
            HomeCache.hasMorePopular = hasMorePopular
        }

        val ok = trendingRes.isSuccess && popularRes.isSuccess
        // Solo se marca como cargado si todo salió bien; así se reintenta al volver a entrar.
        HomeCache.isLoaded = ok
        // Pantalla de error solo si no hay nada que mostrar
        loadError = !ok && trendingList.isEmpty() && popularList.isEmpty()
        return ok
    }

    /** Recarga el catálogo (pull-to-refresh, "Reintentar" y carga inicial). */
    fun refresh() {
        if (isRefreshing) return
        isRefreshing = true
        coroutineScope.launch {
            var ok = false
            try {
                ok = loadHome()
            } finally {
                isRefreshing = false
                isLoading = false
            }
            if (!ok && !loadError) {
                // Hay datos en pantalla pero la actualización falló (total o parcialmente)
                snackbarHostState.showSnackbar("No se pudo actualizar. Revisa tu conexión.")
            }
        }
    }

    LaunchedEffect(Unit) {
        if (!HomeCache.isLoaded) {
            isLoading = true
            refresh()
        }
    }

    val overlayBottom = LocalAppChromeOverlayInsets.current.asPaddingValues().calculateBottomPadding()
    val isNavBar = AniNavigationSuiteDefaults.calculateLayoutType(currentWindowAdaptiveInfo1()) == NavigationSuiteType.NavigationBar
    val navBarSystemBottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val snackbarBottomPadding = if (isNavBar) {
        if (overlayBottom > 0.dp) overlayBottom + 12.dp else 80.dp + navBarSystemBottom + 12.dp
    } else {
        16.dp
    }

    /** Quita una tarjeta de "Continuar viendo" (pulsación larga) con opción de deshacer. */
    fun removeFromHistory(progress: WatchProgress) {
        watchHistoryManager.removeProgress(progress.animeId)
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val result = snackbarHostState.showSnackbar(
                message = "Quitado de Continuar viendo",
                actionLabel = "Deshacer",
                duration = SnackbarDuration.Long,
            )
            if (result == SnackbarResult.ActionPerformed) {
                watchHistoryManager.restoreProgress(progress)
            }
        }
    }

    Scaffold(
        topBar = {
            AniTopAppBar(
                title = {
                    if (isSearching) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { query ->
                                executeDiscoverySearch(query = query, debounceMs = 350L)
                            },
                            placeholder = { Text("Buscar anime o filtrar...") },
                            singleLine = true,
                            shape = RoundedCornerShape(24.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = Color.Transparent,
                                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 8.dp),
                        )
                    } else {
                        Text(
                            text = "Yokan",
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                },
                actions = {
                    if (!isSearching && onOpenSchedule != null) {
                        IconButton(onClick = onOpenSchedule) {
                            Icon(
                                imageVector = Icons.Rounded.CalendarMonth,
                                contentDescription = "Horario semanal",
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            val newMode = !isSearching
                            setSearching(newMode)
                            if (!newMode) {
                                executeDiscoverySearch(
                                    query = "",
                                    genre = null,
                                    format = null,
                                    status = null,
                                    sort = null,
                                    seasonOnly = false,
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (isSearching) Icons.Rounded.Close else Icons.Rounded.Search,
                            contentDescription = if (isSearching) "Cerrar" else "Buscar",
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = snackbarBottomPadding),
            ) { data ->
                Snackbar(
                    snackbarData = data,
                    shape = RoundedCornerShape(12.dp),
                )
            }
        },
    ) { padding ->
        if (isLoading) {
            HomeSkeleton(
                gridCells = layoutParams.gridCells,
                horizontalArrangement = layoutParams.horizontalArrangement,
                verticalArrangement = layoutParams.verticalArrangement,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            )
            return@Scaffold
        }

        if (isSearching) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                // Barra rápida de filtros
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    FilterChip(
                        selected = currentSeasonOnly,
                        onClick = {
                            executeDiscoverySearch(seasonOnly = !currentSeasonOnly)
                        },
                        label = { Text("Temporada", fontSize = 12.sp) },
                    )
                    FilterChip(
                        selected = selectedStatus == "RELEASING",
                        onClick = {
                            executeDiscoverySearch(
                                status = if (selectedStatus == "RELEASING") null else "RELEASING",
                            )
                        },
                        label = { Text("En emisión", fontSize = 12.sp) },
                    )
                    FilterChip(
                        selected = selectedSort == "SCORE_DESC",
                        onClick = {
                            executeDiscoverySearch(
                                sort = if (selectedSort == "SCORE_DESC") null else "SCORE_DESC",
                            )
                        },
                        label = { Text("Mejor nota", fontSize = 12.sp) },
                    )
                    FilterChip(
                        selected = selectedFormat == "TV",
                        onClick = {
                            executeDiscoverySearch(
                                format = if (selectedFormat == "TV") null else "TV",
                            )
                        },
                        label = { Text("Series TV", fontSize = 12.sp) },
                    )
                    FilterChip(
                        selected = selectedFormat == "MOVIE",
                        onClick = {
                            executeDiscoverySearch(
                                format = if (selectedFormat == "MOVIE") null else "MOVIE",
                            )
                        },
                        label = { Text("Películas", fontSize = 12.sp) },
                    )
                    selectedGenre?.let { genreKey ->
                        val genreLabel = GENRE_OPTIONS.firstOrNull { it.first == genreKey }?.second ?: genreKey
                        FilterChip(
                            selected = true,
                            onClick = { executeDiscoverySearch(genre = null) },
                            label = { Text(genreLabel, fontSize = 12.sp) },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = "Quitar género",
                                    modifier = Modifier.size(16.dp),
                                )
                            },
                        )
                    }
                    if (hasActiveFilters) {
                        TextButton(
                            onClick = {
                                executeDiscoverySearch(
                                    genre = null,
                                    format = null,
                                    status = null,
                                    sort = null,
                                    seasonOnly = false,
                                )
                            },
                        ) {
                            Text("Limpiar", fontSize = 12.sp)
                        }
                    }
                }

                when {
                    !hasActiveSearch -> {
                        // Vista de descubrimiento antes de escribir: recientes, atajos y géneros
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .appChromeHazeSource(MaterialTheme.colorScheme.background)
                                .verticalScroll(rememberScrollState())
                                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 104.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                        ) {
                            if (recentSearches.isNotEmpty()) {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = "Búsquedas recientes",
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        TextButton(onClick = { settingsManager.clearRecentSearches() }) {
                                            Text("Borrar todo", fontSize = 12.sp)
                                        }
                                    }
                                    FlowRow(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                    ) {
                                        recentSearches.forEach { term ->
                                            Surface(
                                                onClick = { executeDiscoverySearch(query = term) },
                                                shape = RoundedCornerShape(50),
                                                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                                                border = BorderStroke(
                                                    1.dp,
                                                    MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                                                ),
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(start = 12.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Rounded.History,
                                                        contentDescription = null,
                                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                        modifier = Modifier.size(15.dp),
                                                    )
                                                    Text(
                                                        text = term,
                                                        style = MaterialTheme.typography.bodySmall,
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis,
                                                    )
                                                    Icon(
                                                        imageVector = Icons.Rounded.Close,
                                                        contentDescription = "Eliminar búsqueda",
                                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                        modifier = Modifier
                                                            .size(16.dp)
                                                            .clip(CircleShape)
                                                            .clickable { settingsManager.removeRecentSearch(term) },
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                Text(
                                    text = "Explorar por género",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                )
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    GENRE_OPTIONS.forEach { (apiKey, label) ->
                                        Surface(
                                            onClick = { executeDiscoverySearch(genre = apiKey) },
                                            shape = RoundedCornerShape(12.dp),
                                            color = MaterialTheme.colorScheme.surfaceContainerLow,
                                            border = BorderStroke(
                                                1.dp,
                                                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                                            ),
                                        ) {
                                            Text(
                                                text = label,
                                                style = MaterialTheme.typography.bodySmall,
                                                fontWeight = FontWeight.Medium,
                                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    isSearchLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    searchResults.isEmpty() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = if (searchQuery.isNotBlank()) {
                                    "No se encontraron animes para \"$searchQuery\""
                                } else {
                                    "No hay resultados para los filtros seleccionados"
                                },
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    else -> {
                        LazyVerticalGrid(
                            columns = layoutParams.gridCells,
                            state = searchGridState,
                            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 100.dp),
                            horizontalArrangement = layoutParams.horizontalArrangement,
                            verticalArrangement = layoutParams.verticalArrangement,
                            modifier = Modifier
                                .fillMaxSize()
                                .appChromeHazeSource(MaterialTheme.colorScheme.background),
                        ) {
                            items(searchResults, key = { it.id }) { anime ->
                                AnimePosterCard(
                                    anime = anime,
                                    onClick = {
                                        if (searchQuery.isNotBlank()) {
                                            settingsManager.addRecentSearch(searchQuery)
                                        }
                                        onAnimeClick(anime)
                                    }
                                )
                            }

                            if (isLoadingMoreSearch) {
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(28.dp),
                                            strokeWidth = 2.5.dp,
                                            color = MaterialTheme.colorScheme.primary,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (loadError) {
            HomeErrorState(
                onRetry = {
                    loadError = false
                    isLoading = true
                    refresh()
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            )
        } else {
            val pullState = rememberPullToRefreshState()
            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = { refresh() },
                state = pullState,
                modifier = Modifier.fillMaxSize(),
                indicator = {
                    PullToRefreshDefaults.Indicator(
                        state = pullState,
                        isRefreshing = isRefreshing,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = padding.calculateTopPadding()),
                    )
                },
            ) {
                LazyVerticalGrid(
                    columns = layoutParams.gridCells,
                    state = gridState,
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 100.dp),
                    horizontalArrangement = layoutParams.horizontalArrangement,
                    verticalArrangement = layoutParams.verticalArrangement,
                    modifier = Modifier
                        .fillMaxSize()
                        .appChromeHazeSource(MaterialTheme.colorScheme.background)
                        .padding(padding),
                ) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        TrendingCarousel(
                            trendingList = trendingList,
                            onAnimeClick = onAnimeClick,
                            modifier = Modifier.padding(bottom = 8.dp),
                        )
                    }

                    // "Continuar viendo" section
                    val activeHistory = watchHistory.filter { !it.isFinished }
                    if (activeHistory.isNotEmpty()) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Text(
                                text = "Continuar viendo",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                            )
                        }
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                contentPadding = PaddingValues(bottom = 12.dp),
                            ) {
                                items(activeHistory, key = { it.animeId }) { progress ->
                                    Card(
                                        modifier = Modifier
                                            .width(130.dp)
                                            .combinedClickable(
                                                enabled = openingAnimeId == null,
                                                onLongClick = {
                                                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                                    removeFromHistory(progress)
                                                },
                                            ) {
                                                val cached = (trendingList + popularList + searchResults)
                                                    .firstOrNull { it.id == progress.animeId }
                                                if (cached != null) {
                                                    if (onEpisodeClick != null) {
                                                        onEpisodeClick(cached, progress.episode)
                                                    } else {
                                                        onAnimeClick(cached)
                                                    }
                                                } else if (progress.animeId < 0) {
                                                    // Versión especial exclusiva de AnimeAV1 (ID negativo): reconstruir desde WatchProgress
                                                    val specialMedia = progress.toFallbackAniListMedia()
                                                    if (onEpisodeClick != null) {
                                                        onEpisodeClick(specialMedia, progress.episode)
                                                    } else {
                                                        onAnimeClick(specialMedia)
                                                    }
                                                } else {
                                                    // No está en las listas en memoria: se pide a AniList por su id
                                                    openingAnimeId = progress.animeId
                                                    coroutineScope.launch {
                                                        aniListClient.getAnimeDetails(progress.animeId)
                                                            .onSuccess { anime ->
                                                                if (onEpisodeClick != null) {
                                                                    onEpisodeClick(anime, progress.episode)
                                                                } else {
                                                                    onAnimeClick(anime)
                                                                }
                                                            }
                                                            .onFailure {
                                                                if (onEpisodeClick != null && progress.animeTitle.isNotBlank()) {
                                                                    onEpisodeClick(progress.toFallbackAniListMedia(), progress.episode)
                                                                } else if (isActive) {
                                                                    snackbarHostState.showSnackbar("No se pudo abrir el anime. Revisa tu conexión.")
                                                                }
                                                            }
                                                        openingAnimeId = null
                                                    }
                                                }
                                            },
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                                        ),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                    ) {
                                        Box {
                                            if (!progress.coverUrl.isNullOrBlank()) {
                                                AsyncImage(
                                                    model = progress.coverUrl,
                                                    contentDescription = progress.animeTitle,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(160.dp),
                                                    contentScale = ContentScale.Crop,
                                                )
                                            } else {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(160.dp),
                                                    contentAlignment = Alignment.Center,
                                                ) {
                                                    Text(
                                                        text = progress.animeTitle.take(2),
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 32.sp,
                                                    )
                                                }
                                            }
                                            // Play icon badge
                                            Box(
                                                modifier = Modifier
                                                    .size(36.dp)
                                                    .clip(CircleShape)
                                                    .background(Color.Black.copy(alpha = 0.55f))
                                                    .align(Alignment.Center),
                                                contentAlignment = Alignment.Center,
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Rounded.PlayArrow,
                                                    contentDescription = "Continuar viendo",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(22.dp),
                                                )
                                            }
                                            // Progress bar at bottom of image
                                            LinearProgressIndicator(
                                                progress = { progress.progressFraction },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(3.dp)
                                                    .align(Alignment.BottomStart),
                                                color = Color(0xFFE0E0E0),
                                                trackColor = Color.Black.copy(alpha = 0.3f),
                                            )
                                            if (openingAnimeId == progress.animeId) {
                                                Box(
                                                    modifier = Modifier
                                                        .matchParentSize()
                                                        .background(Color.Black.copy(alpha = 0.5f)),
                                                    contentAlignment = Alignment.Center,
                                                ) {
                                                    CircularProgressIndicator(
                                                        modifier = Modifier.size(28.dp),
                                                        strokeWidth = 3.dp,
                                                    )
                                                }
                                            }
                                        }
                                        // Title + episode label
                                        Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                                            Column {
                                                Text(
                                                    text = progress.animeTitle,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    fontWeight = FontWeight.SemiBold,
                                                    maxLines = 2,
                                                    overflow = TextOverflow.Ellipsis,
                                                    lineHeight = 16.sp,
                                                )
                                                Spacer(modifier = Modifier.height(2.dp))
                                                Text(
                                                    text = "Ep ${progress.episode}",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = "Más Populares",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                    }

                    items(
                        items = popularList,
                        key = { it.id }
                    ) { anime ->
                        AnimePosterCard(
                            anime = anime,
                            onClick = { onAnimeClick(anime) }
                        )
                    }

                    if (isLoadingMorePopular) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(28.dp),
                                    strokeWidth = 2.5.dp,
                                    color = MaterialTheme.colorScheme.primary,
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
private fun HomeErrorState(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Rounded.CloudOff,
            contentDescription = null,
            modifier = Modifier.size(56.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No se pudo cargar el catálogo",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Revisa tu conexión a internet e inténtalo de nuevo.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRetry) {
            Icon(
                imageVector = Icons.Rounded.Refresh,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Reintentar")
        }
    }
}

/** Marcador de posición con pulso suave mientras carga el catálogo. */
@Composable
private fun HomeSkeleton(
    gridCells: GridCells,
    horizontalArrangement: Arrangement.Horizontal,
    verticalArrangement: Arrangement.Vertical,
    modifier: Modifier = Modifier,
) {
    val transition = rememberInfiniteTransition(label = "skeleton")
    val pulse by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(tween(durationMillis = 800), RepeatMode.Reverse),
        label = "skeletonPulse",
    )
    val color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = pulse)

    LazyVerticalGrid(
        columns = gridCells,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 100.dp),
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement,
        userScrollEnabled = false,
        modifier = modifier,
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(modifier = Modifier.padding(bottom = 12.dp)) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(160.dp)
                        .height(18.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(color),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(color),
                )
            }
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .width(120.dp)
                    .height(18.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color),
            )
        }
        items(9) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.7f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color),
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(12.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(color),
                )
            }
        }
    }
}
