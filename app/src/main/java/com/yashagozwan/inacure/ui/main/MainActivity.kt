package com.yashagozwan.inacure.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
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
import com.yashagozwan.inacure.ui.main.scanf.ScanFragment
import com.yashagozwan.inacure.ui.scan.ScanActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val factory = ViewModelFactory.getInstance(this)
    private val viewModel: MainViewModel by viewModels { factory }
    private lateinit var user: User
    private var getFile: File? = null

    private val launcherIntentCameraX =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == CAMERA_X_RESULT) {
                val myFile = it.data?.getSerializableExtra("image") as File
                getFile = myFile
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
        renderFragment()
        bottomNavigation()
        getUserProfile()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun renderFragment() {
        if (getFile != null) {
            val mBundle = Bundle()
            val mFragment = ScanFragment()
            mBundle.putSerializable("image", getFile)
            mFragment.arguments = mBundle
            replaceFragment(mFragment)
        }
        replaceFragment(HomeFragment())
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
                            binding.cvLoading.visibility = View.GONE
                            finish()
                        }
                    }
                }
            }
        }
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
                    if (getFile != null) {
                        val mBundle = Bundle()
                        val mFragment = ScanFragment()
                        mBundle.putSerializable("image", getFile)
                        mFragment.arguments = mBundle
                        replaceFragment(mFragment)
                    } else {
                        startCameraX()
                    }
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

    private fun startCameraX() {
        val intent = Intent(this@MainActivity, ScanActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    companion object {
        const val CAMERA_X_RESULT = 200
    }
}