package dev.sasikanth.myprojecttemplates.stubs.app

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyApplication(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ${itemName.capitalize()}Application: Application()
"""
