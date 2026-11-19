package app.yokan.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CloudOff
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.yokan.anilist.client.AniListClient
import app.yokan.anilist.model.AniListAiringItem
import app.yokan.anilist.model.AniListMedia
import kotlinx.coroutines.launch
import me.him188.ani.app.ui.adaptive.AniTopAppBar
import me.him188.ani.app.ui.foundation.AsyncImage
import me.him188.ani.app.ui.foundation.theme.appChromeHazeSource
import me.him188.ani.app.ui.foundation.widgets.BackNavigationIconButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private object ScheduleCache {
    private const val TTL_MS = 15 * 60_000L
    var items: List<AniListAiringItem> = emptyList()
    var fetchedAtMillis: Long = 0L

    fun isFresh(): Boolean = items.isNotEmpty() && (System.currentTimeMillis() - fetchedAtMillis) < TTL_MS
}

private data class WeekDayInfo(
    val index: Int,
    val shortLabel: String,
    val fullLabel: String,
    val dayOfMonth: Int,
    val isToday: Boolean,
)

private val DAY_SHORT_LABELS = listOf("Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom")
private val DAY_FULL_LABELS = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    aniListClient: AniListClient,
    onAnimeClick: (AniListMedia) -> Unit,
    onEpisodeClick: (AniListMedia, Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val weekRange = remember { computeCurrentWeekRange() }
    val weekDays = remember(weekRange) { buildWeekDays(weekRange.startMillis, weekRange.todayIndex) }
    val pagerState = rememberPagerState(initialPage = weekRange.todayIndex) { 7 }

    var scheduleItems by remember { mutableStateOf(ScheduleCache.items) }
    var isLoading by remember { mutableStateOf(!ScheduleCache.isFresh()) }
    var isRefreshing by remember { mutableStateOf(false) }
    var hasError by remember { mutableStateOf(false) }

    fun loadSchedule(force: Boolean = false) {
        if (!force && ScheduleCache.isFresh()) {
            scheduleItems = ScheduleCache.items
            isLoading = false
            hasError = false
            return
        }
        if (isRefreshing) return
        if (scheduleItems.isEmpty()) isLoading = true else isRefreshing = true
        hasError = false

        coroutineScope.launch {
            val result = aniListClient.fetchAiringSchedule(
                startEpochSeconds = weekRange.startMillis / 1000L,
                endEpochSeconds = weekRange.endMillis / 1000L,
            )
            result.onSuccess { fetched ->
                scheduleItems = fetched
                ScheduleCache.items = fetched
                ScheduleCache.fetchedAtMillis = System.currentTimeMillis()
                hasError = false
            }.onFailure {
                if (scheduleItems.isEmpty()) {
                    hasError = true
                }
            }
            isLoading = false
            isRefreshing = false
        }
    }

    LaunchedEffect(Unit) {
        loadSchedule(force = false)
    }

    val groupedByDay = remember(scheduleItems) {
        val buckets = List(7) { mutableListOf<AniListAiringItem>() }
        val cal = Calendar.getInstance()
        for (item in scheduleItems) {
            cal.timeInMillis = item.airingAt * 1000L
            val dayIdx = (cal.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7
            buckets[dayIdx].add(item)
        }
        buckets.map { list -> list.sortedBy { it.airingAt } }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AniTopAppBar(
                navigationIcon = { BackNavigationIconButton(onBack) },
                title = {
                    Column {
                        Text(
                            text = "Horario semanal",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                        )
                        Text(
                            text = "${weekDays[pagerState.currentPage].fullLabel} · Hora local",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { loadSchedule(force = true) }) {
                        Icon(
                            imageVector = Icons.Rounded.Refresh,
                            contentDescription = "Actualizar horario",
                        )
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            // Barra de selección de días de la semana
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                weekDays.forEach { day ->
                    val selected = pagerState.currentPage == day.index
                    val count = groupedByDay[day.index].size
                    DaySelectorPill(
                        day = day,
                        selected = selected,
                        hasItems = count > 0,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(day.index)
                            }
                        },
                        modifier = Modifier.weight(1f),
                    )
                }
            }

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            CircularProgressIndicator()
                            Text(
                                text = "Cargando emisiones de la semana...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                hasError -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.CloudOff,
                            contentDescription = null,
                            modifier = Modifier.size(52.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = "No se pudo cargar el horario",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Revisa tu conexión e inténtalo de nuevo.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(Modifier.height(20.dp))
                        Button(onClick = { loadSchedule(force = true) }) {
                            Icon(Icons.Rounded.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Reintentar")
                        }
                    }
                }

                else -> {
                    val pullState = rememberPullToRefreshState()
                    PullToRefreshBox(
                        isRefreshing = isRefreshing,
                        onRefresh = { loadSchedule(force = true) },
                        state = pullState,
                        modifier = Modifier.fillMaxSize(),
                        indicator = {
                            PullToRefreshDefaults.Indicator(
                                state = pullState,
                                isRefreshing = isRefreshing,
                                modifier = Modifier.align(Alignment.TopCenter),
                            )
                        },
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxSize()
                                .appChromeHazeSource(MaterialTheme.colorScheme.background),
                        ) { page ->
                            val dayItems = groupedByDay[page]
                            if (dayItems.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(32.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.CalendarMonth,
                                            contentDescription = null,
                                            modifier = Modifier.size(48.dp),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                        )
                                        Text(
                                            text = "Sin estrenos programados para este día",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            textAlign = TextAlign.Center,
                                        )
                                    }
                                }
                            } else {
                                val nowMillis = System.currentTimeMillis()
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 32.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp),
                                ) {
                                    items(dayItems, key = { it.scheduleId }) { item ->
                                        AiringScheduleCard(
                                            item = item,
                                            nowMillis = nowMillis,
                                            onClick = { onAnimeClick(item.media) },
                                            onPlay = { onEpisodeClick(item.media, item.episode) },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DaySelectorPill(
    day: WeekDayInfo,
    selected: Boolean,
    hasItems: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = when {
            selected -> MaterialTheme.colorScheme.primary
            day.isToday -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            else -> MaterialTheme.colorScheme.surfaceContainerLow
        },
        border = if (day.isToday && !selected) {
            BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
        } else {
            null
        },
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = day.shortLabel,
                fontSize = 11.sp,
                fontWeight = if (selected || day.isToday) FontWeight.Bold else FontWeight.Medium,
                color = if (selected) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                maxLines = 1,
            )
            Text(
                text = day.dayOfMonth.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (selected) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
            )
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            !hasItems -> Color.Transparent
                            selected -> MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
                            else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                        }
                    ),
            )
        }
    }
}

@Composable
private fun AiringScheduleCard(
    item: AniListAiringItem,
    nowMillis: Long,
    onClick: () -> Unit,
    onPlay: () -> Unit,
) {
    val airingMillis = item.airingAt * 1000L
    val isAired = airingMillis <= nowMillis
    val timeText = remember(item.airingAt) {
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(airingMillis))
    }
    val statusText = remember(item.airingAt, nowMillis) {
        formatRelativeAiringStatus(airingMillis, nowMillis)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            AsyncImage(
                model = item.media.coverImage?.bestQualityUrl,
                contentDescription = item.media.title.displayTitle,
                modifier = Modifier
                    .width(64.dp)
                    .height(92.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Schedule,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(14.dp),
                    )
                    Text(
                        text = timeText,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isAired) {
                            Color(0xFF4CAF50).copy(alpha = 0.16f)
                        } else {
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                        },
                    ) {
                        Text(
                            text = statusText,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isAired) {
                                Color(0xFF4CAF50)
                            } else {
                                MaterialTheme.colorScheme.onPrimaryContainer
                            },
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        )
                    }
                }

                Text(
                    text = item.media.title.displayTitle,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                val metaText = buildList {
                    add("Episodio ${item.episode}")
                    item.media.averageScore?.takeIf { it > 0 }?.let { add("★ $it%") }
                    item.media.genres.take(2).takeIf { it.isNotEmpty() }?.let { add(it.joinToString(" · ")) }
                }.joinToString("  •  ")

                Text(
                    text = metaText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            if (isAired) {
                Surface(
                    onClick = onPlay,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(38.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = "Ver episodio ${item.episode}",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(22.dp),
                        )
                    }
                }
            }
        }
    }
}

private data class WeekRange(
    val startMillis: Long,
    val endMillis: Long,
    val todayIndex: Int,
)

private fun computeCurrentWeekRange(): WeekRange {
    val now = Calendar.getInstance()
    val todayIndex = (now.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7

    val start = (now.clone() as Calendar).apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        add(Calendar.DAY_OF_YEAR, -todayIndex)
    }
    val end = (start.clone() as Calendar).apply {
        add(Calendar.DAY_OF_YEAR, 7)
        add(Calendar.SECOND, -1)
    }
    return WeekRange(
        startMillis = start.timeInMillis,
        endMillis = end.timeInMillis,
        todayIndex = todayIndex,
    )
}

private fun buildWeekDays(weekStartMillis: Long, todayIndex: Int): List<WeekDayInfo> {
    val cal = Calendar.getInstance()
    return (0 until 7).map { idx ->
        cal.timeInMillis = weekStartMillis
        cal.add(Calendar.DAY_OF_YEAR, idx)
        WeekDayInfo(
            index = idx,
            shortLabel = DAY_SHORT_LABELS[idx],
            fullLabel = DAY_FULL_LABELS[idx],
            dayOfMonth = cal.get(Calendar.DAY_OF_MONTH),
            isToday = idx == todayIndex,
        )
    }
}

private fun formatRelativeAiringStatus(airingMillis: Long, nowMillis: Long): String {
    val diffMs = airingMillis - nowMillis
    if (diffMs <= 0L) return "Emitido"
    val totalMinutes = (diffMs / 60_000L).coerceAtLeast(1L)
    val days = totalMinutes / (24 * 60)
    val hours = (totalMinutes % (24 * 60)) / 60
    val minutes = totalMinutes % 60
    return when {
        days > 0 -> "En ${days}d ${hours}h"
        hours > 0 -> "En ${hours}h ${minutes}m"
        else -> "En ${minutes}m"
    }
}
