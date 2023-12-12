package dev.sasikanth.myprojecttemplates.emptyactivity

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyNetworkDataSource(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.data.net

import ${escapeKotlinIdentifier(packageName)}.data.DataState
import retrofit2.Response
import javax.inject.Inject

class Network${itemName.capitalize()}DataSource @Inject constructor(
    private val service: ${itemName.capitalize()}Service,
) {
    suspend fun list${itemName.capitalize()}s(page: Int): DataState<${itemName.capitalize()}ListResponse> {
        return fetch(
            "No more ${itemName.capitalize()} found.",
            "Error retrieving ${itemName.lowercase()}s.",
        ) {
            service.list${itemName.capitalize()}s(page)
        }
    }

    private suspend fun <T> fetch(
        noDataMessage: String,
        unsuccessfulMessage: String,
        loadData: suspend () -> Response<T>,
    ): DataState<T> {
        return try {
            val response = loadData()
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    DataState.Content(data)
                } ?: DataState.Error(
                    Throwable(noDataMessage)
                )
            } else {
                DataState.Error(
                    Throwable(unsuccessfulMessage)
                )
            }
        } catch (thr: Throwable) {
            DataState.Error(
                Throwable("Bad network connectivity.", thr)
            )
        }
    }
}

"""