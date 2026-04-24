package com.example.myapplication.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apilistapp.data.remote.dto.CharacterModel
import com.example.apilistapp.ui.screens.detail.DetailViewModel

class DetailViewModelFactory(private val character: CharacterModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(character) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
