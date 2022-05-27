package com.yashagozwan.inacure.model

data class SignUpResponse(
    val error: Boolean,
    val message: String? = null,
    val data: SignUpData
)

data class SignUpData(
    val _id: String,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: String
)
