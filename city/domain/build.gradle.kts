plugins {
    alias(libs.plugins.weatherapp.jvm.library)
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.kotlinx.coroutines.core)
}