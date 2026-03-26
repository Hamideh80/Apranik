package com.hamideh.apranik.ui.logo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Optional Institute Logo Upload screen.
 */
class InstituteLogoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InstituteLogoUiState())
    val uiState: StateFlow<InstituteLogoUiState> = _uiState.asStateFlow()

    /**
     * Updates the selected image bytes after validation.
     */
    fun onImageSelected(imageBytes: ByteArray) {
        // Mock validation for 1MB (1,048,576 bytes)
        if (imageBytes.size > 1024 * 1024) {
            _uiState.update { it.copy(errorMessage = "File size must be less than 1MB") }
        } else {
            _uiState.update { it.copy(selectedImageBytes = imageBytes, errorMessage = null) }
        }
    }

    /**
     * Clears any existing error messages.
     */
    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}
