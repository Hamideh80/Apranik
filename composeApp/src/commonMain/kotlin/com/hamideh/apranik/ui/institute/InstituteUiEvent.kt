package com.hamideh.apranik.ui.institute

/**
 * Sealed class representing user actions on the Institute creation screen.
 */
sealed class InstituteUiEvent {
    data class OnInstituteNameChanged(val name: String) : InstituteUiEvent()
    data class OnAdminNameChanged(val name: String) : InstituteUiEvent()
    data class OnEmailChanged(val email: String) : InstituteUiEvent()
    data class OnPasswordChanged(val password: String) : InstituteUiEvent()
    object OnTogglePasswordVisibility : InstituteUiEvent()
    object OnSubmit : InstituteUiEvent()
}
