package com.yashagozwan.inacure.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yashagozwan.inacure.databinding.ItemArticleBinding
import com.yashagozwan.inacure.model.ArticleData

class HomeAdapter(private val articles: List<ArticleData>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articleData = articles[position]
        holder.bind(articleData)
    }

    override fun getItemCount(): Int = articles.size

    inner class ViewHolder(val viewBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(articleData: ArticleData) {
            viewBinding.apply {
                Glide.with(viewBinding.root).load(articleData.imageUrl).into(ivImage)
                tvTitle.text = articleData.name
                tvDescription.text = articleData.description
            }
        }
    }
}