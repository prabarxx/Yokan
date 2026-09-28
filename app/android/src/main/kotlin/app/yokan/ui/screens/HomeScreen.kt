package app.yokan.ui.screens

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
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
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
import androidx.compose.material3.Text
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

    LaunchedEffect(Unit) {
        if (!HomeCache.isLoaded) {
            isLoading = true
            val trendingRes = aniListClient.fetchTrending(1, 10)
            val popularRes = aniListClient.fetchPopular(1, 30)

            trendingRes.onSuccess {
                trendingList = it
                HomeCache.trendingList = it
            }
            popularRes.onSuccess {
                popularList = it
                HomeCache.popularList = it
            }
            HomeCache.isLoaded = true
            isLoading = false
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
        }
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
        } else {
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
                                        .clickable {
                                            // Find the anime in trending/popular or create a minimal reference
                                            val matchedAnime = (trendingList + popularList)
                                                .firstOrNull { it.id == progress.animeId }
                                            if (matchedAnime != null) {
                                                onAnimeClick(matchedAnime)
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
