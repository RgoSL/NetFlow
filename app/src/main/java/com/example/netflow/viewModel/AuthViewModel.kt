package com.example.netflow.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.netflow.auth.data.repository.AuthRepository

class AuthViewModel (
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun login(email: String, password: String, onSucess: () -> Unit) {
        isLoading = true
        repository.login(email, password) { success, error ->
            isLoading = false
            if (success) onSucess() else errorMessage = error
        }
    }

}