package com.example.pistalibreandroid.ui.setting.club

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pistalibreandroid.data.Repository
import com.example.pistalibreandroid.data.network.request.ClubCourtConfigRequest
import com.example.pistalibreandroid.model.Idle
import com.example.pistalibreandroid.model.ResponseError
import com.example.pistalibreandroid.model.ResponseLoading
import com.example.pistalibreandroid.model.ResponseOk
import com.example.pistalibreandroid.model.ResponseState
import com.example.pistalibreandroid.model.setting.club.CourtList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubSettingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _photo = MutableStateFlow<String>("")
    private val _name = MutableStateFlow<String>("")
    private val _direction = MutableStateFlow<String>("")
    private val _courts = MutableStateFlow<MutableList<CourtList>>(mutableListOf())
    private val _isConfigEnable = MutableStateFlow<Boolean>(false)
    private val _state = MutableStateFlow<ResponseState>(Idle())

    val photo: StateFlow<String> = _photo
    val name: StateFlow<String> = _name
    val direction: StateFlow<String> = _direction
    val courts: StateFlow<MutableList<CourtList>> = _courts
    val isConfigEnable: StateFlow<Boolean> = _isConfigEnable
    val state: StateFlow<ResponseState> = _state

    fun onConfigChanged(name: String, direction: String) {
        _name.value = name
        _direction.value = direction
        _isConfigEnable.value = enableConfig(name, direction)
    }

    fun enableConfig(name: String, direction: String) = name.length > 1 && direction.length > 1 && _courts.value.isNotEmpty()

    fun addNewCourt() {
        val list = _courts.value.toMutableList()
        list.add(CourtList(_courts.value.count() + 1, false, 0.0F))
        _courts.value = list
        
        onConfigChanged(_name.value, _direction.value)
    }
    
    fun onCourtChange(index: Int, number: Int?, indoor: Boolean, price: Float?) {
        val list = _courts.value.toMutableList()
        list.removeAt(index)
        list.add(index, CourtList(number ?: 0, indoor, price ?: 0.0F))
        _courts.value = list
    }

    fun removeCourt(index: Int) {
        val list = _courts.value.toMutableList()
        list.removeAt(index)
        _courts.value = list
        
        onConfigChanged(_name.value, _direction.value)
    }
    
    fun postConfig() {
        val courtClub = _courts.value.map {
            ClubCourtConfigRequest(it.number, it.indoor, it.price)
        }
        
        viewModelScope.launch { 
            _state.value = ResponseLoading()
            val response = repository.configClub(_name.value, _direction.value, courtClub)
            
            if (response.isSuccessful) {
                _state.value = ResponseOk(Unit)
            } else {
                _state.value = ResponseError("Hay algun error")
            }
        }
    }
}