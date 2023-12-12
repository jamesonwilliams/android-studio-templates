package dev.sasikanth.myprojecttemplates.emptyactivity


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyListResponse(
    packageName: String,
    itemName: String,
) = """
package ${escapeKotlinIdentifier(packageName)}.data.net

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ${itemName.capitalize()}ListResponse(
    val info: Info,
    val results: List<Result>
) {
    @JsonClass(generateAdapter = true)
    data class Info(
        val count: Int,
        val next: String?,
        val pages: Int,
        val prev: String?
    )

    @JsonClass(generateAdapter = true)
    data class Result(
        val created: String,
        val episode: List<String>,
        val gender: String,
        val id: Int,
        val image: String,
        val location: Location,
        val name: String,
        val origin: Origin,
        val species: String,
        val status: String,
        val type: String,
        val url: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Location(
            val name: String,
            val url: String
        )

        @JsonClass(generateAdapter = true)
        data class Origin(
            val name: String,
            val url: String
        )
    }
}
"""
