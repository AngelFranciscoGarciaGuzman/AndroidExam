package com.example.examenmovil.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenmovil.data.network.model.Character
import com.example.examenmovil.data.network.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val characterRepository = CharacterRepository()
    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters
    private var currentPage = 1 // Variable para rastrear la página actual

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                // Obtener los personajes de la página actual
                val characterList = characterRepository.getCharacters(currentPage)
                _characters.postValue(characterList) // Puedes modificar esto para agregar a la lista existente si lo deseas
                currentPage++ // Incrementar la página para la próxima llamada
            } catch (e: Exception) {
                _error.postValue(e.message ?: "An error occurred while fetching characters.")
            }
        }
    }
}








