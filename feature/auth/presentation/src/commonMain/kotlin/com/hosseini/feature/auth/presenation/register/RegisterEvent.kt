package com.hosseini.feature.auth.presenation.register

sealed interface RegisterEvent {
    data class Success(val email: String) : RegisterEvent
}