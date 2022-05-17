package com.yashagozwan.inacure.ui.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yashagozwan.inacure.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideAppBar()
        addListenerSearch()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun addListenerSearch() {
        val editSearch = binding.editSearch
        editSearch.setOnEditorActionListener { _, actionId, _ ->
            val searchValue = editSearch.text.toString().trim()
            if (actionId == EditorInfo.IME_ACTION_DONE) showToast(searchValue)
            true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}