package com.yashagozwan.inacure.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yashagozwan.inacure.data.repositories.UserRepository
import com.yashagozwan.inacure.di.Injection
import com.yashagozwan.inacure.ui.main.MainViewModel
import com.yashagozwan.inacure.ui.signin.SignInViewModel
import com.yashagozwan.inacure.ui.splash.SplashViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> SignInViewModel(
                userRepository
            ) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel() as T
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel() as T
            else -> throw IllegalArgumentException("Invalid ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(userRepository = Injection.provideUserRepository(context))
        }.also { instance = it }
    }
}