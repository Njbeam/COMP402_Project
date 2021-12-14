package com.example.finalproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


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
}