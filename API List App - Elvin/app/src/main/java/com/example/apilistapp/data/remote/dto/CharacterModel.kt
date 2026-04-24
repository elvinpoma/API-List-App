package com.example.apilistapp.data.remote.dto

import com.example.apilistapp.data.local.dao.RMCharacterDao
import com.example.apilistapp.data.local.entity.RMCharacterEntity
import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val id: Int,
     val name: String,
     val status: String,
     val species: String,
     val type: String,
     val gender: String,
     val origin: Origin,
     val location: Location,
     val image: String,
     val episode: List<String>,
     val url: String,
     val created: String
) : RMCharacterDao {
    override suspend fun getAllCharacters(): MutableList<RMCharacterEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(characterId: Int): RMCharacterEntity {
        TODO("Not yet implemented")
    }

    override suspend fun addCharacter(character: RMCharacterEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCharacter(character: RMCharacterEntity) {
        TODO("Not yet implemented")
    }
}
