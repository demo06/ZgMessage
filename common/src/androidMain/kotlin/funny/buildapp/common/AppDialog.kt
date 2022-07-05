package funny.buildapp.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
actual fun AppDialog(content: String, modifier: Modifier, onDismiss: () -> Unit) {
    AlertDialog(modifier = modifier.width(250.dp), onDismissRequest = onDismiss, title = {
        Text(text = "你想叫个啥")
    }, text = {
        Text(content)
    }, buttons = {
        Box(modifier = Modifier.padding(all = 8.dp)) {
            Button(
                modifier = Modifier.fillMaxWidth(), onClick = onDismiss
            ) {
                Text("OK")
            }
        }

    })
}