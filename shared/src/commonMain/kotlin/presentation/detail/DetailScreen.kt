package presentation.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import domain.model.PokemonModel
import domain.model.PokemonTypeEnum
import domain.model.formatedName
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.components.CustomCardWithIcon
import presentation.components.IconTextWithBox
import presentation.components.TextWithIcon
import ui.theme.PokedexTheme
import ui.theme.Resources
import ui.theme.colorWhite_40
import ui.theme.femaleGender
import ui.theme.maleGender
import ui.theme.textPrimary
import ui.theme.textSecondary
import ui.theme.textTertiary_20

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DetailScreen(navigator: Navigator, pokemon: PokemonModel) {
    val pokemonType = PokemonTypeEnum.valueOf(pokemon.type.first())
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PokedexTheme.dimens.detailsHeaderSize),
            ) {
                val circleRadius = PokedexTheme.dimens.detailsCircleRadius
                val circleTranslate = PokedexTheme.dimens.detailsCircleTranslate
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(PokedexTheme.dimens.detailsHeaderCircleSize)
                ) {
                    translate(top = circleTranslate) {
                        drawCircle(pokemonType.color, radius = circleRadius.toPx())
                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(pokemonType.iconRoute),
                        contentDescription = null,
                        tint = colorWhite_40,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(alignment = Alignment.Center)
                            .padding(PokedexTheme.dimens.paddingNormal)
                    )
                    KamelImage(
                        asyncPainterResource(pokemon.image),
                        "${pokemon.entryNumber}, ${pokemon.name}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.align(alignment = Alignment.BottomCenter)
                            .size(PokedexTheme.dimens.pokemonDetailSize),
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(PokedexTheme.dimens.paddingNormal)
            ) {
                Text(
                    pokemon.name,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = PokedexTheme.typography.h1,
                    color = textPrimary
                )
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerSmall))
                Text(
                    "Nº${pokemon.entryNumber}",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = PokedexTheme.typography.h4,
                    color = textSecondary
                )
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerNormal))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        PokedexTheme.dimens.spacerNormal
                    ),
                ) {
                    items(pokemon.type) {
                        val type = PokemonTypeEnum.valueOf(it)
                        CustomCardWithIcon(
                            title = type.formatedName(),
                            backgroundColor = type.color,
                            contentColor = type.contentColor,
                            icon = type.iconRoute
                        )
                    }
                }
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
                Text(
                    pokemon.description,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = PokedexTheme.typography.body1,
                    color = textSecondary
                )
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = textTertiary_20
                )
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        PokedexTheme.dimens.spacerNormal
                    )
                ) {
                    IconTextWithBox(
                        modifier = Modifier.weight(1f),
                        icon = Resources.Icons.weight,
                        label = "Weight",
                        boxText = pokemon.weight
                    )
                    IconTextWithBox(
                        modifier = Modifier.weight(1f),
                        icon = Resources.Icons.height,
                        label = "Height",
                        boxText = pokemon.height
                    )
                }
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Gender",
                        style = PokedexTheme.typography.body2,
                        color = textPrimary
                    )
                    Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerNormal))
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = maleGender,
                        backgroundColor = femaleGender,
                        progress = 0.5f
                    )
                    Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerNormal))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextWithIcon(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            alignment = Alignment.CenterStart,
                            icon = Resources.Icons.male,
                            text = "50%"
                        )
                        TextWithIcon(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            alignment = Alignment.CenterEnd,
                            icon = Resources.Icons.female,
                            text = "50%"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
                Text(
                    "Weaknesses",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = PokedexTheme.typography.h5,
                    color = textPrimary
                )
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerMedium))
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(PokedexTheme.dimens.detailsWeaknessesSize), //provide a mutable state boolean here ,
                    columns = GridCells.Fixed(PokedexTheme.dimens.detailsGridCells),
                    verticalArrangement = Arrangement.spacedBy(PokedexTheme.dimens.spacerNormal),
                    horizontalArrangement = Arrangement.spacedBy(PokedexTheme.dimens.spacerNormal)
                ) {
                    items(pokemon.weaknesses) {
                        val type = PokemonTypeEnum.valueOf(it)
                        CustomCardWithIcon(
                            title = type.formatedName(),
                            backgroundColor = type.color,
                            contentColor = type.contentColor,
                            icon = type.iconRoute
                        )
                    }
                }
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerNormal))
            }
        }
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(PokedexTheme.dimens.paddingNormal)
                .size(PokedexTheme.dimens.iconSizeNormal)
                .clip(CircleShape)
                .background(pokemonType.color)
                .clickable { navigator.goBack() },
            tint = pokemonType.contentColor
        )
    }
}



