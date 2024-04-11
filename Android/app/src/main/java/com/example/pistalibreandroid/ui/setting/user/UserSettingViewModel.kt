package com.example.pistalibreandroid.ui.setting.user

import android.util.Log
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
class UserSettingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _photo = MutableStateFlow<String>("")
    private val _fullname = MutableStateFlow<String>("")
    private val _username = MutableStateFlow<String>("")
    private val _sidePlay = MutableStateFlow<Int>(0)
    private val _isConfigEnable = MutableStateFlow<Boolean>(false)
    private val _state = MutableStateFlow<ResponseState>(Idle())

    val photo: StateFlow<String> = _photo
    val fullname: StateFlow<String> = _fullname
    val username: StateFlow<String> = _username
    val sidePlay: StateFlow<Int> = _sidePlay
    val isConfigEnable: StateFlow<Boolean> = _isConfigEnable
    val state: StateFlow<ResponseState> = _state

    fun onConfigChanged(fullname: String, username: String) {
        _fullname.value = fullname
        _username.value = username
        _isConfigEnable.value = enableLogin(fullname, username)
    }
    
    fun changeSidePlay(sidePlay: Int) {
        _sidePlay.value = sidePlay
    }

    fun enableLogin(fullname: String, username: String) = fullname.length > 1 && username.length > 1
    
    fun postConfig() {
        viewModelScope.launch {
            _state.value = ResponseLoading()
            val response = repository.configUser(if (sidePlay.value == 0) { "D" } else { "I" }, username.value, fullname.value)
            
            if (response.isSuccessful) {
                _state.value = ResponseOk(Unit)
            } else {
                _state.value = ResponseError("Usuario repetido")
            }
        }
    }
}