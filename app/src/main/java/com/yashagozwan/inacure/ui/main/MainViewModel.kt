package com.yashagozwan.inacure.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashagozwan.inacure.data.repositories.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getToken() = userRepository.getToken()
    fun getUserProfile(token: String) = userRepository.getUserProfile(token)
    fun deleteToken() {
        viewModelScope.launch {
            userRepository.deleteToken()
        }
    }
}