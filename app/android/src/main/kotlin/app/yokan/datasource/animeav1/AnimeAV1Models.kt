package app.yokan.datasource.animeav1

import app.yokan.datasource.nyaa.NyaaTorrent

data class AnimeAV1MediaItem(
    val slug: String,
    val title: String,
)

data class AnimeAV1Source(
    val server: String,
    val embedUrl: String,
    val isDub: Boolean = false,
    val quality: String = "1080p",
) {
    val isUpn: Boolean get() = server.equals("UPNShare", ignoreCase = true) || embedUrl.contains("uns.bio", ignoreCase = true)

    val displayName: String get() = when {
        isUpn -> "AnimeAV1 (UPN - HLS Directo)"
        server.equals("MP4Upload", ignoreCase = true) -> "AnimeAV1 (MP4Upload - 1080p)"
        server.equals("Voe", ignoreCase = true) -> "AnimeAV1 (Voe - 720p)"
        server.equals("StreamTape", ignoreCase = true) -> "AnimeAV1 (StreamTape)"
        server.equals("YourUpload", ignoreCase = true) -> "AnimeAV1 (YourUpload)"
        server.equals("VidHide", ignoreCase = true) -> "AnimeAV1 (VidHide)"
        else -> "AnimeAV1 ($server)"
    }
}

data class WebStreamSource(
    val title: String,
    val serverName: String,
    val streamUrl: String,
    val headers: Map<String, String> = emptyMap(),
    val quality: String = "1080p",
    val isHls: Boolean = true,
)

sealed interface PlayableSource {
    val displayName: String
    val qualityTag: String

    data class Torrent(
        val torrent: NyaaTorrent,
    ) : PlayableSource {
        override val displayName: String get() = torrent.title
        override val qualityTag: String get() = torrent.quality
    }

    data class WebStream(
        val stream: WebStreamSource,
    ) : PlayableSource {
        override val displayName: String get() = stream.title
        override val qualityTag: String get() = stream.quality
    }
}
