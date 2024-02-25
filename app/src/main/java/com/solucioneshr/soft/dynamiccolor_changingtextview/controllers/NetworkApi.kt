package com.solucioneshr.soft.qrappreport.controllers

import com.solucioneshr.soft.dynamiccolor_changingtextview.models.DynamicColors
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {

    @GET(".")
    fun getPostById(): Call<DynamicColors>
}