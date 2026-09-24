package app.yokan.media

import me.him188.ani.app.torrent.api.files.TorrentFileHandle
import org.openani.mediamp.ExperimentalMediampApi
import org.openani.mediamp.io.SeekableInput
import org.openani.mediamp.source.MediaExtraFiles
import org.openani.mediamp.source.SeekableInputMediaData
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalMediampApi::class)
class TorrentMediaData(
    val handle: TorrentFileHandle,
    private val onClose: () -> Unit,
    override val extraFiles: MediaExtraFiles = MediaExtraFiles.EMPTY,
    override val options: List<String> = emptyList(),
) : SeekableInputMediaData {
    val entry get() = handle.entry
    val filename: String get() = entry.fileName
    override val uri: String get() = "torrent://dummy/${entry.fileName}"

    override fun fileLength(): Long = entry.length

    override suspend fun createInput(coroutineContext: CoroutineContext): SeekableInput =
        entry.createInput(coroutineContext)

    override fun close() {
        onClose()
    }

    override fun toString(): String {
        return "TorrentMediaData(entry=$entry)"
    }
}
