package com.example.apilistapp.data.remote

import com.example.apilistapp.data.remote.dto.RMCharterDto
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("character")
    suspend fun getData(
        @Query("page") page: Int? = 1
    ): Response<RMCharterDto>

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}
