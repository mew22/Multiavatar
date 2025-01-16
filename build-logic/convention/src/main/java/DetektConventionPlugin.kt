import com.sd.template.convention.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.detektPlugin.get().pluginId)
            }
            extensions.configure<DetektExtension> {
                toolVersion = libs.versions.detekt.get()
                source.setFrom(
                    DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
                    DetektExtension.DEFAULT_TEST_SRC_DIR_KOTLIN,
                    "src/androidTest/kotlin",
                    "src/androidMain/kotlin",
                    "src/commonMain/kotlin",
                    "src/jvmMain/kotlin",
                    "src/nativeMain/kotlin",
                    "src/nativeTest/kotlin",
                )
                parallel = true
                autoCorrect = true

                tasks.named<Detekt>("detekt") {
                    reports {
                        xml.required.set(true)
                        html.required.set(true)
                        txt.required.set(true)
                        sarif.required.set(true)
                        md.required.set(true)
                    }
                }
            }

            dependencies {
                "detektPlugins"(libs.detekt.formatting)
                "detektPlugins"(libs.detekt.rules.compose)
                "detektPlugins"(libs.detekt.twitter.compose.rules)
            }

            afterEvaluate {
                tasks.named("check") {
                    dependsOn(tasks.named("detekt"))
                }
            }
        }
    }
}
