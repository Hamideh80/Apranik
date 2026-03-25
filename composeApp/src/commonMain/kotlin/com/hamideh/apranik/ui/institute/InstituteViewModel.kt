package com.hamideh.apranik.ui.institute

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Institute creation screen, handling business logic and state.
 */
class InstituteViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InstituteUiState())
    val uiState: StateFlow<InstituteUiState> = _uiState.asStateFlow()

    /**
     * Handles UI events triggered by the user.
     */
    fun onEvent(event: InstituteUiEvent) {
        when (event) {
            is InstituteUiEvent.OnInstituteNameChanged -> {
                _uiState.update { it.copy(instituteName = event.name, errorMessage = null) }
            }
            is InstituteUiEvent.OnAdminNameChanged -> {
                _uiState.update { it.copy(adminName = event.name, errorMessage = null) }
            }
            is InstituteUiEvent.OnEmailChanged -> {
                _uiState.update { it.copy(email = event.email, errorMessage = null) }
            }
            is InstituteUiEvent.OnPasswordChanged -> {
                _uiState.update { it.copy(password = event.password, errorMessage = null) }
            }
            is InstituteUiEvent.OnTogglePasswordVisibility -> {
                _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            is InstituteUiEvent.OnSubmit -> {
                createInstitute()
            }
        }
    }

    /**
     * Mocks the institute creation process with a delay.
     */
    private fun createInstitute() {
        if (!_uiState.value.isFormValid) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            
            // Mock API Delay
            delay(2000)
            
            // Simulate success
            _uiState.update { it.copy(isLoading = false, isSuccess = true) }
        }
    }
}
