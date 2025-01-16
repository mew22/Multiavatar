import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.sd.template.build_logic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.compiler.compose.gradlePlugin)
    compileOnly(libs.buildKonfig.gradlePlugin)
    compileOnly(libs.buildKonfig.compiler.gradlePlugin)
    implementation (libs.plugin.detekt)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    // workaround to allow precompiled script to access version catalog
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatformLib") {
            id = "com.sd.template.kmp-lib-plugin"
            implementationClass = "KotlinMultiplatformLibPlugin"
        }
        register("kotlinMultiplatformApp") {
            id = "com.sd.template.kmp-app-plugin"
            implementationClass = "KotlinMultiplatformAppPlugin"
        }
        register("composeMultiplatform") {
            id = "com.sd.template.cmp-plugin"
            implementationClass = "ComposeMultiplatformPlugin"
        }
        register("detektPlugin") {
            id = "com.sd.template.detekt-plugin"
            implementationClass = "DetektConventionPlugin"
        }
    }
}
