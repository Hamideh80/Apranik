package com.hamideh.apranik.ui.logo

/**
 * UI State for the Optional Institute Logo Upload screen.
 */
data class InstituteLogoUiState(
    val selectedImageBytes: ByteArray? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as InstituteLogoUiState
        if (selectedImageBytes != null) {
            if (other.selectedImageBytes == null) return false
            if (!selectedImageBytes.contentEquals(other.selectedImageBytes)) return false
        } else if (other.selectedImageBytes != null) return false
        if (isLoading != other.isLoading) return false
        if (errorMessage != other.errorMessage) return false
        return true
    }

    override fun hashCode(): Int {
        var result = selectedImageBytes?.contentHashCode() ?: 0
        result = 31 * result + isLoading.hashCode()
        result = 31 * result + (errorMessage?.hashCode() ?: 0)
        return result
    }
}
