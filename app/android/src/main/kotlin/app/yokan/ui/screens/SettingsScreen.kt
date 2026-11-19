package app.yokan.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.FastForward
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.yokan.BuildConfig
import app.yokan.media.TorrentManager
import app.yokan.media.WatchHistoryManager
import app.yokan.settings.SeedColorPreset
import app.yokan.settings.YokanSettings
import app.yokan.settings.YokanSettingsManager
import me.him188.ani.app.data.models.preference.DarkMode
import me.him188.ani.app.ui.adaptive.AniTopAppBar
import me.him188.ani.app.ui.foundation.theme.appChromeHazeSource
import me.him188.ani.app.ui.foundation.theme.isPlatformSupportDynamicTheme
import me.him188.ani.app.videoplayer.videoenhancement.VideoEnhancementMode

private const val GITHUB_REPO_URL = "https://github.com/prabarxx/Yokan"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val settingsManager = remember(context) { YokanSettingsManager.getInstance(context) }
    val watchHistoryManager = remember(context) { WatchHistoryManager.getInstance(context) }
    val torrentManager = remember(context) { TorrentManager.getInstance(context) }

    val settings by settingsManager.settings.collectAsState()
    val preferDub by watchHistoryManager.preferDub.collectAsState()
    val supportsDynamicTheme = isPlatformSupportDynamicTheme()

    var showResetDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AniTopAppBar(
                title = {
                    Text(
                        text = "Ajustes",
                        fontWeight = FontWeight.Black,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .appChromeHazeSource(MaterialTheme.colorScheme.background)
                .padding(padding),
            contentPadding = PaddingValues(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // --- APARIENCIA ---
            item("appearance_section") {
                SettingsSection(
                    title = "Apariencia",
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Palette,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp),
                        )
                    },
                ) {
                    SettingsChoiceBlock(
                        title = "Tema de la aplicación",
                        subtitle = "Elige entre modo oscuro, claro o seguir el sistema",
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            listOf(
                                DarkMode.AUTO to "Sistema",
                                DarkMode.DARK to "Oscuro",
                                DarkMode.LIGHT to "Claro",
                            ).forEach { (mode, label) ->
                                SettingsOptionPill(
                                    label = label,
                                    selected = settings.darkMode == mode,
                                    onClick = { settingsManager.setDarkMode(mode) },
                                )
                            }
                        }
                    }

                    SettingsDivider()

                    val amoledEnabled = settings.darkMode != DarkMode.LIGHT
                    SettingsSwitchRow(
                        title = "Negro puro AMOLED",
                        subtitle = "Usa fondo negro absoluto en modo oscuro para ahorrar batería",
                        checked = settings.useBlackBackground,
                        enabled = amoledEnabled,
                        onCheckedChange = { settingsManager.setUseBlackBackground(it) },
                    )

                    if (supportsDynamicTheme) {
                        SettingsDivider()
                        SettingsSwitchRow(
                            title = "Color dinámico (Material You)",
                            subtitle = "Adapta los colores de acento al fondo de pantalla de Android",
                            checked = settings.useDynamicTheme,
                            onCheckedChange = { settingsManager.setUseDynamicTheme(it) },
                        )
                    }

                    SettingsDivider()

                    val seedEnabled = !(supportsDynamicTheme && settings.useDynamicTheme)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(if (seedEnabled) 1f else 0.45f)
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = "Color de acento",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = if (seedEnabled) {
                                    "Personaliza la paleta de botones y controles"
                                } else {
                                    "Desactiva Material You para usar un color personalizado"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            YokanSettings.SEED_COLOR_PRESETS.forEach { preset ->
                                SeedColorChip(
                                    preset = preset,
                                    selected = settings.seedColorArgb == preset.argb,
                                    enabled = seedEnabled,
                                    onClick = { settingsManager.setSeedColorArgb(preset.argb) },
                                )
                            }
                        }
                    }

                    SettingsDivider()

                    SettingsSwitchRow(
                        title = "Efecto vidrio esmerilado",
                        subtitle = "Desenfoque translúcido estilo Animeko en barras superior e inferior",
                        checked = settings.enableFrostedGlassEffect,
                        onCheckedChange = { settingsManager.setEnableFrostedGlassEffect(it) },
                    )
                }
            }

            // --- REPRODUCTOR ---
            item("player_section") {
                SettingsSection(
                    title = "Reproductor",
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp),
                        )
                    },
                ) {
                    SettingsChoiceBlock(
                        title = "Idioma predeterminado",
                        subtitle = "Prioriza audio japonés subtitulado o doblaje latino cuando esté disponible",
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            SettingsOptionPill(
                                label = "Subtitulado (SUB)",
                                selected = !preferDub,
                                onClick = { watchHistoryManager.setPreferDub(false) },
                            )
                            SettingsOptionPill(
                                label = "Español Latino (LAT)",
                                selected = preferDub,
                                onClick = { watchHistoryManager.setPreferDub(true) },
                            )
                        }
                    }

                    SettingsDivider()

                    SettingsChoiceBlock(
                        title = "Mejora de video (Anime4K)",
                        subtitle = "Restaura líneas y mejora la nitidez por GPU (OpenGL). Rendimiento usa CNN-S; Calidad añade escalado x2 CNN-M.",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.AutoAwesome,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp),
                            )
                        },
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            listOf(
                                VideoEnhancementMode.OFF to "Desactivado",
                                VideoEnhancementMode.PERFORMANCE to "Rendimiento",
                                VideoEnhancementMode.QUALITY to "Calidad",
                            ).forEach { (mode, label) ->
                                SettingsOptionPill(
                                    label = label,
                                    selected = settings.videoEnhancementMode == mode,
                                    onClick = { settingsManager.setVideoEnhancementMode(mode) },
                                )
                            }
                        }
                    }

                    SettingsDivider()

                    SettingsChoiceBlock(
                        title = "Salto de opening",
                        subtitle = "Segundos que adelanta el botón «Saltar intro» en pantalla completa",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.FastForward,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp),
                            )
                        },
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            YokanSettings.SKIP_INTRO_OPTIONS.forEach { seconds ->
                                SettingsOptionPill(
                                    label = "+${seconds}s",
                                    selected = settings.skipIntroSeconds == seconds,
                                    onClick = { settingsManager.setSkipIntroSeconds(seconds) },
                                )
                            }
                        }
                    }

                    SettingsDivider()

                    SettingsSwitchRow(
                        title = "Avance automático de episodio",
                        subtitle = "Muestra cuenta regresiva al terminar un episodio para pasar al siguiente",
                        checked = settings.autoPlayNextEpisode,
                        onCheckedChange = { settingsManager.setAutoPlayNextEpisode(it) },
                    )
                }
            }

            // --- RED Y TORRENTS ---
            item("network_section") {
                SettingsSection(
                    title = "Red y BitTorrent (P2P)",
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Bolt,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp),
                        )
                    },
                ) {
                    SettingsChoiceBlock(
                        title = "Límite de descarga torrent",
                        subtitle = "Se aplica en tiempo real al motor Anitorrent",
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            YokanSettings.DOWNLOAD_LIMIT_OPTIONS_BYTES.forEach { limitBytes ->
                                SettingsOptionPill(
                                    label = formatSpeedLimit(limitBytes),
                                    selected = settings.torrentDownloadLimitBytes == limitBytes,
                                    onClick = {
                                        settingsManager.setTorrentDownloadLimitBytes(limitBytes)
                                        torrentManager.updateRateLimits(
                                            downloadRateLimitBytes = limitBytes,
                                            uploadRateLimitBytes = settings.torrentUploadLimitBytes,
                                        )
                                    },
                                )
                            }
                        }
                    }

                    SettingsDivider()

                    SettingsChoiceBlock(
                        title = "Límite de subida torrent",
                        subtitle = "Controla el ancho de banda de compartición P2P",
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            YokanSettings.UPLOAD_LIMIT_OPTIONS_BYTES.forEach { limitBytes ->
                                SettingsOptionPill(
                                    label = formatSpeedLimit(limitBytes),
                                    selected = settings.torrentUploadLimitBytes == limitBytes,
                                    onClick = {
                                        settingsManager.setTorrentUploadLimitBytes(limitBytes)
                                        torrentManager.updateRateLimits(
                                            downloadRateLimitBytes = settings.torrentDownloadLimitBytes,
                                            uploadRateLimitBytes = limitBytes,
                                        )
                                    },
                                )
                            }
                        }
                    }
                }
            }

            // --- ACERCA DE ---
            item("about_section") {
                SettingsSection(
                    title = "Acerca de Yokan",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp),
                        )
                    },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = "Versión instalada",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = "Licencia GNU AGPL-3.0 · Inspirada en Animeko",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.primaryContainer,
                        ) {
                            Text(
                                text = "v${BuildConfig.VERSION_NAME}",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                            )
                        }
                    }

                    SettingsDivider()

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                runCatching {
                                    context.startActivity(
                                        Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_REPO_URL))
                                    )
                                }
                            }
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                        ) {
                            Text(
                                text = "Código fuente en GitHub",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = "github.com/prabarxx/Yokan",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                            contentDescription = "Abrir GitHub",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }

            item("reset_action") {
                OutlinedButton(
                    onClick = { showResetDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Restablecer ajustes predeterminados")
                }
            }
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Restablecer ajustes") },
            text = {
                Text("Se restaurarán los valores predeterminados de apariencia, reproductor y límites BitTorrent. Tu historial y caché no se borrarán.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        settingsManager.resetToDefaults()
                        watchHistoryManager.setPreferDub(false)
                        torrentManager.updateRateLimits(0, 0)
                        showResetDialog = false
                    },
                ) {
                    Text("Restablecer")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Cancelar")
                }
            },
        )
    }
}

@Composable
private fun SettingsSection(
    title: String,
    icon: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 4.dp),
        ) {
            icon()
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}

@Composable
private fun SettingsDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
    )
}

@Composable
private fun SettingsSwitchRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (enabled) 1f else 0.45f)
            .clickable(enabled = enabled) { onCheckedChange(!checked) }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Switch(
            checked = checked,
            enabled = enabled,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Composable
private fun SettingsChoiceBlock(
    title: String,
    subtitle: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    optionsContent: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                leadingIcon?.invoke()
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        optionsContent()
    }
}

@Composable
private fun SettingsOptionPill(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        color = if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surfaceContainerHigh
        },
        border = if (selected) {
            null
        } else {
            BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.45f))
        },
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            color = if (selected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
        )
    }
}

@Composable
private fun SeedColorChip(
    preset: SeedColorPreset,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val swatch = preset.color
    val checkTint = if (swatch.luminance() > 0.5f) Color.Black else Color.White
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(4.dp),
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(swatch)
                .then(
                    if (selected) {
                        Modifier.border(2.5.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                    } else {
                        Modifier.border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                    }
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (selected) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = preset.name,
                    tint = checkTint,
                    modifier = Modifier.size(18.dp),
                )
            }
        }
        Text(
            text = preset.name,
            style = MaterialTheme.typography.labelSmall,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        )
    }
}

private fun formatSpeedLimit(bytesPerSec: Int): String = when {
    bytesPerSec <= 0 -> "Sin límite"
    bytesPerSec >= 1024 * 1024 -> "${bytesPerSec / (1024 * 1024)} MB/s"
    else -> "${bytesPerSec / 1024} KB/s"
}
