package com.example.examenmovil.data.network.repository

import com.example.examenmovil.data.network.model.Character
import com.example.examenmovil.data.network.RetrofitInstance

class CharacterRepository {

    suspend fun getCharacters(page: Int): List<Character> {
        // Hacer la llamada a la API con la página correspondiente
        val response = RetrofitInstance.api.getCharacters(page)
        return response.items // Asegúrate de que `items` sea el campo correcto en la respuesta JSON
    }
}