package com.example.naluri.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private var retrofit: Retrofit? = null


    //init retrofit for our http request
    fun getClient(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.pi.delivery")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }
}