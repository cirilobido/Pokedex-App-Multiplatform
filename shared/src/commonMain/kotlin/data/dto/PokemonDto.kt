package com.example.dontgetbored.data.dto

import domain.model.PokemonModel
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    val description: String,
    val entry_number: String,
    val height: String,
    val image: String,
    val name: String,
    val type: List<String>,
    val weaknesses: List<String>,
    val weight: String
)

fun PokemonDto.toPokemonModel(): PokemonModel {
    return PokemonModel(
        description = this.description,
        entryNumber = this.entry_number,
        height = this.height,
        image = "https://raw.githubusercontent.com/cirilobido/Pokedex-API/master/pokemons/${this.entry_number}.png",
        name = this.name,
        type = this.type,
        weaknesses = this.weaknesses,
        weight = this.weight
    )
}
