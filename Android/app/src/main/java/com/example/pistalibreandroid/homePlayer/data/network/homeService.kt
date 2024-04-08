package com.example.pistalibreandroid.homePlayer.data.network

import retrofit2.http.GET

interface homeService {
    @GET("club/all")
    suspend fun getClubs() : List<>
}