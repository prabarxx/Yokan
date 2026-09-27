package app.yokan.ui.state

import app.yokan.anilist.model.AniListMedia

object HomeCache {
    var trendingList: List<AniListMedia> = emptyList()
    var popularList: List<AniListMedia> = emptyList()
    var isLoaded: Boolean = false

    var isSearching: Boolean = false
    var searchQuery: String = ""
    var searchResults: List<AniListMedia> = emptyList()

    fun clear() {
        trendingList = emptyList()
        popularList = emptyList()
        isLoaded = false
        isSearching = false
        searchQuery = ""
        searchResults = emptyList()
    }
}
