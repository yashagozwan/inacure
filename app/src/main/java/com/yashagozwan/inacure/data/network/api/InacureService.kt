package com.yashagozwan.inacure.data.network.api

import com.yashagozwan.inacure.model.*
import com.yashagozwan.inacure.utils.Endpoint
import okhttp3.MultipartBody
import retrofit2.http.*

interface InacureService {

    @POST(Endpoint.SignIn)
    suspend fun signIn(@Body signIn: SignIn): SignInResponse

    @POST(Endpoint.SignUp)
    suspend fun signUp(@Body signup: SignUp): SignUpResponse

    @GET(Endpoint.Profile)
    suspend fun getUserProfile(): CurrentUserResponse

    @GET(Endpoint.History)
    suspend fun getHistory(): HistoryResponse

    @POST(Endpoint.Predict)
    @Multipart
    suspend fun predict(
        @Part file: MultipartBody.Part
    ): PredictResponse

    @GET(Endpoint.Articles)
    suspend fun getArticles(): ArticleResponse

}