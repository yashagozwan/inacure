package com.yashagozwan.inacure.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yashagozwan.inacure.databinding.FragmentProfileBinding
import com.yashagozwan.inacure.model.User

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val bundle = arguments
        val user = bundle?.getParcelable<User>("USER") as User
        binding.tvName.text = user.name
        binding.tvEmail.text = user.email

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}