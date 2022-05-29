package com.yashagozwan.inacure.ui.process

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.databinding.ActivityProcessBinding
import com.yashagozwan.inacure.model.ScanImage
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.utils.Util.reduceFileImage
import com.yashagozwan.inacure.utils.Util.rotateBitmap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProcessActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityProcessBinding
    private val factory = ViewModelFactory.getInstance(this)
    private val viewModel: ProcessViewModel by viewModels { factory }
    private var imageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProcessBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        hideAppBar()
        renderTitle()
        renderImage()
        uploadImageFile()
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
            imageFile = scanImageCameraX.image
            val image = BitmapFactory.decodeFile(scanImageCameraX.image.path)
            val fixImage = rotateBitmap(image, true)
            viewBinding.ivImageScan.setImageBitmap(fixImage)
        }

        if (scanImageGallery != null) {
            imageFile = scanImageGallery.image
            val image = BitmapFactory.decodeFile(scanImageGallery.image.path)
            viewBinding.ivImageScan.setImageBitmap(image)
        }
    }

    private fun uploadImageFile() {
        if (imageFile != null) {
            val file = reduceFileImage(imageFile as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart =
                MultipartBody.Part.createFormData("file", file.name, requestImageFile)

            viewModel.predict(imageMultipart).observe(this) {
                when (it) {
                    is Result.Loading -> {
                        makeToast("Loading")
                    }
                    is Result.Success -> {
                        makeToast("Success")
                    }
                    is Result.Error -> {
                        makeToast(it.error)
                    }
                }
            }
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val CAMERA_X_RESULT = "cameraXResult"
        const val GALLERY_RESULT = "galleryResult"
        const val TITLE = "title"
    }
}