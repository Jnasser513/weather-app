plugins {
    alias(libs.plugins.weatherapp.android.library)
    alias(libs.plugins.weatherapp.jvm.ktor)
}

android {
    namespace = "com.jnasser.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)

    implementation(libs.timber)
    implementation(libs.bundles.koin)
    implementation(libs.androidx.datastore.preference)
}