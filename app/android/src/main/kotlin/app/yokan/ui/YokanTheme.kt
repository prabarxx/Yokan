package app.yokan.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import dev.chrisbanes.haze.rememberHazeState
import me.him188.ani.app.data.models.preference.DarkMode
import me.him188.ani.app.data.models.preference.ThemeSettings
import me.him188.ani.app.ui.foundation.LocalPlatformFontFamily
import me.him188.ani.app.ui.foundation.rememberPlatformFontFamily
import me.him188.ani.app.ui.foundation.theme.AniTheme
import me.him188.ani.app.ui.foundation.theme.LocalAppChromeHazeState
import me.him188.ani.app.ui.foundation.theme.LocalThemeSettings
import me.him188.ani.app.ui.foundation.theme.SystemBarColorEffect

@Composable
fun YokanTheme(
    content: @Composable () -> Unit,
) {
    val themeSettings = ThemeSettings(
        darkMode = DarkMode.DARK,
        useDynamicTheme = false,
        useBlackBackground = true,
        enableFrostedGlassEffect = true,
        seedColorValue = 0xFF6750A4u,
    )
    val hazeState = rememberHazeState()
    val platformFont = rememberPlatformFontFamily(null)

    CompositionLocalProvider(
        LocalThemeSettings provides themeSettings,
        LocalPlatformFontFamily provides platformFont,
        LocalAppChromeHazeState provides hazeState,
    ) {
        SystemBarColorEffect(isDark = true)
        AniTheme(darkModeOverride = DarkMode.DARK) {
            content()
        }
    }
}
