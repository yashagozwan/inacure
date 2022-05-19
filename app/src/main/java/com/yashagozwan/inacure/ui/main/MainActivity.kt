package com.yashagozwan.inacure.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivityMainBinding
import com.yashagozwan.inacure.ui.main.bookmark.BookmarkFragment
import com.yashagozwan.inacure.ui.main.home.HomeFragment
import com.yashagozwan.inacure.ui.main.profile.ProfileFragment
import com.yashagozwan.inacure.ui.scan.ScanActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
        replaceFragment(HomeFragment())
        bottomNavigation()
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
                    replaceFragment(ProfileFragment())
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