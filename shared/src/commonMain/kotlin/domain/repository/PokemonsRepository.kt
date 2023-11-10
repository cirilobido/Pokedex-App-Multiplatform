package domain.repository

import domain.model.PokemonModel

interface PokemonsRepository {
    suspend fun getPokemons(): List<PokemonModel>
}