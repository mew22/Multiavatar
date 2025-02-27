plugins {
    alias(libs.plugins.io.github.mew22.kmp.app.plugin)
    alias(libs.plugins.io.github.mew22.cmp.plugin)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(projects.multiavatar)
    }
}