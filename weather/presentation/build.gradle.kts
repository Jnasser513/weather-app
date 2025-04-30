plugins {
    alias(libs.plugins.weatherapp.android.feature.ui)
}

android {
    namespace = "com.jnasser.weather.presentation"
}

dependencies {
    implementation(projects.weather.domain)
    implementation(projects.core.domain)

    implementation(libs.google.maps.android.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)
    implementation(libs.androidx.storage)
    implementation(libs.coil.compose)
    implementation(libs.androidx.constraintlayout.compose)
}