package com.solucioneshr.soft.qrappreport.controllers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientApi {
    private const val BASE_URL = "https://x-colors.yurace.pro/api/random/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object NetworkClient{
    val apiService: NetworkApi by lazy {
        ClientApi.retrofit.create(NetworkApi::class.java)
    }
}