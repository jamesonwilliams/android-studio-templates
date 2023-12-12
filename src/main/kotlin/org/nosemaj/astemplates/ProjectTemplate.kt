package org.nosemaj.astemplates

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import java.io.File

val projectTemplate
    get() = template {
        name = "List/Detail App"
        description =
            "Creates a simple list/detail with MVI architecture. Uses Kotlin, Jetpack Compose, Retrofit, Room, Hilt, Coil"
        minApi = 24
        constraints = listOf(
            TemplateConstraint.AndroidX,
            TemplateConstraint.Kotlin
        )
        category = Category.Application
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.NewProject, WizardUiContext.NewProjectExtraDetail)

        val restApiUrl = stringParameter {
            name = "URL to rest API"
            default = "https://localhost/api/v1/items"
        }

        val packageName = defaultPackageNameParameter

        val itemName = stringParameter {
            name = "Name of the item being listed?"
            default = "Item"
        }
        val useRoom = booleanParameter {
            name = "Add Room persistence?"
            default = true
        }

        widgets(
            TextFieldWidget(restApiUrl),
            TextFieldWidget(itemName),
            CheckBoxWidget(useRoom),
            PackageNameWidget(packageName),
        )

        // I am reusing the thumbnail provided by Android Studio, but
        // replace it with your own
        thumb { File("compose-activity-material3").resolve("template_compose_empty_activity_material3.png") }

        recipe = { data: TemplateData ->
            projectRecipe(
                moduleData = data as ModuleTemplateData,
                packageName = packageName.value,
                itemName = itemName.value,
                restApiUrl = restApiUrl.value,
                useRoom = useRoom.value,
            )
        }
    }
