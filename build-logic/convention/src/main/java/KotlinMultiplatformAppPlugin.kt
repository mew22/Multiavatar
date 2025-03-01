import com.android.build.api.dsl.ApplicationExtension
import io.github.mew22.convention.libs
import io.github.mew22.convention.configureKotlinMultiplatform
import io.github.mew22.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformAppPlugin: Plugin<Project> {

    override fun apply(target: Project):Unit = with(target){
        with(pluginManager){
            apply(libs.plugins.kotlinMultiplatform.get().pluginId)
            apply(libs.plugins.androidApplication.get().pluginId)
            apply(libs.plugins.kotlinxSerialization.get().pluginId)
            apply(libs.plugins.io.github.mew22.detekt.plugin.get().pluginId)
        }

        extensions.configure<ApplicationExtension> {
            defaultConfig {
                applicationId = "io.github.mew22"
                targetSdk = libs.versions.android.targetSdk.get().toInt()
                versionCode = 1
                versionName = "1.0"
            }
        }
        extensions.configure<KotlinMultiplatformExtension>(::configureKotlinMultiplatform)
        extensions.configure<ApplicationExtension>(::configureKotlinAndroid)
    }
}