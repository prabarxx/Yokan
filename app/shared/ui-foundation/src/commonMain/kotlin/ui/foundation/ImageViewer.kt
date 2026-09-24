/*
 * Copyright (C) 2024-2026 OpenAni and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license, which can be found at the following link.
 *
 * https://github.com/open-ani/ani/blob/main/LICENSE
 */

package me.him188.ani.app.ui.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.him188.ani.app.ui.foundation.animation.AniAnimatedVisibility
import me.him188.ani.app.ui.foundation.animation.LocalAniMotionScheme

interface ImageViewerHandler {
    val imageModel: StateFlow<String?>
    val viewing: State<Boolean>

    fun viewImage(model: String?)
    fun clear()
}

val LocalImageViewerHandler: ProvidableCompositionLocal<ImageViewerHandler> = compositionLocalOf {
    error("no ImageViewerHandler provided")
}

/** [ImageViewer] 缩放层的 test tag, 供 UI 测试断言查看器已打开. */
const val IMAGE_VIEWER_TEST_TAG = "ImageViewer"

@Composable
fun rememberImageViewerHandler(): ImageViewerHandler {
    return remember {
        object : ImageViewerHandler {
            override val imageModel: MutableStateFlow<String?> = MutableStateFlow(null)
            override val viewing: MutableState<Boolean> = mutableStateOf(false)

            override fun viewImage(model: String?) {
                imageModel.value = model
                viewing.value = model != null
            }

            override fun clear() {
                imageModel.value = null
                viewing.value = false
            }
        }
    }
}

@Composable
fun ImageViewer(
    handler: ImageViewerHandler,
    onClose: () -> Unit
) {
    val model by handler.imageModel.collectAsStateWithLifecycle()

    AniAnimatedVisibility(
        visible = handler.viewing.value,
        enter = LocalAniMotionScheme.current.animatedVisibility.standardEnter,
        exit = LocalAniMotionScheme.current.animatedVisibility.standardExit,
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .testTag(IMAGE_VIEWER_TEST_TAG)
                .background(Color.Black)
                .clickable { onClose() },
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = model,
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
            )
        }
    }
}
