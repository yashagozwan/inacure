package com.yashagozwan.inacure.ui.main.history

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yashagozwan.inacure.databinding.ItemHistoryBinding
import com.yashagozwan.inacure.model.HistoryData
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HistoryAdapter(private val histories: List<HistoryData>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = histories[position]
        holder.bind(history)
    }

    override fun getItemCount(): Int = histories.size

    inner class ViewHolder(val viewBinding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(historyData: HistoryData) {
            viewBinding.apply {
                Glide.with(viewBinding.root).load(historyData.imageUrl).into(ivImage)
                tvTitle.text = historyData.articleName
                tvRate.text = StringBuilder("Predict Rate : ").append(historyData.predictRate)

                val date = Date(historyData.createdAt.toLong())
                val format = SimpleDateFormat("MMM dd, yyyy - HH:mm:ss")
                val dateTimeCreated = format.format(date)
                tvDateTime.text = dateTimeCreated

            }
        }
    }
}