package com.hosseini.feature.auth.presenation.register

sealed interface RegisterAction {
    data object OnLoginClick : RegisterAction
    data object OnRegisterClick : RegisterAction
    data object OnInputTextFocusGain : RegisterAction
    data object OnTogglePasswordVisibility : RegisterAction
}