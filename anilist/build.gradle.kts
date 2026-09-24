plugins {
    id("ani.kmp-library")
    alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
    android {
        namespace = "app.yokan.anilist"
    }
    sourceSets.commonMain.dependencies {
        api(libs.kotlinx.coroutines.core)
        api(libs.kotlinx.serialization.json)
        api(libs.ktor.client.core)
        api(libs.ktor.client.content.negotiation)
        api(libs.ktor.serialization.kotlinx.json)
        api(projects.utils.coroutines)
        api(projects.utils.ktorClient)
        api(projects.utils.logging)
    }
}
