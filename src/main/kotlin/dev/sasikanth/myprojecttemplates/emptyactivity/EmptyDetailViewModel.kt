package dev.sasikanth.myprojecttemplates.emptyactivity

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyDetailViewModel(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ${escapeKotlinIdentifier(packageName)}.data.${itemName.capitalize()}Repository
import ${escapeKotlinIdentifier(packageName)}.data.DataState
import ${escapeKotlinIdentifier(packageName)}.ui.details.UiEvent.InitialLoad
import ${escapeKotlinIdentifier(packageName)}.ui.details.UiEvent.RetryClicked
import ${escapeKotlinIdentifier(packageName)}.ui.details.UiState.Loading
import javax.inject.Inject

@HiltViewModel
class ${itemName.capitalize()}DetailViewModel @Inject constructor(
    private val ${itemName.lowercase()}Repository: ${itemName.capitalize()}Repository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val ${itemName.lowercase()}Id: Int = checkNotNull(savedStateHandle["${itemName.lowercase()}Id"])
    private val _uiState = MutableStateFlow<UiState>(Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is InitialLoad -> load${itemName.capitalize()}()
            is RetryClicked -> load${itemName.capitalize()}()
        }
    }

    private fun load${itemName.capitalize()}() {
        viewModelScope.launch {
            val dataState = withContext(Dispatchers.IO) {
                ${itemName.lowercase()}Repository.get${itemName.capitalize()}(${itemName.lowercase()}Id = ${itemName.lowercase()}Id)
            }
            when (dataState) {
                is DataState.Content<${itemName.capitalize()}Repository.${itemName.capitalize()}> -> {
                    val ${itemName.lowercase()} = dataState.data
                    _uiState.update {
                        UiState.Content(
                            ${itemName.capitalize()}Detail(
                                id = ${itemName.lowercase()}.id,
                                name = ${itemName.lowercase()}.name,
                                imageUrl = ${itemName.lowercase()}.image,
                                status = ${itemName.lowercase()}.status,
                                species = ${itemName.lowercase()}.species,
                                gender = ${itemName.lowercase()}.gender,
                            )
                        )
                    }
                }
                is DataState.Error<${itemName.capitalize()}Repository.${itemName.capitalize()}> -> {
                    _uiState.update { UiState.Error(dataState.error.message) }
                }
            }
        }
    }
}

sealed class UiEvent {
    data object InitialLoad : UiEvent()

    data object RetryClicked : UiEvent()
}

sealed class UiState {
    data object Loading: UiState()

    data class Content(val ${itemName.lowercase()}Detail: ${itemName.capitalize()}Detail): UiState()

    data class Error(val errorMessage: String?): UiState()
}

data class ${itemName.capitalize()}Detail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val status: String,
    val species: String,
    val gender: String,
)
"""
