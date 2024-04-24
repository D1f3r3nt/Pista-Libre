package com.example.pistalibreandroid.ui.registro.player

import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pistalibreandroid.data.Repository
import com.example.pistalibreandroid.model.Idle
import com.example.pistalibreandroid.model.ResponseError
import com.example.pistalibreandroid.model.ResponseLoading
import com.example.pistalibreandroid.model.ResponseOk
import com.example.pistalibreandroid.model.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUserViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    
    private val _email = MutableStateFlow<String>("")
    private val _password = MutableStateFlow<String>("")
    private val _username = MutableStateFlow<String>("")
    private val _fullname = MutableStateFlow<String>("")
    private val _isUserSignEnable = MutableStateFlow<Boolean>(false)
    private val _state = MutableStateFlow<ResponseState>(Idle())

    var email : StateFlow<String> = _email
    var password : StateFlow<String> = _password
    var username : StateFlow<String> = _username
    var fullname : StateFlow<String> = _fullname
    var isUserSignEnable : StateFlow<Boolean> = _isUserSignEnable
    val state: StateFlow<ResponseState> = _state

    fun resetState() {
        _state.value = Idle()
    }

    /**
     * Funcion para setear los nuevos valores
     * 
     * @param username String?
     * @param fullname String?
     * @param email String
     * @param password String
     */
    fun onUserSignChanged(username: String? = null, fullname: String? = null, email:String, password:String) {
        username?.let { _username.value = it }
        fullname?.let { _fullname.value = it }
        _password.value = password
        _email.value = email
        _isUserSignEnable.value = enableSignUser(email, password)
    }

    private fun enableSignUser(email: String, password: String) =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5

    fun onSignUpSelected(){
        viewModelScope.launch {
            _state.value = ResponseLoading()
            val result = repository.singUpUser(username.value, fullname.value, email.value, password.value)
            
            if(result.isSuccessful){
                
                _state.value = ResponseOk(Unit)
            } else {
                _state.value = ResponseError("Email or username already exists")
            }
        }
    }
}