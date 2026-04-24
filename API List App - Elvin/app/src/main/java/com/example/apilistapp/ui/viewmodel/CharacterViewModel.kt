package com.example.apilistapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.remote.dto.CharacterModel // Importa el modelo individual
import com.example.apilistapp.data.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel : ViewModel() {
    private val repository = ApiRepository()
    private val _characters = MutableStateFlow<List<CharacterModel>>(emptyList())
    val characters: StateFlow<List<CharacterModel>> = _characters.asStateFlow()

    private var currentPage = 1
    private var ultimapagina = false
    private var haymas = false

    init {
        obtenerCharactersAPI()
    }

    fun obtenerCharactersAPI() {
        if (haymas || ultimapagina) return

        haymas = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllCharacters(currentPage)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                            _characters.value = _characters.value + (body?.results ?: emptyList())
                            currentPage++
                            ultimapagina = body?.info?.next == null
                    }
                }
            } catch (e: Exception) {
                Log.e("Error en CharacterViewModel creo...", "Error al obtener los personajes", )
            } finally {
                haymas = false
            }
        }
    }

}