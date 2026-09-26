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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import app.yokan.datasource.animeav1.AnimeAV1Client
import app.yokan.datasource.animeav1.AnimeAV1Source
import app.yokan.datasource.animeav1.WebStreamSource
import app.yokan.datasource.nyaa.NyaaSearchEngine
import app.yokan.datasource.nyaa.NyaaTorrent
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private enum class TorrentFilter {
    ALL, P1080, P720, SPANISH
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TorrentSelectionModal(
    anime: AniListMedia,
    episodeNumber: Int,
    searchEngine: NyaaSearchEngine,
    animeAV1Client: AnimeAV1Client,
    onTorrentSelect: (NyaaTorrent) -> Unit,
    onWebStreamSelect: (WebStreamSource) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }
    var isResolvingWebStream by remember { mutableStateOf(false) }
    var resolvingServerName by remember { mutableStateOf("") }
    var allTorrents by remember { mutableStateOf<List<NyaaTorrent>>(emptyList()) }
    var allWebSources by remember { mutableStateOf<List<AnimeAV1Source>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var activeFilter by remember { mutableStateOf(TorrentFilter.ALL) }
    var selectedCategoryTab by remember { mutableIntStateOf(0) } // 0: Web Streaming, 1: Torrents

    LaunchedEffect(anime.id, episodeNumber) {
        isLoading = true
        errorMessage = null

        val torrentDeferred = async {
            searchEngine.search(
                romajiTitle = anime.title.romaji,
                englishTitle = anime.title.english,
                synonyms = anime.synonyms,
                episodeNumber = episodeNumber,
                absoluteEpisodeNumber = anime.calculateAbsoluteEpisode(episodeNumber).takeIf { it != episodeNumber },
                totalEpisodes = anime.effectiveEpisodesCount,
            ).getOrDefault(emptyList())
        }

        val webDeferred = async {
            runCatching {
                val slug = animeAV1Client.resolveSlug(anime.title.romaji, anime.title.english)
                animeAV1Client.getEpisodeSources(slug, episodeNumber)
            }.getOrDefault(emptyList())
        }

        allTorrents = torrentDeferred.await()
        allWebSources = webDeferred.await()

        // Seleccionar pestaña por defecto: si hay fuentes web (con UPN), abrir en Web Streaming
        selectedCategoryTab = if (allWebSources.isNotEmpty()) 0 else 1
        isLoading = false
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

            Spacer(modifier = Modifier.height(8.dp))

            // Selector de Categoría: Streaming Web vs Torrents P2P
            TabRow(
                selectedTabIndex = selectedCategoryTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Tab(
                    selected = selectedCategoryTab == 0,
                    onClick = { selectedCategoryTab = 0 },
                    text = {
                        Text(
                            text = "⚡ Web (${allWebSources.size})",
                            fontWeight = if (selectedCategoryTab == 0) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp,
                        )
                    }
                )
                Tab(
                    selected = selectedCategoryTab == 1,
                    onClick = { selectedCategoryTab = 1 },
                    text = {
                        Text(
                            text = "📦 Torrents (${allTorrents.size})",
                            fontWeight = if (selectedCategoryTab == 1) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp,
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

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
                                text = "Buscando fuentes web y torrents...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                isResolvingWebStream -> {
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
                                text = "Conectando stream $resolvingServerName...",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Aplicando descifrado seguro y bloqueo de anuncios...",
                                style = MaterialTheme.typography.bodySmall,
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
                selectedCategoryTab == 0 -> {
                    // Pestaña Web Streaming (AnimeAV1)
                    if (allWebSources.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "No se encontraron servidores web disponibles para este episodio.",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(450.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(allWebSources) { source ->
                                AnimekoWebStreamCard(
                                    source = source,
                                    isTopRecommended = source.isUpn,
                                    onClick = {
                                        coroutineScope.launch {
                                            isResolvingWebStream = true
                                            resolvingServerName = source.server
                                            val result = animeAV1Client.resolveStream(source)
                                            isResolvingWebStream = false
                                            result.fold(
                                                onSuccess = { resolvedStream ->
                                                    onWebStreamSelect(resolvedStream)
                                                },
                                                onFailure = { err ->
                                                    errorMessage = "Error al resolver servidor: ${err.message}"
                                                }
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
                else -> {
                    // Pestaña Torrents P2P (Nyaa / Erai-raws)
                    if (allTorrents.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
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

                    if (filteredTorrents.isEmpty()) {
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
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(450.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(filteredTorrents) { torrent ->
                                AnimekoTorrentCard(
                                    torrent = torrent,
                                    isTopRecommended = torrent == allTorrents.firstOrNull() && torrent.score > 0.0,
                                    onClick = { onTorrentSelect(torrent) }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * Tarjeta de servidor de streaming web (AnimeAV1) con diseño nativo estilo Animeko
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AnimekoWebStreamCard(
    source: AnimeAV1Source,
    isTopRecommended: Boolean = false,
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
        border = BorderStroke(
            1.dp,
            if (isTopRecommended) MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
            else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = source.displayName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                    color = if (isTopRecommended) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                )
                if (source.isUpn) {
                    AssistChip(
                        onClick = onClick,
                        label = { Text("⚡ HLS Sin Anuncios", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        border = null,
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                if (isTopRecommended) {
                    AssistChip(
                        onClick = onClick,
                        label = { Text("Recomendado por defecto", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        ),
                        border = null,
                    )
                }

                AssistChip(
                    onClick = onClick,
                    label = { Text(if (source.isDub) "Audio Latino/Castellano" else "Sub Español Oficial", fontSize = 11.sp) },
                    border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                )

                AssistChip(
                    onClick = onClick,
                    label = { Text(source.quality, fontSize = 11.sp) },
                    border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                )

                AssistChip(
                    onClick = onClick,
                    label = { Text("Carga Inmediata", fontSize = 11.sp) },
                    border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                )
            }
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
    isTopRecommended: Boolean = false,
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
                if (isTopRecommended) {
                    AssistChip(
                        onClick = onClick,
                        label = { Text("Mejor opción", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        border = null,
                    )
                }

                AssistChip(
                    onClick = onClick,
                    label = { Text(torrent.quality, fontSize = 11.sp) },
                    border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                )

                if (torrent.isSpanishOrMulti) {
                    AssistChip(
                        onClick = onClick,
                        label = {
                            Text(
                                text = "Sub Español",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32),
                            )
                        },
                        border = null,
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color(0xFF4CAF50).copy(alpha = 0.15f)
                        )
                    )
                }

                if (torrent.isBatch) {
                    AssistChip(
                        onClick = onClick,
                        label = {
                            Text(
                                text = "Pack Completo",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1565C0),
                            )
                        },
                        border = null,
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color(0xFF2196F3).copy(alpha = 0.15f)
                        )
                    )
                }

                AssistChip(
                    onClick = onClick,
                    label = { Text(torrent.size, fontSize = 11.sp) },
                    border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                )

                AssistChip(
                    onClick = onClick,
                    label = { Text("⬆ ${torrent.seeders} seeds", fontSize = 11.sp) },
                    border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                )

                val source = extractSource(torrent.title)
                if (source != null) {
                    AssistChip(
                        onClick = onClick,
                        label = {
                            Text(
                                text = source,
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

private fun extractSource(title: String): String? {
    val upper = title.uppercase()
    return when {
        upper.contains("CR") || upper.contains("CRUNCHYROLL") -> "Crunchyroll"
        upper.contains("HIDIVE") -> "HIDIVE"
        upper.contains("NETFLIX") || upper.contains("NF") -> "Netflix"
        upper.contains("AMZN") || upper.contains("PRIME") -> "Prime Video"
        upper.contains("B-GLOBAL") || upper.contains("BILIBILI") -> "Bilibili"
        else -> null
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
