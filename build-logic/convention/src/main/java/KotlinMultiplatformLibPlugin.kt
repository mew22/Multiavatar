import com.android.build.api.dsl.LibraryExtension
import io.github.mew22.convention.libs
import io.github.mew22.convention.configureKotlinMultiplatform
import io.github.mew22.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformLibPlugin: Plugin<Project> {

    override fun apply(target: Project):Unit = with(target){
        with(pluginManager){
            apply(libs.plugins.kotlinMultiplatform.get().pluginId)
            apply(libs.plugins.androidLibrary.get().pluginId)
            apply(libs.plugins.io.github.mew22.detekt.plugin.get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension>(::configureKotlinMultiplatform)
        extensions.configure<LibraryExtension>(::configureKotlinAndroid)
    }
}