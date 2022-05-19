package com.yashagozwan.inacure.ui.scan

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivityScanBinding
import com.yashagozwan.inacure.ui.UploadActivity

class ScanActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customStatusBar()
        hideAppBar()
        addButtonListener()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun addButtonListener() {
        binding.btnBack.setOnClickListener(this)
        binding.btnUpload.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_back -> finish()
            R.id.btn_upload -> Intent(this, UploadActivity::class.java).also { startActivity(it) }
        }
    }

    @Suppress("DEPRECATION")
    private fun customStatusBar() {
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
    }
}