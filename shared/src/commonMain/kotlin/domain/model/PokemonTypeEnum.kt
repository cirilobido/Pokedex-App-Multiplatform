package domain.model

import androidx.compose.ui.graphics.Color
import com.example.dontgetbored.data.dto.PokemonDto
import ui.theme.Resources
import ui.theme.bug
import ui.theme.dark
import ui.theme.dragon
import ui.theme.electric
import ui.theme.fairy
import ui.theme.fighting
import ui.theme.fire
import ui.theme.flying
import ui.theme.ghost
import ui.theme.grass
import ui.theme.ground
import ui.theme.ice
import ui.theme.normal
import ui.theme.poison
import ui.theme.psychic
import ui.theme.rock
import ui.theme.steel
import ui.theme.textPrimary
import ui.theme.unknownType
import ui.theme.water

enum class PokemonTypeEnum(val color: Color, val contentColor: Color, val iconRoute: String) {
    TYPES(color = unknownType, contentColor = Color.White, iconRoute = Resources.Icons.pokeball),
    NORMAL(color = normal, contentColor = Color.White, iconRoute = Resources.Icons.normal),
    FIRE(color = fire, contentColor = textPrimary, iconRoute = Resources.Icons.fire),
    WATER(color = water, contentColor = Color.White, iconRoute = Resources.Icons.water),
    ELECTRIC(color = electric, contentColor = textPrimary, iconRoute = Resources.Icons.electric),
    GRASS(color = grass, contentColor = Color.White, iconRoute = Resources.Icons.grass),
    ICE(color = ice, contentColor = textPrimary, iconRoute = Resources.Icons.ice),
    FIGHTING(color = fighting, contentColor = Color.White, iconRoute = Resources.Icons.fighting),
    POISON(color = poison, contentColor = Color.White, iconRoute = Resources.Icons.poison),
    GROUND(color = ground, contentColor = Color.White, iconRoute = Resources.Icons.ground),
    FLYING(color = flying, contentColor = textPrimary, iconRoute = Resources.Icons.flying),
    PSYCHIC(color = psychic, contentColor = Color.White, iconRoute = Resources.Icons.psychic),
    BUG(color = bug, contentColor = Color.White, iconRoute = Resources.Icons.bug),
    ROCK(color = rock, contentColor = textPrimary, iconRoute = Resources.Icons.rock),
    GHOST(color = ghost, contentColor = Color.White, iconRoute = Resources.Icons.ghost),
    DRAGON(color = dragon, contentColor = Color.White, iconRoute = Resources.Icons.dragon),
    DARK(color = dark, contentColor = Color.White, iconRoute = Resources.Icons.dark),
    STEEL(color = steel, contentColor = Color.White, iconRoute = Resources.Icons.steel),
    FAIRY(color = fairy, contentColor = textPrimary, iconRoute = Resources.Icons.fairy)
}

fun PokemonTypeEnum.formatedName(): String {
    return name.lowercase()
        .replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase()
            } else {
                it.toString()
            }
        }
}