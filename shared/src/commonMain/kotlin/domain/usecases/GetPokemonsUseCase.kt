package domain.usecases

import data.repository.PokemonsRepositoryImpl
import domain.model.PokemonModel
import domain.model.Resource
import domain.repository.PokemonsRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonsUseCase(private val repository: PokemonsRepository = PokemonsRepositoryImpl()) {
    operator fun invoke(): Flow<Resource<List<PokemonModel>>> = flow {
        try {
            emit(Resource.Loading())
            emit(
                Resource.Success(
                    data = repository.getPokemons()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message ?: "An unknown error occurred."))
        }
    }
}