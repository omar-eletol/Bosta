package com.bosta.bostatask.datasource.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class StatusCodeInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response: Response = chain.proceed(originalRequest)
        return response
    }


}