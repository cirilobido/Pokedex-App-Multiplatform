package presentation.components

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import domain.model.PokemonModel
import domain.model.PokemonTypeEnum
import domain.model.formatedName
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.PokedexTheme
import ui.theme.colorWhite_40
import ui.theme.textPrimary
import ui.theme.textSecondary


@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: PokemonModel,
    onTap: () -> Unit = { }
) {
    val pokemonType = PokemonTypeEnum.valueOf(pokemon.type.first())
    Card(
        modifier = modifier.height(PokedexTheme.dimens.pokemonItemContainerSize),
        elevation = PokedexTheme.dimens.elevationNone,
        shape = RoundedCornerShape(PokedexTheme.dimens.roundedShapeNormal),
        backgroundColor = pokemonType.color.copy(alpha = 0.2f),
        onClick = {
            onTap()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = PokedexTheme.dimens.paddingMedium,
                        start = PokedexTheme.dimens.paddingMedium,
                        bottom = PokedexTheme.dimens.paddingMedium,
                        end = PokedexTheme.dimens.paddingNormal
                    )
                    .weight(1.6f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Nº${pokemon.entryNumber}",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = PokedexTheme.typography.body2,
                    color = textSecondary
                )
                Text(
                    pokemon.name,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = PokedexTheme.typography.h2,
                    color = textPrimary
                )
                Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerSmall))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(PokedexTheme.dimens.spacerSmall),
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
            }
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                elevation = PokedexTheme.dimens.elevationNone,
                shape = RoundedCornerShape(PokedexTheme.dimens.roundedShapeNormal),
                backgroundColor = pokemonType.color
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(PokedexTheme.dimens.paddingSmall),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(pokemonType.iconRoute),
                        contentDescription = null,
                        tint = colorWhite_40,
                        modifier = Modifier.size(PokedexTheme.dimens.pokemonIconTypeSize)
                    )
                    KamelImage(
                        asyncPainterResource(pokemon.image),
                        "Pokemon entry number:${pokemon.entryNumber}, ${pokemon.name}",
                        modifier = Modifier.size(PokedexTheme.dimens.pokemonSize),
                    )
                }
            }
        }
    }
}