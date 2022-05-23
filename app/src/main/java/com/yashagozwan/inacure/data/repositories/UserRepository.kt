package com.yashagozwan.inacure.data.repositories

import androidx.lifecycle.liveData
import com.yashagozwan.inacure.data.local.SharedPreferences
import com.yashagozwan.inacure.data.network.api.InacureConfig
import com.yashagozwan.inacure.model.SignIn
import com.yashagozwan.inacure.data.network.Result

class UserRepository private constructor(
    private val sharedPreferences: SharedPreferences,
    private val inacureConfig: InacureConfig
) {
    suspend fun saveToken(token: String) {
        sharedPreferences.saveToken(token)
    }

    fun getToken() = sharedPreferences.getToken()

    fun signIn(signIn: SignIn) = liveData {
        emit(Result.Loading)
        try {
            val response = inacureConfig.inacureService().signIn(signIn)
            emit(Result.Success(response))
        } catch (exc: Exception) {
            emit(Result.Error(exc.message.toString()))
        }
    }

    companion object {
        private var instance: UserRepository? = null
        fun getInstance(
            sharedPreferences: SharedPreferences,
            inacureConfig: InacureConfig
        ) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(sharedPreferences, inacureConfig)
            }.also { instance = it }
    }
}