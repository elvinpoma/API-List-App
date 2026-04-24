package com.example.apilistapp.data.remote.dto
import kotlinx.serialization.Serializable

@Serializable
data class RMCharterDto(
    val info: Info,
    val results: List<CharacterModel>
)