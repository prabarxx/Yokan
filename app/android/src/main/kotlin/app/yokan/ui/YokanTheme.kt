package app.yokan.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import dev.chrisbanes.haze.rememberHazeState
import me.him188.ani.app.data.models.preference.DarkMode
import me.him188.ani.app.data.models.preference.ThemeSettings
import me.him188.ani.app.navigation.NoopBrowserNavigator
import me.him188.ani.app.platform.navigation.LocalBrowserNavigator
import me.him188.ani.app.platform.rememberPlatformWindow
import me.him188.ani.app.tools.LocalTimeFormatter
import me.him188.ani.app.tools.TimeFormatter
import me.him188.ani.app.ui.foundation.LocalImageViewerHandler
import me.him188.ani.app.ui.foundation.LocalPlatformFontFamily
import me.him188.ani.app.ui.foundation.animation.ProvideAniMotionCompositionLocals
import me.him188.ani.app.ui.foundation.layout.LocalPlatformWindow
import me.him188.ani.app.ui.foundation.rememberImageViewerHandler
import me.him188.ani.app.ui.foundation.rememberPlatformFontFamily
import me.him188.ani.app.ui.foundation.theme.AniTheme
import me.him188.ani.app.ui.foundation.theme.LocalAppChromeHazeState
import me.him188.ani.app.ui.foundation.theme.LocalThemeSettings
import me.him188.ani.app.ui.foundation.theme.SystemBarColorEffect
import me.him188.ani.app.ui.foundation.widgets.LocalToaster
import me.him188.ani.app.ui.foundation.widgets.Toaster

@Composable
fun YokanTheme(
    content: @Composable () -> Unit,
) {
    val themeSettings = ThemeSettings(
        darkMode = DarkMode.DARK,
        useDynamicTheme = false,
        useBlackBackground = true,
        enableFrostedGlassEffect = true,
        seedColorValue = Color(0xFF6750A4).value,
    )
    val hazeState = rememberHazeState()
    val platformFont = rememberPlatformFontFamily(null)
    val platformWindow = rememberPlatformWindow()
    val imageViewerHandler = rememberImageViewerHandler()
    val timeFormatter = remember { TimeFormatter() }
    val noopToaster = remember {
        object : Toaster {
            override fun toast(text: String) {}
        }
    }

    CompositionLocalProvider(
        LocalThemeSettings provides themeSettings,
        LocalPlatformFontFamily provides platformFont,
        LocalAppChromeHazeState provides hazeState,
        LocalPlatformWindow provides platformWindow,
        LocalToaster providesDefault noopToaster,
        LocalBrowserNavigator providesDefault NoopBrowserNavigator,
        LocalTimeFormatter providesDefault timeFormatter,
        LocalImageViewerHandler providesDefault imageViewerHandler,
    ) {
        SystemBarColorEffect(isDark = true)
        AniTheme(darkModeOverride = DarkMode.DARK) {
            ProvideAniMotionCompositionLocals {
                content()
            }
        }
    }
}
