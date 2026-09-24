package me.him188.ani.app.domain.media.player

enum class ChunkState {
    NONE,
    DOWNLOADING,
    DONE,
    NOT_AVAILABLE,
}

data class MediaCacheProgressInfo(
    val chunkWeights: List<Float> = emptyList(),
    val chunkStates: List<ChunkState> = emptyList(),
) {
    val size: Int get() = chunkStates.size
    val lastIndex: Int get() = chunkStates.lastIndex
    fun isEmpty(): Boolean = chunkStates.isEmpty()
}
