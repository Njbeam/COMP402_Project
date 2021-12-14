package com.example.finalproject

import android.content.Context

const val PREFERENCE_KEY = "this.should.be.a.completely.unique.key"
const val IP_KEY = "IP"
const val DEFAULT_IP = "http://"

class IPRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

    fun getData() = sharedPreferences.getString(IP_KEY, DEFAULT_IP)

    fun saveData(text: String) {
        val editor = sharedPreferences.edit()
        editor.putString(IP_KEY, text)
        editor.apply()
    }

}