package com.gracodev.domain.usecase

import com.gracodev.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PerformLoginUseCase(
    private val repository: LoginRepository,
    private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(user: String, pass: String) = withContext(defaultDispatcher) {
        repository.performUserLogin(user, pass)
    }
}