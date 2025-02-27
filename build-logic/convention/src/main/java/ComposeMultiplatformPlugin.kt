import io.github.mew22.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeMultiplatformPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.composeMultiplatform.get().pluginId)
            apply(libs.plugins.composeCompiler.get().pluginId)
            apply(libs.plugins.io.github.mew22.detekt.plugin.get().pluginId)
        }

        val composeDeps = extensions.getByType<ComposeExtension>().dependencies

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                androidMain.dependencies {
                    implementation(composeDeps.preview)
                    implementation(libs.androidx.activity.compose)
                }
                commonMain {
                    dependencies {
                        implementation(composeDeps.runtime)
                        implementation(composeDeps.foundation)
                        implementation(composeDeps.material3)
                        implementation(composeDeps.materialIconsExtended)
                        implementation(composeDeps.material)
                        implementation(composeDeps.ui)
                        implementation(composeDeps.components.resources)
                        implementation(composeDeps.components.uiToolingPreview)
                        implementation(libs.coil.compose)
                        implementation(libs.coil.svg)
                        implementation(libs.navigation)
                        implementation(libs.androidx.lifecycle.viewmodel)
                        implementation(libs.androidx.lifecycle.runtime.compose)
                        implementation(libs.koin.compose)
                        implementation(libs.koin.compose.viewmodel)
                        implementation(libs.koin.compose.viewmodel.navigation)
                    }
                }

                val desktopMain = getByName("desktopMain")
                desktopMain.dependencies {
                    implementation(composeDeps.desktop.currentOs)
                    implementation(libs.coroutines.swing)
                }

                jsMain {
                    dependencies {
                        implementation(composeDeps.html.core)
                        implementation(composeDeps.runtime)
                    }
                }
            }
        }

        extensions.configure<ComposeExtension> {
            extensions.configure<DesktopExtension> {
                application {
                    mainClass = "io.github.mew22.MainKt"

                    nativeDistributions {
                        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                        packageName = "io.github.mew22"
                        packageVersion = "1.0.0"
                    }
                }
            }
        }

        extensions.configure<ComposeCompilerGradlePluginExtension> {
            featureFlags.set(
                listOf(
                    ComposeFeatureFlag.StrongSkipping, ComposeFeatureFlag.OptimizeNonSkippingGroups
                )
            )
            reportsDestination.set(layout.buildDirectory.dir("compose_compiler"))
            metricsDestination.set(layout.buildDirectory.dir("compose_compiler"))
        }

        dependencies {
            "debugImplementation"(composeDeps.uiTooling)
        }
    }
}