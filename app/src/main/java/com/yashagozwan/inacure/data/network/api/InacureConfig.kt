package com.yashagozwan.inacure.data.network.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InacureConfig {
    private const val BASE_URL = "https://inacure.as.r.appspot.com"

    fun inacureService(token: String = ""): InacureService {
        val interceptor = Interceptor {
            var request = it.request()
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            it.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(InacureService::class.java)
    }
}