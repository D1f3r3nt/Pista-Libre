package com.example.pistalibreandroid.data.local

import javax.inject.Inject

class LocalDataSource @Inject constructor(

    // Dependencia de PreferencesDatabase inyectada para acceder a métodos de persistencia
    private val preferencesDatabase: PreferencesDatabase
) {
    // Objeto compañero para definir constantes accesibles desde la clase
    companion object {
        val TOKEN = "token_private"
    }

    // Función para obtener el token de las preferencias
    fun getToken(): String? {
        // Llamamos a getData de PreferencesDatabase para recuperar el token usando la clave TOKEN
        return preferencesDatabase.getData(TOKEN)
    }

    // Función para establecer el token en las preferencias
    fun setToken(value: String) {
        // Llamamos a setData de PreferencesDatabase para guardar el token con la clave TOKEN
        preferencesDatabase.setData(TOKEN, value)
    }
}
