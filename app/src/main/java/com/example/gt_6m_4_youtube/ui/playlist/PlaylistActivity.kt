package com.example.gt_6m_4_youtube.ui.playlist


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.gt_6m_4_youtube.base.BaseActivity
import com.example.gt_6m_4_youtube.databinding.ActivityPlaylistBinding
import com.example.gt_6m_4_youtube.model.Item
import com.example.gt_6m_4_youtube.ui.playlistDetail.PlaylistDetailActivity
import com.example.gt_6m_4_youtube.utils.ConnectionLiveData

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }


    override fun initViewModel() {
        super.initViewModel()
            viewModel.playlist().observe(this) {
                val adapter = PlaylistAdapter(it.items as MutableList<Item>, this::itemClick)
                binding.recyclerview.adapter = adapter
            }
    }

    private fun itemClick(item: Item){
        val intent = Intent(this, PlaylistDetailActivity::class.java)
        intent.putExtra(OPEN_PLAYLIST_DETAIL, item)
        startActivity(intent)
    }

    override fun isConnection() {
        super.isConnection()
        connectionLiveData.observe(this) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                updateUI(it)
            }
        }
    }

    private fun updateUI(it: Boolean) {
        binding.recyclerview.isVisible = it
        binding.layoutInternet.root.isVisible = !it
    }


    override fun inflateViewBinding(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    companion object{
        const val OPEN_PLAYLIST_DETAIL = "OPEN_PLAYLIST_DETAIL"
    }

}