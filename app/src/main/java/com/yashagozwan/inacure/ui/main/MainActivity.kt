package com.yashagozwan.inacure.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yashagozwan.inacure.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }
}