package com.example.pistalibreandroid.registro.registroClub.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class SignUpClubService @Inject constructor(private val signUpClubClient: SignUpClubClient) {

    suspend fun doSignUpClub(user:String, password:String):Boolean{
        return withContext(Dispatchers.IO){
            val response = signUpClubClient.doSignUpClub()
            response.body()?.success ?: false
        }
    }
}