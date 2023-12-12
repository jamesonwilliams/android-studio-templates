package dev.sasikanth.myprojecttemplates.stubs.ui

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyListViewModel(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ${escapeKotlinIdentifier(packageName)}.data.${itemName.capitalize()}Repository
import ${escapeKotlinIdentifier(packageName)}.ui.list.UiEvent.BottomReached
import ${escapeKotlinIdentifier(packageName)}.ui.list.UiEvent.InitialLoad
import ${escapeKotlinIdentifier(packageName)}.ui.list.UiEvent.RetryClicked

@HiltViewModel
class ${itemName.capitalize()}ListViewModel @Inject constructor(
    private val ${itemName.lowercase()}Repository: ${itemName.capitalize()}Repository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.INITIAL)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onEvent(event: UiEvent) {
        when (event) {
            is RetryClicked -> refreshUi()
            is InitialLoad -> if (uiState.value.displayState == DisplayState.LOADING) {
                refreshUi()
            }
            is BottomReached -> refreshUi(showLoading = false)
        }
    }

    private fun refreshUi(showLoading: Boolean = true) {
        if (showLoading) {
            _uiState.update { it.copy(displayState = DisplayState.LOADING) }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                ${itemName.lowercase()}Repository.load${itemName.capitalize()}s(uiState.value.currentPage)
            }
                .onSuccess { ${itemName.lowercase()}s ->
                    val ${itemName.lowercase()}Summaries = ${itemName.lowercase()}s
                        .map { ${itemName.capitalize()}Summary(id = it.id, name = it.name, imageUrl = it.image) }
                    _uiState.update {
                        it.copy(
                            ${itemName.lowercase()}Summaries = it.${itemName.lowercase()}Summaries.plus(${itemName.lowercase()}Summaries),
                            displayState = DisplayState.CONTENT,
                            currentPage = it.currentPage + 1
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            displayState = DisplayState.ERROR,
                            errorMessage = error.message
                        )
                    }
                }
        }
    }
}

sealed class UiEvent {
    data object InitialLoad : UiEvent()
    data object RetryClicked : UiEvent()
    data object BottomReached : UiEvent()
}

data class UiState(
    val currentPage: Int,
    val ${itemName.lowercase()}Summaries: List<${itemName.capitalize()}Summary>,
    val displayState: DisplayState,
    val errorMessage: String?
) {
    companion object {
        val INITIAL = UiState(
            currentPage = 1,
            ${itemName.lowercase()}Summaries = emptyList(),
            displayState = DisplayState.LOADING,
            errorMessage = null
        )
    }
}

enum class DisplayState {
    LOADING,
    CONTENT,
    ERROR
}

data class ${itemName.capitalize()}Summary(
    val id: Int,
    val name: String,
    val imageUrl: String
)
"""
