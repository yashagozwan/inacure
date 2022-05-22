package com.yashagozwan.inacure.di

import android.content.Context
import com.yashagozwan.inacure.data.local.SharedPreferences
import com.yashagozwan.inacure.data.repositories.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val sharedPreferences = SharedPreferences.getInstance(context)
        return UserRepository.getInstance(sharedPreferences)
    }
}