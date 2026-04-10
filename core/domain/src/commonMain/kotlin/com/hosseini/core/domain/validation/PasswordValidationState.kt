package com.hosseini.core.domain.validation

data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasUppercase: Boolean = false,
    val hasDigit: Boolean = false
) {
    val isValid: Boolean
        get() = hasMinLength && hasUppercase && hasDigit
}