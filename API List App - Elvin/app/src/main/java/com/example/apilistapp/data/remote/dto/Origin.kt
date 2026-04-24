package com.example.apilistapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    val name: String,
    val url: String
)