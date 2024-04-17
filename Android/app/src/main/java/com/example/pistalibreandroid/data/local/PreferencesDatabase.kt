package com.example.pistalibreandroid.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesDatabase @Inject constructor (
    private val sharedPreferences: SharedPreferences
) {

    // Función para establecer un dato en SharedPreferences
    fun setData(key: String, value: String) {
        // Iniciamos la edición de SharedPreferences
        sharedPreferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    // Función para obtener un dato de SharedPreferences
    fun getData(key: String): String? {
        // Devolvemos un String de SharedPreferences usando una clave, o `null` si no se encuentra nada
        return sharedPreferences.getString(key, null)
    }

}