package app.yokan.ui.state

import app.yokan.anilist.model.AniListMedia

object HomeCache {
    var trendingList: List<AniListMedia> = emptyList()
    var popularList: List<AniListMedia> = emptyList()
    var popularPage: Int = 1
    var hasMorePopular: Boolean = true
    var isLoaded: Boolean = false

    // Posición de la grilla principal, para volver donde estaba tras abrir un detalle
    var gridScrollIndex: Int = 0
    var gridScrollOffset: Int = 0

    var isSearching: Boolean = false
    var searchQuery: String = ""
    var searchResults: List<AniListMedia> = emptyList()
    var searchPage: Int = 1
    var hasMoreSearch: Boolean = true

    // Posición de la grilla de búsqueda
    var searchGridScrollIndex: Int = 0
    var searchGridScrollOffset: Int = 0

    fun clear() {
        trendingList = emptyList()
        popularList = emptyList()
        popularPage = 1
        hasMorePopular = true
        isLoaded = false
        gridScrollIndex = 0
        gridScrollOffset = 0
        isSearching = false
        searchQuery = ""
        searchResults = emptyList()
        searchPage = 1
        hasMoreSearch = true
        searchGridScrollIndex = 0
        searchGridScrollOffset = 0
    }
}
