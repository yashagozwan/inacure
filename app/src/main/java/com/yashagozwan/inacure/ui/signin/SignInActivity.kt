package com.yashagozwan.inacure.ui.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yashagozwan.inacure.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private  lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
    }

    private  fun hideAppBar(){
        supportActionBar?.hide()
    }
}