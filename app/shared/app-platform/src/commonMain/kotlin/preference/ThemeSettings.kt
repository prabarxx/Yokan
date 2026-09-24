/*
 * Copyright (C) 2024-2026 OpenAni and contributors.
 *
 * Use of this source code is governed by the GNU AGPLv3 license.
 */

package me.him188.ani.app.data.models.preference

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

private val DefaultSeedColor = Color(0xFF6750A4)

@Serializable
enum class DarkMode {
    AUTO, LIGHT, DARK,
}

@Serializable
@Immutable
data class ThemeSettings(
    val darkMode: DarkMode = DarkMode.AUTO,
    val useDynamicTheme: Boolean = false,
    val useBlackBackground: Boolean = false,
    val alwaysDarkInEpisodePage: Boolean = false,
    val useDynamicSubjectPageTheme: Boolean = false,
    val seedColorValue: ULong = DefaultSeedColor.value,
    val enableAnimatedGradientSubjectPage: Boolean = false,
    val enableFrostedGlassEffect: Boolean = false,
    @Suppress("PropertyName") @Transient val _placeholder: Int = 0,
) {
    @Transient
    val seedColor: Color = runCatching {
        if (seedColorValue > 0xFFFFFFFFu) {
            Color(value = seedColorValue)
        } else {
            Color(color = seedColorValue.toLong())
        }
    }.getOrElse { DefaultSeedColor }.let {
        if (it == Color.Unspecified) DefaultSeedColor else it
    }

    companion object {
        @Stable
        val Default = ThemeSettings()
    }
}

@Serializable
@Immutable
data class PlayerKernelConfig(
    val mpvOptions: List<String> = emptyList(),
    val exoPlayerInitEffectGraphInAdvance: Boolean = true,
    @Suppress("PropertyName") @Transient val _placeholder: Int = 0,
) {
    companion object {
        @Stable
        val Default = PlayerKernelConfig()
    }
}
