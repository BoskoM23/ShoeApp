package com.bendingbytes.shoes.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ShoeClient {

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addNetworkInterceptor(getHttpLoggerInterceptor())
            .addInterceptor(AuthorizationInterceptor())
        return okHttpClientBuilder.build()
    }

    private fun getHttpLoggerInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://shoes-collections.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()
    val service: ShoeService = retrofit.create(ShoeService::class.java)
}


