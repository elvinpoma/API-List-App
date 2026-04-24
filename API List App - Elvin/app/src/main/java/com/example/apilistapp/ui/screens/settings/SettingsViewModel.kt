package com.example.apilistapp.ui.screens.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apilistapp.data.repository.FavoriteRepository
import com.example.apilistapp.data.repository.SettingsRepository
import com.example.apilistapp.ui.screens.favorites.FavoriteViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SettingsViewModel(context: Context) : ViewModel() {
    private val basedatos = FavoriteRepository()
    private val repository = SettingsRepository(context)

    //ESTADOS DEL DarkMode
    private val _isDarkMode = MutableStateFlow<Boolean>(repository.isDarkModeEnabled())
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    //ESTADOS DEL ListMode o del GridMode
    private val _isList = MutableStateFlow<Boolean>(repository.islitsModeEnabled())
    val isList: StateFlow<Boolean> = _isList

    private val _borrarDatos = MutableStateFlow<Boolean>(false)
    val borrarDatos: StateFlow<Boolean> = _borrarDatos



    fun borrarFavs() {
        _borrarDatos.value = true
    }
    fun reiniciarBorrador(){
        _borrarDatos.value = false
    }
    fun onThemeChanged(enabled: Boolean) {
        repository.setDarkMode(enabled)
        _isDarkMode.value = enabled
    }

    fun onListChanged(enabled: Boolean) {
        repository.setlitsMode(enabled)
        _isList.value = enabled
    }

     fun borrarFavoritos() {
        viewModelScope.launch {
            val listaDeCHaracters = basedatos.getFavorites()
            listaDeCHaracters.forEach { character->
                basedatos.deleteFavorite(character)
            }
            _borrarDatos.value = false
        }
    }
}