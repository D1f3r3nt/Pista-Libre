package com.example.pistalibreandroid.data

import com.example.pistalibreandroid.data.local.LocalDataSourceInterface
import com.example.pistalibreandroid.data.network.NetworkDataSource
import com.example.pistalibreandroid.data.network.response.ClubsListResponse
import com.example.pistalibreandroid.data.network.request.ClubCourtConfigRequest
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSourceInterface,
    private val networkDataSource: NetworkDataSource,
) {
    
    /**
     * Función para iniciar sesión usando la fuente de datos de red
     * 
     * @param username String
     * @param password String
     * 
     * @return Response<String>
     */
    suspend fun login(username: String, password: String): Response<String> {
        return networkDataSource.login(username, password)
    }
    
    /**
     * Función para registrar un club usando la fuente de datos de red
     * 
     * @param name String
     * @param location String
     * @param email String
     * @param password String
     * 
     * @return Response<Unit>
     */
    suspend fun singUpClub(
        name: String,
        location: String,
        email: String,
        password: String
    ): Response<Unit> {
        // Delega la acción de registro de club a la fuente de datos de red
        return networkDataSource.singUpClub(name, location, email, password)
    }

    /**
     * Función para registrar un jugador usando la fuente de datos de red
     * 
     * @param username String
     * @param fullName String
     * @param email String
     * @param password String
     * 
     * @return Response<Unit>
     */
    suspend fun singUpUser(
        username: String,
        fullName: String,
        email: String,
        password: String
    ): Response<Unit> {
        // Delega la acción de registro de jugador a la fuente de datos de red
        return networkDataSource.singUpUser(username, fullName, email, password)
    }

    /**
     * Función para configurar datos de un usuario usando la fuente de datos de red
     * 
     * @param sidePlay String
     * @param username String
     * @param fullName String
     * 
     * @return Response<Unit>
     */
    suspend fun configUser(
        sidePlay: String,
        username: String,
        fullName: String,
    ): Response<Unit> {
        // Delega la configuración de usuario a la fuente de datos de red, incluye obtener token
        return networkDataSource.configUser(sidePlay, username, fullName,getToken() ?: "")
    }

    /**
     * Función para obtener todos los clubes usando la fuente de datos de red
     * 
     * @return Response<List<ClubsListResponse>>
     */
    suspend fun getAllClubs(): Response<List<ClubsListResponse>>{
        // Delega la obtención de clubes a la fuente de datos de red, incluye obtener token
        return networkDataSource.getAllClubs(getToken() ?: "")
    }
    
    /**
     * Función para configurar datos de un club usando la fuente de datos de red
     * 
     * @param name String
     * @param location String
     * @param clubCourtConfigRequest List<ClubCourtConfigRequest>
     *     
     * @return Response<Unit>
     */
    suspend fun configClub(
        name: String,
        location: String,
        clubCourtConfigRequest: List<ClubCourtConfigRequest>
    ): Response<Unit> {
        // Delega la configuración de club a la fuente de datos de red, incluye obtener token
        return networkDataSource.configClub(getToken() ?: "", name, location, clubCourtConfigRequest)
    }

    /**
     * Función para obtener el token de la fuente de datos local
     * 
     * @return String?
     */
    fun getToken(): String? {
        // Recupera el token guardado en la fuente de datos local
        return localDataSource.getToken()
    }

    /**
     * Función para establecer un nuevo token en la fuente de datos local
     * 
     * @param value String
     */
    fun setToken(value: String) {
        // Guarda el nuevo token en la fuente de datos local
        localDataSource.setToken(value)
    }
}