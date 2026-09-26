package app.yokan

import android.app.Application
import app.yokan.anilist.client.AniListClient
import app.yokan.datasource.animeav1.AnimeAV1Client
import app.yokan.datasource.nyaa.NyaaSearchEngine
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class YokanApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val yokanModule = module {
            single {
                HttpClient(OkHttp) {
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        })
                    }
                }
            }
            single { AniListClient(get()) }
            single { NyaaSearchEngine(get()) }
            single { AnimeAV1Client(get()) }
        }

        startKoin {
            androidLogger()
            androidContext(this@YokanApplication)
            modules(yokanModule)
        }
    }
}
