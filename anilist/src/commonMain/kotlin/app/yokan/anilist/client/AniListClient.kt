package app.yokan.anilist.client

import app.yokan.anilist.model.AniListCoverImage
import app.yokan.anilist.model.AniListMedia
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
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import me.him188.ani.utils.coroutines.IO_

class AniListClient(
    private val httpClient: HttpClient = HttpClient(),
) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val endpoint = "https://graphql.anilist.co"

    suspend fun fetchTrending(page: Int = 1, perPage: Int = 20): Result<List<AniListMedia>> = withContext(Dispatchers.IO_) {
        runCatching {
            val query = """
                query (${'$'}page: Int, ${'$'}perPage: Int) {
                  Page(page: ${'$'}page, perPage: ${'$'}perPage) {
                    media(type: ANIME, sort: TRENDING_DESC) {
                      id
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
        runCatching {
            val query = """
                query (${'$'}page: Int, ${'$'}perPage: Int) {
                  Page(page: ${'$'}page, perPage: ${'$'}perPage) {
                    media(type: ANIME, sort: POPULARITY_DESC) {
                      id
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
        runCatching {
            val query = """
                query (${'$'}search: String, ${'$'}page: Int, ${'$'}perPage: Int) {
                  Page(page: ${'$'}page, perPage: ${'$'}perPage) {
                    media(search: ${'$'}search, type: ANIME, sort: SEARCH_MATCH) {
                      id
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

    suspend fun getAnimeDetails(id: Int): Result<AniListMedia> = withContext(Dispatchers.IO_) {
        runCatching {
            val query = """
                query (${'$'}id: Int) {
                  Media(id: ${'$'}id, type: ANIME) {
                    id
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
                    synonyms
                    relations {
                      edges {
                        relationType
                        node {
                          id
                          episodes
                          title {
                            romaji
                            english
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

        val genres = obj["genres"]?.jsonArray?.mapNotNull { it.jsonPrimitive.contentOrNull } ?: emptyList()
        val synonyms = obj["synonyms"]?.jsonArray?.mapNotNull { it.jsonPrimitive.contentOrNull } ?: emptyList()

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

        val streamingEpisodes = obj["streamingEpisodes"]?.jsonArray?.mapNotNull { epElem ->
            val epObj = epElem.jsonObject
            AniListStreamingEpisode(
                title = epObj["title"]?.jsonPrimitive?.contentOrNull.orEmpty(),
                url = epObj["url"]?.jsonPrimitive?.contentOrNull.orEmpty(),
                site = epObj["site"]?.jsonPrimitive?.contentOrNull,
                thumbnail = epObj["thumbnail"]?.jsonPrimitive?.contentOrNull,
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
        )
    }
}
