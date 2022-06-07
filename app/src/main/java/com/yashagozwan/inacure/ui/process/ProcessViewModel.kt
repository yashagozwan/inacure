package com.yashagozwan.inacure.ui.process

import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.data.repositories.InacureRepository
import com.yashagozwan.inacure.data.repositories.UserRepository
import okhttp3.MultipartBody

class ProcessViewModel(
    private val userRepository: UserRepository,
    private val inacureRepository: InacureRepository
) : ViewModel() {
    fun getToken() = userRepository.getToken()
    fun predict(token: String, file: MultipartBody.Part) = inacureRepository.predict(token, file)
}