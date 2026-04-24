package com.hosseini.feature.auth.presenation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chirp.feature.auth.presentation.generated.resources.Res
import chirp.feature.auth.presentation.generated.resources.error_account_exists
import chirp.feature.auth.presentation.generated.resources.error_invalid_email
import chirp.feature.auth.presentation.generated.resources.error_invalid_password
import chirp.feature.auth.presentation.generated.resources.error_invalid_username
import com.hosseini.core.domain.auth.AuthService
import com.hosseini.core.domain.util.DataError
import com.hosseini.core.domain.util.onFailure
import com.hosseini.core.domain.util.onSuccess
import com.hosseini.core.domain.validation.PasswordValidator
import com.hosseini.core.presentation.util.UiText
import com.hosseini.core.presentation.util.toUiText
import com.hosseini.feature.auth.domain.EmailValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(private val authService: AuthService) : ViewModel() {

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()


    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.onStart {
        if (!hasLoadedInitialData) {
            /** Load initial data here **/
            hasLoadedInitialData = true
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RegisterState()
    )

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.OnLoginClick -> {
                validateFormInputs()
            }

            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnInputTextFocusGain -> {}

            RegisterAction.OnTogglePasswordVisibility -> {
                _state.update {
                    it.copy(isPasswordVisible = !it.isPasswordVisible)
                }
            }
        }
    }

    private fun register() {
        _state.update { it.copy(isRegistering = true) }
        if (!validateFormInputs())
            return

        viewModelScope.launch {
            val email = state.value.emailTextState.text.toString()
            val username = state.value.usernameTextState.text.toString()
            val password = state.value.passwordTextState.text.toString()

            authService.register(
                email = email,
                username = username,
                password = password
            ).onSuccess {
                _state.update { it.copy(isRegistering = false) }

            }.onFailure { error ->
                val registrationError = when (error) {
                    DataError.Remote.CONFLICT -> UiText.Resource(Res.string.error_account_exists)
                    else -> error.toUiText()
                }
                _state.update {
                    it.copy(
                        isRegistering = false,
                        registrationError = registrationError
                    )
                }
            }
        }
    }

    private fun clearAllTextFieldsErrors() {
        _state.update {
            it.copy(
                emailError = null,
                usernameError = null,
                passwordError = null,
                registrationError = null
            )
        }
    }

    private fun validateFormInputs(): Boolean {
        clearAllTextFieldsErrors()

        val currentState = state.value

        val email = currentState.emailTextState.text.toString()
        val username = currentState.usernameTextState.text.toString()
        val password = currentState.passwordTextState.text.toString()

        val isEmailValid = EmailValidator.validate(email)
        val isUsernameValid = username.length in 3..20
        val isPasswordValid = PasswordValidator.validate(password)


        _state.update {
            it.copy(
                emailError = if (!isEmailValid) UiText.Resource(Res.string.error_invalid_email) else null,
                usernameError = if (!isUsernameValid) UiText.Resource(Res.string.error_invalid_username) else null,
                passwordError = if (!isPasswordValid.isValid) UiText.Resource(Res.string.error_invalid_password) else null,
                isEmailValid = isEmailValid,
                isPasswordValid = isPasswordValid.isValid,
                isUsernameValid = isUsernameValid
            )
        }
        return isEmailValid && isUsernameValid && isPasswordValid.isValid
    }
}