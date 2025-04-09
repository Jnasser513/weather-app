plugins {
    alias(libs.plugins.weatherapp.android.library)
    alias(libs.plugins.weatherapp.android.room)
}

android {
    namespace = "com.jnasser.core.database"
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.bundles.koin)
}