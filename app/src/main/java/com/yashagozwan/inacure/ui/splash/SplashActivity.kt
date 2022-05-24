package com.yashagozwan.inacure.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivitySplashBinding
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.ui.main.MainActivity
import com.yashagozwan.inacure.ui.signin.SignInActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySplashBinding
    private val factory = ViewModelFactory.getInstance(this)
    private val viewModel: SplashViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
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
            viewModel.getToken().observe(this) {
                val intent: Intent = if (it.isNotEmpty()) {
                    Intent(this@SplashActivity, MainActivity::class.java)
                } else {
                    Intent(this@SplashActivity, SignInActivity::class.java)
                }
                startActivity(intent)
                finish()
            }
        }, duration)
    }
}