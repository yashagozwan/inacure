package com.yashagozwan.inacure.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivitySplashBinding
import com.yashagozwan.inacure.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
        setDuration()
    }

    private fun hideAppBar() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        supportActionBar?.hide()
    }

    private fun setDuration() {
        val duration = 3000L
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this@SplashActivity, MainActivity::class.java).also { startActivity(it) }
            finish()
        }, duration)
    }
}