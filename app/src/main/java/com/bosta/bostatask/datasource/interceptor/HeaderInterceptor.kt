package com.bosta.bostatask.datasource.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request.Builder = chain.request().newBuilder().apply {
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/x-www-form-urlencoded")
        }
        return chain.proceed(request.build())
    }
}