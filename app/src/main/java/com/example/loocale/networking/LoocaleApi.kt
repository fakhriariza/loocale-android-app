package com.example.loocale.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoocaleApi {
    val baseUrl = "https://api.loocale.id/api/loocale/"
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> getInstance(service: Class<T>): T{
        return retrofit.create(service)
    }
}
