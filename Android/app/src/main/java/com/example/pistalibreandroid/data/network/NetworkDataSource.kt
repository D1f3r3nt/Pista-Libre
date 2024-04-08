package com.example.pistalibreandroid.data.network

import com.example.pistalibreandroid.data.network.apis.PistaLibreApi
import com.example.pistalibreandroid.data.network.request.ClubCreateRequest
import com.example.pistalibreandroid.data.network.request.UserCreateRequest
import retrofit2.Response
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val api: PistaLibreApi
) {

    suspend fun login(email: String, password: String): Response<String> {
        return api.login(email, password)
    }

    suspend fun singUpUser(
        username: String,
        fullName: String,
        email: String,
        password: String
    ): Response<Unit> {
        return api.singUpUser(UserCreateRequest(username, fullName, email, password))
    }

    suspend fun singUpClub(
        name: String,
        location: String,
        email: String,
        password: String
    ): Response<Unit> {
        return api.singUpClub(ClubCreateRequest(name, location, email, password))
    }

}