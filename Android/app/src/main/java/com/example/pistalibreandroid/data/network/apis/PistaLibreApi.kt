package com.example.pistalibreandroid.data.network.apis

import com.example.pistalibreandroid.data.network.request.ClubCreateRequest
import com.example.pistalibreandroid.data.network.request.UserConfigRequest
import com.example.pistalibreandroid.data.network.request.UserCreateRequest
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

    @POST("/config/user")
    suspend fun configUser(
        @Header(value = "Authorization") token: String,
        @Body userConfigRequest: UserConfigRequest
    ): Response<Unit>
}