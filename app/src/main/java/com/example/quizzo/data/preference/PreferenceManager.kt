package com.example.quizzo.data.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


const val LANGUAGE_MODE: String = "la"
class PreferenceManager(val context: Context) {

    val prefs = context.getSharedPreferences("MyPref",MODE_PRIVATE)


    fun saveLanguage(code: String){
        prefs.edit().putString(LANGUAGE_MODE,code).apply()
    }

    fun getLanguage() = prefs.getString(LANGUAGE_MODE,"la")
}