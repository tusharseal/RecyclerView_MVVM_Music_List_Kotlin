package com.example.rvmvvm.repository

import com.example.rvmvvm.api.ApiInterface

class MainRepository constructor(private val apiInterface: ApiInterface) {
    fun getAllMusic() = apiInterface.getData("379283", "All")
}