package com.example.gt_6m_4_youtube.ui.playlistDetail

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gt_6m_4_youtube.core.network.result.Resource
import com.example.gt_6m_4_youtube.core.network.result.Status
import com.example.gt_6m_4_youtube.core.ui.BaseActivity
import com.example.gt_6m_4_youtube.databinding.ActivityPlaylistDetailBinding
import com.example.gt_6m_4_youtube.data.remote.model.Item
import com.example.gt_6m_4_youtube.data.remote.model.ItemDetail
import com.example.gt_6m_4_youtube.data.remote.model.Playlist
import com.example.gt_6m_4_youtube.data.remote.model.Video
import com.example.gt_6m_4_youtube.ui.playlist.PlaylistActivity.Companion.OPEN_PLAYLIST_DETAIL
import com.example.gt_6m_4_youtube.ui.playlist.PlaylistAdapter

class PlaylistDetailActivity :
    BaseActivity<ActivityPlaylistDetailBinding, PlaylistDetailViewModel>() {

    override val viewModel: PlaylistDetailViewModel by lazy {
        ViewModelProvider(this)[PlaylistDetailViewModel::class.java]
    }
    private var pageToken: String? = null
    private lateinit var adapter: PlaylistDetailAdapter
    private var isEnd = false
    private var playlistId: String? = null
    private var item: Item? = null

//    override fun initViews() {
//        super.initViews()
//        val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.extras?.getParcelable(OPEN_PLAYLIST_DETAIL, Item::class.java)
//        } else {
//            intent.extras?.getParcelable(OPEN_PLAYLIST_DETAIL)
//        }
//        Toast.makeText(this, item?.id, Toast.LENGTH_SHORT).show()
//    }

    override fun initViewModel() {
        super.initViewModel()
        item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(OPEN_PLAYLIST_DETAIL, Item::class.java)
        } else {
            intent.extras?.getParcelable(OPEN_PLAYLIST_DETAIL)
        }
        playlistId = item?.id
        item?.id?.let { it ->
            viewModel.getPlaylistItems(it, null).observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        adapter =
                            PlaylistDetailAdapter(it.data?.items as MutableList<ItemDetail>, this::getVideo, this)
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
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        super.initViews()
        with(binding) {
            tvTitle.text = item?.snippet?.title
            description.text = item?.snippet?.description
            tvVideoCount.text = item?.contentDetails?.itemCount.toString() + " video series"
        }

    }

    override fun initListener() {
        super.initListener()
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1) && newState== RecyclerView.SCROLL_STATE_IDLE){
                    if(!isEnd && pageToken?.isNotEmpty() == true)
                        addPlaylists()
                }
            }
        })

        binding.btnBack.setOnClickListener {
            finish()
        }
    }


    private fun addPlaylists(){
        playlistId?.let { it ->
            viewModel.getPlaylistItems(it, pageToken=pageToken).observe(this) {
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
    }

    fun getVideo(id: String): LiveData<Resource<Video>> {
        return viewModel.getVideo(id)
    }



    override fun inflateViewBinding(): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)
    }

}