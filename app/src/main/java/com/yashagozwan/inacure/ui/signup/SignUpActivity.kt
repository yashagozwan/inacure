package com.yashagozwan.inacure.ui.signup

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
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
        binding.tvSignIn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.tv_sign_in -> finish()
        }
    }
}