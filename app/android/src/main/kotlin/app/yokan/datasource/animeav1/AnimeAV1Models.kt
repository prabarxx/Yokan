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

    val isMp4Upload: Boolean get() = server.equals("MP4Upload", ignoreCase = true) || embedUrl.contains("mp4upload.com", ignoreCase = true)

    val isVoe: Boolean get() = server.equals("Voe", ignoreCase = true) || embedUrl.contains("voe.", ignoreCase = true)

    val isVidHide: Boolean get() = server.equals("VidHide", ignoreCase = true) ||
        server.equals("Filemoon", ignoreCase = true) ||
        embedUrl.contains("vidhide", ignoreCase = true) ||
        embedUrl.contains("filemoon", ignoreCase = true)

    val isStreamTape: Boolean get() = server.equals("StreamTape", ignoreCase = true) ||
        embedUrl.contains("streamtape.", ignoreCase = true) ||
        embedUrl.contains("strcloud.", ignoreCase = true)

    val isSupportedExtractor: Boolean get() = isUpn ||
        isMp4Upload ||
        server.equals("YourUpload", ignoreCase = true) ||
        isVoe ||
        isVidHide ||
        isStreamTape

    val langTag: String get() = if (isDub) "LAT" else "SUB"

    val displayName: String get() = when {
        isUpn -> "AnimeAV1 [$langTag] (UPN - HLS Directo)"
        isMp4Upload -> "AnimeAV1 [$langTag] (MP4Upload - 1080p)"
        isVoe -> "AnimeAV1 [$langTag] (Voe - HLS)"
        isStreamTape -> "AnimeAV1 [$langTag] (StreamTape)"
        server.equals("YourUpload", ignoreCase = true) -> "AnimeAV1 [$langTag] (YourUpload)"
        isVidHide -> "AnimeAV1 [$langTag] (VidHide - HLS)"
        else -> "AnimeAV1 [$langTag] ($server)"
    }
}

data class WebStreamSource(
    val title: String,
    val serverName: String,
    val streamUrl: String,
    val headers: Map<String, String> = emptyMap(),
    val quality: String = "1080p",
    val isHls: Boolean = true,
    val isDub: Boolean = false,
    /**
     * Parámetros de query que el CDN exige en **todas** las peticiones de la secuencia
     * (manifest, playlists de variante y segmentos).
     *
     * El CDN de UPNShare (`uns.bio`) protege sus archivos con un token (`k`/`kx`) que llega
     * en el campo `pk` de la respuesta cifrada. Los playlists referencian los segmentos con
     * URLs *relativas*, así que el reproductor las resuelve sin el query original y el CDN
     * responde 403. Estos parámetros se reinyectan en cada petición (ver `QueryParamDataSource`).
     */
    val queryParams: Map<String, String> = emptyMap(),
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
