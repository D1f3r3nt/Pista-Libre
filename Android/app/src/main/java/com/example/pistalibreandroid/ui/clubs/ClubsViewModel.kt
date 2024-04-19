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

    /**
     * Inicializamos el bloque que llama a getAllClubs al crear el ViewModel
     */
    init {
        getAllClubs()
    }

    /**
     * Función para cargar todos los clubs
     */
    fun getAllClubs(){
        // Lanzamos una coroutina dentro del alcance del ViewModel
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getAllClubs()
            if(response.isSuccessful){
                // Si la respuesta es exitosa, actualizamos el flujo de lista de clubs
                _clubsList.value = response.body() ?: listOf()
            }
            _isLoading.value = false // Indicamos que el proceso de carga ha finalizado
        }
    }
}