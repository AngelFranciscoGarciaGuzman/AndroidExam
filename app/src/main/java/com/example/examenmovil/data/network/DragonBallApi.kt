package com.example.examenmovil.data.network

import com.example.examenmovil.framework.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DragonBallApi {
    @GET("characters")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse
}
