package com.yashagozwan.inacure.ui.signin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivitySignInBinding
import com.yashagozwan.inacure.model.SignIn
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.ui.signup.SignUpActivity
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.ui.main.MainActivity
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.io.StringBufferInputStream

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewBinding: ActivitySignInBinding
    private val factory = ViewModelFactory.getInstance(this)
    private val viewModel: SignInViewModel by viewModels { factory }
    private var isValidEmail = false
    private var isValidPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        hideAppBar()
        customStatusBar()
        addButtonListener()
        handleErrorField()
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

    private fun handleErrorField() {
        viewBinding.editEmail.doOnTextChanged { text, _, _, _ ->
            if (text!!.isEmpty()) {
                viewBinding.tilEmail.isErrorEnabled = true
                viewBinding.tilEmail.error = "Email not allowed empty"
                isValidEmail = false
            } else if (!text.contains("@") || !text.contains(".")) {
                viewBinding.tilEmail.isErrorEnabled = true
                viewBinding.tilEmail.error = "Please enter valid email"
                isValidEmail = false
            } else {
                viewBinding.tilEmail.isErrorEnabled = false
                viewBinding.tilEmail.error = null
                isValidEmail = true
            }
        }

        viewBinding.editPassword.doOnTextChanged { text, _, _, _ ->
            if (text!!.isEmpty()) {
                viewBinding.tilPassword.isErrorEnabled = true
                viewBinding.tilPassword.error = "Password not allowed empty"
                isValidPassword = false
            } else {
                viewBinding.tilPassword.isErrorEnabled = false
                viewBinding.tilPassword.error = null
                isValidPassword = true
            }
        }
    }

    private fun signIn() {
        val email = viewBinding.editEmail.text.toString().trim()
        val password = viewBinding.editPassword.text.toString().trim()

        if (isValidEmail && isValidPassword) {
            val signIn = SignIn(email, password)
            viewModel.signIn(signIn).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        viewBinding.cvLoading.visibility = View.VISIBLE
                        viewBinding.tvInvalidAccount.visibility = View.GONE
                    }
                    is Result.Success -> {
                        viewBinding.cvLoading.visibility = View.GONE
                        viewBinding.tvInvalidAccount.visibility = View.GONE
                        val response = result.data
                        val token = response.data.token
                        viewModel.saveToken(token)
                        Intent(this@SignInActivity, MainActivity::class.java).also { intent ->
                            startActivity(intent)
                        }
                        finish()
                        showToast("Sign In ...")
                    }
                    is Result.Error -> {
                        viewBinding.cvLoading.visibility = View.GONE
                        viewBinding.tvInvalidAccount.visibility = View.VISIBLE
                        val error = result.error
                        Log.d(TAG, error)
                    }
                }
            }
        } else {
            showToast("Please fill in all the fields")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@SignInActivity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val TAG = SignInActivity::class.java.simpleName
    }
}