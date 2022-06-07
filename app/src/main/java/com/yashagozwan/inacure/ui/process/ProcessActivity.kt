package com.yashagozwan.inacure.ui.process

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.databinding.ActivityProcessBinding
import com.yashagozwan.inacure.model.MyImage
import com.yashagozwan.inacure.model.PredictData
import com.yashagozwan.inacure.model.ScanImage
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.ui.appropriate.AppropriateActivity
import com.yashagozwan.inacure.ui.failed.FailedActivity
import com.yashagozwan.inacure.utils.Constants
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
    private var myImage: MyImage? = null

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
            myImage = MyImage(scanImageCameraX.image, Constants.CAMERA)
            val image = BitmapFactory.decodeFile(scanImageCameraX.image.path)
            val fixImage = rotateBitmap(image, true)
            viewBinding.ivImageScan.setImageBitmap(fixImage)
        }

        if (scanImageGallery != null) {
            myImage = MyImage(scanImageGallery.image, Constants.GALLERY)
            val image = BitmapFactory.decodeFile(scanImageGallery.image.path)
            viewBinding.ivImageScan.setImageBitmap(image)
        }
    }

    private fun uploadImageFile() {
        if (myImage != null) {
            val file = reduceFileImage(myImage?.image as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart =
                MultipartBody.Part.createFormData("file", file.name, requestImageFile)

            viewModel.getToken().observe(this) { token ->
                viewModel.predict(token, imageMultipart).observe(this) {
                    when (it) {
                        is Result.Loading -> {

                        }
                        is Result.Success -> {
                            val intent =
                                Intent(this@ProcessActivity, AppropriateActivity::class.java)
                            val response = it.data
                            val data = response.data
                            intent.putExtra(AppropriateActivity.DATA, data)
                            intent.putExtra(AppropriateActivity.IMAGE_RESULT, myImage)
                            startActivity(intent)
                            finish()
                        }
                        is Result.Error -> {
                            Log.d("ProcessActivity", it.error)
                            val intent = Intent(this@ProcessActivity, FailedActivity::class.java)
                            intent.putExtra(FailedActivity.FROM_ACTIVITY, myImage?.from)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val CAMERA_X_RESULT = "cameraXResult"
        const val GALLERY_RESULT = "galleryResult"
        const val TITLE = "title"
    }
}