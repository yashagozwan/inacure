package com.yashagozwan.inacure.data.network.api

import com.yashagozwan.inacure.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InacureService {

    @POST("/api/v1/auth/signin")
    suspend fun signIn(@Body signIn: SignIn): SignInResponse

    @POST("api/v1/auth/signup")
    suspend fun signUp(@Body signup: SignUp): SignUpResponse

    @GET("api/v1/users/profile")
    suspend fun getUserProfile(): CurrentUserResponse
}