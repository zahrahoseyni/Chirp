package com.hosseini.feature.auth.presenation.email_verification

data class EmailVerificationState(
    val isVerifying: Boolean = false,
    val isVerified: Boolean = false
)