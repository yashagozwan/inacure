package com.yashagozwan.inacure.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization", "bearer $token")
            .build()

        return chain.proceed(request)
    }
}