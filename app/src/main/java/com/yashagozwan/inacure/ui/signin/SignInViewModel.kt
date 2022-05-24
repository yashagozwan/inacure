package com.yashagozwan.inacure.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashagozwan.inacure.data.repositories.UserRepository
import com.yashagozwan.inacure.model.SignIn
import kotlinx.coroutines.launch

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun signIn(signIn: SignIn) = userRepository.signIn(signIn)
    fun saveToken(token: String) {
        viewModelScope.launch {
            userRepository.saveToken(token)
        }
    }
}