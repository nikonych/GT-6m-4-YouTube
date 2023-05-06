package com.example.gt_6m_4_youtube.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gt_6m_4_youtube.BuildConfig
import com.example.gt_6m_4_youtube.core.network.RetrofitClient
import com.example.gt_6m_4_youtube.core.network.result.Resource
import com.example.gt_6m_4_youtube.data.remote.ApiService
import com.example.gt_6m_4_youtube.data.remote.model.Playlist
import com.example.gt_6m_4_youtube.data.remote.model.PlaylistDetail
import com.example.gt_6m_4_youtube.data.remote.model.Video
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService by lazy { RetrofitClient.create() }


    fun getPlaylist(pageToken: String?): LiveData<Resource<Playlist>> {
        val data = MutableLiveData<Resource<Playlist>>()
        data.value = Resource.loading()
        apiService.getPlaylist(
            BuildConfig.API_KEY,
            "snippet,contentDetails",
            "UCdmauIL-k-djcct-yMrf82A",
            pageToken = pageToken
        )
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    Log.d("gg", t.message.toString())
                    data.value = Resource.error(t.message.toString(), null, null)
                }

            })
        return data
    }

    fun getPlaylistItems(playlistId: String, pageToken: String?): LiveData<Resource<PlaylistDetail>>
    {
        val data = MutableLiveData<Resource<PlaylistDetail>>()
        data.value = Resource.loading()
        apiService.getPlaylistItems(
            BuildConfig.API_KEY,
            part="snippet,contentDetails,id",
            playlistId = playlistId,
            pageToken = pageToken
        ).enqueue(object : Callback<PlaylistDetail>{
            override fun onResponse(
                call: Call<PlaylistDetail>,
                response: Response<PlaylistDetail>
            ) {
                if(response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<PlaylistDetail>, t: Throwable) {
                Log.d("gg", t.message.toString())
                data.value = Resource.error(t.message.toString(), null, null)
            }

        })
        return data
    }

    fun getVideo(id: String): LiveData<Resource<Video>> {
        val data = MutableLiveData<Resource<Video>>()
        data.value = Resource.loading()
        apiService.getVideo(BuildConfig.API_KEY, id=id, part = "contentDetails")
            .enqueue(object : Callback<Video>{
            override fun onResponse(call: Call<Video>, response: Response<Video>) {
                if(response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<Video>, t: Throwable) {
                Log.d("gg", t.message.toString())
                data.value = Resource.error(t.message.toString(), null, null)
            }
        })
        return data
    }
}