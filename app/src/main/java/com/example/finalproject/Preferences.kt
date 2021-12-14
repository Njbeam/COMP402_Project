package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.SharedMemory
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
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
            val updatedIP = binding.addressEditText.text.toString()
            viewModel.saveData(updatedIP)
            Toast.makeText(this.context, "IP saved: $updatedIP", Toast.LENGTH_SHORT).show()
        }

//        binding.settingsButton.setOnClickListener {
//
//
//            // inflate the layout of the popup window
//            //v/al inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val popupView: View = inflater.inflate(R.layout.fragment_popup_window, null)
//
//            // create the popup window
//            val width = LinearLayout.LayoutParams.WRAP_CONTENT
//            val height = LinearLayout.LayoutParams.WRAP_CONTENT
//            val focusable = true // lets taps outside the popup also dismiss it
//            val popupWindow = PopupWindow(popupView, width, height, focusable)
//
//            // show the popup window
//            // which view you pass in doesn't matter, it is only used for the window tolken
//            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
//
//
//            // dismiss the popup window when touched
//            popupView.setOnTouchListener { v, event ->
//                popupWindow.dismiss()
//                true
//            }
//        }

        return binding.root
    }

    private fun loadData() {
        binding.addressEditText.setText(viewModel.getData())
    }

}

