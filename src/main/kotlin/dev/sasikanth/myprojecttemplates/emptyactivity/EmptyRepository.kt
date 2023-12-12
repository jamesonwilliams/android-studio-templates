package dev.sasikanth.myprojecttemplates.emptyactivity


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyRepository(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data

import ${escapeKotlinIdentifier(packageName)}.data.DataState.Content
import ${escapeKotlinIdentifier(packageName)}.data.DataState.Error
import ${escapeKotlinIdentifier(packageName)}.data.db.Db${itemName.capitalize()}
import ${escapeKotlinIdentifier(packageName)}.data.db.Db${itemName.capitalize()}DataSource
import ${escapeKotlinIdentifier(packageName)}.data.net.${itemName.capitalize()}ListResponse
import ${escapeKotlinIdentifier(packageName)}.data.net.Network${itemName.capitalize()}DataSource
import javax.inject.Inject

class ${itemName.capitalize()}Repository @Inject constructor(
    private val db${itemName.capitalize()}DataSource: Db${itemName.capitalize()}DataSource,
    private val network${itemName.capitalize()}DataSource: Network${itemName.capitalize()}DataSource,
) {
    suspend fun load${itemName.capitalize()}s(page: Int): DataState<List<${itemName.capitalize()}>> {
        when (val dbResult = db${itemName.capitalize()}DataSource.loadPageOf${itemName.capitalize()}s(page = page)) {
            is Error<List<Db${itemName.capitalize()}>> -> {}
            is Content<List<Db${itemName.capitalize()}>> -> return Content(dbResult.data.map { it.as${itemName.capitalize()}() })
        }
        return when (val networkResult = network${itemName.capitalize()}DataSource.list${itemName.capitalize()}s(page = page)) {
            is Content<${itemName.capitalize()}ListResponse> -> return save(networkResult.data.results)
            is Error<${itemName.capitalize()}ListResponse> -> Error(networkResult.error)
        }
    }

    suspend fun get${itemName.capitalize()}(${itemName.lowercase()}Id: Int): DataState<${itemName.capitalize()}> {
        return when (val dbResult = db${itemName.capitalize()}DataSource.load${itemName.capitalize()}ById(${itemName.lowercase()}Id = ${itemName.lowercase()}Id)) {
            is Error<Db${itemName.capitalize()}> -> Error(dbResult.error)
            is Content<Db${itemName.capitalize()}> -> return Content(dbResult.data.as${itemName.capitalize()}())
        }
    }

    private suspend fun save(network${itemName.capitalize()}s: List<${itemName.capitalize()}ListResponse.Result>): DataState<List<${itemName.capitalize()}>> {
        val ${itemName.lowercase()}s = network${itemName.capitalize()}s.map { it.as${itemName.capitalize()}() }
        val db${itemName.capitalize()}s = ${itemName.lowercase()}s.map { it.asDb${itemName.capitalize()}() }.toTypedArray()
        return when (val result = db${itemName.capitalize()}DataSource.store${itemName.capitalize()}s(*db${itemName.capitalize()}s)) {
            is Content<List<Db${itemName.capitalize()}>> -> Content(${itemName.lowercase()}s)
            is Error<List<Db${itemName.capitalize()}>> -> Error(result.error)
        }
    }

    private fun ${itemName.capitalize()}ListResponse.Result.as${itemName.capitalize()}(): ${itemName.capitalize()} {
        return ${itemName.capitalize()}(
            id = id,
            name = name,
            status = status,
            species = species,
            gender = gender,
            image = image,
        )
    }

    private fun Db${itemName.capitalize()}.as${itemName.capitalize()}(): ${itemName.capitalize()} {
        return ${itemName.capitalize()}(
            id = id,
            name = name,
            status = status,
            species = species,
            gender = gender,
            image = image,
        )
    }

    private fun ${itemName.capitalize()}.asDb${itemName.capitalize()}(): Db${itemName.capitalize()} {
        return Db${itemName.capitalize()}(
            id = id,
            name = name,
            status = status,
            species = species,
            gender = gender,
            image = image,
        )
    }

    data class ${itemName.capitalize()}(
        val id: Int,
        val name: String,
        val status: String,
        val species: String,
        val gender: String,
        val image: String,
    )
}
"""
