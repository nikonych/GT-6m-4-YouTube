package com.example.gt_6m_4_youtube.ui.playlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gt_6m_4_youtube.R
import com.example.gt_6m_4_youtube.databinding.ItemPlaylistBinding
import com.example.gt_6m_4_youtube.data.remote.model.Item

class PlaylistAdapter(
    private val list: MutableList<Item>,
    private val clickListener: (Item) -> Unit
): RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<Item>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    inner class PlaylistViewHolder(
        private val binding: ItemPlaylistBinding
    ) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            with(binding) {
                tvTitle.text = item.snippet.title
                tvVideoCount.text = item.contentDetails.itemCount.toString() + " video series"
                Glide.with(binding.root).load(item.snippet.thumbnails.default.url).into(binding.image)
                this.root.setOnClickListener {
                    clickListener(item)
                }
            }
        }
    }
}