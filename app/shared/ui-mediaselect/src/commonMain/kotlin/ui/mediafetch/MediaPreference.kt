/*
 * Copyright (C) 2024-2026 OpenAni and contributors.
 *
 * Use of this source code is governed by the GNU AGPLv3 license.
 */

package me.him188.ani.app.data.models.preference

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import me.him188.ani.datasources.api.source.MediaSourceKind
import me.him188.ani.datasources.api.topic.Resolution
import me.him188.ani.datasources.api.topic.SubtitleLanguage
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds

@Immutable
@Serializable
data class MediaPreference(
    val alliance: String? = null,
    val alliancePatterns: List<String>? = DefaultAlliancePatterns,
    val resolution: String? = null,
    val fallbackResolutions: List<String>? = listOf(
        Resolution.R2160P,
        Resolution.R1440P,
        Resolution.R1080P,
        Resolution.R720P,
    ).map { it.id },
    val subtitleLanguageId: String? = null,
    val fallbackSubtitleLanguageIds: List<String>? = listOf(
        SubtitleLanguage.Spanish,
        SubtitleLanguage.English,
        SubtitleLanguage.Japanese,
    ).map { it.id },
    val showWithoutSubtitle: Boolean = true,
    val mediaSourceId: String? = null,
    val fallbackMediaSourceIds: List<String>? = null,
    @Suppress("PropertyName") @Transient val _placeholder: Int = 0,
) {
    companion object {
        val DefaultAlliancePatterns: List<String> = listOf(
            "(?i).*Erai[-_ ]?raws.*",
            "(?i).*SubsPlease.*",
            "(?i).*Judas.*",
        )
        val PlatformDefault = MediaPreference()
        val Empty = MediaPreference(
            alliancePatterns = emptyList(),
            mediaSourceId = null,
            fallbackSubtitleLanguageIds = null,
            fallbackResolutions = null,
        )
        const val ANY_FILTER = "*"
    }
}

@Serializable
@Immutable
data class MediaSelectorSettings(
    val showDisabled: Boolean = true,
    val hideSingleEpisodeForCompleted: Boolean = false,
    val preferSeasons: Boolean = true,
    val autoEnableLastSelected: Boolean = true,
    val preferKind: MediaSourceKind? = null,
    val fastSelectWebKind: Boolean = true,
    val fastSelectWebLowTierToleranceDuration: Duration = 5.seconds,
    val enableImageCaptchaAutoSolve: Boolean = true,
    val webSearchCacheTtl: Duration = 6.hours,
    val minSeedersForTorrent: Int = 1,
    @Suppress("PropertyName") @Transient val _placeholder: Int = 0,
) {
    companion object {
        @Stable
        val Default = MediaSelectorSettings(
            preferKind = MediaSourceKind.WEB,
        )
    }
}
