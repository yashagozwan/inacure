package com.yashagozwan.inacure.ui.main.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yashagozwan.inacure.data.network.Result
import com.yashagozwan.inacure.databinding.FragmentHistoryBinding
import com.yashagozwan.inacure.ui.ViewModelFactory

class HistoryFragment : Fragment() {
    private var _viewBinding: FragmentHistoryBinding? = null
    private val viewBinding get() = _viewBinding!!
    private lateinit var factory: ViewModelFactory
    private val viewModel: HistoryViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentHistoryBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkIfFragmentAttached {
            factory = ViewModelFactory.getInstance(this)
        }

        renderHistoryList()
    }

    private fun renderHistoryList() {
        checkIfFragmentAttached {
            viewModel.getToken().observe(viewLifecycleOwner) { token ->
                viewModel.getHistory(token).observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            val response = result.data
                            val data = response.data

                            val rvHistory = viewBinding.rvHistory
                            rvHistory.layoutManager = LinearLayoutManager(this)
                            rvHistory.adapter = HistoryAdapter(data)
                        }
                        is Result.Error -> {}
                    }
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