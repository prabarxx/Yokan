package app.yokan.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.yokan.anilist.model.AniListMedia
import app.yokan.datasource.nyaa.NyaaSearchEngine
import app.yokan.datasource.nyaa.NyaaTorrent

private enum class TorrentFilter {
    ALL, P1080, P720, SPANISH
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TorrentSelectionModal(
    anime: AniListMedia,
    episodeNumber: Int,
    searchEngine: NyaaSearchEngine,
    onTorrentSelect: (NyaaTorrent) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isLoading by remember { mutableStateOf(true) }
    var allTorrents by remember { mutableStateOf<List<NyaaTorrent>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var activeFilter by remember { mutableStateOf(TorrentFilter.ALL) }

    LaunchedEffect(anime.id, episodeNumber) {
        isLoading = true
        errorMessage = null
        val titleToSearch = anime.title.english?.takeIf { it.isNotBlank() } ?: anime.title.romaji
        val result = searchEngine.search(titleToSearch, episodeNumber)
        result.fold(
            onSuccess = {
                allTorrents = it
                isLoading = false
            },
            onFailure = {
                errorMessage = it.message ?: "Error al buscar torrents"
                isLoading = false
            }
        )
    }

    val filteredTorrents = remember(allTorrents, activeFilter) {
        when (activeFilter) {
            TorrentFilter.ALL -> allTorrents
            TorrentFilter.P1080 -> allTorrents.filter { it.quality.contains("1080", ignoreCase = true) }
            TorrentFilter.P720 -> allTorrents.filter { it.quality.contains("720", ignoreCase = true) }
            TorrentFilter.SPANISH -> allTorrents.filter { it.isSpanishOrMulti }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            // Header estilo Animeko
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Seleccionar fuente de video",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Episodio $episodeNumber • ${anime.title.displayTitle}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Rounded.Close, contentDescription = "Cerrar")
                }
            }

            // Filtros rápidos estilo Animeko
            if (allTorrents.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = activeFilter == TorrentFilter.ALL,
                        onClick = { activeFilter = TorrentFilter.ALL },
                        label = { Text("Todos (${allTorrents.size})", fontSize = 12.sp) }
                    )
                    FilterChip(
                        selected = activeFilter == TorrentFilter.SPANISH,
                        onClick = { activeFilter = TorrentFilter.SPANISH },
                        label = { Text("Sub Español", fontSize = 12.sp) }
                    )
                    FilterChip(
                        selected = activeFilter == TorrentFilter.P1080,
                        onClick = { activeFilter = TorrentFilter.P1080 },
                        label = { Text("1080p", fontSize = 12.sp) }
                    )
                    FilterChip(
                        selected = activeFilter == TorrentFilter.P720,
                        onClick = { activeFilter = TorrentFilter.P720 },
                        label = { Text("720p", fontSize = 12.sp) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CircularProgressIndicator()
                            Text(
                                text = "Buscando torrents en AnimeTosho & Nyaa...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                errorMessage != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                Icons.Rounded.Warning,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error,
                            )
                            Text(
                                text = errorMessage.orEmpty(),
                                color = MaterialTheme.colorScheme.error,
                            )
                        }
                    }
                }
                filteredTorrents.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = if (activeFilter != TorrentFilter.ALL) {
                                "No hay torrents para este filtro."
                            } else {
                                "No se encontraron torrents disponibles para este episodio."
                            },
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(filteredTorrents) { torrent ->
                            AnimekoTorrentCard(
                                torrent = torrent,
                                onClick = { onTorrentSelect(torrent) }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * Card de Torrent con el diseño idéntico a Animeko (MediaSelectorItemLayout)
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AnimekoTorrentCard(
    torrent: NyaaTorrent,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            // Título original del Torrent
            ProvideTextStyle(MaterialTheme.typography.titleSmall) {
                Text(
                    text = torrent.title,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Fila de chips estilo Animeko
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                // Sembradores (🌱)
                AssistChip(
                    onClick = onClick,
                    label = {
                        Text(
                            text = "🌱 ${torrent.seeders}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (torrent.seeders >= 5) {
                                Color(0xFF4CAF50)
                            } else if (torrent.seeders > 0) {
                                Color(0xFFFF9800)
                            } else {
                                Color(0xFFF44336)
                            }
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (torrent.seeders >= 5) {
                            Color(0xFF4CAF50).copy(alpha = 0.12f)
                        } else {
                            MaterialTheme.colorScheme.surfaceContainerHighest
                        }
                    ),
                    border = null,
                )

                // Tamaño
                AssistChip(
                    onClick = onClick,
                    label = { Text(torrent.size, fontSize = 11.sp) },
                    border = null,
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )

                // Calidad / Resolución
                AssistChip(
                    onClick = onClick,
                    label = {
                        Text(
                            text = torrent.quality,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    },
                    border = null,
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                    )
                )

                // Subtítulos Español
                if (torrent.isSpanishOrMulti) {
                    AssistChip(
                        onClick = onClick,
                        label = {
                            Text(
                                text = "Sub Español",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE65100),
                            )
                        },
                        border = null,
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color(0xFFFF9800).copy(alpha = 0.15f)
                        )
                    )
                }

                // Fansub / Grupo extractor
                val fansub = extractFansub(torrent.title)
                if (fansub != null) {
                    AssistChip(
                        onClick = onClick,
                        label = { Text(fansub, fontSize = 11.sp) },
                        border = null,
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Fila inferior con fecha de publicación
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "⬇ ${torrent.leechers} leechers",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                )
                Text(
                    text = torrent.publishDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                )
            }
        }
    }
}

private fun extractFansub(title: String): String? {
    val trimmed = title.trim()
    if (trimmed.startsWith("[") && trimmed.contains("]")) {
        val group = trimmed.substringAfter("[").substringBefore("]").trim()
        if (group.isNotBlank() && group.length <= 25) {
            return group
        }
    }
    return null
}
