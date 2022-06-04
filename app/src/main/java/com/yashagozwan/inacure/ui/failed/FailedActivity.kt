package com.yashagozwan.inacure.ui.failed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yashagozwan.inacure.databinding.ActivityFailedBinding
import com.yashagozwan.inacure.ui.scan.ScanActivity
import com.yashagozwan.inacure.ui.upload.UploadActivity
import com.yashagozwan.inacure.utils.Constants

class FailedActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFailedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        hideAppBar()
        addButtonListener()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun addButtonListener() {
        viewBinding.btnTryAgain.setOnClickListener { tryAgainScan() }
    }

    private fun tryAgainScan() {
        val from = intent.getStringExtra(FROM_ACTIVITY)

        if (from != null) {
            val intent: Intent
            when (from) {
                Constants.CAMERA -> {
                    intent = Intent(this@FailedActivity, ScanActivity::class.java)
                    startActivity(intent)
                }
                Constants.GALLERY -> {
                    intent = Intent(this@FailedActivity, UploadActivity::class.java)
                    startActivity(intent)
                }
            }
            finish()
        }
    }

    companion object {
        const val FROM_ACTIVITY = "fromActivity"
    }
}