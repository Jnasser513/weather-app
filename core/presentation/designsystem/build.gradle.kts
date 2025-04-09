plugins {
    alias(libs.plugins.weatherapp.android.library.compose)
}

android {
    namespace = "com.jnasser.core.presentation.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.tooling)
    api(libs.androidx.material3)
}