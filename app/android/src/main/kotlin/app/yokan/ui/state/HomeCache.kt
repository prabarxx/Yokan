package app.yokan.ui.state

import app.yokan.anilist.model.AniListMedia

object HomeCache {
    var trendingList: List<AniListMedia> = emptyList()
    var popularList: List<AniListMedia> = emptyList()
    var isLoaded: Boolean = false

    // Posición de la grilla principal, para volver donde estaba tras abrir un detalle
    var gridScrollIndex: Int = 0
    var gridScrollOffset: Int = 0

    var isSearching: Boolean = false
    var searchQuery: String = ""
    var searchResults: List<AniListMedia> = emptyList()

    fun clear() {
        trendingList = emptyList()
        popularList = emptyList()
        isLoaded = false
        gridScrollIndex = 0
        gridScrollOffset = 0
        isSearching = false
        searchQuery = ""
        searchResults = emptyList()
    }
}
