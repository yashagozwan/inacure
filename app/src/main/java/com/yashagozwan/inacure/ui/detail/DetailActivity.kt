package com.yashagozwan.inacure.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.yashagozwan.inacure.databinding.ActivityDetailBinding
import com.yashagozwan.inacure.model.PredictData

class DetailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        hideAppBar()
        renderArticleDetail()
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun renderArticleDetail() {
        val data = intent.getParcelableExtra<PredictData>(PREDICTION)

        if (data != null) {
            viewBinding.apply {
                Glide.with(this@DetailActivity).load(data.imageUrl).into(ivImageArticle)
                val dot = "• "
                val lineBreakString = "\n"
                tvTitleArticle.text =
                    StringBuilder("Apa itu buah ").append(data.name).append("?")
                        .append(lineBreakString)
                        .append("maanfaat apa yang diberiakan?")
                tvDescriptionLong.text = data.description
                tvSubIngredient.text = StringBuilder(dot).append(data.ingredient)
                tvSubKhasiat.text =
                    StringBuilder(dot).append(data.efficacy[0]).append(lineBreakString)
                        .append(dot).append(data.efficacy[1]).append(lineBreakString)
                        .append(dot).append(data.efficacy[2]).append(lineBreakString)
                        .append(dot).append(data.efficacy[3]).append(lineBreakString)

                btnGotoStore.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(data.onlineShop)
                    startActivity(intent)
                }
            }
        }
    }

    companion object {
        const val PREDICTION = "PREDICTION"
    }
}