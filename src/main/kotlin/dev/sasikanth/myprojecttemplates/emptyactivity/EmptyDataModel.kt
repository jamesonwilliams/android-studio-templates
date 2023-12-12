package dev.sasikanth.myprojecttemplates

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyDataModel(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

// Yes, we're sharing a model across a few modules.
// Ends up significantly cutting down on maintenance in this small proj.
@JsonClass(generateAdapter = true)
@Entity
data class ${itemName.capitalize()}Model(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)
"""
