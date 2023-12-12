package dev.sasikanth.myprojecttemplates

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyActivity(packageName: String, itemName: String) = """
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
          ${itemName.capitalize()}AppUi()
        }
    }
}
"""
