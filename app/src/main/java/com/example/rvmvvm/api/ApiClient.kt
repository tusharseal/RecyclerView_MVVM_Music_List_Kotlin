package com.example.rvmvvm.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://adminmindfulnessgreece.eu"


class ApiClient {

    companion object {
        val retrofitBuilder: ApiInterface = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

    }
}