package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonModel(
    val description: String,
    val entryNumber: String,
    val height: String,
    val image: String,
    val name: String,
    val type: List<String>,
    val weaknesses: List<String>,
    val weight: String
)