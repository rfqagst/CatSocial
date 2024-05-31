package com.example.catsocial.presentation.screen.auth

import androidx.lifecycle.ViewModel
import com.example.catsocial.data.firebase.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val repository: AuthRepository
) : ViewModel() {
}