package com.example.naluri.util

import com.example.naluri.model.PiModel
import retrofit2.Call
import retrofit2.http.GET

//api service interface
interface PiService {
    @GET("/v1/pi?start=0&numberOfDigits=100")
    fun doGetListResources(): Call<PiModel>
}