package dev.sasikanth.myprojecttemplates

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyDbDataSource(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data.db

import android.content.Context
import androidx.room.Room
import ${escapeKotlinIdentifier(packageName)}.data.DataState
import javax.inject.Inject

class Db${itemName.capitalize()}DataSource @Inject constructor(applicationContext: Context) {
    private val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "${itemName.capitalize()}s",
    ).build()

    suspend fun load${itemName.capitalize()}ById(${itemName.lowercase()}Id: Int): DataState<Db${itemName.capitalize()}> {
        return try {
            DataState.Content(db.${itemName.lowercase()}Dao().loadById(${itemName.lowercase()}Id))
        } catch (thr: Throwable) {
            DataState.Error(
                Throwable("Unable to get ${itemName.lowercase()} ${'$'}${itemName.lowercase()}Id from DB.", thr)
            )
        }
    }

    suspend fun loadPageOf${itemName.capitalize()}s(page: Int): DataState<List<Db${itemName.capitalize()}>>  {
        return try {
            val ids = ((page - 1) * 20 + 1 .. page * 20).toSet().toIntArray()
            val ${itemName.lowercase()}s = db.${itemName.lowercase()}Dao().loadAllByIds(ids).sortedBy { it.id }
            if (${itemName.lowercase()}s.isEmpty()) {
                return DataState.Error(
                    Throwable("No ${itemName.lowercase()}s found for page ${'$'}page.")
                )
            } else {
                return DataState.Content(${itemName.lowercase()}s)
            }

        } catch (thr: Throwable) {
            DataState.Error(
                Throwable("Unable to fetch ${itemName.lowercase()}s for page ${'$'}page.", thr)
            )
        }
    }

    suspend fun store${itemName.capitalize()}s(vararg db${itemName.capitalize()}: Db${itemName.capitalize()}): DataState<List<Db${itemName.capitalize()}>> {
        return try {
            db.${itemName.lowercase()}Dao().insertAll(*db${itemName.capitalize()})
            DataState.Content(db${itemName.capitalize()}.toList())
        } catch (thr: Throwable) {
            val ids = db${itemName.capitalize()}.map { it.id }
            DataState.Error(
                Throwable("Unable to insert ${itemName.lowercase()}s ${'$'}ids to database.", thr)
            )
        }
    }
}
"""
