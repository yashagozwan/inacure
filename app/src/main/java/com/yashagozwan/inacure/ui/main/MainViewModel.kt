package com.yashagozwan.inacure.ui.main

import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.data.repositories.UserRepository

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getToken() = userRepository.getToken()
    fun getUserProfile(token: String) = userRepository.getUserProfile(token)
}