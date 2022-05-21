package com.yashagozwan.inacure.ui.scan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.ActivityScanBinding
import com.yashagozwan.inacure.ui.UploadActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ScanActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewBinding: ActivityScanBinding
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_PERMISSION_CODE)
        }

        cameraExecutor = Executors.newSingleThreadExecutor()

        customStatusBar()
        hideAppBar()
        addButtonListener()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun addButtonListener() {
        viewBinding.btnBack.setOnClickListener(this)
        viewBinding.btnUpload.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_back -> finish()
            R.id.btn_upload -> Intent(this, UploadActivity::class.java).also { startActivity(it) }
        }
    }

    private fun startCamera() {

    }

    private fun allPermissionsGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    @Suppress("DEPRECATION")
    private fun customStatusBar() {
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val REQUEST_PERMISSION_CODE = 10
        private val REQUIRED_PERMISSIONS = mutableListOf(Manifest.permission.CAMERA).apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }
}