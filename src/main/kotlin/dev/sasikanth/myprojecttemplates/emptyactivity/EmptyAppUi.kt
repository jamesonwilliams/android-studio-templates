package dev.sasikanth.myprojecttemplates.emptyactivity


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyUi(
    packageName: String,
    itemName: String,
) = """
package ${escapeKotlinIdentifier(packageName)}.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ${escapeKotlinIdentifier(packageName)}.ui.details.${itemName.capitalize()}DetailScreen
import ${escapeKotlinIdentifier(packageName)}.ui.list.${itemName.capitalize()}ListScreen

@Composable
fun ${itemName.capitalize()}AppUi() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            ${itemName.capitalize()}ListScreen { ${itemName.lowercase()}Id ->
                navController.navigate("detail/${'$'}${itemName.lowercase()}Id")
            }
        }
        composable(
            route = "detail/{${itemName.lowercase()}Id}",
            arguments = listOf(navArgument("${itemName.lowercase()}Id") { type = NavType.IntType }),
        ) {
            ${itemName.capitalize()}DetailScreen {
                navController.navigateUp()
            }
        }
    }
}
"""
