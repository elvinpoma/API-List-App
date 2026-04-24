package com.example.apilistapp.data.repository

import com.example.apilistapp.APIListApplication
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.mapper.toEntity
import com.example.apilistapp.domain.RMCharacter

class FavoriteRepository {
    val daoInterface = APIListApplication.database.characterDao()
    suspend fun saveAsFavorite(character: RMCharacter) = daoInterface.addCharacter(character.toEntity())
    suspend fun deleteFavorite(character: RMCharacter)= daoInterface.deleteCharacter(character.toEntity())
    suspend fun isFavorite(characterId: Int) = daoInterface.getCharacterById(characterId)?.toDomain()

    suspend fun getFavorites(): MutableList<RMCharacter> {
        val favoritesList = mutableListOf<RMCharacter>()
        daoInterface.getAllCharacters().forEach {
            favoritesList.add(it.toDomain())
        }
        return favoritesList
    }
}
