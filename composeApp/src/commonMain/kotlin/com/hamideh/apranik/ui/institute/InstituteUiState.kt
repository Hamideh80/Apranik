package com.hamideh.apranik.ui.institute

/**
 * Represents the UI state for the Institute creation screen.
 */
data class InstituteUiState(
    val instituteName: String = "",
    val adminName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
) {
    /**
     * Checks if the form is valid based on the requirements.
     */
    val isFormValid: Boolean
        get() = instituteName.isNotBlank() &&
                adminName.isNotBlank() &&
                email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$")) &&
                password.length >= 6
}
