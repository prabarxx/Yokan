package app.yokan.ui

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import app.yokan.settings.YokanSettingsManager
import dev.chrisbanes.haze.rememberHazeState
import me.him188.ani.app.data.models.preference.DarkMode
import me.him188.ani.app.navigation.BrowserNavigator
import me.him188.ani.app.navigation.OpenBrowserResult
import me.him188.ani.app.platform.Context as AniPlatformContext
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
    val context = LocalContext.current
    val settingsManager = remember(context) { YokanSettingsManager.getInstance(context) }
    val appSettings by settingsManager.settings.collectAsState()
    val themeSettings = remember(appSettings) { appSettings.toThemeSettings() }
    val isDark = when (themeSettings.darkMode) {
        DarkMode.LIGHT -> false
        DarkMode.DARK -> true
        DarkMode.AUTO -> isSystemInDarkTheme()
    }

    val hazeState = rememberHazeState()
    val platformFont = rememberPlatformFontFamily(null)
    val platformWindow = rememberPlatformWindow()
    val imageViewerHandler = rememberImageViewerHandler()
    val timeFormatter = remember { TimeFormatter() }
    val androidToaster = remember(context) {
        val appCtx = context.applicationContext
        val mainHandler = Handler(Looper.getMainLooper())
        object : Toaster {
            override fun toast(text: String) {
                mainHandler.post {
                    Toast.makeText(appCtx, text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    val browserNavigator = remember {
        object : BrowserNavigator {
            private fun openUrl(ctx: AniPlatformContext, url: String): OpenBrowserResult {
                return try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    ctx.startActivity(intent)
                    OpenBrowserResult.Success
                } catch (e: Throwable) {
                    OpenBrowserResult.Failure(e, url)
                }
            }

            override fun openBrowser(context: AniPlatformContext, url: String): OpenBrowserResult =
                openUrl(context, url)

            override fun openJoinGroup(context: AniPlatformContext): OpenBrowserResult =
                openUrl(context, "https://github.com/prabarxx/Yokan")

            override fun intentActionView(context: AniPlatformContext, url: String): OpenBrowserResult =
                openUrl(context, url)
        }
    }

    CompositionLocalProvider(
        LocalThemeSettings provides themeSettings,
        LocalPlatformFontFamily provides platformFont,
        LocalAppChromeHazeState provides hazeState,
        LocalPlatformWindow provides platformWindow,
        LocalToaster providesDefault androidToaster,
        LocalBrowserNavigator providesDefault browserNavigator,
        LocalTimeFormatter providesDefault timeFormatter,
        LocalImageViewerHandler providesDefault imageViewerHandler,
    ) {
        SystemBarColorEffect(isDark = isDark)
        AniTheme {
            ProvideAniMotionCompositionLocals {
                content()
            }
        }
    }
}
