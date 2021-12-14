package com.example.finalproject

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.fragment.app.viewModels
import com.example.finalproject.databinding.FragmentPopupWindowBinding
import com.example.finalproject.databinding.FragmentPreferencesBinding

class Preferences: Fragment() {

    private val viewModel: PreferencesViewModel by viewModels()

    private lateinit var binding: FragmentPreferencesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreferencesBinding.inflate(layoutInflater)

        loadData()

        binding.saveButton.setOnClickListener {
            val popupView: View = inflater.inflate(R.layout.fragment_popup_window, null)

            // create the popup window
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true // lets taps outside the popup also dismiss it
            val popupWindow = PopupWindow(popupView, width, height, focusable)
            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window token
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
            var popupBinding: FragmentPopupWindowBinding = FragmentPopupWindowBinding.inflate(layoutInflater)

            // Update popup with latest text
            popupBinding.editLabelTextView.text = "test"

            val updatedIP = binding.addressEditText.text.toString()
            viewModel.updateIP(updatedIP)
        }

        return binding.root
    }

    private fun loadData() {
        binding.addressEditText.setText(viewModel.getIP())
    }

}

