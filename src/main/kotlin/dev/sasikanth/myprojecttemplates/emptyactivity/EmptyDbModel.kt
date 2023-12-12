package dev.sasikanth.myprojecttemplates

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyDbModel(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Db${itemName.capitalize()}(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
)
"""
