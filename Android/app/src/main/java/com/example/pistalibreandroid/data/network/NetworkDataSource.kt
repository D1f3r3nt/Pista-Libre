package com.example.pistalibreandroid.data.network

import com.example.pistalibreandroid.data.network.apis.PistaLibreApi
import com.example.pistalibreandroid.data.network.request.ClubCourtConfigRequest
import com.example.pistalibreandroid.data.network.request.ClubCreateRequest
import com.example.pistalibreandroid.data.network.request.UserConfigRequest
import com.example.pistalibreandroid.data.network.request.UserCreateRequest
import com.example.pistalibreandroid.data.network.response.CheckTokenResponse
import com.example.pistalibreandroid.data.network.response.ClubsListResponse
import retrofit2.Response
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val api: PistaLibreApi
) {
    
    /**
     * Método suspendido para realizar el login, usa coroutines para operaciones asíncronas
     * 
     * @param email String
     * @param password String
     * 
     * @return Response<String>
     */
    suspend fun login(email: String, password: String): Response<String> {
        // Realiza llamada al método login de la API y devuelve su respuesta
        return api.login(email, password)
    }
    
    /**
     * Método suspendido para registrar un nuevo usuario
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
        // Crea una solicitud de creación de usuario y la envía a la API
        return api.singUpUser(UserCreateRequest(username, fullName, email, password))
    }
    
    /**
     * Método suspendido para registrar un nuevo club
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
        // Crea una solicitud de creación de club y la envía a la API
        return api.singUpClub(ClubCreateRequest(name, location, email, password))
    }

    /**
     * Método suspendido para obtener una lista de todos los clubs
     * 
     * @param token String
     * 
     * @return Response<List<ClubsListResponse>>
     */
    suspend fun getAllClubs(
        token: String
    ): Response<List<ClubsListResponse>> {
        // Realiza llamada al método getClubs de la API pasando el token de autorización
        return api.getClubs("Bearer $token")
    }
    
    /**
     * Método suspendido para configurar datos de un usuario
     * 
     * @param sidePlay String
     * @param username String
     * @param fullName String
     * @param token String
     * 
     * @return Response<Unit>
     */
    suspend fun configUser(
        sidePlay: String,
        username: String,
        fullName: String,
        token: String
    ): Response<Unit> {
        // Envía una solicitud de configuración de usuario a la API
        return api.configUser("Bearer $token", UserConfigRequest("", username, fullName, sidePlay))
    }
    
    /**
     * Método suspendido para configurar datos de un club
     * 
     * @param token String
     * @param name String
     * @param location String
     * @param clubCourtConfigRequest List<ClubCourtConfigRequest>
     *     
     * @return Response<Unit>
     */
    suspend fun configClub(
        token: String,
        name: String,
        location: String,
        clubCourtConfigRequest: List<ClubCourtConfigRequest>
    ): Response<Unit> {
        // Envía una solicitud de configuración de club a la API
        return api.configClub("Bearer $token", name, location, clubCourtConfigRequest)
    }

    /**
     * Método para obtener si es user o club
     * 
     * @param token String
     * @return Response<CheckTokenResponse>
     */
    suspend fun checkToken(
        token: String,
    ): Response<CheckTokenResponse> {
        return api.checkToken("Bearer $token")
    }

}