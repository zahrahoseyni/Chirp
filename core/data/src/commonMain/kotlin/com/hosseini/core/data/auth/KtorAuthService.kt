package com.hosseini.core.data.auth

import com.hosseini.core.data.networking.post
import com.hosseini.core.data.dto.requests.RegisterRequest
import com.hosseini.core.domain.auth.AuthService
import com.hosseini.core.domain.util.DataError
import com.hosseini.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class KtorAuthService(private val httpClient: HttpClient) : AuthService {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/register",
            body = RegisterRequest(email = email, username = username, password = password)
        )
    }
}