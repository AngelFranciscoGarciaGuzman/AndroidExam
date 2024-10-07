package com.example.examenmovil.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenmovil.R
import com.example.examenmovil.data.network.model.Character
import com.example.examenmovil.framework.adapters.CharacterAdapter
import com.example.examenmovil.framework.viewmodel.CharacterViewModel
import com.example.examenmovil.databinding.FragmentCharacterListBinding

class CharacterListFragment : Fragment() {

    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: FragmentCharacterListBinding // Declara el binding

    // Agrega las razas que deseas mostrar en el Spinner
    private val races = listOf("All", "Saiyan", "Namekian", "Human", "Frieza Race", "Android", "Majin", "God", "Angel", "Unknown", "Nucleico", "Nucleico Benigno", "Evil")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false) // Infla el layout y asigna a binding

        val recyclerView = binding.recyclerView // Accede al RecyclerView desde binding
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializamos el adaptador vacío con un manejador de clics
        characterAdapter = CharacterAdapter(emptyList()) { character ->
            onCharacterClick(character)
        }
        recyclerView.adapter = characterAdapter

        // Configura el Spinner
        setupRaceSpinner()

        observeViewModel()

        return binding.root
    }

    private fun setupRaceSpinner() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, races)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRaces.adapter = adapter // Ahora aquí no debería dar error

        binding.spinnerRaces.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedRace = races[position]
                filterCharactersByRace(selectedRace)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada
            }
        }
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

    private fun onCharacterClick(character: Character) {
        // Aquí puedes manejar lo que quieres hacer al hacer clic en un personaje
        // Toast.makeText(requireContext(), "Seleccionaste a ${character.name}", Toast.LENGTH_SHORT).show()
    }

    private fun filterCharactersByRace(selectedRace: String) {
        // Filtra los personajes por raza
        if (selectedRace == "All") {
            characterAdapter.updateCharacters(characterViewModel.characters.value ?: emptyList())
        } else {
            val filteredCharacters = characterViewModel.characters.value?.filter { it.race == selectedRace }
            characterAdapter.updateCharacters(filteredCharacters ?: emptyList())
        }
    }
}
