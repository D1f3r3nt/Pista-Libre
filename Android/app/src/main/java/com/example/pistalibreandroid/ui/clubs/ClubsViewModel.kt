package com.example.pistalibreandroid.ui.clubs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pistalibreandroid.data.Repository
import com.example.pistalibreandroid.data.network.response.ClubsListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _clubsList = MutableStateFlow<List<ClubsListResponse>>(emptyList())
    val clubsList: StateFlow<List<ClubsListResponse>> = _clubsList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        getAllClubs()
    }
    fun getAllClubs(){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getAllClubs()
            if(response.isSuccessful){
                _clubsList.value = response.body() ?: listOf()
            }else {

            }
            _isLoading.value = false
        }
    }
}