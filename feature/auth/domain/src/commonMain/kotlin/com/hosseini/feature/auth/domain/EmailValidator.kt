package com.hosseini.feature.auth.domain

object EmailValidator {
    private const val EMAIL_ADDRESS_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"

    fun validate(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.toRegex().matches(email)
    }
}