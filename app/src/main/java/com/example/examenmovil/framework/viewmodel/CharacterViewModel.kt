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

    fun fetchCharacters() {
        viewModelScope.launch {
            val characterList = characterRepository.getCharacters()
            _characters.postValue(characterList)
        }
    }
}
