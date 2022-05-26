package com.yashagozwan.inacure.ui.process

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yashagozwan.inacure.databinding.ActivityProcessBinding
import com.yashagozwan.inacure.model.ScanImage
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.utils.Util.rotateBitmap

class ProcessActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityProcessBinding
    private val factory = ViewModelFactory.getInstance(this)
    private val viewModel: ProcessViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProcessBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        hideAppBar()
        renderTitle()
        renderImage()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun renderTitle() {
        val title = intent.getStringExtra(TITLE)
        if (title != null) {
            viewBinding.tvTitle.text = title
        }
    }

    private fun renderImage() {
        val scanImageCameraX = intent.getParcelableExtra<ScanImage>(CAMERA_X_RESULT)
        val scanImageGallery = intent.getParcelableExtra<ScanImage>(GALLERY_RESULT)

        if (scanImageCameraX != null) {
            val image = BitmapFactory.decodeFile(scanImageCameraX.image.path)
            val fixImage = rotateBitmap(image, true)
            viewBinding.ivImageScan.setImageBitmap(fixImage)
        }

        if (scanImageGallery != null) {
            val image = BitmapFactory.decodeFile(scanImageGallery.image.path)
            viewBinding.ivImageScan.setImageBitmap(image)
        }
    }

    companion object {
        const val CAMERA_X_RESULT = "cameraXResult"
        const val GALLERY_RESULT = "galleryResult"
        const val TITLE = "title"
    }
}