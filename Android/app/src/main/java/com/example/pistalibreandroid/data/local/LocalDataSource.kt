package com.example.pistalibreandroid.data.local

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val preferencesDatabase: PreferencesDatabase
): LocalDataSourceInterface {
    companion object {
        val TOKEN = "token_private"
    }

    /**
     * Función para obtener el token de las preferencias
     * @return String?
     */
    override fun getToken(): String? {
        // Llamamos a getData de PreferencesDatabase para recuperar el token usando la clave TOKEN
        return preferencesDatabase.getData(TOKEN)
    }
    
    /**
     * Función para establecer el token en las preferencias
     * @param String
     */
    override fun setToken(value: String) {
        // Llamamos a setData de PreferencesDatabase para guardar el token con la clave TOKEN
        preferencesDatabase.setData(TOKEN, value)
    }
}
