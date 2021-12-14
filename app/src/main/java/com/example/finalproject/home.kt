package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.finalproject.databinding.FragmentHomeBinding
import androidx.fragment.app.viewModels


/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class home: Fragment() {
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

        binding.preferencesButton.setOnClickListener {
            val action = homeDirections.actionHome2ToPreferences()
            findNavController().navigate(action)
        }

        return binding.root

    }

}



