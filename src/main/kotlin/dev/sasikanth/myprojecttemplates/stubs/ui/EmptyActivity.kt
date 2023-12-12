package dev.sasikanth.myprojecttemplates.stubs.ui

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun emptyActivity(packageName: String, themeName: String) = """
package ${escapeKotlinIdentifier(packageName)}.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()      
        }
    }
}
"""
