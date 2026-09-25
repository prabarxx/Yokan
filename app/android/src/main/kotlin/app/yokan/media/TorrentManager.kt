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
import java.net.HttpURLConnection
import java.net.URI

val FAST_PUBLIC_TRACKERS = listOf(
    "http://nyaa.tracker.wf:7777/announce",
    "udp://open.stealth.si:80/announce",
    "udp://tracker.opentrackr.org:1337/announce",
    "udp://tracker.torrent.eu.org:451/announce",
    "udp://tracker.moeking.me:6969/announce",
    "udp://explodie.org:6969/announce",
    "udp://tracker.dler.org:6969/announce",
    "udp://open.demonii.com:1337/announce",
    "udp://exodus.desync.com:6969/announce",
    "udp://tracker.openbittorrent.com:6969/announce",
    "udp://opentracker.i2p.rocks:6969/announce",
    "udp://tracker.tiny-vps.com:6969/announce",
    "udp://retracker.hotplug.ru:2710/announce",
    "wss://tracker.openwebtorrent.com:443/announce",
    "wss://tracker.btorrent.xyz:443/announce",
)

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
                    val conn = (URI(url).toURL().openConnection() as HttpURLConnection).apply {
                        setRequestProperty(
                            "User-Agent",
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
                        )
                        setRequestProperty("Accept", "application/x-bittorrent, */*")
                        connectTimeout = 10_000
                        readTimeout = 15_000
                        instanceFollowRedirects = true
                    }
                    conn.inputStream.use { it.readBytes() }
                }
                override fun close() {}
            },
            torrentDownloaderConfig = TorrentDownloaderConfig(
                extraTrackers = FAST_PUBLIC_TRACKERS,
                downloadRateLimitBytes = 0,
                uploadRateLimitBytes = 0,
            ),
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
