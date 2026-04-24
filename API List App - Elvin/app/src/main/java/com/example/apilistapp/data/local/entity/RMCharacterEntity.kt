package com.example.apilistapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apilistapp.data.remote.dto.Location
import com.example.apilistapp.data.remote.dto.Origin

@Entity(tableName = "favorites")
data class RMCharacterEntity(
    @PrimaryKey
    val id: Int,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

