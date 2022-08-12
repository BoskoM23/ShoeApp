package com.bendingbytes.shoes.network

import retrofit2.http.GET

interface ShoeService {
    @GET("shoes")
    suspend fun getShoes(): List<ShoeNetworkEntity>
}