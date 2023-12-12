package dev.sasikanth.myprojecttemplates

import com.android.tools.idea.wizard.template.RecipeExecutor

fun RecipeExecutor.addRetrofitDependencies(
    retrofitVersion: String = "2.9.0",
    moshiVersion: String = "1.15.0"
) {
    addDependency(mavenCoordinate = "com.squareup.retrofit2:retrofit:$retrofitVersion")
    addDependency(mavenCoordinate = "com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    addDependency(mavenCoordinate = "com.squareup.moshi:moshi:$moshiVersion")
    addDependency(mavenCoordinate = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion", configuration = "ksp")
}

fun RecipeExecutor.addRoomDependencies(roomVersion: String = "2.6.1") {
    addDependency(mavenCoordinate = "androidx.room:room-compiler:$roomVersion", configuration = "ksp")
    addDependency(mavenCoordinate = "androidx.room:room-runtime:$roomVersion")
    addDependency(mavenCoordinate = "androidx.room:room-ktx:$roomVersion")
    addDependency(mavenCoordinate = "androidx.room:room-compiler:$roomVersion", configuration = "annotationProcessor")
}

fun RecipeExecutor.addHiltDependencies(hiltVersion: String = "2.49") {
    applyPlugin("com.google.dagger.hilt.android", hiltVersion)
    addDependency(mavenCoordinate = "com.google.dagger:hilt-compiler:$hiltVersion", configuration = "ksp")
    addDependency(mavenCoordinate = "com.google.dagger:hilt-android:$hiltVersion")
    addDependency(mavenCoordinate = "androidx.hilt:hilt-navigation-compose:1.1.0")
}

fun RecipeExecutor.addMiscUiDependencies() {
    addDependency(mavenCoordinate = "androidx.core:core-ktx:1.12.0")
    addDependency(mavenCoordinate = "androidx.activity:activity-compose:1.8.1")
    addDependency(mavenCoordinate = "io.coil-kt:coil-compose:2.5.0")
}

fun RecipeExecutor.addLifecycleDependencies(lifecycleVersion: String = "2.6.2") {
    addDependency(mavenCoordinate = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    addDependency(mavenCoordinate = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    addDependency(mavenCoordinate = "androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
}

fun RecipeExecutor.addNavDependencies(navVersion: String = "2.7.5") {
    addDependency(mavenCoordinate = "androidx.navigation:navigation-compose:$navVersion")
    addDependency(mavenCoordinate = "androidx.navigation:navigation-fragment-ktx:$navVersion")
    addDependency(mavenCoordinate = "androidx.navigation:navigation-ui-ktx:$navVersion")
}

fun RecipeExecutor.addComposeDependencies(composeBom: String = "2023.10.01") {
    addPlatformDependency(mavenCoordinate = "androidx.compose:compose-bom:$composeBom")
    addPlatformDependency(mavenCoordinate = "androidx.compose:compose-bom:$composeBom", "androidTestImplementation")
    addDependency(mavenCoordinate = "androidx.compose.ui:ui")
    addDependency(mavenCoordinate = "androidx.compose.ui:ui-graphics")
    addDependency(mavenCoordinate = "androidx.compose.ui:ui-tooling-preview")
    addDependency(mavenCoordinate = "androidx.compose.material3:material3")
    addDependency(mavenCoordinate = "androidx.compose.ui:ui-tooling", configuration = "debugImplementation")
    addDependency(mavenCoordinate = "androidx.compose.ui:ui-test-manifest:1.5.4", configuration = "debugImplementation")
    addDependency(mavenCoordinate = "androidx.compose.ui:ui-test-junit4", configuration = "androidTestImplementation")
}