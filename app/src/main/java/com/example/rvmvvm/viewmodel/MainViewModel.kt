package com.example.rvmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rvmvvm.model.MusicModel
import com.example.rvmvvm.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val musicList = MutableLiveData<MusicModel>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMusic() {
        val response = repository.getAllMusic()
        response.enqueue(object : Callback<MusicModel> {
            override fun onResponse(call: Call<MusicModel>, response: Response<MusicModel>) {
                musicList.postValue(response.body())
            }
            override fun onFailure(call: Call<MusicModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}