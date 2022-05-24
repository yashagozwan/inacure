package com.yashagozwan.inacure.ui.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yashagozwan.inacure.databinding.FragmentProfileBinding
import com.yashagozwan.inacure.model.User
import com.yashagozwan.inacure.ui.ViewModelFactory
import com.yashagozwan.inacure.ui.signin.SignInActivity
import com.yashagozwan.inacure.utils.myItemMenu

class ProfileFragment(context: Context) : Fragment() {
    private var _viewBinding: FragmentProfileBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val myContext = context
    private val factory = ViewModelFactory.getInstance(myContext)
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
        val bundle = arguments
        val user = bundle?.getParcelable<User>("USER") as User
        viewBinding.tvName.text = user.name
        viewBinding.tvEmail.text = user.email

        val rvMenu = viewBinding.rvMenu
        rvMenu.layoutManager = LinearLayoutManager(activity)
        rvMenu.adapter = ItemMenuAdapter(myItemMenu())

        viewBinding.cvSignOut.setOnClickListener {
            showAlert()
        }
    }

    private fun showAlert() {
        val alertBuilder = AlertDialog.Builder(myContext)
        val alert = alertBuilder
            .setTitle("Are your sure?")
            .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
            .setPositiveButton("Yes") { _, _ ->
                activity?.finish()
                viewModel.deleteToken()
            }

        alert.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}