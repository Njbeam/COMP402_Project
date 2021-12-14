package com.example.finalproject

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//const val PREFERENCE_KEY = "TEST"

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = IPRepository(application)

    private var ip = repository.getData() ?: "test"

    private val _ipData = MutableLiveData(ip)
    val ipData: LiveData<String> = _ipData

    fun getIP() = repository.getData()
}