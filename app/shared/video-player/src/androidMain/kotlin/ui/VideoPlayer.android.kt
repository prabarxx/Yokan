/*
 * Copyright (C) 2024 OpenAni and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license, which can be found at the following link.
 *
 * https://github.com/open-ani/ani/blob/main/LICENSE
 */

package me.him188.ani.app.videoplayer.ui

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.SurfaceView
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.common.Tracks
import androidx.media3.common.VideoSize
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.CaptionStyleCompat
import androidx.media3.ui.PlayerView.ControllerVisibilityListener
import io.github.peerless2012.ass.media.widget.AssSubtitleView
import me.him188.ani.app.videoplayer.media.LibassExoPlayerMediampPlayer
import org.openani.mediamp.MediampPlayer
import org.openani.mediamp.exoplayer.ExoPlayerMediampPlayer
import org.openani.mediamp.exoplayer.compose.ExoPlayerMediampPlayerSurface

@OptIn(UnstableApi::class)
@Composable
actual fun VideoPlayer(
    player: MediampPlayer,
    modifier: Modifier
) {
    val isPreviewing by rememberUpdatedState(me.him188.ani.app.ui.foundation.LocalIsPreviewing.current)

    if (isPreviewing) {
        Box(modifier)
    } else {
        val libassPlayer = player as? LibassExoPlayerMediampPlayer
        val exoPlayer = libassPlayer?.exoMediampPlayer ?: player as ExoPlayerMediampPlayer
        ExoPlayerMediampPlayerSurface(exoPlayer, modifier) {
            (videoSurfaceView as? SurfaceView)?.let { registerAndroidVideoSurface(player, it) }
            controllerAutoShow = false
            useController = false
            controllerHideOnTouch = false

            // Media3 setVideoEffects may suppress onVideoSizeChanged on PlayerView (androidx/media#2284).
            // Explicitly sync the video aspect ratio with AspectRatioFrameLayout so that subtitles
            // match the 16:9 video frame instead of stretching across ultra-wide (20:9) screens.
            fun updateAspectRatio() {
                val contentFrame = findViewById<AspectRatioFrameLayout>(androidx.media3.ui.R.id.exo_content_frame) ?: return
                var ratio = 0f
                for (group in exoPlayer.impl.currentTracks.groups) {
                    if (group.isSelected && group.type == C.TRACK_TYPE_VIDEO) {
                        val format = group.getTrackFormat(0)
                        if (format.width > 0 && format.height > 0) {
                            val par = if (format.pixelWidthHeightRatio > 0f) format.pixelWidthHeightRatio else 1.0f
                            ratio = (format.width.toFloat() * par) / format.height.toFloat()
                            break
                        }
                    }
                }
                if (ratio <= 0f) {
                    val vs = exoPlayer.impl.videoSize
                    if (vs.width > 0 && vs.height > 0) {
                        val par = if (vs.pixelWidthHeightRatio > 0f) vs.pixelWidthHeightRatio else 1.0f
                        ratio = (vs.width.toFloat() * par) / vs.height.toFloat()
                    }
                }
                if (ratio <= 0f && libassPlayer != null) {
                    val vs = libassPlayer.assHandler.videoSize
                    if (vs.width > 0 && vs.height > 0) {
                        ratio = vs.width.toFloat() / vs.height.toFloat()
                    }
                }
                if (ratio > 0f) {
                    contentFrame.setAspectRatio(ratio)
                }
            }

            val playerListener = object : Player.Listener {
                override fun onTracksChanged(tracks: Tracks) {
                    updateAspectRatio()
                }

                override fun onVideoSizeChanged(videoSize: VideoSize) {
                    updateAspectRatio()
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    updateAspectRatio()
                }
            }

            exoPlayer.impl.addListener(playerListener)
            updateAspectRatio()

            addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View) {
                    exoPlayer.impl.addListener(playerListener)
                    updateAspectRatio()
                }

                override fun onViewDetachedFromWindow(v: View) {
                    exoPlayer.impl.removeListener(playerListener)
                }
            })

            subtitleView?.apply {
                if (findViewWithTag<View>("animeko_ass_subtitle_view") == null) {
                    libassPlayer?.let {
                        val assView = AssSubtitleView(context, it.assHandler).apply {
                            tag = "animeko_ass_subtitle_view"
                        }
                        addView(
                            assView,
                            FrameLayout.LayoutParams(
                                FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.MATCH_PARENT,
                                Gravity.CENTER,
                            ),
                        )
                    }
                }
                this.setStyle(
                    CaptionStyleCompat(
                        Color.WHITE,
                        0x000000FF,
                        0x00000000,
                        CaptionStyleCompat.EDGE_TYPE_OUTLINE,
                        Color.BLACK,
                        Typeface.DEFAULT,
                    ),
                )
            }
            setControllerVisibilityListener(
                ControllerVisibilityListener { visibility ->
                    if (visibility == View.VISIBLE) {
                        hideController()
                    }
                },
            )
        }
    }
}
