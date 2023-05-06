package com.example.gt_6m_4_youtube.ui.playlistDetail

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.gt_6m_4_youtube.core.network.result.Resource
import com.example.gt_6m_4_youtube.core.network.result.Status
import com.example.gt_6m_4_youtube.data.remote.model.Item
import com.example.gt_6m_4_youtube.data.remote.model.ItemDetail
import com.example.gt_6m_4_youtube.data.remote.model.PlaylistDetail
import com.example.gt_6m_4_youtube.data.remote.model.Video
import com.example.gt_6m_4_youtube.databinding.ItemVideoBinding

class PlaylistDetailAdapter(
    private val list: MutableList<ItemDetail>,
    private val getVideo: (id: String) -> LiveData<Resource<Video>>,
    private val lifecycleOwner: LifecycleOwner
): Adapter<PlaylistDetailAdapter.PlaylistDetailViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistDetailViewHolder {
        return PlaylistDetailViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PlaylistDetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<ItemDetail>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    inner class PlaylistDetailViewHolder(private val binding: ItemVideoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(itemDetail: ItemDetail) {
            with(binding) {
                try {

                    Glide.with(itemView.context).load(itemDetail.snippet.thumbnails.default.url).into(image)
                } catch (e: Exception) {
                    Log.d("gg", e.toString())
                }
                tvTitle.text = itemDetail.snippet.title
                val video = getVideo(itemDetail.snippet.resourceId.videoId).observe(lifecycleOwner, Observer {
                    when (it.status) {
                        Status.SUCCESS -> {
                            val items = it.data?.items
                            if (!items.isNullOrEmpty()) {
                                binding.tvTime.text = convertTime(items[0].contentDetails.duration)
                            }

                            binding.progressBar.isVisible = false
                        }

                        Status.ERROR -> {
                            binding.progressBar.isVisible = false
                        }

                        Status.LOADING -> {
                            binding.progressBar.isVisible = true
                        }
                    }
                })
            }
        }
        fun convertTime(duration: String): String {
            val pattern = "^PT(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?$".toRegex()

            val (hours, minutes, seconds) = pattern.matchEntire(duration)?.destructured
                ?: throw IllegalArgumentException("Invalid duration format")

            val totalSeconds = (hours.toIntOrNull() ?: 0) * 3600 +
                    (minutes.toIntOrNull() ?: 0) * 60 +
                    (seconds.toIntOrNull() ?: 0)

            val hoursStr = "%02d".format(totalSeconds / 3600)
            val minutesStr = "%02d".format((totalSeconds % 3600) / 60)
            val secondsStr = "%02d".format(totalSeconds % 60)

            return "$hoursStr:$minutesStr:$secondsStr"
        }


    }
}