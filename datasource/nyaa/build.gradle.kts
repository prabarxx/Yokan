plugins {
    id("ani.kmp-library")
    alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
    android {
        namespace = "app.yokan.datasource.nyaa"
    }
    sourceSets.commonMain.dependencies {
        api(projects.datasource.datasourceApi)
        api(projects.utils.coroutines)
        api(projects.utils.ktorClient)
        api(projects.utils.xml)
        api(projects.utils.logging)
        api(libs.kotlinx.coroutines.core)
        api(libs.kotlinx.serialization.json)
        api(libs.ktor.client.core)
    }
}
