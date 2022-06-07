package com.yashagozwan.inacure.data.network.api

import com.yashagozwan.inacure.model.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface InacureService {

    @POST("/api/v1/auth/signin")
    suspend fun signIn(@Body signIn: SignIn): SignInResponse

    @POST("/api/v1/auth/signup")
    suspend fun signUp(@Body signup: SignUp): SignUpResponse

    @GET("/api/v1/users/profile")
    suspend fun getUserProfile(): CurrentUserResponse

    @POST("/api/v1/predict")
    @Multipart
    suspend fun predict(
        @Part file: MultipartBody.Part
    ): PredictResponse
}