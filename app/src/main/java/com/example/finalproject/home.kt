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




/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class home : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        //on click listeners to go to other recycler views
        binding.lightsButton.setOnClickListener {
            val action = homeDirections.actionHome2ToLightsFragment3()
            findNavController().navigate(action)
        }

        binding.doorsButton.setOnClickListener {
            val action = homeDirections.actionHome2ToDoorsFragment()
            findNavController().navigate(action)
        }

        binding.musicButton.setOnClickListener {
            val action = homeDirections.actionHome2ToMediaFragment()
            findNavController().navigate(action)
        }
//        binding.settingsButton.setOnClickListener {
//            val action = homeDirections.actionHome2ToPreferencesFragment()
//            findNavController().navigate(action)
//        }
        binding.settingsButton.setOnClickListener {


            // inflate the layout of the popup window
            //v/al inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView: View = inflater.inflate(R.layout.fragment_popup_window, null)

            // create the popup window
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true // lets taps outside the popup also dismiss it
            val popupWindow = PopupWindow(popupView, width, height, focusable)

            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window tolken
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)


            // dismiss the popup window when touched
            popupView.setOnTouchListener { v, event ->
                popupWindow.dismiss()
                true
            }
        }
        binding.preferencesButton.setOnClickListener {
            val action = homeDirections.actionHome2ToPreferences()
            findNavController().navigate(action)
        }
        return binding.root

    }

}



