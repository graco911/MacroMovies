package com.gracodev.macromovies.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gracodev.domain.usecase.PerformLoginUseCase
import com.gracodev.macromovies.viewmodels.LoginViewModel
import com.gracodev.macromovies.viewmodels.MovieViewModel

class LoginViewModelFactory(
    private val performLoginUseCase: PerformLoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return LoginViewModel(performLoginUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}