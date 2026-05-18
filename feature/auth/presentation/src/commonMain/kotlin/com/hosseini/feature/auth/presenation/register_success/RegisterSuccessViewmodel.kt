package com.hosseini.feature.auth.presenation.register_success

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hosseini.core.domain.auth.AuthService
import com.hosseini.core.domain.util.onFailure
import com.hosseini.core.domain.util.onSuccess
import com.hosseini.core.presentation.util.toUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RegisterSuccessViewModel(
    private val authService: AuthService,
    saveStateHandler: SavedStateHandle
) : ViewModel() {
    private var hasLoadedInitialData = false
    private val email = saveStateHandler.get<String>("email")
        ?: throw IllegalArgumentException("email is not passed ")

    private val eventChannel = Channel<RegisterSuccessEvent>()
    val events = eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(RegisterSuccessState(registeredEmail = email))
    val state = _state.onStart {
        if (!hasLoadedInitialData) {
            /** Load initial data here **/
            hasLoadedInitialData = true
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = RegisterSuccessState()
    )

    fun onAction(action: RegisterSuccessAction) {
        when (action) {
            is RegisterSuccessAction.OnResendVerificationEmailClick -> resendVerificationEmail()
            else -> Unit
        }
    }

    private fun resendVerificationEmail() {
        if (state.value.isResendingVerificationEmail) {
            return
        }
        viewModelScope.launch {
            _state.value = state.value.copy(
                isResendingVerificationEmail = true,
                resendVerificationError = null
            )

            authService.resendVerificationEmail(email)
                .onSuccess {
                    _state.value = state.value.copy(isResendingVerificationEmail = false)
                    eventChannel.send(RegisterSuccessEvent.ResendVerificationEmailSuccess)
                }
                .onFailure { error ->
                    _state.value = state.value.copy(
                        isResendingVerificationEmail = false,
                        resendVerificationError = error.toUiText()
                    )
                }
        }

    }

}