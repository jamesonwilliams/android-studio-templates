package dev.sasikanth.myprojecttemplates.emptyactivity


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun emptyDataState(packageName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data

sealed class DataState<T> {
    data class Content<T>(val data: T): DataState<T>()

    data class Error<T>(val error: Throwable): DataState<T>()
}
"""
