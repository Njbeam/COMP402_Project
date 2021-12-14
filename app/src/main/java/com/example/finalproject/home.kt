package com.example.finalproject

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.widget.PopupWindow
import androidx.core.content.ContextCompat.getSystemService
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.example.finalproject.databinding.FragmentHomeBinding
import android.view.MotionEvent

import android.view.Gravity

import android.widget.LinearLayout


import com.example.finalproject.R

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.View.OnTouchListener

import androidx.core.content.ContextCompat.getSystemService

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels


/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class home : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        val ip = viewModel.getIP().toString()

        //on click listeners to go to other recycler views
        binding.lightsButton.setOnClickListener {
            val action = homeDirections.actionHome2ToLightsFragment3(ip)
            findNavController().navigate(action)
        }

        binding.doorsButton.setOnClickListener {
            val action = homeDirections.actionHome2ToDoorsFragment(ip)
            findNavController().navigate(action)
        }

        binding.musicButton.setOnClickListener {
            val action = homeDirections.actionHome2ToMediaFragment(ip)
            findNavController().navigate(action)
        }
//        binding.settingsButton.setOnClickListener {
//            val action = homeDirections.actionHome2ToPreferencesFragment()
//            findNavController().navigate(action)
//        }

        binding.preferencesButton.setOnClickListener {
            val action = homeDirections.actionHome2ToPreferences()
            findNavController().navigate(action)
        }

        //binding.doorsButton.text = viewModel.getIP()

        return binding.root

    }

}



