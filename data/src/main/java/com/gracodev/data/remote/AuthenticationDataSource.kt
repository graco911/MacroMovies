package com.gracodev.data.remote

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.gracodev.data.usecaseresult.UseCaseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthenticationDataSource(
    private val firebaseInstance: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun performLogin(user: String, pass: String): UseCaseResult<AuthResult> =
        withContext(ioDispatcher) {
            try {
                val authResult = firebaseInstance.signInWithEmailAndPassword(user, pass).await()
                UseCaseResult.Success(authResult)
            } catch (e: Exception) {
                UseCaseResult.Error(e)
            }
        }
}