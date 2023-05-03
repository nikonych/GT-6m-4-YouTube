package com.example.gt_6m_4_youtube.ui.playlist

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gt_6m_4_youtube.BuildConfig
import com.example.gt_6m_4_youtube.base.BaseViewModel
import com.example.gt_6m_4_youtube.model.Playlist
import com.example.gt_6m_4_youtube.remote.ApiService
import com.example.gt_6m_4_youtube.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy { RetrofitClient.create() }

    fun playlist(): LiveData<Playlist> {
        return getPlaylist()
    }

    private fun getPlaylist(): LiveData<Playlist> {
        val data = MutableLiveData<Playlist>()

        apiService.getPlaylist(BuildConfig.API_KEY, "snippet,contentDetails", "UCWOA1ZGywLbqmigxE4Qlvuw")
            .enqueue(object : Callback<Playlist>{
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful){
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    Log.d("gg", t.message.toString())
                }

            })
        return data
    }
}