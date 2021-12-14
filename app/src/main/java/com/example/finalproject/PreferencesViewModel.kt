package com.example.finalproject

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//const val PREFERENCE_KEY = "TEST"

class PreferencesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = IPRepository(application)

    private var ip = repository.getData() ?: "test"

    private val _ipData = MutableLiveData(ip)
    val ipData: LiveData<String> = _ipData

    fun getIP() = repository.getData()

    fun updateIP(newIP: String) {
        ip = newIP

        repository.saveData(ip)
        _ipData.postValue(ip)
    }
//    private val sharedPreferences = application.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
//
//    fun getData() = sharedPreferences.getString(IP_KEY, DEFAULT_IP)
//
//    fun saveData(text: String) {
//        val editor = sharedPreferences.edit()
//        editor.putString(IP_KEY, text)
//        editor.apply()
//    }
}