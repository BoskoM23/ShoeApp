package com.bendingbytes.shoes.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", "7d647b9184msh5b3e1f1fbae0e80p1591a6jsnb869ef4a2d1c")
            .addHeader("X-RapidAPI-Host", "shoes-collections.p.rapidapi.com")
            .method(chain.request().method(), chain.request().body())
            .build()
        return chain.proceed(request)
    }
}
