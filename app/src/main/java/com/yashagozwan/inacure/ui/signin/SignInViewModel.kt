package com.yashagozwan.inacure.ui.signin

import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.data.repositories.UserRepository
import com.yashagozwan.inacure.model.SignIn

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun signIn(signIn: SignIn) = userRepository.signIn(signIn)
}