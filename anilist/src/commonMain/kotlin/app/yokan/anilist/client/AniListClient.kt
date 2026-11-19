package app.yokan.anilist.client

import app.yokan.anilist.model.AniListAiringItem
import app.yokan.anilist.model.AniListCoverImage
import app.yokan.anilist.model.AniListMedia
import app.yokan.anilist.model.AniListRelation
import app.yokan.anilist.model.AniListStreamingEpisode
import app.yokan.anilist.model.AniListTitle
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlinx.serialization.json.put
import me.him188.ani.utils.coroutines.IO_
import me.him188.ani.utils.coroutines.runSuspendCatching

class AniListClient(
    private val httpClient: HttpClient = HttpClient(),
) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val endpoint = "https://graphql.anilist.co"

    suspend fun fetchTrending(page: Int = 1, perPage: Int = 20): Result<List<AniListMedia>> = withContext(Dispatchers.IO_) {
        runSuspendCatching {
            val query = """
                query (${'$'}page: Int, ${'$'}perPage: Int) {
                  Page(page: ${'$'}page, perPage: ${'$'}perPage) {
                    media(type: ANIME, sort: TRENDING_DESC) {
                      id
                      idMal
                      title {
                        romaji
                        english
                        native
                      }
                      coverImage {
                        extraLarge
                        large
                        medium
                      }
                      bannerImage
                      description(asHtml: false)
                      episodes
                      averageScore
                      genres
                      status
                    }
                  }
                }
            """.trimIndent()

            val variables = buildJsonObject {
                put("page", page)
                put("perPage", perPage)
            }

            executeQuery(query, variables) { root ->
                val mediaArray = root["data"]?.jsonObject?.get("Page")?.jsonObject?.get("media")?.jsonArray
                    ?: return@executeQuery emptyList()
                mediaArray.mapNotNull { parseMediaItem(it) }
            }
        }
    }

    suspend fun fetchPopular(page: Int = 1, perPage: Int = 20): Result<List<AniListMedia>> = withContext(Dispatchers.IO_) {
        runSuspendCatching {
            val query = """
                query (${'$'}page: Int, ${'$'}perPage: Int) {
                  Page(page: ${'$'}page, perPage: ${'$'}perPage) {
                    media(type: ANIME, sort: POPULARITY_DESC) {
                      id
                      idMal
                      title {
                        romaji
                        english
                        native
                      }
                      coverImage {
                        extraLarge
                        large
                        medium
                      }
                      bannerImage
                      description(asHtml: false)
                      episodes
                      averageScore
                      genres
                      status
                    }
                  }
                }
            """.trimIndent()

            val variables = buildJsonObject {
                put("page", page)
                put("perPage", perPage)
            }

            executeQuery(query, variables) { root ->
                val mediaArray = root["data"]?.jsonObject?.get("Page")?.jsonObject?.get("media")?.jsonArray
                    ?: return@executeQuery emptyList()
                mediaArray.mapNotNull { parseMediaItem(it) }
            }
        }
    }

    suspend fun searchAnime(searchQuery: String, page: Int = 1, perPage: Int = 20): Result<List<AniListMedia>> = withContext(Dispatchers.IO_) {
        runSuspendCatching {
            val query = """
                query (${'$'}search: String, ${'$'}page: Int, ${'$'}perPage: Int) {
                  Page(page: ${'$'}page, perPage: ${'$'}perPage) {
                    media(search: ${'$'}search, type: ANIME, sort: SEARCH_MATCH) {
                      id
                      idMal
                      title {
                        romaji
                        english
                        native
                      }
                      coverImage {
                        extraLarge
                        large
                        medium
                      }
                      bannerImage
                      description(asHtml: false)
                      episodes
                      averageScore
                      genres
                      status
                    }
                  }
                }
            """.trimIndent()

            val variables = buildJsonObject {
                put("search", searchQuery)
                put("page", page)
                put("perPage", perPage)
            }

            executeQuery(query, variables) { root ->
                val mediaArray = root["data"]?.jsonObject?.get("Page")?.jsonObject?.get("media")?.jsonArray
                    ?: return@executeQuery emptyList()
                mediaArray.mapNotNull { parseMediaItem(it) }
            }
        }
    }

    /**
     * Búsqueda y exploración filtrada por texto, género, formato, estado, temporada y orden.
     */
    suspend fun discoverAnime(
        searchQuery: String? = null,
        genre: String? = null,
        format: String? = null,
        status: String? = null,
        season: String? = null,
        seasonYear: Int? = null,
        sort: String? = null,
        page: Int = 1,
        perPage: Int = 30,
    ): Result<List<AniListMedia>> = withContext(Dispatchers.IO_) {
        runSuspendCatching {
            val cleanQuery = searchQuery?.trim()?.takeIf { it.isNotBlank() }
            val effectiveSort = sort?.takeIf { it.isNotBlank() }
                ?: if (cleanQuery != null) "SEARCH_MATCH" else "POPULARITY_DESC"

            val query = """
                query (
                  ${'$'}page: Int,
                  ${'$'}perPage: Int,
                  ${'$'}search: String,
                  ${'$'}genre: String,
                  ${'$'}format: MediaFormat,
                  ${'$'}status: MediaStatus,
                  ${'$'}season: MediaSeason,
                  ${'$'}seasonYear: Int,
                  ${'$'}sort: [MediaSort]
                ) {
                  Page(page: ${'$'}page, perPage: ${'$'}perPage) {
                    media(
                      type: ANIME,
                      isAdult: false,
                      search: ${'$'}search,
                      genre: ${'$'}genre,
                      format: ${'$'}format,
                      status: ${'$'}status,
                      season: ${'$'}season,
                      seasonYear: ${'$'}seasonYear,
                      sort: ${'$'}sort
                    ) {
                      id
                      idMal
                      title {
                        romaji
                        english
                        native
                      }
                      coverImage {
                        extraLarge
                        large
                        medium
                      }
                      bannerImage
                      description(asHtml: false)
                      episodes
                      averageScore
                      genres
                      status
                      season
                      seasonYear
                      format
                    }
                  }
                }
            """.trimIndent()

            val variables = buildJsonObject {
                put("page", page)
                put("perPage", perPage)
                cleanQuery?.let { put("search", it) }
                genre?.takeIf { it.isNotBlank() }?.let { put("genre", it) }
                format?.takeIf { it.isNotBlank() }?.let { put("format", it) }
                status?.takeIf { it.isNotBlank() }?.let { put("status", it) }
                season?.takeIf { it.isNotBlank() }?.let { put("season", it) }
                seasonYear?.takeIf { it > 0 }?.let { put("seasonYear", it) }
                put("sort", buildJsonArray { add(JsonPrimitive(effectiveSort)) })
            }

            executeQuery(query, variables) { root ->
                val mediaArray = root["data"]?.jsonObject?.get("Page")?.jsonObject?.get("media")?.jsonArray
                    ?: return@executeQuery emptyList()
                mediaArray.mapNotNull { parseMediaItem(it) }
            }
        }
    }

    /**
     * Obtiene el calendario de episodios emitidos y por emitir en un rango Unix (segundos).
     */
    suspend fun fetchAiringSchedule(
        startEpochSeconds: Long,
        endEpochSeconds: Long,
        maxPages: Int = 4,
    ): Result<List<AniListAiringItem>> = withContext(Dispatchers.IO_) {
        runSuspendCatching {
            val query = """
                query (${'$'}page: Int, ${'$'}perPage: Int, ${'$'}start: Int, ${'$'}end: Int) {
                  Page(page: ${'$'}page, perPage: ${'$'}perPage) {
                    pageInfo {
                      hasNextPage
                    }
                    airingSchedules(airingAt_greater: ${'$'}start, airingAt_lesser: ${'$'}end, sort: TIME) {
                      id
                      episode
                      airingAt
                      media {
                        id
                        idMal
                        type
                        isAdult
                        format
                        status
                        episodes
                        averageScore
                        genres
                        season
                        seasonYear
                        title {
                          romaji
                          english
                          native
                        }
                        coverImage {
                          extraLarge
                          large
                          medium
                        }
                        bannerImage
                      }
                    }
                  }
                }
            """.trimIndent()

            val collected = mutableListOf<AniListAiringItem>()
            var page = 1
            while (page <= maxPages) {
                val variables = buildJsonObject {
                    put("page", page)
                    put("perPage", 50)
                    put("start", startEpochSeconds)
                    put("end", endEpochSeconds)
                }

                val (pageItems, hasNext) = executeQuery(query, variables) { root ->
                    val pageObj = root["data"]?.jsonObject?.get("Page")?.jsonObject
                        ?: return@executeQuery emptyList<AniListAiringItem>() to false
                    val hasNextPage = pageObj["pageInfo"]?.jsonObject?.get("hasNextPage")?.jsonPrimitive?.booleanOrNull == true
                    val schedules = pageObj["airingSchedules"]?.jsonArray ?: return@executeQuery emptyList<AniListAiringItem>() to false

                    val parsed = schedules.mapNotNull { elem ->
                        val schedObj = elem.jsonObject
                        val schedId = schedObj["id"]?.jsonPrimitive?.intOrNull ?: return@mapNotNull null
                        val ep = schedObj["episode"]?.jsonPrimitive?.intOrNull ?: return@mapNotNull null
                        val airingAt = schedObj["airingAt"]?.jsonPrimitive?.longOrNull ?: return@mapNotNull null
                        val mediaObj = schedObj["media"] as? kotlinx.serialization.json.JsonObject ?: return@mapNotNull null
                        if (mediaObj["isAdult"]?.jsonPrimitive?.booleanOrNull == true) return@mapNotNull null
                        val mediaType = mediaObj["type"]?.jsonPrimitive?.contentOrNull
                        if (mediaType != null && mediaType != "ANIME") return@mapNotNull null
                        val media = parseMediaItem(mediaObj) ?: return@mapNotNull null
                        AniListAiringItem(
                            scheduleId = schedId,
                            episode = ep,
                            airingAt = airingAt,
                            media = media,
                        )
                    }
                    parsed to hasNextPage
                }

                collected.addAll(pageItems)
                if (!hasNext) break
                page++
            }

            collected.distinctBy { it.scheduleId }.sortedBy { it.airingAt }
        }
    }

    suspend fun getAnimeDetails(id: Int): Result<AniListMedia> = withContext(Dispatchers.IO_) {
        runSuspendCatching {
            val query = """
                query (${'$'}id: Int) {
                  Media(id: ${'$'}id, type: ANIME) {
                    id
                    idMal
                    title {
                      romaji
                      english
                      native
                    }
                    coverImage {
                      extraLarge
                      large
                      medium
                    }
                    bannerImage
                    description(asHtml: false)
                    episodes
                    averageScore
                    genres
                    status
                    season
                    seasonYear
                    format
                    duration
                    source
                    popularity
                    favourites
                    studios(isMain: true) {
                      nodes {
                        name
                      }
                    }
                    nextAiringEpisode {
                      episode
                      airingAt
                    }
                    synonyms
                    recommendations(sort: RATING_DESC, perPage: 10) {
                      nodes {
                        mediaRecommendation {
                          id
                          type
                          format
                          episodes
                          seasonYear
                          title {
                            romaji
                            english
                            native
                          }
                          coverImage {
                            large
                            medium
                          }
                        }
                      }
                    }
                    relations {
                      edges {
                        relationType
                        node {
                          id
                          type
                          format
                          episodes
                          seasonYear
                          title {
                            romaji
                            english
                          }
                          coverImage {
                            large
                            medium
                          }
                          relations {
                            edges {
                              relationType
                              node {
                                id
                                episodes
                                relations {
                              edges {
                                relationType
                                node {
                                  id
                                  episodes
                                  relations {
                                    edges {
                                      relationType
                                      node {
                                        id
                                        episodes
                                      }
                                    }
                                  }
                                }
                              }
                            }
                              }
                            }
                          }
                        }
                      }
                    }
                    streamingEpisodes {
                      title
                      url
                      site
                      thumbnail
                    }
                  }
                }
            """.trimIndent()

            val variables = buildJsonObject {
                put("id", id)
            }

            executeQuery(query, variables) { root ->
                val mediaObj = root["data"]?.jsonObject?.get("Media")?.jsonObject
                    ?: throw NoSuchElementException("Media not found for id $id")
                parseMediaItem(mediaObj) ?: throw IllegalStateException("Failed to parse media details")
            }
        }
    }

    private suspend fun <T> executeQuery(
        query: String,
        variables: JsonElement,
        parser: (JsonObject: kotlinx.serialization.json.JsonObject) -> T,
    ): T {
        val payload = buildJsonObject {
            put("query", query)
            put("variables", variables)
        }

        val response = httpClient.post(endpoint) {
            header(HttpHeaders.UserAgent, "Yokan/1.0 (Android; Linux)")
            contentType(ContentType.Application.Json)
            setBody(payload.toString())
        }

        if (!response.status.isSuccess()) {
            throw IllegalStateException("AniList GraphQL request failed with status: ${response.status}")
        }

        val rawText = response.bodyAsText()
        val jsonRoot = json.parseToJsonElement(rawText).jsonObject
        return parser(jsonRoot)
    }

    private fun parseMediaItem(element: JsonElement): AniListMedia? {
        val obj = element.jsonObject
        val id = obj["id"]?.jsonPrimitive?.intOrNull ?: return null
        val idMal = obj["idMal"]?.jsonPrimitive?.intOrNull

        val titleObj = obj["title"]?.jsonObject
        val title = AniListTitle(
            romaji = titleObj?.get("romaji")?.jsonPrimitive?.contentOrNull.orEmpty(),
            english = titleObj?.get("english")?.jsonPrimitive?.contentOrNull,
            native = titleObj?.get("native")?.jsonPrimitive?.contentOrNull,
        )

        val coverObj = obj["coverImage"]?.jsonObject
        val coverImage = coverObj?.let {
            AniListCoverImage(
                extraLarge = it["extraLarge"]?.jsonPrimitive?.contentOrNull,
                large = it["large"]?.jsonPrimitive?.contentOrNull,
                medium = it["medium"]?.jsonPrimitive?.contentOrNull,
            )
        }

        val bannerImage = obj["bannerImage"]?.jsonPrimitive?.contentOrNull
        val description = obj["description"]?.jsonPrimitive?.contentOrNull
        val episodes = obj["episodes"]?.jsonPrimitive?.intOrNull
        val averageScore = obj["averageScore"]?.jsonPrimitive?.intOrNull
        val status = obj["status"]?.jsonPrimitive?.contentOrNull

        // nextAiringEpisode llega como JsonNull cuando no hay próximo episodio: `as?` lo trata como null
        val nextAiringObj = obj["nextAiringEpisode"] as? kotlinx.serialization.json.JsonObject
        val nextAiringEpisode = nextAiringObj?.get("episode")?.jsonPrimitive?.intOrNull
        val nextAiringAt = nextAiringObj?.get("airingAt")?.jsonPrimitive?.longOrNull

        val genres = obj["genres"]?.jsonArray?.mapNotNull { it.jsonPrimitive.contentOrNull } ?: emptyList()
        val synonyms = obj["synonyms"]?.jsonArray?.mapNotNull { it.jsonPrimitive.contentOrNull } ?: emptyList()
        val season = obj["season"]?.jsonPrimitive?.contentOrNull
        val seasonYear = obj["seasonYear"]?.jsonPrimitive?.intOrNull
        val format = obj["format"]?.jsonPrimitive?.contentOrNull
        val duration = obj["duration"]?.jsonPrimitive?.intOrNull
        val source = obj["source"]?.jsonPrimitive?.contentOrNull
        val popularity = obj["popularity"]?.jsonPrimitive?.intOrNull
        val favourites = obj["favourites"]?.jsonPrimitive?.intOrNull
        val studios = obj["studios"]?.jsonObject?.get("nodes")?.jsonArray
            ?.mapNotNull { it.jsonObject["name"]?.jsonPrimitive?.contentOrNull }
            ?: emptyList()

        var previousEpisodes: Int? = null
        var currentRelNode: kotlinx.serialization.json.JsonObject? = obj
        while (currentRelNode != null) {
            val edges = currentRelNode["relations"]?.jsonObject?.get("edges")?.jsonArray
            var nextNode: kotlinx.serialization.json.JsonObject? = null
            if (edges != null) {
                for (edge in edges) {
                    val relType = edge.jsonObject["relationType"]?.jsonPrimitive?.contentOrNull
                    if (relType == "PREQUEL") {
                        val nodeObj = edge.jsonObject["node"]?.jsonObject
                        val ep = nodeObj?.get("episodes")?.jsonPrimitive?.intOrNull
                        if (ep != null && ep > 0) {
                            previousEpisodes = (previousEpisodes ?: 0) + ep
                        }
                        nextNode = nodeObj
                        break
                    }
                }
            }
            currentRelNode = nextNode
        }

        val parsedRelations = mutableListOf<AniListRelation>()
        val directEdges = obj["relations"]?.jsonObject?.get("edges")?.jsonArray
        if (directEdges != null) {
            for (edgeElem in directEdges) {
                val edgeObj = edgeElem.jsonObject
                val relType = edgeObj["relationType"]?.jsonPrimitive?.contentOrNull ?: continue
                val nodeObj = edgeObj["node"]?.jsonObject ?: continue
                val nodeType = nodeObj["type"]?.jsonPrimitive?.contentOrNull
                // Ignorar adaptaciones que no sean anime (Manga, Novela, etc.)
                if (nodeType != null && nodeType != "ANIME") continue

                val relId = nodeObj["id"]?.jsonPrimitive?.intOrNull ?: continue
                val titleObj = nodeObj["title"]?.jsonObject
                val relTitle = AniListTitle(
                    romaji = titleObj?.get("romaji")?.jsonPrimitive?.contentOrNull.orEmpty(),
                    english = titleObj?.get("english")?.jsonPrimitive?.contentOrNull,
                    native = titleObj?.get("native")?.jsonPrimitive?.contentOrNull,
                )
                val coverObj = nodeObj["coverImage"]?.jsonObject
                val relCover = if (coverObj != null) {
                    AniListCoverImage(
                        large = coverObj["large"]?.jsonPrimitive?.contentOrNull,
                        medium = coverObj["medium"]?.jsonPrimitive?.contentOrNull,
                    )
                } else null

                val format = nodeObj["format"]?.jsonPrimitive?.contentOrNull
                val epCount = nodeObj["episodes"]?.jsonPrimitive?.intOrNull
                val seasonYear = nodeObj["seasonYear"]?.jsonPrimitive?.intOrNull

                parsedRelations.add(
                    AniListRelation(
                        id = relId,
                        relationType = relType,
                        title = relTitle,
                        coverImage = relCover,
                        format = format,
                        episodes = epCount,
                        seasonYear = seasonYear,
                    )
                )
            }
        }
        val priorityMap = mapOf(
            "SEQUEL" to 1,
            "PREQUEL" to 2,
            "ALTERNATIVE" to 3,
            "SIDE_STORY" to 4,
            "SPIN_OFF" to 5,
        )
        val sortedRelations = parsedRelations.sortedWith(
            compareBy<AniListRelation> { priorityMap[it.relationType.uppercase()] ?: 99 }
                .thenBy { it.seasonYear ?: 9999 }
        )

        val streamingEpisodes = obj["streamingEpisodes"]?.jsonArray?.mapNotNull { epElem ->
            val epObj = epElem.jsonObject
            AniListStreamingEpisode(
                title = epObj["title"]?.jsonPrimitive?.contentOrNull.orEmpty(),
                url = epObj["url"]?.jsonPrimitive?.contentOrNull.orEmpty(),
                site = epObj["site"]?.jsonPrimitive?.contentOrNull,
                thumbnail = epObj["thumbnail"]?.jsonPrimitive?.contentOrNull,
            )
        } ?: emptyList()

        val recommendations = obj["recommendations"]?.jsonObject?.get("nodes")?.jsonArray?.mapNotNull { nodeElem ->
            val recObj = (nodeElem.jsonObject["mediaRecommendation"] as? kotlinx.serialization.json.JsonObject)
                ?: return@mapNotNull null
            val recType = recObj["type"]?.jsonPrimitive?.contentOrNull
            if (recType != null && recType != "ANIME") return@mapNotNull null
            val recId = recObj["id"]?.jsonPrimitive?.intOrNull ?: return@mapNotNull null
            val recTitleObj = recObj["title"]?.jsonObject
            val recTitle = AniListTitle(
                romaji = recTitleObj?.get("romaji")?.jsonPrimitive?.contentOrNull.orEmpty(),
                english = recTitleObj?.get("english")?.jsonPrimitive?.contentOrNull,
                native = recTitleObj?.get("native")?.jsonPrimitive?.contentOrNull,
            )
            val recCoverObj = recObj["coverImage"]?.jsonObject
            val recCover = if (recCoverObj != null) {
                AniListCoverImage(
                    large = recCoverObj["large"]?.jsonPrimitive?.contentOrNull,
                    medium = recCoverObj["medium"]?.jsonPrimitive?.contentOrNull,
                )
            } else null
            AniListRelation(
                id = recId,
                relationType = "RECOMMENDATION",
                title = recTitle,
                coverImage = recCover,
                format = recObj["format"]?.jsonPrimitive?.contentOrNull,
                episodes = recObj["episodes"]?.jsonPrimitive?.intOrNull,
                seasonYear = recObj["seasonYear"]?.jsonPrimitive?.intOrNull,
            )
        } ?: emptyList()

        return AniListMedia(
            id = id,
            title = title,
            coverImage = coverImage,
            bannerImage = bannerImage,
            description = description,
            episodes = episodes,
            averageScore = averageScore,
            genres = genres,
            status = status,
            streamingEpisodes = streamingEpisodes,
            synonyms = synonyms,
            previousEpisodesCount = previousEpisodes,
            nextAiringEpisode = nextAiringEpisode,
            nextAiringAt = nextAiringAt,
            relations = sortedRelations,
            season = season,
            seasonYear = seasonYear,
            format = format,
            duration = duration,
            source = source,
            popularity = popularity,
            favourites = favourites,
            studios = studios,
            recommendations = recommendations,
            idMal = idMal,
        )
    }
}
