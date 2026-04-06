package com.hosseini.feature.auth.presenation.register

import androidx.compose.foundation.text.input.TextFieldState
import com.hosseini.core.presentation.util.UiText

data class RegisterState(
    val emailTextState: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = true,
    val emailError: UiText? = null,
    val passwordTextState: TextFieldState = TextFieldState(),
    val isPasswordValid: Boolean = true,
    val passwordError: UiText? = null,
    val usernameTextState: TextFieldState = TextFieldState(),
    val isUsernameValid: Boolean = true,
    val usernameError: UiText? = null,
    val registrationError: UiText? = null,
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false,
    val isPasswordVisible: Boolean = false
)