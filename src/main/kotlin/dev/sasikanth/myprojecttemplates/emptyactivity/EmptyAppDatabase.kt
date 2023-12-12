package dev.sasikanth.myprojecttemplates

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyAppDatabase(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Db${itemName.capitalize()}::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ${itemName.lowercase()}Dao(): Db${itemName.capitalize()}Dao
}
"""
