package com.yashagozwan.inacure.model

data class CurrentUserResponse(
    val error: Boolean,
    val message: String?,
    val data: CurrentUserData
)

data class CurrentUserData(
    val _id: String,
    val imageUrl: String,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: String,
)