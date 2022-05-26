package com.yashagozwan.inacure.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewBinding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        hideAppBar()
        addButtonListener()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun addButtonListener() {
        viewBinding.btnBack.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_back -> finish()
        }
    }
}