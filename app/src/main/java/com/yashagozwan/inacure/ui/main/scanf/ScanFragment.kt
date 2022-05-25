package com.yashagozwan.inacure.ui.main.scanf

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.databinding.FragmentScanBinding
import com.yashagozwan.inacure.ui.scan.ScanActivity
import com.yashagozwan.inacure.utils.Util.rotateBitmap
import java.io.File


class ScanFragment : Fragment() {
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
        renderImage()

        viewBinding.btnTakeAgain.setOnClickListener {
            val intent = Intent(activity, ScanActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private fun renderImage() {
        val bundle = arguments
        val image = bundle?.getSerializable("image") as File
        val result = rotateBitmap(BitmapFactory.decodeFile(image.path), true)
        viewBinding.ivImage.setImageBitmap(result)
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}