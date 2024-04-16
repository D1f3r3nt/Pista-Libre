package com.example.pistalibreandroid.data

import com.example.pistalibreandroid.data.local.LocalDataSource
import com.example.pistalibreandroid.data.network.NetworkDataSource
import com.example.pistalibreandroid.data.network.response.ClubsListResponse
import com.example.pistalibreandroid.data.network.request.ClubCourtConfigRequest
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource,
) {

    // Función para iniciar sesión usando la fuente de datos de red
    suspend fun login(username: String, password: String): Response<String> {
        return networkDataSource.login(username, password)
    }

    // Función para registrar un club usando la fuente de datos de red
    suspend fun singUpClub(
        name: String,
        location: String,
        email: String,
        password: String
    ): Response<Unit> {
        // Delega la acción de registro de club a la fuente de datos de red
        return networkDataSource.singUpClub(name, location, email, password)
    }

    // Función para registrar un jugador usando la fuente de datos de red
    suspend fun singUpUser(
        username: String,
        fullName: String,
        email: String,
        password: String
    ): Response<Unit> {
        // Delega la acción de registro de jugador a la fuente de datos de red
        return networkDataSource.singUpUser(username, fullName, email, password)
    }

    // Función para configurar datos de un usuario usando la fuente de datos de red
    suspend fun configUser(
        sidePlay: String,
        username: String,
        fullName: String,
    ): Response<Unit> {
        // Delega la configuración de usuario a la fuente de datos de red, incluye obtener token
        return networkDataSource.configUser(sidePlay, username, fullName,getToken() ?: "")
    }

    // Función para obtener todos los clubes usando la fuente de datos de red
    suspend fun getAllClubs(): Response<List<ClubsListResponse>>{
        // Delega la obtención de clubes a la fuente de datos de red, incluye obtener token
        return networkDataSource.getAllClubs(getToken() ?: "")
    }

    // Función para configurar datos de un club usando la fuente de datos de red
    suspend fun configClub(
        name: String,
        location: String,
        clubCourtConfigRequest: List<ClubCourtConfigRequest>
    ): Response<Unit> {
        // Delega la configuración de club a la fuente de datos de red, incluye obtener token
        return networkDataSource.configClub(getToken() ?: "", name, location, clubCourtConfigRequest)
    }

    // Función para obtener el token de la fuente de datos local
    fun getToken(): String? {
        // Recupera el token guardado en la fuente de datos local
        return localDataSource.getToken()
    }

    // Función para establecer un nuevo token en la fuente de datos local
    fun setToken(value: String) {
        // Guarda el nuevo token en la fuente de datos local
        localDataSource.setToken(value)
    }
}