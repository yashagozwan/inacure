package com.yashagozwan.inacure.ui.main.home

import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.data.repositories.InacureRepository
import com.yashagozwan.inacure.data.repositories.UserRepository

class HomeViewModel(
    private val userRepository: UserRepository,
    private val inacureRepository: InacureRepository
) : ViewModel() {
    fun getToken() = userRepository.getToken()
    fun getUserProfile(token: String) = userRepository.getUserProfile(token)
    fun getArticles(token: String) = inacureRepository.getArticles(token)
}