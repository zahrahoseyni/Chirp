package com.hosseini.core.domain.validation

object PasswordValidator {
    private const val MIN_LENGTH = 9

    fun validate(password: String): PasswordValidationState {
        return PasswordValidationState(
            hasMinLength = password.length >= MIN_LENGTH,
            hasUppercase = password.any { it.isUpperCase() },
            hasDigit = password.any { it.isDigit() }
        )
    }
}