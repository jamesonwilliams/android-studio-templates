package dev.sasikanth.myprojecttemplates

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyDbDao(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Db${itemName.capitalize()}Dao {
    @Query("SELECT * FROM db${itemName.lowercase()} WHERE id IN (:${itemName.lowercase()}Ids) ORDER BY id ASC")
    suspend fun loadAllByIds(${itemName.lowercase()}Ids: IntArray): List<Db${itemName.capitalize()}>

    @Query("SELECT * FROM db${itemName.lowercase()} WHERE id = :${itemName.lowercase()}Id")
    suspend fun loadById(${itemName.lowercase()}Id: Int): Db${itemName.capitalize()}

    @Insert
    suspend fun insertAll(vararg db${itemName.capitalize()}: Db${itemName.capitalize()})

    @Delete
    suspend fun delete(db${itemName.capitalize()}: Db${itemName.capitalize()})
}
"""
