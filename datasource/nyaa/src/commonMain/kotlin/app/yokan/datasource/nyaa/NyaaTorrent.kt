package app.yokan.datasource.nyaa

import kotlinx.serialization.Serializable

@Serializable
data class NyaaTorrent(
    val title: String,
    val magnetUrl: String,
    val seeders: Int,
    val leechers: Int,
    val size: String,
    val quality: String,
    val isSpanishOrMulti: Boolean,
    val publishDate: String = "",
    val torrentUrl: String = "",
    val infoHash: String = "",
) {
    val seedersBadgeColor: Long
        get() = when {
            seeders >= 10 -> 0xFF4CAF50 // Verde
            seeders >= 3 -> 0xFFFF9800  // Naranja
            else -> 0xFFF44336          // Rojo
        }
}
