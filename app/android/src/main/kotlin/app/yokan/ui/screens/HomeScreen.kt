package app.yokan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudOff
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.yokan.anilist.client.AniListClient
import app.yokan.anilist.model.AniListMedia
import app.yokan.media.WatchHistoryManager
import app.yokan.ui.components.AnimePosterCard
import app.yokan.ui.components.TrendingCarousel
import app.yokan.ui.state.HomeCache
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.him188.ani.app.ui.adaptive.AniTopAppBar
import me.him188.ani.app.ui.foundation.AsyncImage
import me.him188.ani.app.ui.foundation.theme.appChromeHazeSource
import me.him188.ani.app.ui.subject.SubjectGridDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    aniListClient: AniListClient,
    onAnimeClick: (AniListMedia) -> Unit,
    isSearchTab: Boolean = false,
    onSearchModeChange: ((Boolean) -> Unit)? = null,
) {
    val context = LocalContext.current
    val watchHistoryManager = remember { WatchHistoryManager.getInstance(context) }
    val watchHistory by watchHistoryManager.allHistory.collectAsState()

    var trendingList by remember { mutableStateOf(HomeCache.trendingList) }
    var popularList by remember { mutableStateOf(HomeCache.popularList) }
    var searchResults by remember { mutableStateOf(HomeCache.searchResults) }

    var isSearching by remember { mutableStateOf(isSearchTab || HomeCache.isSearching) }
    var searchQuery by remember { mutableStateOf(HomeCache.searchQuery) }
    var isLoading by remember { mutableStateOf(!HomeCache.isLoaded) }
    var isSearchLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }

    var loadError by remember { mutableStateOf(false) }
    var isRefreshing by remember { mutableStateOf(false) }
    var openingAnimeId by remember { mutableStateOf<Int?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    val layoutParams = SubjectGridDefaults.coverLayoutParameters()

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
            HomeCache.popularList = it
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

    Scaffold(
        topBar = {
            AniTopAppBar(
                title = {
                    if (isSearching) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { query ->
                                searchQuery = query
                                HomeCache.searchQuery = query
                                searchJob?.cancel()
                                if (query.isNotBlank()) {
                                    isSearchLoading = true
                                    searchJob = coroutineScope.launch {
                                        delay(350)
                                        val res = aniListClient.searchAnime(query, 1, 30)
                                        res.onSuccess {
                                            searchResults = it
                                            HomeCache.searchResults = it
                                            isSearchLoading = false
                                        }.onFailure {
                                            // isActive: el cliente puede devolver Failure si esta búsqueda fue cancelada por otra
                                            if (isActive) {
                                                isSearchLoading = false
                                                snackbarHostState.showSnackbar("No se pudo buscar. Revisa tu conexión.")
                                            }
                                        }
                                    }
                                } else {
                                    searchResults = emptyList()
                                    HomeCache.searchResults = emptyList()
                                    isSearchLoading = false
                                }
                            },
                            placeholder = { Text("Buscar anime...") },
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
                    IconButton(
                        onClick = {
                            val newMode = !isSearching
                            setSearching(newMode)
                            if (!newMode) {
                                searchQuery = ""
                                searchResults = emptyList()
                                HomeCache.searchQuery = ""
                                HomeCache.searchResults = emptyList()
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
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        if (isSearching) {
            if (isSearchLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else if (searchQuery.isNotBlank() && searchResults.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "No se encontraron animes para \"$searchQuery\"",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = layoutParams.gridCells,
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 100.dp),
                    horizontalArrangement = layoutParams.horizontalArrangement,
                    verticalArrangement = layoutParams.verticalArrangement,
                    modifier = Modifier
                        .fillMaxSize()
                        .appChromeHazeSource(MaterialTheme.colorScheme.background)
                        .padding(padding),
                ) {
                    items(searchResults, key = { it.id }) { anime ->
                        AnimePosterCard(
                            anime = anime,
                            onClick = { onAnimeClick(anime) }
                        )
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
                            modifier = Modifier.padding(bottom = 12.dp),
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
                                            .clickable(enabled = openingAnimeId == null) {
                                                val cached = (trendingList + popularList + searchResults)
                                                    .firstOrNull { it.id == progress.animeId }
                                                if (cached != null) {
                                                    onAnimeClick(cached)
                                                } else {
                                                    // No está en las listas en memoria: se pide a AniList por su id
                                                    openingAnimeId = progress.animeId
                                                    coroutineScope.launch {
                                                        aniListClient.getAnimeDetails(progress.animeId)
                                                            .onSuccess { onAnimeClick(it) }
                                                            .onFailure {
                                                                if (isActive) {
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
