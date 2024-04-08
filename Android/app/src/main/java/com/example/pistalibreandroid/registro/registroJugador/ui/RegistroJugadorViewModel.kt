package com.example.pistalibreandroid.registro.registroJugador.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pistalibreandroid.registro.registroJugador.domain.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroJugadorViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase):ViewModel() {

    private val _email = MutableLiveData<String>()
    var email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    var password : LiveData<String> = _password

    private val _isRegistroJugadorEnable = MutableLiveData<Boolean>()
    var isRegistroJugadorEnable : LiveData<Boolean> = _isRegistroJugadorEnable


    fun onRegistroJugadorChanged(email:String, password:String) {
        _email.value = email
        _password.value = password
        _isRegistroJugadorEnable.value = enableRegistroJugador(email, password)
    }

    fun enableRegistroJugador(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun onSignUpSelected(){
        viewModelScope.launch {
            val result = signUpUseCase(email.value!!, password.value!!)
            if(result){
                //Navegar a la pantalla de login
            }
        }
    }
}