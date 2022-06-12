package com.yashagozwan.inacure.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PredictResponse(
    val error: Boolean,
    val message: String? = null,
    val data: PredictData
)

@Parcelize
data class PredictData(
    val _id: String,
    val imageUrl: String,
    val name: String,
    val latinName: String,
    val family: String,
    val description: String,
    val ingredient: String,
    val efficacy: List<String>,
    val onlineShop: String
): Parcelable