package com.example.examenmovil.network

import com.example.examenmovil.data.network.model.Character
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {
    @GET("characters")
    suspend fun getCharacters(@Query("page") page: Int): List<Character>
}
