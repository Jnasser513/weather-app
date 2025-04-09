plugins {
    alias(libs.plugins.weatherapp.android.library)
}

android {
    namespace = "com.jnasser.weather.location"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.weather.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.koin)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.android.gms.play.services.location)
}