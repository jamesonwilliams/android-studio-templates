package org.nosemaj.astemplates

import com.android.tools.idea.npw.module.recipes.addTestDependencies
import com.android.tools.idea.npw.module.recipes.generateManifest
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.PackageName
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import org.nosemaj.astemplates.stubs.app.emptyApplication
import org.nosemaj.astemplates.stubs.app.emptyManifestXml
import org.nosemaj.astemplates.stubs.data.*
import org.nosemaj.astemplates.stubs.di.emptyAppModule
import org.nosemaj.astemplates.stubs.res.errorDrawable
import org.nosemaj.astemplates.stubs.res.placeholderDrawable
import org.nosemaj.astemplates.stubs.ui.*
import org.jetbrains.kotlin.lombok.utils.capitalize

fun RecipeExecutor.projectRecipe(
    moduleData: ModuleTemplateData,
    packageName: PackageName,
    itemName: String,
    restApiUrl: String,
    useRoom: Boolean,
) {
    applyPlugin("com.google.devtools.ksp", "1.9.10-1.0.13")
    addAllKotlinDependencies(moduleData, revision = "1.9.10")
    addTestDependencies()

    generateManifest(hasApplicationBlock = true)

    // Discretionary packages...
    addRetrofitDependencies()
    if (useRoom) addRoomDependencies()
    addHiltDependencies()
    addNavDependencies()
    addComposeDependencies()
    addMiscUiDependencies()
    addLifecycleDependencies()

    requireJavaVersion("1.8", true)
    setBuildFeature("compose", true)
    // Note: kotlinCompilerVersion default is declared in TaskManager.COMPOSE_KOTLIN_COMPILER_VERSION
    setComposeOptions(kotlinCompilerExtensionVersion = "1.5.3")

    // Activity, App, and Navigation Component
    save(
        emptyActivity(packageName),
        moduleData.srcDir.resolve("ui/MainActivity.kt")
    )
    save(
        emptyApplication(packageName, itemName),
        moduleData.srcDir.resolve("${itemName.capitalize()}Application.kt")
    )
    save(
        emptyUi(packageName, itemName),
        moduleData.srcDir.resolve("ui/${itemName.capitalize()}AppUi.kt")
    )

    // Views and ViewModels
    save(
        emptyListScreen(packageName, itemName),
        moduleData.srcDir.resolve("ui/list/${itemName.capitalize()}ListScreen.kt")
    )
    save(
        emptyListViewModel(packageName, itemName),
        moduleData.srcDir.resolve("ui/list/${itemName.capitalize()}ListViewModel.kt")
    )
    save(
        emptyDetailScreen(packageName, itemName),
        moduleData.srcDir.resolve("ui/details/${itemName.capitalize()}DetailScreen.kt")
    )
    save(
        emptyDetailViewModel(packageName, itemName),
        moduleData.srcDir.resolve("ui/details/${itemName.capitalize()}DetailViewModel.kt")
    )

    val (_, _, _, manifestOut) = moduleData

    // Manifest
    mergeXml(
        emptyManifestXml(
            itemName,
            "@style/${moduleData.themesData.main.name}"
        ),
        manifestOut.resolve("AndroidManifest.xml")
    )

    // Generic UI
    save(
        emptyErrorUi(packageName),
        moduleData.srcDir.resolve("ui/shared/ErrorUi.kt")
    )
    save(
        emptyLoadingUi(packageName),
        moduleData.srcDir.resolve("ui/shared/LoadingUi.kt")
    )
    save(
        emptyRemoteImage(packageName),
        moduleData.srcDir.resolve("ui/shared/RemoteImage.kt")
    )

    // DI
    save(
        emptyAppModule(packageName, itemName),
        moduleData.srcDir.resolve("di/AppModule.kt")
    )

    // Data
    save(
        emptyRepository(packageName, itemName, useRoom),
        moduleData.srcDir.resolve("data/${itemName.capitalize()}Repository.kt")
    )
    save(
        emptyDataModel(packageName, itemName, useRoom),
        moduleData.srcDir.resolve("data/${itemName.capitalize()}Model.kt")
    )

    // Net
    save(
        emptyService(
            packageName,
            itemName,
            restApiUrl,
        ),
        moduleData.srcDir.resolve("data/net/${itemName.capitalize()}Service.kt")
    )
    save(
        emptyNetworkDataSource(packageName, itemName),
        moduleData.srcDir.resolve("data/net/Network${itemName.capitalize()}DataSource.kt")
    )
    save(
        emptyListResponse(packageName, itemName),
        moduleData.srcDir.resolve("data/net/${itemName.capitalize()}ListResponse.kt")
    )

    // Database
    if (useRoom) {
        save(
            emptyDbDataSource(packageName, itemName),
            moduleData.srcDir.resolve("data/db/Db${itemName.capitalize()}DataSource.kt")
        )
        save(
            emptyDbDao(packageName, itemName),
            moduleData.srcDir.resolve("data/db/Db${itemName.capitalize()}Dao.kt")
        )
        save(
            emptyAppDatabase(packageName, itemName),
            moduleData.srcDir.resolve("data/db/AppDatabase.kt")
        )
    }

    // Drawables
    save(
        errorDrawable(),
        moduleData.resDir.resolve("drawable/error_drawable.xml")
    )
    save(
        placeholderDrawable(),
        moduleData.resDir.resolve("drawable/placeholder_drawable.xml")
    )
}
