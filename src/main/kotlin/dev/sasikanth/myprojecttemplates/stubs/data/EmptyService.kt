package dev.sasikanth.myprojecttemplates.stubs.data


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyService(
    packageName: String,
    itemName: String,
    defaultUrl: String,
) = """
package ${escapeKotlinIdentifier(packageName)}.data.net

import ${escapeKotlinIdentifier(packageName)}.data.${itemName.capitalize()}Model
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ${itemName.capitalize()}Service {
    @GET("${itemName.lowercase()}")
    suspend fun list${itemName.capitalize()}s(@Query("page") page: Int): Response<${itemName.capitalize()}ListResponse>

    @GET("${itemName.lowercase()}/{${itemName.lowercase()}Id")
    suspend fun get${itemName.capitalize()}(${itemName.lowercase()}Id: Int): Response<${itemName.capitalize()}Model>

    companion object {
        fun create(baseUrl: String = "$defaultUrl"): ${itemName.capitalize()}Service {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(${itemName.capitalize()}Service::class.java)
        }
    }
}
"""
