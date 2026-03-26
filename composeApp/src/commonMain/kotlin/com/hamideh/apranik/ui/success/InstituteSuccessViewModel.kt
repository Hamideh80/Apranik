package com.hamideh.apranik.ui.success

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Institute Success screen.
 */
class InstituteSuccessViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InstituteSuccessUiState())
    val uiState: StateFlow<InstituteSuccessUiState> = _uiState.asStateFlow()

    /**
     * Initializes the state with the created institute details.
     */
    fun init(instituteName: String) {
        val subdomain = "${instituteName.lowercase().replace(" ", "")}.etherly.app"
        _uiState.update { 
            it.copy(
                instituteName = instituteName,
                subdomain = subdomain
            )
        }
    }
}
