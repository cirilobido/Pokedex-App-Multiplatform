package presentation.home

import domain.model.OrderByEnum
import domain.model.PokemonModel
import domain.model.PokemonTypeEnum
import domain.model.Resource
import domain.usecases.GetPokemonsUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope


data class HomeUiState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val searchError: Boolean = false,
    val data: List<PokemonModel> = emptyList(),
)

class HomeScreenViewModel(
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _searchState =
        MutableStateFlow("")
    val searchState = _searchState.asStateFlow()

    private val _selectedTypeState =
        MutableStateFlow(PokemonTypeEnum.TYPES)
    val selectedTypeState = _selectedTypeState.asStateFlow()

    private val _selectedFilterState =
        MutableStateFlow(OrderByEnum.NUMBER_LOWEST)
    val selectedFilterState = _selectedFilterState.asStateFlow()

    private var pokemonList: List<PokemonModel> = emptyList()

    private val httpClient: HttpClient = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    init {
        getPokemons()
    }

    override fun onCleared() {
        super.onCleared()
        httpClient.close()
    }

    private fun getPokemons() {
        val getPokemonsUseCase = GetPokemonsUseCase()
        getPokemonsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    pokemonList = result.data ?: emptyList()
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = false,
                            data = pokemonList
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = true
                        )
                    }
                }

                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(
                            loading = true,
                            error = false,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun clearSearchValue() {
        _searchState.value = ""
        applyFilter()
    }

    fun applyFilter(value: Any) {
        if(value is PokemonTypeEnum){
            _selectedTypeState.value = value
        }
        if(value is OrderByEnum){
            _selectedFilterState.value = value
        }
        if(value is String){
            _searchState.value = value
        }
        applyFilter()
    }

    private fun applyFilter() {
        val searchValue = _searchState.value
        var filteredList = pokemonList
        if (searchValue.isNotEmpty()) {
            filteredList = filteredList.filter { pokemon ->
                pokemon.entryNumber.lowercase().contains(searchValue.lowercase())
                        || pokemon.name.lowercase()
                    .contains(searchValue.lowercase())
            }
        }
        if (_selectedTypeState.value != PokemonTypeEnum.TYPES) {
            filteredList = filteredList.filter { pokemon ->
                pokemon.type.contains(_selectedTypeState.value.name)
            }
        }
        filteredList = when (_selectedFilterState.value) {
            OrderByEnum.A_Z -> {
                filteredList.sortedBy {
                    it.name
                }
            }

            OrderByEnum.Z_A -> {
                filteredList.sortedByDescending {
                    it.name
                }
            }

            OrderByEnum.NUMBER_LOWEST -> {
                filteredList.sortedBy {
                    it.entryNumber
                }
            }

            OrderByEnum.NUMBER_HIGHEST -> {
                filteredList.sortedByDescending {
                    it.entryNumber
                }
            }
        }
        _uiState.update {
            it.copy(
                loading = false,
                error = false,
                searchError = filteredList.isEmpty(),
                data = filteredList
            )
        }
    }
}