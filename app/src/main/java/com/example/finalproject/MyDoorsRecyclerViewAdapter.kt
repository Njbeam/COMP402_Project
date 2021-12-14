package com.example.finalproject

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.finalproject.databinding.FragmentDoorsBinding
import com.example.finalproject.placeholder.PlaceholderContent.PlaceholderItem
import androidx.recyclerview.widget.ListAdapter


class DiffCallback : DiffUtil.ItemCallback<doors>() {
    override fun areItemsTheSame(oldItem: doors, newItem: doors): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: doors, newItem: doors): Boolean =
        oldItem == newItem
}

class MyDoorsRecyclerViewAdapter : ListAdapter<doors, MyDoorsRecyclerViewAdapter.ViewHolder>(DiffCallback()) {
    var onClick: ((doors) -> Unit)? = null

    inner class ViewHolder(private val binding: FragmentDoorsBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(doors: doors) {
            binding.doorsName.text = doors.name
            binding.motorizedText.text = "Motorized: ${doors.motorized}"
            binding.doorsSwitch.isChecked = doors.isOpen

            binding.doorsSwitch.setOnClickListener {
                onClick?.invoke(doors)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentDoorsBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}