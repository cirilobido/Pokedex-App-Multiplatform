package data.remote

import com.example.dontgetbored.data.dto.PokemonDto
import domain.usecases.GetPokemonsUseCase
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class PokedexApi {
    suspend fun getPokemons(): List<PokemonDto> {
//        https://raw.githubusercontent.com/cirilobido/Pokedex-API/master/api/poke_api.json
        return KtorService.client
            .get("https://api.npoint.io/2b8d1dac8111cf4d55a7")
            .body<List<PokemonDto>>()
    }
}

abstract class KtorService {
    companion object {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
}