package com.yashagozwan.inacure.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.databinding.ActivityMainBinding
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.ui.signin.SignInActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val factory = ViewModelFactory.getInstance(this)
    private val viewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
        bottomNavigation()
        getUserProfile()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun getUserProfile() {
        viewModel.getToken().observe(this) { token ->
            if (token.isEmpty()) {
                finish()
            } else {
                viewModel.getUserProfile(token).observe(this) {
                    when (it) {
                        is Result.Loading -> {
                            binding.cvLoading.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.cvLoading.visibility = View.GONE
                        }
                        is Result.Error -> {
                            binding.cvLoading.visibility = View.GONE
                            viewModel.deleteToken()
                            Intent(
                                this@MainActivity,
                                SignInActivity::class.java
                            ).also { startActivity(it) }
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun bottomNavigation() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_scan,
                R.id.navigation_bookmark,
                R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}