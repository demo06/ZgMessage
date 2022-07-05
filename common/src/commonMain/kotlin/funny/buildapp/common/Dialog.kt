package funny.buildapp.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
expect fun AppDialog(content: String, modifier: Modifier, onDismiss: () -> Unit)