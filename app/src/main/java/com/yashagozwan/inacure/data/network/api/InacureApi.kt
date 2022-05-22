package com.yashagozwan.inacure.data.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InacureApi {
    private const val BASE_URL = "https://naufalpilotproject.de.r.appspot.com"

    fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}