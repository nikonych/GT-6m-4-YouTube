package com.example.gt_6m_4_youtube.data.remote

import com.example.gt_6m_4_youtube.data.remote.model.Playlist
import com.example.gt_6m_4_youtube.data.remote.model.PlaylistDetail
import com.example.gt_6m_4_youtube.data.remote.model.Video
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylist(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("pageToken") pageToken: String? = null
    ): Call<Playlist>


    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("key") apiKey: String,
        @Query("playlistId") playlistId: String,
        @Query("part") part: String,
        @Query("pageToken") pageToken: String? = null
    ): Call<PlaylistDetail>

    @GET("videos")
    fun getVideo(
        @Query("key") apiKey: String,
        @Query("id") id: String,
        @Query("part") part: String,
    ): Call<Video>
}