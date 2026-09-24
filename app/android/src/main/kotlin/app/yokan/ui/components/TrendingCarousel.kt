package app.yokan.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.yokan.anilist.model.AniListMedia
import me.him188.ani.app.ui.foundation.AsyncImage
import me.him188.ani.app.ui.foundation.layout.CarouselAutoAdvanceEffect
import me.him188.ani.app.ui.foundation.layout.CarouselItem
import me.him188.ani.app.ui.foundation.layout.CarouselItemDefaults
import me.him188.ani.app.ui.foundation.layout.rememberMaskShape

@Composable
fun TrendingCarousel(
    trendingList: List<AniListMedia>,
    onAnimeClick: (AniListMedia) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    itemSpacing: Dp = 8.dp,
) {
    if (trendingList.isEmpty()) return

    val size = CarouselItemDefaults.itemSize()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val carouselState = rememberCarouselState(initialItem = 0) { trendingList.size }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Tendencias en Emisión",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )

        Box(modifier = Modifier.padding(contentPadding).hoverable(interactionSource)) {
            HorizontalCenteredHeroCarousel(
                state = carouselState,
                modifier = Modifier.fillMaxWidth(),
                maxItemWidth = 320.dp,
                itemSpacing = itemSpacing,
                flingBehavior = CarouselDefaults.multiBrowseFlingBehavior(
                    state = carouselState,
                    snapAnimationSpec = spring(stiffness = Spring.StiffnessMedium),
                ),
            ) { index ->
                val anime = trendingList[index]
                val imageUrl = anime.bannerImage?.takeIf { it.isNotBlank() }
                    ?: anime.coverImage?.bestQualityUrl

                val scoreText = anime.averageScore?.takeIf { it > 0 }?.let { "★ $it%" }
                val genreText = anime.genres.take(2).joinToString(" • ")
                val supporting = listOfNotNull(scoreText, genreText.takeIf { it.isNotBlank() }).joinToString("   ")

                CarouselItem(
                    label = { CarouselItemDefaults.Text(anime.title.displayTitle, maxLines = 1) },
                    supportingText = {
                        if (supporting.isNotBlank()) {
                            CarouselItemDefaults.Text(supporting, maxLines = 1)
                        }
                    },
                    shape = rememberMaskShape(CarouselItemDefaults.shape),
                ) {
                    Surface(
                        onClick = { onAnimeClick(anime) },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            modifier = Modifier
                                .height(size.imageHeight)
                                .fillMaxWidth(),
                            contentDescription = anime.title.displayTitle,
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }

            CarouselAutoAdvanceEffect(enabled = !isHovered, carouselState = carouselState)
        }
    }
}
