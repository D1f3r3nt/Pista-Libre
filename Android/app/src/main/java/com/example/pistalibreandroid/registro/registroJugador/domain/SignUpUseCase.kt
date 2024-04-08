package com.example.pistalibreandroid.registro.registroJugador.domain

import com.example.pistalibreandroid.registro.registroJugador.data.SignUpUserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: SignUpUserRepository) {

    suspend operator fun invoke(user:String, password:String):Boolean{
        return repository.doSignUpUser(user, password)
    }
}