package com.sd.template.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        val moduleName = path.split(":").drop(2).joinToString(".")
        namespace = if(moduleName.isNotEmpty()) "com.sd.template.$moduleName" else "com.sd.template"

        compileSdk = libs.versions.android.compileSdk.get().toInt()

        defaultConfig {
            minSdk = libs.versions.android.minSdk.get().toInt()
        }

        buildFeatures {
            buildConfig = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        lint {
            disable += "FlowOperatorInvokedInComposition"
        }
    }
}
