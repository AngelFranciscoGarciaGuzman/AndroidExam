package com.example.examenmovil.data.network.repository

import com.example.examenmovil.data.network.model.Character
import com.example.examenmovil.network.RetrofitInstance

class CharacterRepository {

    suspend fun getCharacters(): List<Character> {
        return RetrofitInstance.api.getCharacters(1) // Puedes ajustar la paginación según sea necesario
    }
}
