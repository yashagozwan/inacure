package com.yashagozwan.inacure.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yashagozwan.inacure.data.repositories.InacureRepository
import com.yashagozwan.inacure.data.repositories.UserRepository
import com.yashagozwan.inacure.di.Injection
import com.yashagozwan.inacure.ui.main.MainViewModel
import com.yashagozwan.inacure.ui.main.history.HistoryViewModel
import com.yashagozwan.inacure.ui.main.home.HomeViewModel
import com.yashagozwan.inacure.ui.main.profile.ProfileViewModel
import com.yashagozwan.inacure.ui.process.ProcessViewModel
import com.yashagozwan.inacure.ui.signin.SignInViewModel
import com.yashagozwan.inacure.ui.signup.SignUpViewModel
import com.yashagozwan.inacure.ui.splash.SplashViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository,
    private val inacureRepository: InacureRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> SignInViewModel(
                userRepository
            ) as T
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(
                userRepository
            ) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                userRepository
            ) as T
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(
                userRepository
            ) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                userRepository,
                inacureRepository
            ) as T
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> HistoryViewModel(
                userRepository
            ) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(
                userRepository
            ) as T
            modelClass.isAssignableFrom(ProcessViewModel::class.java) -> ProcessViewModel(
                userRepository,
                inacureRepository
            ) as T
            else -> throw IllegalArgumentException("Invalid ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                userRepository = Injection.provideUserRepository(context),
                inacureRepository = Injection.provideInacureRepository()
            )
        }.also { instance = it }
    }
}