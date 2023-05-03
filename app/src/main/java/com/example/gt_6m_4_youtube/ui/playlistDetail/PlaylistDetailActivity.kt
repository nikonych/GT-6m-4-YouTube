package com.example.gt_6m_4_youtube.ui.playlistDetail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.gt_6m_4_youtube.R
import com.example.gt_6m_4_youtube.base.BaseActivity
import com.example.gt_6m_4_youtube.base.BaseViewModel
import com.example.gt_6m_4_youtube.databinding.ActivityPlaylistDetailBinding
import com.example.gt_6m_4_youtube.model.Item
import com.example.gt_6m_4_youtube.ui.playlist.PlaylistActivity.Companion.OPEN_PLAYLIST_DETAIL
import com.example.gt_6m_4_youtube.ui.playlist.PlaylistViewModel

class PlaylistDetailActivity :
    BaseActivity<ActivityPlaylistDetailBinding, PlaylistDetailViewModel>() {

    override val viewModel: PlaylistDetailViewModel by lazy {
        ViewModelProvider(this)[PlaylistDetailViewModel::class.java]
    }

    override fun initViews() {
        super.initViews()
        val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(OPEN_PLAYLIST_DETAIL, Item::class.java)
        } else {
            intent.extras?.getParcelable(OPEN_PLAYLIST_DETAIL)
        }
        Toast.makeText(this, item?.id, Toast.LENGTH_SHORT).show()
    }



    override fun inflateViewBinding(): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)
    }

}