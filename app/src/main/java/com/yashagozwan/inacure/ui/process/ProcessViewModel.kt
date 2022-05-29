package com.yashagozwan.inacure.ui.process

import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.data.repositories.InacureRepository
import okhttp3.MultipartBody

class ProcessViewModel(private val inacureRepository: InacureRepository) : ViewModel() {
    fun predict(file: MultipartBody.Part) = inacureRepository.predict(file)
}