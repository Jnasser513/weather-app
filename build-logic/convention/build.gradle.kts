plugins {
    `kotlin-dsl`
}
group = "com.jnasser.weatherapp.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "weatherapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "weatherapp.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "weatherapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "weatherapp.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "weatherapp.android.feature.ui"
            implementationClass = "AndroidFeatureUIConventionPlugin"
        }
        register("androidRoom") {
            id = "weatherapp.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidDynamicFeature") {
            id = "weatherapp.android.dynamic.feature"
            implementationClass = "AndroidDynamicFeatureApplicationConventionPlugin"
        }
        register("jvm") {
            id = "weatherapp.jvm.library"
            implementationClass = "JVMLibraryConventionPlugin"
        }
        register("jvmKtor") {
            id = "weatherapp.jvm.ktor"
            implementationClass = "JVMKtorLibraryConventionPlugin"
        }
        register("jvmJunit5") {
            id = "weatherapp.jvm.junit5"
            implementationClass = "JvmJUnit5ConventionPlugin"
        }
        register("androidJunit5") {
            id = "weatherapp.android.junit5"
            implementationClass = "AndroidJUnit5ConventionPlugin"
        }
    }
}