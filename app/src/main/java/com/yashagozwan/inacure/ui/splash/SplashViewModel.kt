package com.yashagozwan.inacure.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.data.repositories.UserRepository

class SplashViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getToken() = userRepository.getToken()

}