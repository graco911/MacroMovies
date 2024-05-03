package com.gracodev.macromovies.viewmodels

import androidx.lifecycle.ViewModel
import com.gracodev.data.usecaseresult.UseCaseResult
import com.gracodev.macromovies.states.UIStates

open class BaseViewModel : ViewModel() {

    fun <T : Any> UseCaseResult<T>.toUIStates(): UIStates<T> {
        return when (this) {
            is UseCaseResult.Success -> UIStates.Success(this.data)
            is UseCaseResult.Error -> UIStates.Error(this.exception.message ?: "An error occurred")
        }
    }
}