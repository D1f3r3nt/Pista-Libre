package com.example.pistalibreandroid.ui.login

import android.util.Log
import android.util.Patterns
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
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _email = MutableStateFlow<String>("")
    private val _password = MutableStateFlow<String>("")
    private val _isLoginEnable = MutableStateFlow<Boolean>(false)
    private val _state = MutableStateFlow<ResponseState>(Idle())

    val email: StateFlow<String> = _email
    val password: StateFlow<String> = _password
    val isLoginEnable: StateFlow<Boolean> = _isLoginEnable
    val state: StateFlow<ResponseState> = _state

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5

    fun onLoginSelected() {
        viewModelScope.launch {
            _state.value = ResponseLoading()
            val result = repository.login(email.value, password.value)
            
            if (result.isSuccessful) {
                _state.value = ResponseOk(Unit)
                
                repository.setToken(result.body()!!)
                
                //Navegar a la siguiente pantalla
                Log.i("sergio", "todo ok, navegamos a la home")
            } else {
                _state.value = ResponseError(result.body()!!)
            }
        }
    }

}