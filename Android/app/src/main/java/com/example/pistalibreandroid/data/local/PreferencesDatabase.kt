package com.example.pistalibreandroid.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesDatabase @Inject constructor (
    private val sharedPreferences: SharedPreferences
) {

    fun setData(key: String, value: String) {
        sharedPreferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

}