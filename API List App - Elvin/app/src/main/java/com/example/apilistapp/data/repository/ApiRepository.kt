package com.example.apilistapp.data.repository

import com.example.apilistapp.data.remote.ApiInterface

class ApiRepository {
    private val api = ApiInterface.create()
    suspend fun getAllCharacters(page: Int = 1) = api.getData(page)
}