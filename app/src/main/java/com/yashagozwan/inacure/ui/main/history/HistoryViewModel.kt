package com.yashagozwan.inacure.ui.main.history

import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.data.repositories.UserRepository

class HistoryViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getHistory(token: String) = userRepository.getHistory(token)
    fun getToken() = userRepository.getToken()
}