package dev.sasikanth.myprojecttemplates.stubs.data

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyNetworkDataSource(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data.net

import javax.inject.Inject
import ${escapeKotlinIdentifier(packageName)}.data.${itemName.capitalize()}Model
import retrofit2.Response

class Network${itemName.capitalize()}DataSource @Inject constructor(
    private val service: ${itemName.capitalize()}Service
) {
    suspend fun list${itemName.capitalize()}s(page: Int): Result<${itemName.capitalize()}ListResponse> {
        return fetch(
            "No more ${itemName.lowercase()}s found.",
            "Error retrieving ${itemName.lowercase()}s."
        ) {
            service.list${itemName.capitalize()}s(page)
        }
    }

    suspend fun get${itemName.capitalize()}(${itemName.lowercase()}Id: Int): Result<${itemName.capitalize()}Model> {
        return fetch(
            "No ${itemName.lowercase()} $${itemName.lowercase()}Id found",
            "Error retrieving ${itemName.lowercase()} $${itemName.lowercase()}Id"
        ) {
            service.get${itemName.capitalize()}(${itemName.lowercase()}Id)
        }
    }

    private suspend fun <T> fetch(
        noDataMessage: String,
        unsuccessfulMessage: String,
        loadData: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val response = loadData()
            if (response.isSuccessful) {
                response.body()?.let { data -> Result.success(data) }
                    ?: Result.failure(Throwable(noDataMessage))
            } else {
                Result.failure(Throwable(unsuccessfulMessage))
            }
        } catch (thr: Throwable) {
            Result.failure(Throwable("Bad network connectivity.", thr))
        }
    }
}
"""