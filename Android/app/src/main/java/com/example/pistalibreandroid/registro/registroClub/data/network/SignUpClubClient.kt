package com.example.pistalibreandroid.registro.registroClub.data.network

import com.example.pistalibreandroid.registro.registroClub.data.network.response.SignUpClubResponse
import retrofit2.Response
import retrofit2.http.GET

interface SignUpClubClient {
    @GET("/v3/f78c9d33-28b1-4f81-9cf1-6d6ff78fa014")

    suspend fun doSignUpClub():Response<SignUpClubResponse>
}