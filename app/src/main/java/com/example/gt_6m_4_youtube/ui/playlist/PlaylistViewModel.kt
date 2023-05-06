package com.example.gt_6m_4_youtube.ui.playlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gt_6m_4_youtube.App
import com.example.gt_6m_4_youtube.BuildConfig
import com.example.gt_6m_4_youtube.core.ui.BaseViewModel
import com.example.gt_6m_4_youtube.data.remote.model.Playlist
import com.example.gt_6m_4_youtube.data.remote.ApiService
import com.example.gt_6m_4_youtube.core.network.RetrofitClient
import com.example.gt_6m_4_youtube.core.network.result.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {


    fun getPlaylist(pageToken: String?): LiveData<Resource<Playlist>> {
        return App.repository.getPlaylist(pageToken)
    }

}