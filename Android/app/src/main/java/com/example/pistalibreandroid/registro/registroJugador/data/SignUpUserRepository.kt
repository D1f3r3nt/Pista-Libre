package com.example.pistalibreandroid.registro.registroJugador.data

import com.example.pistalibreandroid.registro.registroJugador.data.network.SignUpUserService
import javax.inject.Inject

class SignUpUserRepository @Inject constructor(private val api:SignUpUserService) {

    suspend fun doSignUpUser(user:String, password:String):Boolean{
        return api.doSignUpUser(user, password)
    }
}