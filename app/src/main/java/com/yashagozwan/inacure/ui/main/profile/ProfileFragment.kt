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

class ProfileFragment : Fragment() {
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

        val rvMenu = viewBinding.rvMenu
        rvMenu.layoutManager = LinearLayoutManager(activity)
        rvMenu.adapter = ItemMenuAdapter(myItemMenu())

        viewBinding.cvSignOut.setOnClickListener { showAlert() }
    }

    private fun renderProfile() {
        val bundle = arguments
        val user = bundle?.getParcelable<User>("USER")

        if (user != null) {
            viewBinding.tvName.text = user.name
            viewBinding.tvEmail.text = user.email
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