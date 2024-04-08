package com.example.pistalibreandroid.ui.registro.club

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
class SignClubViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _password = MutableStateFlow<String>("")
    private val _email = MutableStateFlow<String>("")
    private val _clubName = MutableStateFlow<String>("")
    private val _directionClub = MutableStateFlow<String>("")
    private val _isSignClubEnable = MutableStateFlow<Boolean>(false)
    private val _state = MutableStateFlow<ResponseState>(Idle())

    val email: StateFlow<String> = _email
    val password: StateFlow<String> = _password
    val clubName: StateFlow<String> = _clubName
    val directionClub: StateFlow<String> = _directionClub
    val isSignClubEnable: StateFlow<Boolean> = _isSignClubEnable
    val state: StateFlow<ResponseState> = _state

    fun onSignClubChanged(clubName: String? = null, directionClub: String? = null, email: String, password: String) {
        clubName?.let { _clubName.value = it }
        directionClub?.let { _directionClub.value = it }
        _email.value = email
        _password.value = password
        _isSignClubEnable.value = enableSignClub(email, password)
    }

    private fun enableSignClub(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5

    fun onSignUpClubSelected() {
        viewModelScope.launch {
            _state.value = ResponseLoading()
            val result = repository.singUpClub(clubName.value, directionClub.value, email.value, password.value)

            if (result.isSuccessful) {
                _state.value = ResponseOk(Unit)

                //Navegar a la pantalla de login
            } else {
                _state.value = ResponseError("Email already exists")
            }
        }
    }


}