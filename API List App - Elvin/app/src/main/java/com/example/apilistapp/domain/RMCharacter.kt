package com.example.apilistapp.domain

import com.example.apilistapp.data.remote.dto.Location
import com.example.apilistapp.data.remote.dto.Origin
import kotlinx.serialization.Serializable

@Serializable
data class RMCharacter(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)