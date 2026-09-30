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
data class AniListRelation(
    val id: Int,
    val relationType: String, // SEQUEL, PREQUEL, SIDE_STORY, SPIN_OFF, ALTERNATIVE, DIRECTOR_CUT, etc.
    val title: AniListTitle = AniListTitle(),
    val coverImage: AniListCoverImage? = null,
    val format: String? = null,
    val episodes: Int? = null,
    val seasonYear: Int? = null,
    val animeav1Slug: String? = null,
) {
    fun toAniListMedia(): AniListMedia {
        return AniListMedia(
            id = id,
            title = title,
            coverImage = coverImage,
            episodes = episodes,
        )
    }

    val displayBadge: String
        get() = when (relationType.uppercase()) {
            "SEQUEL" -> "Secuela"
            "PREQUEL" -> "Precuela"
            "DIRECTOR_CUT", "SHIN_HENSHUU_BAN" -> "Director's Cut"
            "ALTERNATIVE" -> "Versión alternativa"
            "SIDE_STORY" -> if (format?.uppercase() == "MOVIE") "Película" else "Historia secundaria"
            "SPIN_OFF" -> "Spin-off"
            else -> relationType.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() }
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
    val synonyms: List<String> = emptyList(),
    val previousEpisodesCount: Int? = null,
    val nextAiringEpisode: Int? = null,
    val nextAiringAt: Long? = null,
    val relations: List<AniListRelation> = emptyList(),
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
        get() {
            if (episodes != null && episodes > 0) return episodes
            if (streamingEpisodes.isNotEmpty()) return streamingEpisodes.size
            if (latestAiredEpisode > 0) return latestAiredEpisode
            if (status.equals("RELEASING", ignoreCase = true)) return maxOf(latestAiredEpisode, 1)
            return 1
        }

    /**
     * Último episodio ya emitido. Solo se conoce si el anime está en emisión y AniList informa el próximo.
     * 0 = desconocido / sin límite.
     */
    val latestAiredEpisode: Int
        get() {
            val next = nextAiringEpisode
            return if (status.equals("RELEASING", ignoreCase = true) && next != null) {
                (next - 1).coerceAtLeast(0)
            } else {
                0
            }
        }

    val absoluteEpisodeOffset: Int
        get() = previousEpisodesCount ?: 0

    fun calculateAbsoluteEpisode(relativeEpisode: Int): Int = absoluteEpisodeOffset + relativeEpisode
}
