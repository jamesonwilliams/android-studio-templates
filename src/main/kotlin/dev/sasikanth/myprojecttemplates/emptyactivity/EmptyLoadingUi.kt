package dev.sasikanth.myprojecttemplates.emptyactivity


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun emptyLoadingUi(
    packageName: String,
    itemName: String,
) = """
package ${escapeKotlinIdentifier(packageName)}.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun LoadingUI() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Loading...", fontSize = 30.sp)
    }
}
"""
