package app.yokan.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.yokan.anilist.client.AniListClient
import app.yokan.anilist.model.AniListMedia
import app.yokan.ui.components.AnimePosterCard
import app.yokan.ui.components.TrendingCarousel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.him188.ani.app.ui.adaptive.AniTopAppBar
import me.him188.ani.app.ui.foundation.theme.appChromeHazeSource
import me.him188.ani.app.ui.subject.SubjectGridDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    aniListClient: AniListClient,
    onAnimeClick: (AniListMedia) -> Unit,
) {
    var trendingList by remember { mutableStateOf<List<AniListMedia>>(emptyList()) }
    var popularList by remember { mutableStateOf<List<AniListMedia>>(emptyList()) }
    var searchResults by remember { mutableStateOf<List<AniListMedia>>(emptyList()) }

    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    var isSearchLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }

    val layoutParams = SubjectGridDefaults.coverLayoutParameters()

    LaunchedEffect(Unit) {
        isLoading = true
        val trendingRes = aniListClient.fetchTrending(1, 10)
        val popularRes = aniListClient.fetchPopular(1, 30)

        trendingRes.onSuccess { trendingList = it }
        popularRes.onSuccess { popularList = it }
        isLoading = false
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
                                searchJob?.cancel()
                                if (query.isNotBlank()) {
                                    isSearchLoading = true
                                    searchJob = coroutineScope.launch {
                                        delay(350)
                                        val res = aniListClient.searchAnime(query, 1, 30)
                                        res.onSuccess {
                                            searchResults = it
                                            isSearchLoading = false
                                        }
                                    }
                                } else {
                                    searchResults = emptyList()
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
                            isSearching = !isSearching
                            if (!isSearching) {
                                searchQuery = ""
                                searchResults = emptyList()
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
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp),
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
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 32.dp),
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
