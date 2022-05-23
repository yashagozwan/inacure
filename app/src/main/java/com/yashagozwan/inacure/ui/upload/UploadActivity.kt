package com.yashagozwan.inacure.ui.upload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
        addButtonListener()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun addButtonListener() {
        binding.btnBack.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_back -> finish()
        }
    }
}