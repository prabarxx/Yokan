package app.yokan.media

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import me.him188.ani.app.torrent.anitorrent.AnitorrentDownloaderFactory
import me.him188.ani.app.torrent.anitorrent.AnitorrentTorrentDownloader
import me.him188.ani.app.torrent.api.HttpFileDownloader
import me.him188.ani.app.torrent.api.TorrentDownloaderConfig
import me.him188.ani.utils.io.inSystem
import me.him188.ani.utils.io.toKtPath
import me.him188.ani.utils.logging.logger

class TorrentManager private constructor(context: Context) {
    private val appContext = context.applicationContext
    private val factory = AnitorrentDownloaderFactory()

    val downloader: AnitorrentTorrentDownloader<*, *> by lazy {
        logger.info("Loading Anitorrent native libraries...")
        factory.libraryLoader.loadLibraries()
        val torrentDir = appContext.cacheDir.resolve("torrents").apply { mkdirs() }
        logger.info("Initializing Anitorrent downloader at: ${torrentDir.absolutePath}")
        factory.createDownloader(
            rootDataDirectory = torrentDir.toKtPath().inSystem,
            httpFileDownloader = object : HttpFileDownloader {
                override suspend fun download(url: String): ByteArray = withContext(Dispatchers.IO) {
                    java.net.URL(url).readBytes()
                }
                override fun close() {}
            },
            torrentDownloaderConfig = TorrentDownloaderConfig(),
            parentCoroutineContext = Dispatchers.IO + SupervisorJob(),
        )
    }

    companion object {
        private val logger = logger<TorrentManager>()

        @Volatile
        private var instance: TorrentManager? = null

        fun getInstance(context: Context): TorrentManager =
            instance ?: synchronized(this) {
                instance ?: TorrentManager(context).also { instance = it }
            }
    }
}
