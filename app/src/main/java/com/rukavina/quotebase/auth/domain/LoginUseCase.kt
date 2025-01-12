package com.rukavina.quotebase.auth.domain

import com.rukavina.quotebase.auth.data.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return authRepository.loginUser(email, password)
    }
}