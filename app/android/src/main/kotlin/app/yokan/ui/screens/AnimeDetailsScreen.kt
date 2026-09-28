package app.yokan.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import me.him188.ani.app.ui.foundation.AsyncImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimeDetailsScreen(
    animeId: Int,
    initialAnime: AniListMedia?,
    aniListClient: AniListClient,
    onEpisodeClick: (AniListMedia, Int) -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val watchHistoryManager = remember { WatchHistoryManager.getInstance(context) }
    val watchHistory by watchHistoryManager.allHistory.collectAsState()

    var animeDetails by remember { mutableStateOf<AniListMedia?>(initialAnime) }
    var isLoading by remember { mutableStateOf(animeDetails == null) }
    var isSynopsisExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(animeId) {
        val result = aniListClient.getAnimeDetails(animeId)
        result.onSuccess {
            animeDetails = it
            isLoading = false
        }
    }

    val anime = animeDetails

    Scaffold { padding ->
        if (anime == null && isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        if (anime == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No se pudo cargar la información del anime.")
            }
            return@Scaffold
        }

        val totalEps = anime.effectiveEpisodesCount
        val savedProgress = watchHistory.firstOrNull { it.animeId == anime.id }
        val hasSavedProgress = savedProgress != null && !savedProgress.isFinished
        val buttonEpisode = if (hasSavedProgress) savedProgress!!.episode else 1
        val buttonLabel = if (hasSavedProgress) {
            val mins = (savedProgress!!.positionMillis / 1000) / 60
            val secs = (savedProgress.positionMillis / 1000) % 60
            "Continuar — Ep $buttonEpisode (${mins}:${secs.toString().padStart(2, '0')})"
        } else {
            "Comenzar a ver (Ep 1)"
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            // ── HEADER: fondo desenfocado + póster + info (estilo Animeko compact) ──
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            ) {
                val bannerUrl = anime.bannerImage?.takeIf { it.isNotBlank() }
                    ?: anime.coverImage?.bestQualityUrl

                // Fondo desenfocado
                if (!bannerUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = bannerUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().blur(28.dp),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF1A1A2E)))
                }

                // Degradado oscuro
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.45f),
                                    Color.Black.copy(alpha = 0.80f),
                                    MaterialTheme.colorScheme.background,
                                ),
                            )
                        )
                )

                // Botón volver (top-start)
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .padding(top = 12.dp, start = 4.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White,
                    )
                }

                // Póster + columna info (estilo SubjectDetailsHeaderCompact)
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    // Póster
                    val coverUrl = anime.coverImage?.bestQualityUrl
                    Card(
                        modifier = Modifier
                            .width(110.dp)
                            .aspectRatio(0.72f),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    ) {
                        if (!coverUrl.isNullOrBlank()) {
                            AsyncImage(
                                model = coverUrl,
                                contentDescription = anime.title.displayTitle,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }

                    // Columna derecha: título + metadata
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        // Título principal
                        Text(
                            text = anime.title.displayTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 20.sp,
                        )

                        // Título nativo si difiere
                        val nativeTitle = anime.title.native
                        if (!nativeTitle.isNullOrBlank() && nativeTitle != anime.title.displayTitle) {
                            Text(
                                text = nativeTitle,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.65f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }

                        // Estado + episodios
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            val status = anime.status
                            if (!status.isNullOrBlank()) {
                                val statusLabel = when (status.uppercase()) {
                                    "RELEASING" -> "En emisión"
                                    "FINISHED" -> "Finalizado"
                                    "NOT_YET_RELEASED" -> "Próximamente"
                                    else -> status
                                }
                                Text(
                                    text = statusLabel,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                )
                                Text(
                                    text = "·",
                                    color = Color.White.copy(alpha = 0.5f),
                                    fontSize = 10.sp,
                                )
                            }
                            if (totalEps > 0) {
                                Text(
                                    text = "$totalEps eps",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White.copy(alpha = 0.75f),
                                )
                            }
                        }

                        // Score con estrella
                        val score = anime.averageScore
                        if (score != null && score > 0) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(3.dp),
                            ) {
                                Icon(
                                    Icons.Rounded.Star,
                                    contentDescription = null,
                                    tint = Color(0xFFFFD700),
                                    modifier = Modifier.size(14.dp),
                                )
                                Text(
                                    text = "${score / 10.0f}",
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                )
                                Text(
                                    text = "(${score}%)",
                                    color = Color.White.copy(alpha = 0.6f),
                                    fontSize = 11.sp,
                                )
                            }
                        }
                    }
                }
            }

            // ── BOTÓN PRINCIPAL: Comenzar / Continuar ──
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Button(
                    onClick = { onEpisodeClick(anime, buttonEpisode) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                ) {
                    Icon(Icons.Rounded.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = buttonLabel,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )
                }
                // Barra de progreso gris claro si hay progreso guardado
                if (hasSavedProgress) {
                    Spacer(modifier = Modifier.height(5.dp))
                    LinearProgressIndicator(
                        progress = { savedProgress!!.progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = Color(0xFFE0E0E0),
                        trackColor = Color.White.copy(alpha = 0.18f),
                    )
                }
            }

            // ── SECCIÓN EPISODIOS (LazyRow horizontal estilo Animeko) ──
            if (totalEps > 0) {
                // Header de sección
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Episodios",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                    )
                    val statusLabel = when (anime.status?.uppercase()) {
                        "FINISHED" -> "Completado"
                        "RELEASING" -> "En emisión"
                        else -> null
                    }
                    if (statusLabel != null) {
                        Text(
                            text = "$statusLabel · $totalEps eps",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                // LazyRow de celdas estilo EpisodeGridCell de Animeko
                val listState = rememberLazyListState()
                // Scroll automático al episodio actual
                LaunchedEffect(savedProgress?.episode) {
                    val targetEp = savedProgress?.episode ?: 1
                    if (targetEp > 1) listState.scrollToItem((targetEp - 1).coerceAtLeast(0))
                }

                LazyRow(
                    state = listState,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(totalEps) { index ->
                        val epNum = index + 1
                        val isCurrentEpisode = hasSavedProgress && epNum == savedProgress?.episode

                        val containerColor = when {
                            isCurrentEpisode -> MaterialTheme.colorScheme.primaryContainer
                            else -> MaterialTheme.colorScheme.surfaceContainerHigh
                        }
                        val epNumColor = when {
                            isCurrentEpisode -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.onSurface
                        }

                        Surface(
                            modifier = Modifier
                                .width(72.dp)
                                .height(64.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onEpisodeClick(anime, epNum) },
                            color = containerColor,
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Text(
                                    text = "$epNum",
                                    color = epNumColor,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                )
                                Text(
                                    text = "Ep $epNum",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.labelSmall,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                )
            }

            // ── SINOPSIS ──
            if (anime.cleanDescription.isNotBlank()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .animateContentSize()
                ) {
                    Text(
                        text = "Sinopsis",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 6.dp),
                    )
                    Text(
                        text = anime.cleanDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = if (isSynopsisExpanded) Int.MAX_VALUE else 4,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 22.sp,
                    )
                    Text(
                        text = if (isSynopsisExpanded) "Mostrar menos" else "Leer más...",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .clickable { isSynopsisExpanded = !isSynopsisExpanded }
                            .padding(vertical = 4.dp),
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                )
            }

            // ── GÉNEROS (chips estilo Animeko) ──
            if (anime.genres.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Géneros",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        anime.genres.forEach { genre ->
                            AssistChip(
                                onClick = {},
                                label = {
                                    Text(
                                        text = genre,
                                        fontSize = 12.sp,
                                    )
                                },
                                border = null,
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f),
                                    labelColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                ),
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
