package app.yokan.anilist.model

import kotlinx.serialization.Serializable

@Serializable
data class AniListTitle(
    val romaji: String = "",
    val english: String? = null,
    val native: String? = null,
) {
    val displayTitle: String
        get() = english?.takeIf { it.isNotBlank() }
            ?: romaji.takeIf { it.isNotBlank() }
            ?: native.orEmpty()
}

@Serializable
data class AniListCoverImage(
    val extraLarge: String? = null,
    val large: String? = null,
    val medium: String? = null,
) {
    val bestQualityUrl: String
        get() = extraLarge ?: large ?: medium.orEmpty()
}

@Serializable
data class AniListStreamingEpisode(
    val title: String = "",
    val url: String = "",
    val site: String? = null,
    val thumbnail: String? = null,
) {
    val episodeNumber: Int?
        get() {
            val match = Regex("""(?:Episode|Ep|Capítulo|Cap)\s*(\d+)""", RegexOption.IGNORE_CASE).find(title)
            return match?.groupValues?.get(1)?.toIntOrNull()
        }
}

@Serializable
data class AniListMedia(
    val id: Int,
    val title: AniListTitle = AniListTitle(),
    val coverImage: AniListCoverImage? = null,
    val bannerImage: String? = null,
    val description: String? = null,
    val episodes: Int? = null,
    val averageScore: Int? = null,
    val genres: List<String> = emptyList(),
    val status: String? = null,
    val streamingEpisodes: List<AniListStreamingEpisode> = emptyList(),
) {
    val displayScore: String
        get() = averageScore?.let { "$it%" } ?: "N/A"

    val cleanDescription: String
        get() {
            val raw = description ?: return ""
            return raw.replace(Regex("<br\\s*/?>"), "\n")
                .replace(Regex("<[^>]*>"), "")
                .trim()
        }

    val effectiveEpisodesCount: Int
        get() = episodes ?: streamingEpisodes.size.takeIf { it > 0 } ?: 0
}
