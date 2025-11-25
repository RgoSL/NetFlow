plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.gms.google-services")
    alias(libs.plugins.compose.compiler) apply false
}

