package app.yokan.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
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
import kotlinx.coroutines.withTimeoutOrNull

private enum class TorrentFilter {
    ALL, P1080, P720, SPANISH
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TorrentSelectionModal(
    anime: AniListMedia,
    episodeNumber: Int,
    searchEngine: NyaaSearchEngine,
    animeAV1Client: AnimeAV1Client,
    onTorrentSelect: (NyaaTorrent) -> Unit,
    onWebStreamSelect: (WebStreamSource) -> Unit,
    onDismiss: () -> Unit,
    notice: String? = null,
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
    var selectedCategoryTab by remember { mutableIntStateOf(0) } // 0: Servidores Web, 1: Torrents

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

        allTorrents = withTimeoutOrNull(12_000L) { torrentDeferred.await() } ?: emptyList()
        allWebSources = withTimeoutOrNull(12_000L) { webDeferred.await() } ?: emptyList()
        torrentDeferred.cancel()
        webDeferred.cancel()

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
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp)
        ) {
            // Cabecera minimalista
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Seleccionar fuente",
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
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Cerrar",
                        modifier = Modifier.size(20.dp),
                    )
                }
            }

            if (notice != null) {
                Text(
                    text = notice,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 6.dp),
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Selector de pestañas estilo píldora limpia (sin emojis recargados)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    SourceTabButton(
                        title = "Servidores Web (${allWebSources.size})",
                        selected = selectedCategoryTab == 0,
                        onClick = { selectedCategoryTab = 0 },
                        modifier = Modifier.weight(1f),
                    )
                    SourceTabButton(
                        title = "Torrents (${allTorrents.size})",
                        selected = selectedCategoryTab == 1,
                        onClick = { selectedCategoryTab = 1 },
                        modifier = Modifier.weight(1f),
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(32.dp), strokeWidth = 3.dp)
                            Text(
                                text = "Buscando fuentes disponibles...",
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
                            .height(220.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(32.dp), strokeWidth = 3.dp)
                            Text(
                                text = "Conectando con $resolvingServerName...",
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                errorMessage != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
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
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error,
                            )
                        }
                    }
                }
                selectedCategoryTab == 0 -> {
                    if (allWebSources.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "No hay servidores web disponibles para este episodio.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 420.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(allWebSources) { source ->
                                WebStreamRow(
                                    source = source,
                                    isRecommended = source == allWebSources.firstOrNull() || source.isUpn,
                                    onClick = {
                                        coroutineScope.launch {
                                            isResolvingWebStream = true
                                            resolvingServerName = source.displayName
                                            val result = animeAV1Client.resolveStream(source)
                                            isResolvingWebStream = false
                                            result.fold(
                                                onSuccess = { resolvedStream ->
                                                    onWebStreamSelect(resolvedStream)
                                                },
                                                onFailure = { err ->
                                                    errorMessage = "Error al conectar con ${source.displayName}: ${err.message}"
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
                    if (allTorrents.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                                .padding(bottom = 8.dp),
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
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 420.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(filteredTorrents) { torrent ->
                                TorrentRow(
                                    torrent = torrent,
                                    isRecommended = torrent == allTorrents.firstOrNull() && torrent.score > 0.0,
                                    onClick = { onTorrentSelect(torrent) }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun SourceTabButton(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(9.dp),
        color = if (selected) MaterialTheme.colorScheme.surface else Color.Transparent,
        shadowElevation = if (selected) 1.dp else 0.dp,
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                color = if (selected) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
            )
        }
    }
}

/**
 * Fila compacta de servidor web sin chips innecesarios.
 */
@Composable
private fun WebStreamRow(
    source: AnimeAV1Source,
    isRecommended: Boolean,
    onClick: () -> Unit,
) {
    val languageText = if (source.isDub) "Español Latino" else "Sub Español"
    val cleanName = remember(source) {
        when {
            source.isUpn -> "UPNShare"
            source.isMp4Upload -> "MP4Upload"
            source.isVoe -> "Voe"
            source.isVidHide -> "VidHide"
            source.isStreamTape -> "StreamTape"
            else -> source.server
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        border = BorderStroke(
            width = 1.dp,
            color = if (isRecommended) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.45f)
            } else {
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
            },
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = cleanName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    if (isRecommended) {
                        InlineBadge(
                            text = "Recomendado",
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                    InlineBadge(
                        text = if (source.isDub) "LAT" else "SUB",
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
                Text(
                    text = "${source.quality} • $languageText",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Rounded.PlayArrow,
                    contentDescription = "Reproducir",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp),
                )
            }
        }
    }
}

/**
 * Fila compacta de Torrent: arriba metadatos clave limpios + tamaño/seeds, abajo título en 1 línea.
 */
@Composable
private fun TorrentRow(
    torrent: NyaaTorrent,
    isRecommended: Boolean,
    onClick: () -> Unit,
) {
    val fansub = remember(torrent.title) { extractFansub(torrent.title) }
    val source = remember(torrent.title) { extractSource(torrent.title) }
    val primaryLabel = remember(fansub, source, torrent.quality) {
        buildList {
            if (fansub != null) add(fansub)
            if (source != null && !source.equals(fansub, ignoreCase = true)) add(source)
            add(torrent.quality)
        }.joinToString(" • ")
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        border = BorderStroke(
            width = 1.dp,
            color = if (isRecommended) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.45f)
            } else {
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
            },
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 11.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = primaryLabel,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (torrent.isSpanishOrMulti) {
                        InlineBadge(
                            text = "Sub Esp",
                            containerColor = Color(0xFF4CAF50).copy(alpha = 0.16f),
                            contentColor = Color(0xFF4CAF50),
                        )
                    }
                    if (torrent.isBatch) {
                        InlineBadge(
                            text = "Pack",
                            containerColor = Color(0xFF2196F3).copy(alpha = 0.16f),
                            contentColor = Color(0xFF42A5F5),
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = torrent.size,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = "${torrent.seeders} ▲",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (torrent.seeders > 0) Color(0xFF4CAF50) else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Text(
                text = torrent.title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 11.sp,
            )
        }
    }
}

@Composable
private fun InlineBadge(
    text: String,
    containerColor: Color,
    contentColor: Color,
) {
    Box(
        modifier = Modifier
            .background(color = containerColor, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp),
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor,
        )
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
