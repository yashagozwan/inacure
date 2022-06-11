package com.yashagozwan.inacure.model

data class HistoryResponse(
    val error: Boolean,
    val message: String?,
    val data: List<HistoryData>
)

data class HistoryData(
    val userId: String,
    val email: String,
    val imageUrl: String,
    val articleName: String,
    val predictRate: String,
    val createdAt: String,
)