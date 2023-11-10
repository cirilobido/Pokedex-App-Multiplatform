package presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import domain.model.OrderByEnum
import domain.model.PokemonModel
import domain.model.PokemonTypeEnum
import domain.model.formatedName
import io.ktor.util.encodeBase64
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import presentation.components.CustomCardWithIcon
import presentation.components.FilterButton
import presentation.components.GenericContainer
import presentation.components.PokemonItem
import presentation.components.SearchField
import ui.theme.PokedexTheme
import ui.theme.Resources
import ui.theme.primary
import ui.theme.textPrimary
import ui.theme.textSecondary
import ui.theme.textTertiary
import ui.theme.textTertiary_20

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onDetailNav: (pokemon: String) -> Unit = {},
    homeScreenViewModel: HomeScreenViewModel
) {
    val uiState by homeScreenViewModel.uiState.collectAsState()
    val searchState by homeScreenViewModel.searchState.collectAsState()
    val selectedTypeState by homeScreenViewModel.selectedTypeState.collectAsState()
    val selectedFilterState by homeScreenViewModel.selectedFilterState.collectAsState()
    val listState = rememberLazyGridState()
    var showSortFilter by remember { mutableStateOf(false) }
    val screenScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(
            topStartPercent = 10,
            topEndPercent = 10
        ),
        sheetElevation = PokedexTheme.dimens.elevationNormal,
        sheetContent = {
            ModalBottomContent(
                showSortFilter = showSortFilter,
                onSelect = {
                    homeScreenViewModel.applyFilter(it)
                    screenScope.launch {
                        listState.scrollToItem(0)
                        bottomSheetState.hide()
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PokedexTheme.dimens.paddingNormal),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = textPrimary
                        )
                    ) {
                        append("Poke")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = primary
                        )
                    ) {
                        append("dex")
                    }
                },
                style = PokedexTheme.typography.h2,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerNormal))
            AnimatedVisibility(
                uiState.loading || uiState.error,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                if (uiState.loading) {
                    GenericContainer(
                        icon = Resources.Images.player,
                        text = "Loading pokemons...",
                        isLoading = true
                    )
                }
                if (uiState.error) {
                    GenericContainer(
                        icon = Resources.Images.magicarp,
                        text = "An error occurred while bringing the pokemon list!",
                    )
                }
            }
            AnimatedVisibility(
                !uiState.loading && !uiState.error,
                exit = fadeOut()
            ) {
                Column {
                    SearchField(
                        value = searchState,
                        onChange = { homeScreenViewModel.applyFilter(it) },
                        onClear = { homeScreenViewModel.clearSearchValue() },
                        isError = uiState.searchError
                    )
                    Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = textTertiary_20
                    )
                    Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement
                            .spacedBy(PokedexTheme.dimens.paddingNormal)
                    ) {
                        FilterButton(
                            modifier = Modifier
                                .weight(1f),
                            title = selectedTypeState.formatedName(),
                            icon = selectedTypeState.iconRoute,
                            backgroundColor = selectedTypeState.color,
                            contentColor = selectedTypeState.contentColor,
                            onTap = {
                                showSortFilter = false
                                screenScope.launch {
                                    bottomSheetState.show()
                                }
                            }
                        )
                        FilterButton(
                            modifier = Modifier
                                .weight(1f),
                            title = selectedFilterState.title,
                            backgroundColor = textSecondary,
                            contentColor = Color.White,
                            onTap = {
                                showSortFilter = true
                                screenScope.launch {
                                    bottomSheetState.show()
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
                    AnimatedVisibility(
                        uiState.data.isEmpty(),
                        enter = fadeIn()
                    ) {
                        GenericContainer(
                            icon = Resources.Images.greenDino,
                            text = "No Pokemons Found!",
                        )
                    }
                    AnimatedVisibility(
                        uiState.data.isNotEmpty(),
                        enter = fadeIn()
                    ) {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            state = listState,
                            columns = GridCells.Fixed(PokedexTheme.dimens.gridCells),
                            verticalArrangement = Arrangement.spacedBy(PokedexTheme.dimens.spacerNormal),
                            horizontalArrangement = Arrangement.spacedBy(PokedexTheme.dimens.spacerNormal)
                        ) {
                            items(
                                uiState.data,
                                key = { it.entryNumber }
                            ) { pokemon ->
                                PokemonItem(pokemon = pokemon) {
                                    onDetailNav(
                                        Json.encodeToString(
                                            PokemonModel.serializer(),
                                            pokemon
                                        ).encodeBase64()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ModalBottomContent(
    modifier: Modifier = Modifier,
    showSortFilter: Boolean,
    onSelect: (value: Any) -> Unit = { },
) {
    Column(
        modifier = modifier
            .fillMaxHeight(
                if (showSortFilter)
                    PokedexTheme.dimens.bottomSheetCompact
                else
                    PokedexTheme.dimens.bottomSheetExpanded
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            modifier = Modifier
                .padding(PokedexTheme.dimens.paddingMedium)
                .clip(CircleShape)
                .width(PokedexTheme.dimens.spacerLarge),
            thickness = PokedexTheme.dimens.spacerSmall,
            color = textTertiary
        )
        Text(
            text = if (showSortFilter) "Sort By" else "Pokemon Type",
            style = PokedexTheme.typography.h5,
            modifier = Modifier
                .padding(horizontal = PokedexTheme.dimens.paddingNormal),
            color = textPrimary
        )
        Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = PokedexTheme.dimens.paddingMedium),
            columns = GridCells.Fixed(PokedexTheme.dimens.gridCells),
            verticalArrangement = Arrangement.spacedBy(PokedexTheme.dimens.spacerNormal),
            horizontalArrangement = Arrangement.spacedBy(PokedexTheme.dimens.spacerNormal)
        ) {
            if (showSortFilter) {
                items(
                    OrderByEnum.entries,
                    key = { it.name }
                ) { filter ->
                    CustomCardWithIcon(
                        modifier = Modifier
                            .padding(horizontal = PokedexTheme.dimens.paddingNormal)
                            .height(PokedexTheme.dimens.buttonHeightNormal),
                        title = filter.title,
                        backgroundColor = textSecondary,
                        contentColor = Color.White,
                    ) {
                        onSelect(filter)
                    }
                }
            } else {
                items(
                    PokemonTypeEnum.entries,
                    key = { it.name }
                ) { type ->
                    CustomCardWithIcon(
                        modifier = Modifier
                            .padding(horizontal = PokedexTheme.dimens.paddingNormal)
                            .height(PokedexTheme.dimens.buttonHeightNormal),
                        title = type.formatedName(),
                        backgroundColor = type.color,
                        contentColor = type.contentColor,
                        icon = type.iconRoute
                    ) {
                        onSelect(type)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
    }
}