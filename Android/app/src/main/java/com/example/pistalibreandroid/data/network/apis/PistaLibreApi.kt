package com.example.pistalibreandroid.data.network.apis

import com.example.pistalibreandroid.data.network.request.ClubCourtConfigRequest
import com.example.pistalibreandroid.data.network.request.ClubCreateRequest
import com.example.pistalibreandroid.data.network.request.UserConfigRequest
import com.example.pistalibreandroid.data.network.request.UserCreateRequest
import com.example.pistalibreandroid.data.network.response.CheckTokenResponse
import com.example.pistalibreandroid.data.network.response.ClubsListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PistaLibreApi {

    @GET("/auth/log-in")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String,
    ): Response<String>

    @POST("/auth/user/sign-up")
    suspend fun singUpUser(
        @Body userCreateRequest: UserCreateRequest
    ): Response<Unit>

    @POST("/auth/club/sign-up")
    suspend fun singUpClub(
        @Body clubCreateRequest: ClubCreateRequest
    ): Response<Unit>

    @GET("/club/all")
    suspend fun getClubs(
        @Header("Authorization") token:String
    ) : Response<List<ClubsListResponse>>


    @POST("/config/user")
    suspend fun configUser(
        @Header(value = "Authorization") token: String,
        @Body userConfigRequest: UserConfigRequest
    ): Response<Unit>

    @POST("/config/club")
    suspend fun configClub(
        @Header(value = "Authorization") token: String,
        @Query("name") name: String,
        @Query("location") location: String,
        @Body clubCourtConfigRequest: List<ClubCourtConfigRequest>
    ): Response<Unit>
    
    @GET("/auth/checkToken")
    suspend fun checkToken(
        @Header(value = "Authorization") token: String,
    ): Response<CheckTokenResponse>
}