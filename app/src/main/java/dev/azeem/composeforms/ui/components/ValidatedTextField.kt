package dev.azeem.composeforms.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Reusable composable for a text field with built-in validation support.
 * Validation errors are only shown after user interaction (touched and modified the field).
 *
 * @param value Current text value
 * @param onValueChange Callback when text changes
 * @param label Field label text
 * @param placeholder Placeholder text
 * @param errorMessage Error message to display (null if no error)
 * @param showError Whether to show the error message (controlled by parent)
 * @param onFocusChanged Callback when focus state changes
 * @param modifier Modifier for the component
 * @param showHelpIcon Whether to show the help icon
 * @param onHelpClick Callback when help icon is clicked
 * @param trailingContent Optional trailing content (e.g., button)
 * @param keyboardOptions Keyboard configuration
 * @param keyboardActions Keyboard actions
 * @param singleLine Whether the field is single line
 */
@Composable
fun ValidatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    errorMessage: String? = null,
    showError: Boolean = false,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    showHelpIcon: Boolean = true,
    onHelpClick: (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true
) {
    // Determine if error should be displayed
    val shouldShowError = showError && errorMessage != null

    Column(modifier = modifier) {
        // TextField with optional help icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged { focusState ->
                        onFocusChanged?.invoke(focusState.isFocused)
                    },
                placeholder = { Text(label, color = Color.Gray) },
                isError = shouldShowError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (shouldShowError) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = if (shouldShowError) MaterialTheme.colorScheme.error
                    else Color.Gray
                ),
                trailingIcon = trailingContent,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                shape = RoundedCornerShape(8.dp)
            )

            // Help icon
            if (showHelpIcon) {
                IconButton(
                    onClick = { onHelpClick?.invoke() },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Help",
                        tint = Color.Gray
                    )
                }
            }
        }

        // Error message - only shown when shouldShowError is true
        if (shouldShowError) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
