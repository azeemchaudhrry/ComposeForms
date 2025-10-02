package dev.azeem.composeforms.models

/**
 * Enhanced data class to hold form field state with validation and interaction tracking
 *
 * @param value The current field value
 * @param error The validation error message (null if valid)
 * @param isTouched Whether the user has interacted with this field
 * @param hasBeenModified Whether the user has entered and then cleared the field
 */
data class FieldState(
    val value: String = "",
    val error: String? = null,
    val isTouched: Boolean = false,
    val hasBeenModified: Boolean = false
) {
    /**
     * Determines if error should be displayed based on user interaction
     * Error is shown only if:
     * 1. Field has been touched (focused at least once) AND value was entered then cleared
     * 2. OR field has been marked for validation (e.g., form submission attempted)
     */
    fun shouldShowError(forceValidation: Boolean = false): Boolean {
        return (hasBeenModified || forceValidation) && error != null
    }
}