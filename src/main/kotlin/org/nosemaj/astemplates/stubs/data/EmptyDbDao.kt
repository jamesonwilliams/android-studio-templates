package org.nosemaj.astemplates.stubs.data

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyDbDao(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ${escapeKotlinIdentifier(packageName)}.data.${itemName.capitalize()}Model

@Dao
interface ${itemName.capitalize()}Dao {
    @Query("SELECT * FROM ${itemName.lowercase()}model WHERE id IN (:${itemName.lowercase()}Ids) ORDER BY id ASC")
    suspend fun loadAllByIds(${itemName.lowercase()}Ids: IntArray): List<${itemName.capitalize()}Model>

    @Query("SELECT * FROM ${itemName.lowercase()}model WHERE id = :${itemName.lowercase()}Id")
    suspend fun loadById(${itemName.lowercase()}Id: Int): ${itemName.capitalize()}Model

    @Insert
    suspend fun insertAll(vararg db${itemName.capitalize()}: ${itemName.capitalize()}Model)

    @Delete
    suspend fun delete(db${itemName.capitalize()}: ${itemName.capitalize()}Model)
}
"""
