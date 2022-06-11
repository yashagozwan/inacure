package com.yashagozwan.inacure.ui.main.home

import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.data.repositories.InacureRepository
import com.yashagozwan.inacure.data.repositories.UserRepository

class HomeViewModel(
    private val userRepository: UserRepository,
    private val inacureRepository: InacureRepository
) : ViewModel() {
    fun getToken() = userRepository.getToken()
    fun getArticles(token: String) = inacureRepository.getArticles(token)
}