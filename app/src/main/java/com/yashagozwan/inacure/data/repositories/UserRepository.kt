package com.yashagozwan.inacure.data.repositories

import com.yashagozwan.inacure.data.local.SharedPreferences

class UserRepository private constructor(private val sharedPreferences: SharedPreferences) {
    suspend fun saveToken(token: String) {
        sharedPreferences.saveToken(token)
    }

    fun getToken() = sharedPreferences.getToken()

    companion object {
        private var instance: UserRepository? = null
        fun getInstance(sharedPreferences: SharedPreferences) = instance ?: synchronized(this) {
            instance ?: UserRepository(sharedPreferences)
        }.also { instance = it }
    }
}