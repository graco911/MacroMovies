package com.gracodev.macromovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.gracodev.data.usecaseresult.UseCaseResult
import com.gracodev.domain.usecase.PerformLoginUseCase
import com.gracodev.macromovies.states.UIStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val performLoginUseCase: PerformLoginUseCase
) : BaseViewModel() {

    private var password: String = ""
    private var email: String = ""

    private val _loginValidate = MutableLiveData<UIStates<Boolean>>(UIStates.Init)
    val loginValidate: LiveData<UIStates<Boolean>> = _loginValidate

    private val _loginResult = MutableLiveData<UIStates<AuthResult>>(UIStates.Init)
    val loginResult: LiveData<UIStates<AuthResult>> = _loginResult

    fun performLogin() {
        try {
            viewModelScope.launch {
                _loginResult.value = UIStates.Loading
                val result = performLoginUseCase(email, password)
                _loginResult.value = result.toUIStates()
            }
        } catch (ex: Exception) {
            _loginResult.value = UIStates.Error(ex.message ?: "Error desconocido")
        }
    }

    fun login() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                validateLogin(email, password)
            }
            when (result) {
                is UseCaseResult.Success -> _loginValidate.value = UIStates.Success(true)
                is UseCaseResult.Error -> _loginValidate.value = UIStates.Success(false)
            }
        }
    }

    private fun validateLogin(user: String, pass: String): UseCaseResult<Boolean> {
        return if (user.isEmpty()
            || pass.isEmpty()
        ) {
            UseCaseResult.Error(Exception("Error de Validación"))
        } else {
            UseCaseResult.Success(validateCredentials(user, pass))
        }
    }

    private fun validateCredentials(user: String, pass: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return emailRegex.toRegex().matches(user)
                && pass.isNotEmpty()
    }

    fun setEmail(newEmail: String) {
        email = newEmail
    }

    fun setPass(newPassword: String) {
        password = newPassword
    }
}