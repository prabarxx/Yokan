package app.yokan.media

import android.content.Context
import android.os.StatFs
import app.yokan.datasource.nyaa.NyaaTorrent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.him188.ani.utils.logging.logger
import java.io.File
import java.util.Locale
import kotlin.math.log10
import kotlin.math.pow

@Serializable
data class CachedTorrentItem(
    val id: String,
    val title: String,
    val magnetUrl: String,
    val torrentUrl: String = "",
    val fileSizeBytes: Long = 0L,
    val addedTimeMillis: Long = System.currentTimeMillis(),
) {
    val formattedSize: String get() = formatBytes(fileSizeBytes)

    fun toNyaaTorrent(): NyaaTorrent {
        return NyaaTorrent(
            title = title,
            magnetUrl = magnetUrl,
            seeders = 10,
            leechers = 0,
            size = formattedSize,
            quality = "HD",
            isSpanishOrMulti = true,
            torrentUrl = torrentUrl,
            infoHash = id,
        )
    }
}

data class StorageInfo(
    val cacheUsedBytes: Long,
    val deviceFreeBytes: Long,
    val deviceTotalBytes: Long,
) {
    val usedFormatted: String get() = formatBytes(cacheUsedBytes)
    val freeFormatted: String get() = formatBytes(deviceFreeBytes)
    val totalFormatted: String get() = formatBytes(deviceTotalBytes)
    val progress: Float
        get() = if (deviceTotalBytes > 0) {
            (cacheUsedBytes.toFloat() / deviceTotalBytes.toFloat()).coerceIn(0.01f, 1f)
        } else 0f
}

fun formatBytes(bytes: Long): String {
    if (bytes <= 0) return "0 B"
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (log10(bytes.toDouble()) / log10(1024.0)).toInt().coerceIn(0, units.size - 1)
    val value = bytes / 1024.0.pow(digitGroups.toDouble())
    return String.format(Locale.getDefault(), "%.1f %s", value, units[digitGroups])
}

class CacheStorageManager private constructor(context: Context) {
    private val appContext = context.applicationContext
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val prefs = appContext.getSharedPreferences("yokan_cache_storage", Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    private val _cachedTorrents = MutableStateFlow<List<CachedTorrentItem>>(emptyList())
    val cachedTorrents: StateFlow<List<CachedTorrentItem>> = _cachedTorrents.asStateFlow()

    private val _storageInfo = MutableStateFlow(
        StorageInfo(cacheUsedBytes = 0L, deviceFreeBytes = 0L, deviceTotalBytes = 0L)
    )
    val storageInfo: StateFlow<StorageInfo> = _storageInfo.asStateFlow()

    init {
        loadCachedTorrents()
        refreshStorageInfo()
    }

    private fun loadCachedTorrents() {
        val rawJson = prefs.getString(PREF_KEY_ITEMS, null) ?: return
        runCatching {
            val list = json.decodeFromString<List<CachedTorrentItem>>(rawJson)
            _cachedTorrents.value = list.sortedByDescending { it.addedTimeMillis }
        }.onFailure {
            logger.error("Failed to load cached torrents", it)
        }
    }

    private fun saveCachedTorrents(items: List<CachedTorrentItem>) {
        _cachedTorrents.value = items
        scope.launch {
            runCatching {
                val encoded = json.encodeToString(items)
                prefs.edit().putString(PREF_KEY_ITEMS, encoded).apply()
            }.onFailure {
                logger.error("Failed to save cached torrents", it)
            }
        }
    }

    fun recordCachedTorrent(
        title: String,
        magnetUrl: String,
        torrentUrl: String = "",
        fileSizeBytes: Long = 0L,
    ) {
        val id = title.hashCode().toString()
        val current = _cachedTorrents.value.toMutableList()
        val existingIndex = current.indexOfFirst { it.id == id || it.magnetUrl == magnetUrl }
        val item = CachedTorrentItem(
            id = id,
            title = title,
            magnetUrl = magnetUrl,
            torrentUrl = torrentUrl,
            fileSizeBytes = if (fileSizeBytes > 0) fileSizeBytes else (current.getOrNull(existingIndex)?.fileSizeBytes ?: 0L),
            addedTimeMillis = System.currentTimeMillis(),
        )
        if (existingIndex >= 0) {
            current[existingIndex] = item
        } else {
            current.add(0, item)
        }
        saveCachedTorrents(current)
        refreshStorageInfo()
    }

    fun refreshStorageInfo() {
        scope.launch {
            val cacheDir = appContext.cacheDir
            val usedBytes = calculateDirSize(cacheDir)
            val stat = runCatching { StatFs(cacheDir.path) }.getOrNull()
            val freeBytes = stat?.let { it.availableBlocksLong * it.blockSizeLong } ?: 0L
            val totalBytes = stat?.let { it.blockCountLong * it.blockSizeLong } ?: 0L
            _storageInfo.value = StorageInfo(
                cacheUsedBytes = usedBytes,
                deviceFreeBytes = freeBytes,
                deviceTotalBytes = totalBytes,
            )
        }
    }

    private fun calculateDirSize(dir: File?): Long {
        if (dir == null || !dir.exists()) return 0L
        var total = 0L
        val files = dir.listFiles() ?: return 0L
        for (file in files) {
            total += if (file.isDirectory) calculateDirSize(file) else file.length()
        }
        return total
    }

    suspend fun deleteTorrent(item: CachedTorrentItem) = withContext(Dispatchers.IO) {
        val current = _cachedTorrents.value.filter { it.id != item.id }
        saveCachedTorrents(current)

        val torrentDir = appContext.cacheDir.resolve("torrents")
        val piecesDir = torrentDir.resolve("pieces")
        if (piecesDir.exists()) {
            piecesDir.listFiles()?.forEach { subDir ->
                if (subDir.name.contains(item.id) || (subDir.isDirectory && subDir.listFiles()?.any { it.name.contains(item.title, ignoreCase = true) } == true)) {
                    subDir.deleteRecursively()
                }
            }
        }
        refreshStorageInfo()
    }

    suspend fun clearAllCache() = withContext(Dispatchers.IO) {
        saveCachedTorrents(emptyList())

        runCatching {
            appContext.cacheDir.listFiles()?.forEach { file ->
                file.deleteRecursively()
            }
        }.onFailure {
            logger.error("Failed to clear cache directory", it)
        }
        refreshStorageInfo()
    }

    companion object {
        private val logger = logger<CacheStorageManager>()
        private const val PREF_KEY_ITEMS = "cached_torrent_items"

        @Volatile
        private var instance: CacheStorageManager? = null

        fun getInstance(context: Context): CacheStorageManager =
            instance ?: synchronized(this) {
                instance ?: CacheStorageManager(context).also { instance = it }
            }
    }
}
