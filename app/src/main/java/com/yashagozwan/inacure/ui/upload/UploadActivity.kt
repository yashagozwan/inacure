package com.yashagozwan.inacure.ui.upload

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivityUploadBinding
import com.yashagozwan.inacure.model.ScanImage
import com.yashagozwan.inacure.ui.process.ProcessActivity
import com.yashagozwan.inacure.utils.Util.uriToFile
import java.io.File

class UploadActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewBinding: ActivityUploadBinding
    private var getFile: File? = null

    private val launcherIntentGalley =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val selectedImage = it.data?.data as Uri
                val myFile = uriToFile(selectedImage, this)
                getFile = myFile
                viewBinding.ivImageUpload.setImageURI(selectedImage)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        hideAppBar()
        addButtonListener()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun addButtonListener() {
        viewBinding.btnBack.setOnClickListener(this)
        viewBinding.btnChooseFile.setOnClickListener(this)
        viewBinding.btnUpload.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_back -> finish()
            R.id.btn_choose_file -> startGallery()
            R.id.btn_upload -> {
                if (getFile != null) {
                    uploadImage()
                } else {
                    Toast.makeText(this, "Please choose picture", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun uploadImage() {
        val intent = Intent(this, ProcessActivity::class.java)
        val scanImage = ScanImage(getFile as File)
        intent.putExtra(ProcessActivity.GALLERY_RESULT, scanImage)
        intent.putExtra(ProcessActivity.TITLE, "Searching for the image you uploaded")
        startActivity(intent)
        finish()
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Select Image")
        launcherIntentGalley.launch(chooser)
    }
}