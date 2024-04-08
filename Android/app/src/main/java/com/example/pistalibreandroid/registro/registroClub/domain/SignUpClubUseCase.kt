package com.example.pistalibreandroid.registro.registroClub.domain

import com.example.pistalibreandroid.registro.registroClub.data.SignUpClubRepository
import javax.inject.Inject

class SignUpClubUseCase @Inject constructor(private val repository: SignUpClubRepository) {

    suspend operator fun invoke(user:String, password:String):Boolean{
        return repository.doSignUpClub(user, password)
    }
}