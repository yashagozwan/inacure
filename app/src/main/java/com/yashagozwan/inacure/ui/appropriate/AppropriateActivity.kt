package com.yashagozwan.inacure.ui.appropriate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivityAppropriateBinding

class AppropriateActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAppropriateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAppropriateBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        hideAppBar()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }
}