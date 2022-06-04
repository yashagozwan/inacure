package com.yashagozwan.inacure.ui.appropriate

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.yashagozwan.inacure.databinding.ActivityAppropriateBinding
import com.yashagozwan.inacure.model.MyImage
import com.yashagozwan.inacure.utils.Constants
import com.yashagozwan.inacure.utils.Util.rotateBitmap

class AppropriateActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAppropriateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAppropriateBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        hideAppBar()
        renderImage()
        addButtonListener()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun addButtonListener() {
        viewBinding.btnBack.setOnClickListener { finish() }
    }

    private fun renderImage() {
        val myImage = intent.getParcelableExtra<MyImage>(IMAGE_RESULT)
        if (myImage != null) {
            when (myImage.from) {
                Constants.CAMERA -> {
                    val image = myImage.image
                    viewBinding.tvImage.setImageBitmap(
                        rotateBitmap(BitmapFactory.decodeFile(image.path), true)
                    )
                }
                Constants.GALLERY -> {
                    val image = myImage.image
                    viewBinding.tvImage.setImageBitmap(BitmapFactory.decodeFile(image.path))
                }
            }
        }
    }

    companion object {
        const val IMAGE_RESULT = "IMAGE_RESULT"
    }
}