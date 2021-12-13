package com.example.finalproject

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.finalproject.placeholder.PlaceholderContent.PlaceholderItem
import com.example.finalproject.databinding.FragmentLightsBinding


class DiffCallback1 : DiffUtil.ItemCallback<lights>() {
    override fun areItemsTheSame(oldItem: lights, newItem: lights): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: lights, newItem: lights): Boolean =
        oldItem == newItem
}

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MylightsRecyclerViewAdapter : ListAdapter<lights, MylightsRecyclerViewAdapter.ViewHolder>(DiffCallback1()) {
    var onClick: ((lights) -> Unit)? = null

    inner class ViewHolder(private val binding: FragmentLightsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lights: lights) {
            binding.lightsText.text = lights.name
            binding.switch1.isChecked = lights.isOn

            binding.switch1.setOnClickListener {
                onClick?.invoke(lights)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentLightsBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}