package com.example.apilistapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.apilistapp.data.local.entity.RMCharacterEntity


@Dao
interface RMCharacterDao {
    @Query("SELECT * FROM favorites")
    suspend fun getAllCharacters(): MutableList<RMCharacterEntity>
    @Query("SELECT * FROM favorites WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): RMCharacterEntity?
    @Insert
    suspend fun addCharacter(character: RMCharacterEntity)
    @Delete
    suspend fun deleteCharacter(character: RMCharacterEntity)

}
