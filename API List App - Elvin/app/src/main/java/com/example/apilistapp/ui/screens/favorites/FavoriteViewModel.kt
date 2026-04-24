package com.example.apilistapp.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.remote.dto.CharacterModel
import com.example.apilistapp.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val consultabase = FavoriteRepository()
    private val _characters = MutableStateFlow<List<CharacterModel>>(emptyList())
    val characters: StateFlow<List<CharacterModel>> = _characters.asStateFlow()

    init {
        obtenerCharactersDeBaseLocal()
    }

    fun obtenerCharactersDeBaseLocal() {
        viewModelScope.launch {
            val response = consultabase.getFavorites()
            _characters.value = response.map { it.toDomain() }
        }
    }
}