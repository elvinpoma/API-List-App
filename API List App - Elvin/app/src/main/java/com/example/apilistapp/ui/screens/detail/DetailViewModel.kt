package com.example.apilistapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.mapper.toDomain

import com.example.apilistapp.data.remote.dto.CharacterModel
import com.example.apilistapp.data.repository.FavoriteRepository
import com.example.apilistapp.domain.RMCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DetailViewModel(private val character: CharacterModel) : ViewModel() {

    private val _existe = MutableStateFlow(false)
    val existe: StateFlow<Boolean> = _existe.asStateFlow()

    private val _characterAct = MutableStateFlow(character)
    val characterAct: StateFlow<CharacterModel> = _characterAct.asStateFlow()

    private val _clickFav = MutableStateFlow(existe.value)
    val clickFav: StateFlow<Boolean> = _clickFav.asStateFlow()


     fun addfav() {
        viewModelScope.launch {
            guardarCharacterFav(character)
            _existe.value = true
        }
    }

    fun deletefav() {
        viewModelScope.launch {
            borrarCharacterFav(character)
            _existe.value = false
        }
    }

    suspend fun obtenerCharacterX(character: CharacterModel) {
        val characterFav = FavoriteRepository().isFavorite(character.id)?.toDomain()

         _existe.value = characterFav != null

        if (characterFav != null) {
            _characterAct.value = characterFav
        } else {
            _characterAct.value = character
        }
    }

    suspend fun guardarCharacterFav(character: CharacterModel) {
        val characterFav = RMCharacter(
            character.created,
            character.episode,
            character.gender,
            character.id,
            character.image,
            character.location,
            character.name,
            character.origin,
            character.species,
            character.status,
            character.type,
            character.url
        )
        FavoriteRepository().saveAsFavorite(characterFav)
    }

    suspend fun borrarCharacterFav(character: CharacterModel) {
        val characterFav = RMCharacter(
            character.created,
            character.episode,
            character.gender,
            character.id,
            character.image,
            character.location,
            character.name,
            character.origin,
            character.species,
            character.status,
            character.type,
            character.url
        )
        FavoriteRepository().deleteFavorite(characterFav)
    }
}