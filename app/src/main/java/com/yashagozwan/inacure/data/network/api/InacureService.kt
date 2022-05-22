package com.yashagozwan.inacure.data.network.api

import retrofit2.http.POST

interface InacureService {

    @POST("/api/v1/users")
    suspend fun signIn()
}