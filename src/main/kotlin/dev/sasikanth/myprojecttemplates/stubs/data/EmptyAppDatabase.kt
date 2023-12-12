package dev.sasikanth.myprojecttemplates.stubs.data

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyAppDatabase(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ${escapeKotlinIdentifier(packageName)}.data.${itemName.capitalize()}Model

@Database(
    entities = [${itemName.capitalize()}Model::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ${itemName.lowercase()}Dao(): ${itemName.capitalize()}Dao
}
"""
