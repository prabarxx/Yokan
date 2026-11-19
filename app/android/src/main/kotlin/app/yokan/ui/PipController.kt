package app.yokan.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class PipController(
    val isSupported: Boolean = false,
    val isInPipMode: Boolean = false,
    val onUpdatePlaybackActive: (Boolean) -> Unit = {},
    val onEnterPip: () -> Boolean = { false },
)

val LocalPipController = staticCompositionLocalOf { PipController() }
