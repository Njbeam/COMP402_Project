package com.example.finalproject

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.finalproject.databinding.FragmentMediaBinding


class DiffCallback2 : DiffUtil.ItemCallback<media_players>() {
    override fun areItemsTheSame(oldItem: media_players, newItem: media_players): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: media_players, newItem: media_players): Boolean =
        oldItem == newItem
}

class MyMediaRecyclerViewAdapter: ListAdapter<media_players, MyMediaRecyclerViewAdapter.ViewHolder>(DiffCallback2()) {
    var onClick: ((media_players) -> Unit)? = null
    var prevPress: ((media_players) -> Unit)? = null
    var skipPress: ((media_players) -> Unit)? = null

    inner class ViewHolder(private val binding: FragmentMediaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(media: media_players) {



            binding.mediaText.text = media.name
            binding.mediaSwitch.isChecked = media.isPlaying


            binding.mediaSwitch.setOnClickListener {
                onClick?.invoke(media)
            }
            binding.prevButton.setOnClickListener {
                prevPress?.invoke(media)
            }
            binding.skipButton.setOnClickListener {
                skipPress?.invoke(media)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentMediaBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}