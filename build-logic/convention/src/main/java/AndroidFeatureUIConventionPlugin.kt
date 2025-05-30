import com.android.build.api.dsl.LibraryExtension
import com.jnasser.convention.ExtensionType
import com.jnasser.convention.addUiLayerDependencies
import com.jnasser.convention.configureAndroidCompose
import com.jnasser.convention.configureBuildTypes
import com.jnasser.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureUIConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("weatherapp.android.library.compose")
            }

            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }
}