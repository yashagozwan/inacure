package com.yashagozwan.inacure.ui.signin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivitySignInBinding
import com.yashagozwan.inacure.ui.main.MainActivity
import com.yashagozwan.inacure.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
        customStatusBar()
        addButtonListener()

    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    @Suppress("DEPRECATION")
    private fun customStatusBar() {
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
    }

    private fun addButtonListener() {
        binding.btnSignIn.setOnClickListener(this)
        binding.tvSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_sign_in -> Intent(this, MainActivity::class.java).also { startActivity(it) }
            R.id.tv_sign_up -> Intent(this, SignUpActivity::class.java).also { startActivity(it) }
        }
    }
}