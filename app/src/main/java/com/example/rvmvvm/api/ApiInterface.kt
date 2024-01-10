package com.example.rvmvvm.api

import com.example.rvmvvm.model.MusicModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @POST("/api/getChooseMeditation")
    fun getData(
        @Query("customerId") customerId: String,
        @Query("key") key: String
    ): Call<MusicModel>

    companion object {
        var apiInterface : ApiInterface? = null

        fun getInstance() : ApiInterface {
            if (apiInterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://adminmindfulnessgreece.eu")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiInterface = retrofit.create(ApiInterface::class.java)
            }
            return apiInterface!!
        }
    }


}
