import com.android.build.api.dsl.ApplicationExtension
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.sd.template.convention.libs
import com.sd.template.convention.configureKotlinMultiplatform
import com.sd.template.convention.configureKotlinAndroid
import com.sd.template.convention.configureBuildKonfig
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
            apply(libs.plugins.com.sd.template.detekt.plugin.get().pluginId)
        }

        extensions.configure<ApplicationExtension> {
            defaultConfig {
                applicationId = "com.sd.template"
                targetSdk = libs.versions.android.targetSdk.get().toInt()
                versionCode = 1
                versionName = "1.0"
            }
        }
        extensions.configure<KotlinMultiplatformExtension>(::configureKotlinMultiplatform)
        extensions.configure<ApplicationExtension>(::configureKotlinAndroid)
    }
}