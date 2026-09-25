package app.yokan.ui.state

import app.yokan.anilist.model.AniListMedia

object HomeCache {
    var trendingList: List<AniListMedia> = emptyList()
    var popularList: List<AniListMedia> = emptyList()
    var isLoaded: Boolean = false

    fun clear() {
        trendingList = emptyList()
        popularList = emptyList()
        isLoaded = false
    }
}
