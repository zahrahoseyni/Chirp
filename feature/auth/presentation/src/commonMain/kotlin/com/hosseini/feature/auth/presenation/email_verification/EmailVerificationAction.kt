package com.hosseini.feature.auth.presenation.email_verification

sealed interface EmailVerificationAction {
    data object OnLoginClick : EmailVerificationAction
    data object OnCloseClick : EmailVerificationAction
}