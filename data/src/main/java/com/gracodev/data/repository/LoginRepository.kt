package com.gracodev.data.repository

import com.google.firebase.auth.AuthResult
import com.gracodev.data.remote.AuthenticationDataSource
import com.gracodev.data.usecaseresult.UseCaseResult

class LoginRepository(
    private val authenticationDataSource: AuthenticationDataSource
) {
    suspend fun performUserLogin(user: String, pass: String): UseCaseResult<AuthResult> {
        return when (val response = authenticationDataSource.performLogin(user, pass)) {
            is UseCaseResult.Error -> {
                return UseCaseResult.Error(exception = Throwable("Error"))
            }

            is UseCaseResult.Success -> {
                return UseCaseResult.Success(response.data)
            }
        }
    }
}