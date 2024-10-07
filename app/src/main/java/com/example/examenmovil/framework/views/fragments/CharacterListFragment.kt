package com.example.examenmovil.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenmovil.R
import com.example.examenmovil.framework.adapters.CharacterAdapter
import com.example.examenmovil.framework.viewmodel.CharacterViewModel

class CharacterListFragment : Fragment() {

    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializamos el adaptador vacío
        characterAdapter = CharacterAdapter(emptyList())
        recyclerView.adapter = characterAdapter

        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        // Observa los datos de los personajes del ViewModel
        characterViewModel.characters.observe(viewLifecycleOwner) { characters ->
            // Actualiza el adaptador cuando los datos cambian
            characterAdapter.updateCharacters(characters)
        }

        // Llamada inicial para obtener los personajes del API
        characterViewModel.fetchCharacters()
    }
}
