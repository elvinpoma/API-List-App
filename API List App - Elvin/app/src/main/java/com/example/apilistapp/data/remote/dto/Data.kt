package com.example.apilistapp.data.remote.dto
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val count: Int,
    val next: String,
    val results: List<RMCharterDto>
)