plugins {
    alias(libs.plugins.weatherapp.android.library)
}

android {
    namespace = "com.jnasser.weather.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.weather.domain)
    implementation(projects.core.data)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.android.gms.play.services.location)
    implementation(libs.androidx.work)
    implementation(libs.koin.android.workmanager)
    implementation(libs.kotlinx.serialization.json)
}