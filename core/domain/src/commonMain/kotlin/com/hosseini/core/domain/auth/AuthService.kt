package com.hosseini.core.domain.auth

import com.hosseini.core.domain.util.DataError
import com.hosseini.core.domain.util.EmptyResult

interface AuthService {
    suspend fun register(
        email: String,
        username: String,
        password: String
    ): EmptyResult<DataError.Remote>
}