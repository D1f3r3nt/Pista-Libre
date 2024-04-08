package com.example.pistalibreandroid.registro.registroClub.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pistalibreandroid.registro.registroClub.domain.SignUpClubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignClubViewModel @Inject constructor(private val signUpClubUseCase: SignUpClubUseCase):ViewModel() {


    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isSignClubEnable = MutableLiveData<Boolean>()
    val isSignClubEnable: LiveData<Boolean> = _isSignClubEnable

    fun onSignClubChanged(email:String, password:String) {
        _email.value = email
        _password.value = password
        _isSignClubEnable.value = enableRegistroClub(email, password)
    }

    fun enableRegistroClub(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun onSignUpClubSelected(){
        viewModelScope.launch {
            val result = signUpClubUseCase(email.value!!, password.value!!)
            if(result){
                //Navegar al login
                Log.i("sergio","result ok")
            }
        }
    }




}