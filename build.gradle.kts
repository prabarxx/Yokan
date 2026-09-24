plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlinx.atomicfu) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.kotlin.native.cocoapods) apply false
    alias(libs.plugins.kotlin.plugin.compose) apply false

    alias(libs.plugins.kotlin.plugin.serialization) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.androidx.room) apply false
    alias(libs.plugins.antlr.kotlin) apply false
    alias(libs.plugins.mannodermaus.android.junit5) apply false
    alias(libs.plugins.sentry.kotlin.multiplatform) apply false
    alias(libs.plugins.undercouch.download) apply false
    alias(libs.plugins.compose.stability.analyzer) apply false
    idea
}

group = "app.yokan"
version = providers.gradleProperty("version.name").getOrElse("1.0.0")

idea {
    module {
        excludeDirs.add(file(".kotlin"))
    }
}

tasks.register("downloadAllDependencies") {
    notCompatibleWithConfigurationCache("Filters configurations at execution time")
    description = "Resolves every resolvable configuration in every project"
    group = "help"

    doLast {
        rootProject.allprojects.forEach { p ->
            p.configurations
                .filter { it.isCanBeResolved }
                .forEach {
                    runCatching { it.resolve() }
                }
        }
    }
}
