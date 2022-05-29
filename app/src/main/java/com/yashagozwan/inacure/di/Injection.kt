package com.yashagozwan.inacure.di

import android.content.Context
import com.yashagozwan.inacure.data.local.SharedPreferences
import com.yashagozwan.inacure.data.network.api.InacureConfig
import com.yashagozwan.inacure.data.repositories.InacureRepository
import com.yashagozwan.inacure.data.repositories.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val sharedPreferences = SharedPreferences.getInstance(context)
        val inacureConfig = InacureConfig
        return UserRepository.getInstance(sharedPreferences, inacureConfig)
    }

    fun provideInacureRepository(): InacureRepository {
        val inacureConfig = InacureConfig
        return InacureRepository.getInstance(inacureConfig)
    }
}