package com.yashagozwan.inacure.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yashagozwan.inacure.ui.main.MainViewModel
import com.yashagozwan.inacure.ui.splash.SplashViewModel

class ViewModelFactory private constructor() : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel() as T
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel() as T
            else -> throw IllegalArgumentException("Invalid ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ViewModelFactory()
        }.also { instance = it }
    }
}