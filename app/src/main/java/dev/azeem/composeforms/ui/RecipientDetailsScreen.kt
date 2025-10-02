package dev.azeem.composeforms.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.azeem.composeforms.models.FieldState
import dev.azeem.composeforms.ui.components.ValidatedTextField

/**
 * Main screen for entering recipient details with smart validation
 * Validation errors appear only after user interaction or form submission attempt
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipientDetailsScreen(
    modifier: Modifier,
    onBackClick: () -> Unit = {},
    onContinueClick: (Map<String, String>) -> Unit = {}
) {
    // Form state with interaction tracking
    var firstName by remember { mutableStateOf(FieldState()) }
    var lastName by remember { mutableStateOf(FieldState()) }
    var houseNo by remember { mutableStateOf(FieldState()) }
    var postcode by remember { mutableStateOf(FieldState()) }
    var message by remember { mutableStateOf("") }

    // Track if user has attempted to submit the form
    var submitAttempted by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    // Validation functions
    fun validateFirstName(value: String): String? {
        return when {
            value.isBlank() -> "Please enter the recipient's first name"
            value.length < 2 -> "Name must be at least 2 characters"
            else -> null
        }
    }

    fun validateLastName(value: String): String? {
        return when {
            value.isBlank() -> "Please enter the recipient's last name"
            value.length < 2 -> "Name must be at least 2 characters"
            else -> null
        }
    }

    fun validateHouseNo(value: String): String? {
        return if (value.isBlank()) {
            "Please enter the recipient's house number/name"
        } else null
    }

    fun validatePostcode(value: String): String? {
        return when {
            value.isBlank() -> "Please enter the recipient's postcode"
            // UK postcode regex pattern (simplified)
            !value.matches(Regex("^[A-Z]{1,2}\\d{1,2}[A-Z]?\\s?\\d[A-Z]{2}$", RegexOption.IGNORE_CASE)) ->
                "Please enter a valid UK postcode"
            else -> null
        }
    }

    // Check if form is valid
    val isFormValid = firstName.error == null && firstName.value.isNotBlank() &&
            lastName.error == null && lastName.value.isNotBlank() &&
            houseNo.error == null && houseNo.value.isNotBlank() &&
            postcode.error == null && postcode.value.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Enter Recipient Details",
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // First Name
            ValidatedTextField(
                value = firstName.value,
                onValueChange = { newValue ->
                    val hadValue = firstName.value.isNotEmpty()
                    firstName = FieldState(
                        value = newValue,
                        error = validateFirstName(newValue),
                        isTouched = true,
                        // Mark as modified if user had entered text and now it's empty or changed
                        hasBeenModified = firstName.hasBeenModified || (hadValue && newValue.isEmpty())
                    )
                },
                label = "Recipient's First Name",
                errorMessage = firstName.error,
                showError = firstName.shouldShowError(submitAttempted),
                onFocusChanged = { isFocused ->
                    if (isFocused) {
                        firstName = firstName.copy(isTouched = true)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            // Last Name
            ValidatedTextField(
                value = lastName.value,
                onValueChange = { newValue ->
                    val hadValue = lastName.value.isNotEmpty()
                    lastName = FieldState(
                        value = newValue,
                        error = validateLastName(newValue),
                        isTouched = true,
                        hasBeenModified = lastName.hasBeenModified || (hadValue && newValue.isEmpty())
                    )
                },
                label = "Recipient's Last Name",
                errorMessage = lastName.error,
                showError = lastName.shouldShowError(submitAttempted),
                onFocusChanged = { isFocused ->
                    if (isFocused) {
                        lastName = lastName.copy(isTouched = true)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            // House Number/Name
            ValidatedTextField(
                value = houseNo.value,
                onValueChange = { newValue ->
                    val hadValue = houseNo.value.isNotEmpty()
                    houseNo = FieldState(
                        value = newValue,
                        error = validateHouseNo(newValue),
                        isTouched = true,
                        hasBeenModified = houseNo.hasBeenModified || (hadValue && newValue.isEmpty())
                    )
                },
                label = "House No. or Name",
                errorMessage = houseNo.error,
                showError = houseNo.shouldShowError(submitAttempted),
                onFocusChanged = { isFocused ->
                    if (isFocused) {
                        houseNo = houseNo.copy(isTouched = true)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            // Postcode with Find button
            ValidatedTextField(
                value = postcode.value,
                onValueChange = { newValue ->
                    val hadValue = postcode.value.isNotEmpty()
                    postcode = FieldState(
                        value = newValue.uppercase(),
                        error = validatePostcode(newValue),
                        isTouched = true,
                        hasBeenModified = postcode.hasBeenModified || (hadValue && newValue.isEmpty())
                    )
                },
                label = "Postcode",
                errorMessage = postcode.error,
                showError = postcode.shouldShowError(submitAttempted),
                onFocusChanged = { isFocused ->
                    if (isFocused) {
                        postcode = postcode.copy(isTouched = true)
                    }
                },
                trailingContent = {
                    TextButton(
                        onClick = {
                            // Handle postcode lookup
                            // Mark as modified to show validation if invalid
                            postcode = postcode.copy(
                                hasBeenModified = true,
                                error = validatePostcode(postcode.value)
                            )
                        }
                    ) {
                        Text("Find", fontWeight = FontWeight.Bold)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            // Manual address entry link
            TextButton(
                onClick = { /* Handle manual entry */ },
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text(
                    "Enter address manually",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
            }

            // Message field (no validation required)
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = {
                    Text(
                        "Your message will be printed exactly as you type it",
                        color = Color.Gray
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Gray
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 5
            )

            Spacer(modifier = Modifier.weight(1f))

            // Continue button
            Button(
                onClick = {
                    // Mark that submit was attempted
                    submitAttempted = true

                    // Validate all fields and mark them as modified
                    firstName = firstName.copy(
                        error = validateFirstName(firstName.value),
                        hasBeenModified = true
                    )
                    lastName = lastName.copy(
                        error = validateLastName(lastName.value),
                        hasBeenModified = true
                    )
                    houseNo = houseNo.copy(
                        error = validateHouseNo(houseNo.value),
                        hasBeenModified = true
                    )
                    postcode = postcode.copy(
                        error = validatePostcode(postcode.value),
                        hasBeenModified = true
                    )

                    // Only proceed if form is valid
                    if (isFormValid) {
                        onContinueClick(
                            mapOf(
                                "firstName" to firstName.value,
                                "lastName" to lastName.value,
                                "houseNo" to houseNo.value,
                                "postcode" to postcode.value,
                                "message" to message
                            )
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBDBDBD),
                    disabledContainerColor = Color(0xFFE0E0E0)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewRecipientDetailsScreen() {
    MaterialTheme {
        RecipientDetailsScreen(
            modifier = Modifier
        )
    }
}