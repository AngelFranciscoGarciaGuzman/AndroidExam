package com.example.examenmovil.framework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examenmovil.data.network.model.Character
import com.example.examenmovil.databinding.ItemCharacterBinding


class CharacterAdapter(private var characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    // Método para actualizar la lista de personajes
    fun updateCharacters(newCharacters: List<Character>) {
        characters = characters + newCharacters // Combina las listas en lugar de reemplazar
        notifyDataSetChanged() // Notifica que los datos han cambiado
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.characterName.text = character.name
            Glide.with(binding.root.context).load(character.image).into(binding.characterImage)
        }

    }
}
