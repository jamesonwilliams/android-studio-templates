package dev.sasikanth.myprojecttemplates.emptyactivity


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyRepository(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data

import javax.inject.Inject
import ${escapeKotlinIdentifier(packageName)}.data.db.Db${itemName.capitalize()}DataSource
import ${escapeKotlinIdentifier(packageName)}.data.net.Network${itemName.capitalize()}DataSource

class ${itemName.capitalize()}Repository @Inject constructor(
    private val db${itemName.capitalize()}DataSource: Db${itemName.capitalize()}DataSource,
    private val network${itemName.capitalize()}DataSource: Network${itemName.capitalize()}DataSource
) {
    suspend fun load${itemName.capitalize()}s(page: Int): Result<List<${itemName.capitalize()}Model>> {
        return db${itemName.capitalize()}DataSource.loadPageOf${itemName.capitalize()}s(page = page)
            .onFailure {
                return network${itemName.capitalize()}DataSource.list${itemName.capitalize()}s(page = page)
                    .map { it.results }
                    .onSuccess { ${itemName.lowercase()}s ->
                        db${itemName.capitalize()}DataSource.store${itemName.capitalize()}s(*${itemName.lowercase()}s.toTypedArray())
                    }
            }
    }

    suspend fun get${itemName.capitalize()}(${itemName.lowercase()}Id: Int): Result<${itemName.capitalize()}Model> {
        return db${itemName.capitalize()}DataSource.load${itemName.capitalize()}ById(${itemName.lowercase()}Id = ${itemName.lowercase()}Id)
            .onFailure {
                return network${itemName.capitalize()}DataSource.get${itemName.capitalize()}(${itemName.lowercase()}Id)
                    .onSuccess {
                        db${itemName.capitalize()}DataSource.store${itemName.capitalize()}s(it)
                    }
            }
    }
}
"""
