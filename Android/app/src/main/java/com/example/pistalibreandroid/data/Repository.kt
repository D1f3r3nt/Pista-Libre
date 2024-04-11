package com.example.pistalibreandroid.data

import com.example.pistalibreandroid.data.local.LocalDataSource
import com.example.pistalibreandroid.data.network.NetworkDataSource
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource,
) {
    suspend fun login(username: String, password: String): Response<String> {
        return networkDataSource.login(username, password)
    }

    suspend fun singUpClub(
        name: String,
        location: String,
        email: String,
        password: String
    ): Response<Unit> {
        return networkDataSource.singUpClub(name, location, email, password)
    }

    suspend fun singUpUser(
        username: String,
        fullName: String,
        email: String,
        password: String
    ): Response<Unit> {
        return networkDataSource.singUpUser(username, fullName, email, password)
    }
    
    suspend fun configUser(
        sidePlay: String,
        username: String,
        fullName: String,
    ): Response<Unit> {
        return networkDataSource.configUser(sidePlay, username, fullName, /*getToken() ?: ""*/ "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoidXNlciIsImlkIjoxfQ.m-v5z59bve7CJfe3YsPgQftblxsAF0W8z8XtWA14pAY")
    }

    fun getToken(): String? {
        return localDataSource.getToken()
    }

    fun setToken(value: String) {
        localDataSource.setToken(value)
    }
}