package com.example.gt_6m_4_youtube.ui.playlist


import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gt_6m_4_youtube.core.network.result.Status
import com.example.gt_6m_4_youtube.core.ui.BaseActivity
import com.example.gt_6m_4_youtube.databinding.ActivityPlaylistBinding
import com.example.gt_6m_4_youtube.data.remote.model.Item
import com.example.gt_6m_4_youtube.ui.playlistDetail.PlaylistDetailActivity

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }
    private var pageToken: String? = null
    private lateinit var adapter: PlaylistAdapter
    private var isEnd = false


    override fun initViewModel() {
        super.initViewModel()
            viewModel.getPlaylist(null).observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        adapter = PlaylistAdapter(it.data?.items as MutableList<Item>, this::itemClick)
                        binding.recyclerview.adapter = adapter
                        pageToken = it.data.nextPageToken
                        if (pageToken?.isNotEmpty() == true){
                            addPlaylists()
                        }
                        binding.progressBar.isVisible = false
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        binding.progressBar.isVisible = false
                    }
                    Status.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                }
            }
    }

    override fun initViews() {
        super.initViews()

    }

    override fun initListener() {
        super.initListener()
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE){
                    if(!isEnd && pageToken?.isNotEmpty() == true)
                        addPlaylists()
                }
            }
        })
    }

    private fun itemClick(item: Item){
        Intent(this, PlaylistDetailActivity::class.java).apply {
            putExtra(OPEN_PLAYLIST_DETAIL, item)
            startActivity(this)
        }
    }

    fun addPlaylists(){
        viewModel.getPlaylist(pageToken).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("gg", it.data?.nextPageToken.toString())
                    if(pageToken.equals(it.data?.nextPageToken)|| it.data?.nextPageToken?.isEmpty() == true){
                        isEnd = true
                    } else {
                        it.data?.items?.let { it1 -> adapter.addItems(it1) }
                        pageToken = it.data?.nextPageToken
                        isEnd = false
                    }
                    binding.progressBar.isVisible = false
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                }
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
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
        initViewModel()
    }

    override fun inflateViewBinding(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    companion object{
        const val OPEN_PLAYLIST_DETAIL = "OPEN_PLAYLIST_DETAIL"
    }

}