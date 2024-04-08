package com.example.pistalibreandroid.registro.registroClub.data

import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.pistalibreandroid.registro.registroClub.data.network.SignUpClubService
import javax.inject.Inject

class SignUpClubRepository @Inject constructor(private val api: SignUpClubService) {

    suspend fun doSignUpClub(user:String, password:String):Boolean{
        return api.doSignUpClub(user, password)
    }
}