package com.yashagozwan.inacure.data.repositories

import androidx.lifecycle.liveData
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.data.network.api.InacureConfig
import okhttp3.MultipartBody

class InacureRepository(private val inacureConfig: InacureConfig) {
    fun predict(file: MultipartBody.Part) = liveData {
        emit(Result.Loading)
        try {
            val response = inacureConfig.inacureService().predict(file)
            emit(Result.Success(response))
        } catch (exc: Exception) {
            emit(Result.Error(exc.message.toString()))
        }
    }


    companion object {
        private var instance: InacureRepository? = null
        fun getInstance(inacureConfig: InacureConfig) = instance ?: synchronized(this) {
            instance ?: InacureRepository(inacureConfig)
        }.also { instance = it }
    }
}