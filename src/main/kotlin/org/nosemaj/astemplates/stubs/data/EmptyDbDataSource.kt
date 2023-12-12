package org.nosemaj.astemplates.stubs.data

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyDbDataSource(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data.db

import android.content.Context
import androidx.room.Room
import javax.inject.Inject
import ${escapeKotlinIdentifier(packageName)}.data.${itemName.capitalize()}Model

class Db${itemName.capitalize()}DataSource @Inject constructor(applicationContext: Context) {
    private val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        AppDatabase::class.simpleName
    ).build()

    suspend fun load${itemName.capitalize()}ById(${itemName.lowercase()}Id: Int): Result<${itemName.capitalize()}Model> {
        return try {
            Result.success(db.${itemName.lowercase()}Dao().loadById(${itemName.lowercase()}Id))
        } catch (thr: Throwable) {
            Result.failure(Throwable("Unable to get ${itemName.lowercase()} ${'$'}${itemName.lowercase()}Id from DB.", thr))
        }
    }

    suspend fun loadPageOf${itemName.capitalize()}s(page: Int): Result<List<${itemName.capitalize()}Model>> {
        return try {
            val ids = ((page - 1) * 20 + 1..page * 20).toSet().toIntArray()
            val ${itemName.lowercase()}s = db.${itemName.lowercase()}Dao().loadAllByIds(ids).sortedBy { it.id }
            if (${itemName.lowercase()}s.isEmpty()) {
                return Result.failure(Throwable("No ${itemName.lowercase()}s found for page ${'$'}page."))
            } else {
                return Result.success(${itemName.lowercase()}s)
            }
        } catch (thr: Throwable) {
            Result.failure(Throwable("Unable to fetch ${itemName.lowercase()}s for page ${'$'}page.", thr))
        }
    }

    suspend fun store${itemName.capitalize()}s(vararg db${itemName.capitalize()}: ${itemName.capitalize()}Model): Result<List<${itemName.capitalize()}Model>> {
        return try {
            db.${itemName.lowercase()}Dao().insertAll(*db${itemName.capitalize()})
            Result.success(db${itemName.capitalize()}.toList())
        } catch (thr: Throwable) {
            val ids = db${itemName.capitalize()}.map { it.id }
            Result.failure(Throwable("Unable to insert ${itemName.lowercase()}s ${'$'}ids to database.", thr))
        }
    }
}
"""
