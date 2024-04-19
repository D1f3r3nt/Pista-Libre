package com.example.pistalibreandroid.ui.login

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


    /**
     * Función que llamamos para cuando los inputs de login cambian
     * @param email String
     * @param password String
     */
    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        // Actualizamos el estado de habilitación del botón de login basado en la validez del email y la longitud de la contraseña
        _isLoginEnable.value = enableLogin(email, password)
    }

    /**
     * Función para controlar el formato correcto de los valores
     * @return Boolean -> Para saber si cumple o no el formato
     */
    private fun enableLogin(email: String, password: String) =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5

    /**
     * Función llamada cuando se selecciona la opción de login
     */
    fun onLoginSelected() {
        viewModelScope.launch {
            _state.value = ResponseLoading()
            val result = repository.login(email.value, password.value)

            if (result.isSuccessful) {
                // Si el login es exitoso, guardamos el token y actualizamos el estado a Ok
                repository.setToken(result.body()!!)
                _state.value = ResponseOk(Unit)

            } else {
                // Si el login falla, actualizamos el estado a Error
                _state.value = ResponseError(result.body()!!)
            }
        }
    }

}