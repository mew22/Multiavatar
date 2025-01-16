package com.sd.template.convention

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    jvmToolchain(21)

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    //common dependencies
    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.coroutines.core)
                implementation(libs.kotlin.datetime)
                implementation(libs.kotlinx.serialization)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.koin.android)
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}