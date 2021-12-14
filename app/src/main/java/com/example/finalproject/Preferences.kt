package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.SharedMemory
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.finalproject.databinding.FragmentPreferencesBinding

class Preferences: Fragment() {

    private val viewModel: PreferencesViewModel by viewModels()

    private lateinit var binding: FragmentPreferencesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreferencesBinding.inflate(layoutInflater)

        //
        loadData()

        binding.saveButton.setOnClickListener {
            val updatedIP = binding.addressEditText.text.toString()
            viewModel.saveData(updatedIP)
            Toast.makeText(this.context, "IP saved: $updatedIP", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun loadData() {
        binding.addressEditText.setText(viewModel.getData())
    }

}

