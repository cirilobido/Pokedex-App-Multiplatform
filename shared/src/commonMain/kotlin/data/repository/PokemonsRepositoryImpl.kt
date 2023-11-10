package data.repository

import com.example.dontgetbored.data.dto.toPokemonModel
import data.remote.PokedexApi
import domain.model.PokemonModel
import domain.repository.PokemonsRepository

class PokemonsRepositoryImpl() :
    PokemonsRepository {
    override suspend fun getPokemons(): List<PokemonModel> {
        val api = PokedexApi()
        return api.getPokemons().map { it.toPokemonModel() }
    }
}