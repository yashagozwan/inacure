package com.yashagozwan.inacure.ui.main.scanf

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.FragmentScanBinding
import com.yashagozwan.inacure.ui.scan.ScanActivity
import com.yashagozwan.inacure.ui.upload.UploadActivity

class ScanFragment : Fragment(), View.OnClickListener {
    private var _viewBinding: FragmentScanBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentScanBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addButtonListener()
    }

    private fun addButtonListener() {
        viewBinding.btnScan.setOnClickListener(this)
        viewBinding.btnUpload.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent: Intent
        when (view.id) {
            R.id.btn_scan -> {
                intent = Intent(activity, ScanActivity::class.java)
                activity?.startActivity(intent)
            }
            R.id.btn_upload -> {
                intent = Intent(activity, UploadActivity::class.java)
                activity?.startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}