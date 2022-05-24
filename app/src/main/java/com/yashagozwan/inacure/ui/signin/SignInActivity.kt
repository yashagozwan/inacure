package com.yashagozwan.inacure.ui.signin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivitySignInBinding
import com.yashagozwan.inacure.model.SignIn
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.ui.signup.SignUpActivity
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.ui.main.MainActivity

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewBinding: ActivitySignInBinding
    private val factory = ViewModelFactory.getInstance(this)
    private val viewModel: SignInViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
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
        viewBinding.btnSignIn.setOnClickListener(this)
        viewBinding.tvSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_sign_in -> signIn()
            R.id.tv_sign_up -> Intent(this, SignUpActivity::class.java).also { startActivity(it) }
        }
    }

    private fun signIn() {
        val email = viewBinding.editEmail.text.toString().trim()
        val password = viewBinding.editPassword.text.toString().trim()

        if (email.isEmpty()) {
            viewBinding.editEmail.error = "Field not allowed empty"
            return
        }

        if (password.isEmpty()) {
            viewBinding.editPassword.error = "Field not allowed ampty"
            return
        }

        val signIn = SignIn(email, password)
        viewModel.signIn(signIn).observe(this) {
            when (it) {
                is Result.Loading -> {
                    viewBinding.cvLoading.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    viewBinding.cvLoading.visibility = View.GONE
                    val response = it.data
                    val token = response.data.token
                    viewModel.saveToken(token)
                    Intent(this@SignInActivity, MainActivity::class.java).also { startActivity(it) }
                    finish()
                }
                is Result.Error -> {
                    viewBinding.cvLoading.visibility = View.GONE
                    val error = it.error
                    Log.d(TAG, error)
                }
            }
        }
    }

    companion object {
        private val TAG = SignInActivity::class.java.simpleName
    }
}