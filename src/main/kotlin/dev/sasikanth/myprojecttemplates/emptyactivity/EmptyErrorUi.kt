package dev.sasikanth.myprojecttemplates.emptyactivity


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun emptyErrorUi(
    packageName: String,
    itemName: String,
) = """
package ${escapeKotlinIdentifier(packageName)}.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorUi(
    message: String?,
    onRetryClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val errorMessage = if (message != null) "Error: ${'$'}message" else "Error"
        Text(errorMessage, fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = onRetryClicked,
        ) {
            Text("Retry?")
        }
    }
}
"""
