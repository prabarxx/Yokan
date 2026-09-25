/*
 * Copyright (C) 2024-2026 OpenAni and contributors.
 *
 * Use of this source code is governed by the GNU AGPLv3 license.
 */

package me.him188.ani.app.ui.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.him188.ani.app.ui.external.placeholder.placeholder
import me.him188.ani.app.ui.foundation.AsyncImage
import me.him188.ani.app.ui.foundation.layout.BasicCarouselItem
import me.him188.ani.app.ui.foundation.layout.CarouselItemDefaults

@Composable
fun SubjectCoverCard(
    name: String?,
    image: String?,
    isPlaceholder: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CarouselItemDefaults.shape,
    score: Int? = null,
    imageModifier: Modifier = Modifier,
    brushLayerModifier: Modifier = Modifier,
) {
    BasicCarouselItem(
        label = { CarouselItemDefaults.Text(name ?: "", maxLines = 2) },
        modifier = modifier.placeholder(isPlaceholder, shape = shape),
        maskShape = shape,
        brushLayerModifier = brushLayerModifier,
        overlay = {},
    ) {
        if (!isPlaceholder) {
            val imageContent = @Composable {
                AsyncImage(
                    image,
                    modifier = imageModifier.aspectRatio(9f / 14).fillMaxWidth(),
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                )
            }
            Surface(onClick = { onClick() }, content = imageContent)
        } else {
            Box(Modifier.aspectRatio(9f / 14).fillMaxWidth())
        }
    }
}
