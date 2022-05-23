package com.yashagozwan.inacure.data.network.api

import com.yashagozwan.inacure.model.SignIn
import com.yashagozwan.inacure.model.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface InacureService {

    @POST("/api/v1/auth/signin")
    suspend fun signIn(@Body signIn: SignIn): SignInResponse
}