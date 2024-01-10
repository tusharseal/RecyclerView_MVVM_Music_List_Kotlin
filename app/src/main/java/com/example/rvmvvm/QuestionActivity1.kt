package com.example.rvmvvm


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rvmvvm.adapter.MusicAdapter
import com.example.rvmvvm.api.ApiInterface
import com.example.rvmvvm.model.MusicModel
import com.example.rvmvvm.repository.MainRepository
import com.example.rvmvvm.viewmodel.MainViewModel
import com.example.rvmvvm.viewmodelfactory.MyViewModelFactory


class QuestionActivity1 : AppCompatActivity() {

    private lateinit var musicRv: RecyclerView
    lateinit var viewModel: MainViewModel
    private val apiInterface = ApiInterface.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(apiInterface))).get(
            MainViewModel::class.java)

        musicRv = findViewById(R.id.superHero_rv)
        getMyData()
    }

    private fun getMyData() {
       /* val retrofitData = retrofitBuilder.getData("379283", "All")

        retrofitData.enqueue(object : Callback<MusicModel> {
            override fun onResponse(
                call: Call<MusicModel>,
                response: Response<MusicModel>
            ) {
                val responseBody = response.body()!!
                initSuperHeroRv(responseBody)
            }

            override fun onFailure(call: Call<MusicModel>, t: Throwable) {
                Log.d("Main Activity", "onFailure" + t.message)
            }
        })*/

        viewModel.musicList.observe(this, Observer{
            initSuperHeroRv(it)
        })
        viewModel.getAllMusic()
    }

    private fun initSuperHeroRv(responseBody: MusicModel) {
        val adapter = MusicAdapter(object : MusicAdapter.OnClickInterface {

            override fun onItemClicked(doc: MusicModel.Data) {
                /* val musicDeFrg = MusicDetailsFragment.newInstance()

                 val arguments = Bundle()
                 arguments.putParcelable("MUSIC_ITEM", doc)


                 musicDeFrg.arguments = arguments*/
                val mIntent = Intent(this@QuestionActivity1, MusicActivity::class.java)
                val mBundle = Bundle()
                mBundle.putParcelable("MUSIC_ITEM", doc)
                mIntent.putExtras(mBundle)
                startActivity(mIntent)
            }


        })
        musicRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        musicRv.adapter = adapter
        adapter.submitList(responseBody.data)
    }
}