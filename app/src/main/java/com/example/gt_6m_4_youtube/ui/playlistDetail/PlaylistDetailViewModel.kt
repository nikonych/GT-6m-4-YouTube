package com.example.gt_6m_4_youtube.ui.playlistDetail

import androidx.lifecycle.LiveData
import com.example.gt_6m_4_youtube.App
import com.example.gt_6m_4_youtube.core.network.result.Resource
import com.example.gt_6m_4_youtube.core.ui.BaseViewModel
import com.example.gt_6m_4_youtube.data.remote.model.PlaylistDetail
import com.example.gt_6m_4_youtube.data.remote.model.Video

class PlaylistDetailViewModel: BaseViewModel() {

    fun getPlaylistItems(playlistId: String, pageToken: String?): LiveData<Resource<PlaylistDetail>> {
        return App.repository.getPlaylistItems(playlistId = playlistId, pageToken)
    }

    fun getVideo(id: String): LiveData<Resource<Video>> {
        return App.repository.getVideo(id)
    }
}