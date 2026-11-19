package app.yokan.media

import android.content.Context
import android.os.StatFs
import android.util.Log
import app.yokan.datasource.nyaa.NyaaTorrent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.Locale
import kotlin.math.log10
import kotlin.math.pow

data class CachedTorrentItem(
    val id: String,
    val title: String,
    val magnetUrl: String,
    val torrentUrl: String = "",
    val fileSizeBytes: Long = 0L,
    val addedTimeMillis: Long = System.currentTimeMillis(),
    val saveDirPath: String = "",
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

    fun toJson(): JSONObject = JSONObject().apply {
        put("id", id)
        put("title", title)
        put("magnetUrl", magnetUrl)
        put("torrentUrl", torrentUrl)
        put("fileSizeBytes", fileSizeBytes)
        put("addedTimeMillis", addedTimeMillis)
        put("saveDirPath", saveDirPath)
    }

    companion object {
        fun fromJson(o: JSONObject): CachedTorrentItem = CachedTorrentItem(
            id = o.optString("id", ""),
            title = o.optString("title", ""),
            magnetUrl = o.optString("magnetUrl", ""),
            torrentUrl = o.optString("torrentUrl", ""),
            fileSizeBytes = o.optLong("fileSizeBytes", 0L),
            addedTimeMillis = o.optLong("addedTimeMillis", System.currentTimeMillis()),
            saveDirPath = o.optString("saveDirPath", ""),
        )
    }
}

data class StorageInfo(
    val cacheUsedBytes: Long,
    val deviceFreeBytes: Long,
    val deviceTotalBytes: Long,
    val torrentCacheBytes: Long = cacheUsedBytes,
    val imageCacheBytes: Long = 0L,
) {
    val usedFormatted: String get() = formatBytes(cacheUsedBytes)
    val torrentUsedFormatted: String get() = formatBytes(torrentCacheBytes)
    val imageUsedFormatted: String get() = formatBytes(imageCacheBytes)
    val freeFormatted: String get() = formatBytes(deviceFreeBytes)
    val totalFormatted: String get() = formatBytes(deviceTotalBytes)
    val progress: Float
        get() = when {
            deviceTotalBytes <= 0L || cacheUsedBytes <= 0L -> 0f
            else -> (cacheUsedBytes.toFloat() / deviceTotalBytes.toFloat()).coerceIn(0.01f, 1f)
        }
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
            val arr = JSONArray(rawJson)
            val list = (0 until arr.length()).mapNotNull { idx ->
                val obj = arr.optJSONObject(idx) ?: return@mapNotNull null
                CachedTorrentItem.fromJson(obj).takeIf { it.id.isNotBlank() || it.magnetUrl.isNotBlank() }
            }
            _cachedTorrents.value = list.sortedByDescending { it.addedTimeMillis }
        }.onFailure {
            Log.e(TAG, "Failed to load cached torrents", it)
        }
    }

    private fun saveCachedTorrents(items: List<CachedTorrentItem>) {
        _cachedTorrents.value = items
        scope.launch {
            runCatching {
                val arr = JSONArray()
                items.forEach { arr.put(it.toJson()) }
                prefs.edit().putString(PREF_KEY_ITEMS, arr.toString()).apply()
            }.onFailure {
                Log.e(TAG, "Failed to save cached torrents", it)
            }
        }
    }

    private fun extractInfoHashFromMagnet(magnetUrl: String): String {
        return BTIH_REGEX.find(magnetUrl)?.groupValues?.get(1)?.lowercase().orEmpty()
    }

    fun recordCachedTorrent(
        title: String,
        magnetUrl: String,
        torrentUrl: String = "",
        fileSizeBytes: Long = 0L,
        infoHash: String = "",
        saveDirPath: String = "",
    ) {
        val resolvedId = infoHash.trim().lowercase()
            .ifBlank { extractInfoHashFromMagnet(magnetUrl) }
            .ifBlank { magnetUrl.hashCode().toString() }

        val current = _cachedTorrents.value.toMutableList()
        val existingIndex = current.indexOfFirst {
            it.id.equals(resolvedId, ignoreCase = true) ||
                (saveDirPath.isNotBlank() && it.saveDirPath == saveDirPath) ||
                it.magnetUrl == magnetUrl
        }
        val previous = current.getOrNull(existingIndex)
        val item = CachedTorrentItem(
            id = resolvedId,
            title = title,
            magnetUrl = magnetUrl,
            torrentUrl = torrentUrl.ifBlank { previous?.torrentUrl.orEmpty() },
            fileSizeBytes = if (fileSizeBytes > 0) fileSizeBytes else (previous?.fileSizeBytes ?: 0L),
            addedTimeMillis = System.currentTimeMillis(),
            saveDirPath = saveDirPath.ifBlank { previous?.saveDirPath.orEmpty() },
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
            val torrentDir = cacheDir.resolve("torrents")
            val imageDir = cacheDir.resolve("image-cache")
            val torrentBytes = calculateDirSize(torrentDir)
            val imageBytes = calculateDirSize(imageDir)
            val usedBytes = torrentBytes + imageBytes
            val stat = runCatching { StatFs(cacheDir.path) }.getOrNull()
            val freeBytes = stat?.let { it.availableBlocksLong * it.blockSizeLong } ?: 0L
            val totalBytes = stat?.let { it.blockCountLong * it.blockSizeLong } ?: 0L
            _storageInfo.value = StorageInfo(
                cacheUsedBytes = usedBytes,
                deviceFreeBytes = freeBytes,
                deviceTotalBytes = totalBytes,
                torrentCacheBytes = torrentBytes,
                imageCacheBytes = imageBytes,
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
        val torrentManager = TorrentManager.getInstance(appContext)

        // Determinar directorio exacto de piezas
        val exactPieceDir = when {
            item.saveDirPath.isNotBlank() -> File(item.saveDirPath)
            else -> piecesDir.resolve(item.id)
        }

        if (exactPieceDir.exists() && exactPieceDir.canonicalPath.startsWith(torrentDir.canonicalPath)) {
            torrentManager.closeSessionByKey(exactPieceDir.name)
            runCatching { exactPieceDir.deleteRecursively() }
                .onFailure { Log.e(TAG, "Failed to delete piece directory: ${exactPieceDir.path}", it) }
        }

        // Eliminar también el archivo .torrent cacheado por HTTP si existe
        if (item.torrentUrl.isNotBlank()) {
            val cachedTorrentFile = torrentDir.resolve("torrentFiles")
                .resolve("${item.torrentUrl.hashCode()}.txt")
            if (cachedTorrentFile.exists()) {
                runCatching { cachedTorrentFile.delete() }
            }
        }

        refreshStorageInfo()
    }

    suspend fun clearAllCache(onClearImageCache: (() -> Unit)? = null) = withContext(Dispatchers.IO) {
        saveCachedTorrents(emptyList())

        // 1. Cerrar todas las sesiones activas de torrent antes de borrar sus archivos
        runCatching {
            TorrentManager.getInstance(appContext).closeAllSessions()
        }.onFailure {
            Log.e(TAG, "Failed to close active torrent sessions before clearing cache", it)
        }

        // 2. Borrar el directorio propio de torrents (pieces y torrentFiles)
        runCatching {
            val torrentDir = appContext.cacheDir.resolve("torrents")
            if (torrentDir.exists()) {
                torrentDir.listFiles()?.forEach { child ->
                    child.deleteRecursively()
                }
            }
        }.onFailure {
            Log.e(TAG, "Failed to clear torrent cache directory", it)
        }

        // 3. Limpiar la caché de imágenes de Sketch (memoria + disco)
        runCatching {
            onClearImageCache?.invoke()
        }.onFailure {
            Log.e(TAG, "Failed to clear Sketch image cache via callback", it)
        }
        runCatching {
            val imageDir = appContext.cacheDir.resolve("image-cache")
            if (imageDir.exists()) {
                imageDir.listFiles()?.forEach { child ->
                    child.deleteRecursively()
                }
            }
        }.onFailure {
            Log.e(TAG, "Failed to clear image cache directory", it)
        }
        refreshStorageInfo()
    }

    companion object {
        private const val TAG = "YokanCache"
        private const val PREF_KEY_ITEMS = "cached_torrent_items"
        private val BTIH_REGEX = Regex("""xt=urn:btih:([a-fA-F0-9]{40}|[a-zA-Z2-7]{32})""")

        @Volatile
        private var instance: CacheStorageManager? = null

        fun getInstance(context: Context): CacheStorageManager =
            instance ?: synchronized(this) {
                instance ?: CacheStorageManager(context).also { instance = it }
            }
    }
}
