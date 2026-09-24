package app.yokan.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.yokan.anilist.model.AniListMedia
import me.him188.ani.app.ui.subject.SubjectCoverCard

@Composable
fun AnimePosterCard(
    anime: AniListMedia,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SubjectCoverCard(
        name = anime.title.displayTitle,
        image = anime.coverImage?.bestQualityUrl,
        isPlaceholder = false,
        onClick = onClick,
        score = anime.averageScore,
        modifier = modifier,
    )
}
