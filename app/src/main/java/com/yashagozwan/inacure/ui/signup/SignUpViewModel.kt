package com.yashagozwan.inacure.ui.signup

import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.data.repositories.UserRepository
import com.yashagozwan.inacure.model.SignUp

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun signUp(signUp: SignUp) = userRepository.signUp(signUp)
}