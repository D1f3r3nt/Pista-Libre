package com.example.pistalibreandroid.registro.registroJugador.data.network

import com.example.pistalibreandroid.registro.registroJugador.data.network.response.SignUpUserResponse
import retrofit2.Response
import retrofit2.http.GET

interface SignUpUserClient {
    @GET("/v3/f78c9d33-28b1-4f81-9cf1-6d6ff78fa014")
    suspend fun doSignUpUser(): Response<SignUpUserResponse>
}