package com.yashagozwan.inacure.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.databinding.ActivityMainBinding
import com.yashagozwan.inacure.model.User
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.ui.main.bookmark.BookmarkFragment
import com.yashagozwan.inacure.ui.main.home.HomeFragment
import com.yashagozwan.inacure.ui.main.profile.ProfileFragment
import com.yashagozwan.inacure.ui.scan.ScanActivity
import com.yashagozwan.inacure.ui.signin.SignInActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val factory = ViewModelFactory.getInstance(this)
    private val viewModel: MainViewModel by viewModels { factory }
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
        replaceFragment(HomeFragment())
        bottomNavigation()
        getUserProfile()
    }


    private fun getUserProfile() {
        val intent = Intent(this@MainActivity, SignInActivity::class.java)
        viewModel.getToken().observe(this) { token ->
            if (token.isEmpty()) {
                startActivity(intent)
                finish()
            } else {
                viewModel.getUserProfile(token).observe(this) {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            val response = it.data
                            val myUser = response.data
                            user = User(
                                id = myUser._id,
                                name = myUser.name,
                                email = myUser.email,
                                password = myUser.password,
                                createdAt = myUser.createdAt
                            )
                        }
                        is Result.Error -> {
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun bottomNavigation() {
        val navView: BottomNavigationView = binding.navView
        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.navigation_scan -> {
                    val intent = Intent(this@MainActivity, ScanActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_bookmark -> {
                    replaceFragment(BookmarkFragment())
                    true
                }

                R.id.navigation_profile -> {
                    val mBundle = Bundle()
                    val mFragment = ProfileFragment()
                    mBundle.putParcelable("USER", user)
                    mFragment.arguments = mBundle
                    replaceFragment(mFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}