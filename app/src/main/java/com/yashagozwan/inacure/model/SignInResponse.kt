package com.yashagozwan.inacure.model

data class SignInResponse(
    val error: Boolean,
    val message: String? = null,
    val data: SignInResponseData
)

data class SignInResponseData(
    val token: String,
)