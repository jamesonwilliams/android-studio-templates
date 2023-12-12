package dev.sasikanth.myprojecttemplates.stubs.data


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyListResponse(
    packageName: String,
    itemName: String,
) = """
package ${escapeKotlinIdentifier(packageName)}.data.net

import com.squareup.moshi.JsonClass
import ${escapeKotlinIdentifier(packageName)}.data.${itemName.capitalize()}Model

@JsonClass(generateAdapter = true)
data class ${itemName.capitalize()}ListResponse(
    val info: Info,
    val results: List<${itemName.capitalize()}Model>
) {
    @JsonClass(generateAdapter = true)
    data class Info(
        val count: Int,
        val next: String?,
        val pages: Int,
        val prev: String?
    )
}
"""
