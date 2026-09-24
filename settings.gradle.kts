import java.util.Properties

rootProject.name = "Yokan"

pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        google()
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://androidx.dev/storage/compose-compiler/repository/")
        maven("https://jogamp.org/deployment/maven")
    }
    versionCatalogs {
        create("anitorrentLibs") {
            from("org.openani.anitorrent:catalog:0.2.0")
        }
    }
}

plugins {
    id("com.gradle.develocity") version "4.3.2"
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

develocity {
    buildScan {
        publishing.onlyIf { !System.getenv("CI").isNullOrEmpty() }
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
        uploadInBackground = System.getenv("CI").isNullOrEmpty()
    }
}

fun includeProject(projectPath: String, dir: String? = null) {
    include(projectPath)
    if (dir != null) project(projectPath).projectDir = file(dir)
}

// Utilities
includeProject(":utils:platform")
includeProject(":utils:intellij-annotations")
includeProject(":utils:logging")
includeProject(":utils:serialization", "utils/serialization")
includeProject(":utils:coroutines", "utils/coroutines")
includeProject(":utils:ktor-client", "utils/ktor-client")
includeProject(":utils:io", "utils/io")
includeProject(":utils:testing", "utils/testing")
includeProject(":utils:xml")
includeProject(":utils:build-config", "utils/build-config")
includeProject(":utils:analytics", "utils/analytics")
includeProject(":utils:ui-preview")
includeProject(":utils:video-enhancement-shader-provider")

// Torrent Engine
includeProject(":torrent:torrent-api", "torrent/api")
includeProject(":torrent:anitorrent")

// Datasource & Nyaa
includeProject(":datasource:datasource-api", "datasource/api")
includeProject(":datasource:nyaa", "datasource/nyaa")

// AniList GraphQL Module (The brain)
includeProject(":anilist")

// UI & Shared
includeProject(":app:shared:app-platform", "app/shared/app-platform")
includeProject(":app:shared:ui-foundation", "app/shared/ui-foundation")
includeProject(":app:shared:ui-adaptive", "app/shared/ui-adaptive")
includeProject(":app:shared:ui-mediaselect", "app/shared/ui-mediaselect")
includeProject(":app:shared:placeholder", "app/shared/thirdparty/placeholder")
includeProject(":app:shared:paging-compose", "app/shared/thirdparty/paging-compose")

// Video Player
includeProject(":app:shared:video-player:video-player-api", "app/shared/video-player/api")
includeProject(":app:shared:video-player:torrent-source", "app/shared/video-player/torrent-source")
includeProject(":app:shared:video-player", "app/shared/video-player")

// Android App
includeProject(":app:android", "app/android")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
