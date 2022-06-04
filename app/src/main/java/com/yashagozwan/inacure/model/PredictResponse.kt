package com.yashagozwan.inacure.model

data class PredictResponse(
    val error: Boolean,
    val message: String? = null,
    val data: PredictData
)

data class PredictData(
    val _id: String,
    val name: String,
    val latinName: String,
    val family: String,
    val imageUrl: String
)