package com.yashagozwan.inacure.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashagozwan.inacure.data.repositories.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getToken() = userRepository.getToken()

    fun deleteToken() {
        viewModelScope.launch {
            userRepository.deleteToken()
        }
    }

    fun getUserProfile(token: String) = userRepository.getUserProfile(token)
}