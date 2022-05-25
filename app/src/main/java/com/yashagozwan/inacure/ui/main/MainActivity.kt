package com.yashagozwan.inacure.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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

    private fun startCameraX() {
        val intent = Intent(this@MainActivity, ScanActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    companion object {
        const val CAMERA_X_RESULT = 200
    }
}