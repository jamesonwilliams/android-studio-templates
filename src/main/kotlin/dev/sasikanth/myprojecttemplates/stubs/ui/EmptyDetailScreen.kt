package dev.sasikanth.myprojecttemplates.stubs.ui

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyDetailScreen(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ${escapeKotlinIdentifier(packageName)}.ui.details.UiState.Content
import ${escapeKotlinIdentifier(packageName)}.ui.details.UiState.Error
import ${escapeKotlinIdentifier(packageName)}.ui.details.UiState.Loading
import ${escapeKotlinIdentifier(packageName)}.ui.shared.ErrorUi
import ${escapeKotlinIdentifier(packageName)}.ui.shared.LoadingUI
import ${escapeKotlinIdentifier(packageName)}.ui.shared.RemoteImage

@Composable
fun ${itemName.capitalize()}DetailScreen(onBackPressed: () -> Unit) {
    val viewModel: ${itemName.capitalize()}DetailViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(UiEvent.InitialLoad)
    }
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    when (val currentState = viewState) {
        is Loading -> LoadingUI()
        is Content -> ${itemName.capitalize()}DetailUi(currentState.${itemName.lowercase()}Detail) {
            onBackPressed()
        }
        is Error -> {
            ErrorUi(currentState.errorMessage) {
                viewModel.onEvent(UiEvent.RetryClicked)
            }
        }
    }
}

@Composable
fun ${itemName.capitalize()}DetailUi(
    ${itemName.lowercase()}Detail: ${itemName.capitalize()}Detail,
    onBackClicked: () -> Unit,
) {
    Column(
      modifier = Modifier.fillMaxHeight()
    ) {
        Box {
            RemoteImage(
                imageUrl = ${itemName.lowercase()}Detail.imageUrl,
                contentDescription = ${itemName.lowercase()}Detail.name,
            )
            BackBar {
                onBackClicked()
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            TitleLine(${itemName.lowercase()}Detail.name)
            DetailLine(${itemName.lowercase()}Detail.gender)
            DetailLine(${itemName.lowercase()}Detail.species)
            DetailLine(${itemName.lowercase()}Detail.status)
        }
    }
}

@Composable
fun TitleLine(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun DetailLine(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
fun BackBar(onBackClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.66f))
    ) {
        IconButton(onClick = {
            onBackClicked()
        }) {
            Icon(Icons.Filled.ArrowBack, "Back buton")
        }
    }
}
"""
