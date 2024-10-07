package com.example.examenmovil.framework.views.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenmovil.data.network.model.Character
import com.example.examenmovil.databinding.ActivityMainBinding
import com.example.examenmovil.framework.viewmodel.CharacterViewModel
import com.example.examenmovil.framework.adapters.CharacterAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter: CharacterAdapter
    private val characterViewModel: CharacterViewModel by viewModels()

    private val races = listOf(
        "All",
        "Saiyan",
        "Namekian",
        "Human",
        "Frieza Race",
        "Android",
        "Majin",
        "God",
        "Angel",
        "Unknown",
        "Nucleico",
        "Nucleico Benigno",
        "Evil"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el adaptador y el RecyclerView
        characterAdapter = CharacterAdapter(emptyList()) { character ->
            onCharacterClick(character) // Manejar el clic en el personaje
        }
        binding.recyclerView.adapter = characterAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        // Agregar el OnScrollListener para cargar más personajes
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1) {
                    // Hemos llegado al final, cargar más personajes
                    characterViewModel.fetchCharacters()
                }
            }
        })

        // Observar cambios en la lista de personajes
        characterViewModel.characters.observe(this, { characterList ->
            characterList?.let {
                characterAdapter.updateCharacters(it) // Actualiza el adaptador con la nueva lista
            }
        })

        // Cargar los primeros personajes
        characterViewModel.fetchCharacters()
    }


    private fun filterCharactersByRace(selectedRace: String) {
        if (selectedRace == "All") {
            characterViewModel.fetchCharacters() // Llama para obtener todos los personajes
        } else {
            val filteredCharacters =
                characterViewModel.characters.value?.filter { it.race == selectedRace }
            characterAdapter.updateCharacters(filteredCharacters ?: emptyList())
        }
    }

    private fun onCharacterClick(character: Character) {
        // Aquí puedes manejar lo que quieres hacer al hacer clic en un personaje
        Toast.makeText(this, "Seleccionaste a ${character.name}", Toast.LENGTH_SHORT).show()
    }
}
