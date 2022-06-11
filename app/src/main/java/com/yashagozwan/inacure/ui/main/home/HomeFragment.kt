package com.yashagozwan.inacure.ui.main.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.databinding.FragmentHomeBinding
import com.yashagozwan.inacure.ui.ViewModelFactory

class HomeFragment : Fragment() {
    private var _viewBinding: FragmentHomeBinding? = null
    private val viewBinding get() = _viewBinding!!
    lateinit var factory: ViewModelFactory
    private val viewModel: HomeViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkIfFragmentAttached {
            factory = ViewModelFactory.getInstance(this)
        }
        renderArticlesList()
    }

    private fun renderArticlesList() {
        viewModel.getToken().observe(viewLifecycleOwner) { token ->

            viewModel.getUserProfile(token).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val response = result.data
                        val data = response.data

                        Glide.with(viewBinding.root).load(data.imageUrl)
                            .into(viewBinding.civProfile)
                    }
                    is Result.Error -> {}
                }
            }

            viewModel.getArticles(token).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val response = result.data
                        val data = response.data
                        val rvArticles = viewBinding.rvArticles
                        rvArticles.layoutManager = LinearLayoutManager(requireContext())
                        rvArticles.adapter = HomeAdapter(data)

                    }
                    is Result.Error -> {}
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    private fun checkIfFragmentAttached(operations: Context.() -> Unit) {
        if (isAdded && context != null) {
            operations(requireContext())
        }
    }
}
