package org.nosemaj.astemplates.stubs.ui

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyListScreen(packageName: String, itemName: String) = """
package ${escapeKotlinIdentifier(packageName)}.ui.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign.Companion.Right
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ${escapeKotlinIdentifier(packageName)}.ui.list.UiEvent.BottomReached
import ${escapeKotlinIdentifier(packageName)}.ui.list.UiEvent.InitialLoad
import ${escapeKotlinIdentifier(packageName)}.ui.list.UiEvent.RetryClicked
import ${escapeKotlinIdentifier(packageName)}.ui.shared.ErrorUi
import ${escapeKotlinIdentifier(packageName)}.ui.shared.LoadingUI
import ${escapeKotlinIdentifier(packageName)}.ui.shared.RemoteImage

@Composable
fun ${itemName.capitalize()}ListScreen(
    navigateTo${itemName.capitalize()}: (${itemName.lowercase()}Id: Int) -> Unit,
) {
    val viewModel: ${itemName.capitalize()}ListViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(InitialLoad)
    }
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    when (viewState.displayState) {
        DisplayState.LOADING -> {
            LoadingUI()
        }
        DisplayState.CONTENT -> {
            ${itemName.capitalize()}List(
                ${itemName.lowercase()}Summaries = viewState.${itemName.lowercase()}Summaries,
                onBottomReached = {
                    viewModel.onEvent(BottomReached)
                },
                on${itemName.capitalize()}Clicked = {
                    navigateTo${itemName.capitalize()}(it.id)
                }
            )
        }
        DisplayState.ERROR -> {
            ErrorUi(viewState.errorMessage) {
                viewModel.onEvent(RetryClicked)
            }
        }
    }
}

@Composable
fun ${itemName.capitalize()}List(
    ${itemName.lowercase()}Summaries: List<${itemName.capitalize()}Summary>,
    onBottomReached: () -> Unit,
    on${itemName.capitalize()}Clicked: (${itemName.capitalize()}Summary) -> Unit,
) {
    val columnCount = when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> 4
        else -> 2
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount)
    ) {
        items(${itemName.lowercase()}Summaries) { summary ->
            ${itemName.capitalize()}Item(${itemName.lowercase()}Summary = summary) {
                on${itemName.capitalize()}Clicked(summary)
            }
            if (summary == ${itemName.lowercase()}Summaries.last()) {
                onBottomReached()
            }
        }
    }
}

@Composable
fun ${itemName.capitalize()}Item(
    ${itemName.lowercase()}Summary: ${itemName.capitalize()}Summary,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
) {
    Box(
        modifier = modifier.clickable { onClicked() }
    ) {
        RemoteImage(
            imageUrl = ${itemName.lowercase()}Summary.imageUrl,
            contentDescription = ${itemName.lowercase()}Summary.name
        )
        Text(
            text = ${itemName.lowercase()}Summary.name,
            textAlign = Right,
            fontSize = 20.sp,
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.66f))
                .fillMaxWidth()
                .align(BottomEnd)
                .padding(8.dp)
        )
    }
}
"""
