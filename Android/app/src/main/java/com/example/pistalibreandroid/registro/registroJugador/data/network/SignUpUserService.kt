package com.example.pistalibreandroid.registro.registroJugador.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpUserService @Inject constructor(private val signUpUserClient: SignUpUserClient){
//    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doSignUpUser(user:String, password:String):Boolean{
        return withContext(Dispatchers.IO){
            val response = signUpUserClient.doSignUpUser()
            response.body()?.success ?: false
        }
    }
}