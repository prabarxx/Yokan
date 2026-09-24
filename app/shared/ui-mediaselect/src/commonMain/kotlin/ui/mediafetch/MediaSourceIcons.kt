package me.him188.ani.app.ui.settings.rendering

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DownloadDone
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Radar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.Icon
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import me.him188.ani.datasources.api.source.MediaSourceInfo
import me.him188.ani.datasources.api.source.MediaSourceKind
import me.him188.ani.datasources.api.source.MediaSourceLocation

object MediaSourceIcons {
    inline val KindWeb get() = Icons.Rounded.Public
    inline val KindBT get() = Icons.Rounded.Public
    inline val KindLocal get() = Icons.Rounded.DownloadDone

    @Stable
    fun kind(kind: MediaSourceKind) = when (kind) {
        MediaSourceKind.WEB -> KindWeb
        MediaSourceKind.BitTorrent -> KindBT
        MediaSourceKind.LocalCache -> KindLocal
    }

    inline val LocationLocal get() = Icons.Rounded.DownloadDone
    inline val LocationLan get() = Icons.Rounded.Radar
    inline val LocationOnline get() = Icons.Rounded.Public

    @Stable
    fun location(location: MediaSourceLocation, kind: MediaSourceKind) = when (location) {
        MediaSourceLocation.Local -> LocationLocal
        MediaSourceLocation.Lan -> LocationLan
        MediaSourceLocation.Online -> kind(kind)
    }

    @Stable
    fun getDefaultIconUrl(info: MediaSourceInfo): String {
        return URLBuilder().apply {
            takeFrom("https://ui-avatars.com/api")
            parameters.append("name", info.displayName)
        }.buildString()
    }
}

@Composable
fun MediaSourceIcon(
    iconUrl: String?,
    displayName: String,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Rounded.Public,
        contentDescription = displayName,
        modifier = modifier,
    )
}

@Composable
fun SmallMediaSourceIcon(
    iconUrl: String?,
    displayName: String,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Rounded.Public,
        contentDescription = displayName,
        modifier = modifier,
    )
}
