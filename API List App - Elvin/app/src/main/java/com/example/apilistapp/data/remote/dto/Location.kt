package com.example.apilistapp.data.remote.dto
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val url: String
)