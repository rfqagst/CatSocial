package com.example.catsocial.presentation.screen.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsocial.data.datastore.SessionManager
import com.example.catsocial.data.firebase.repository.AuthRepository
import com.example.catsocial.util.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val context: Application
) : AndroidViewModel(context) {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    private val sessionManager = SessionManager(context)

    val currentUser: FirebaseUser?
        get() = repository.currentUserFirebase

    fun login(email: String, password: String) = viewModelScope.launch {
        val result = repository.loginFirebase(email, password)
        _loginFlow.value = result
        if (result is Resource.Success) {
            sessionManager.saveLoginState(true)
        }
    }

    fun signUp(name: String, email: String, password: String) = viewModelScope.launch {
        val result = repository.signupFirebase(email, password, name)
        _signupFlow.value = result
        Log.d("AuthViewModel", "$email, signUp: ${result.message}")
    }

    fun clearLoginFlow() {
        _loginFlow.value = null
    }

    fun logout() {
        repository.logoutFirebase()
        sessionManager.saveLoginState(false)
    }

    fun isLoggedIn(): Boolean {
        return sessionManager.isLoggedIn()
    }
}