package com.yashagozwan.inacure.ui.process

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.yashagozwan.inacure.databinding.ActivityProcessBinding
import com.yashagozwan.inacure.model.ScanImage
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.utils.Util
import com.yashagozwan.inacure.utils.Util.rotateBitmap
import java.io.File

class ProcessActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityProcessBinding
    private val factory = ViewModelFactory.getInstance(this)
    private val viewModel: ProcessViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProcessBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        renderImage()
    }

    private fun renderImage(){
        val scanImage = intent.getParcelableExtra<ScanImage>(CAMERA_X_RESULT)
        if(scanImage != null) {
            val image = BitmapFactory.decodeFile(scanImage.image.path)
            val fixedImage = rotateBitmap(image,true)
            viewBinding.ivImage.setImageBitmap(fixedImage)
        }
    }

    companion object {
        const val CAMERA_X_RESULT = "cameraXResult"
    }
}