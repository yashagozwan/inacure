package com.yashagozwan.inacure.ui.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.yashagozwan.inacure.R
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.databinding.FragmentProfileBinding
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.ui.signin.SignInActivity
import com.yashagozwan.inacure.utils.myItemMenu

class ProfileFragment : Fragment(), View.OnClickListener {
    private var _viewBinding: FragmentProfileBinding? = null
    private val viewBinding get() = _viewBinding!!
    private lateinit var factory: ViewModelFactory
    private val viewModel: ProfileViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkIfFragmentAttached {
            factory = ViewModelFactory.getInstance(this)
        }

        renderProfile()
        addButtonListener()
        renderListMenu()
    }

    private fun renderListMenu() {
        val rvMenu = viewBinding.rvMenu
        rvMenu.layoutManager = LinearLayoutManager(activity)
        rvMenu.adapter = ItemMenuAdapter(myItemMenu())
    }

    private fun addButtonListener() {
        viewBinding.cvSignOut.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.cv_sign_out -> showAlert()
        }
    }

    private fun renderProfile() {
        viewModel.getToken().observe(viewLifecycleOwner) { token ->
            viewModel.getUserProfile(token).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {
                        viewBinding.cvLoading.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        viewBinding.cvLoading.visibility = View.GONE
                        val response = result.data
                        val user = response.data
                        Glide.with(this).load(user.imageUrl).into(viewBinding.civProfile)
                        viewBinding.tvName.text = user.name
                        viewBinding.tvEmail.text = user.email
                    }
                    is Result.Error -> {
                        viewBinding.cvLoading.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun showAlert() {
        checkIfFragmentAttached {
            val alertBuilder = AlertDialog.Builder(this)
            val alert = alertBuilder
                .setTitle("Are your sure?")
                .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
                .setPositiveButton("Yes") { _, _ ->
                    val intent = Intent(activity, SignInActivity::class.java)
                    activity?.startActivity(intent)
                    activity?.finish()
                    viewModel.deleteToken()
                }
            alert.show()
        }
    }

    private fun checkIfFragmentAttached(operation: Context.() -> Unit) {
        if (isAdded && context != null) {
            operation(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}