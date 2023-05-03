package com.example.gt_6m_4_youtube.remote

import com.example.gt_6m_4_youtube.model.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylist(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String
    ) : Call<Playlist>

}