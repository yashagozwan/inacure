package com.yashagozwan.inacure.model

data class ArticleResponse(
    val error: Boolean,
    val message: String?,
    val data: List<ArticleData>
)

data class ArticleData(
    val _id: String,
    val imageUrl: String,
    val name: String,
    val latinName: String,
    val family: String,
    val description: String,
    val ingredient: String,
    val efficacy: List<String>,
    val onlineShop: String
)