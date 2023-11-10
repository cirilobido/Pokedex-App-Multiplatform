import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.model.PokemonModel
import io.ktor.util.decodeBase64String
import kotlinx.serialization.json.Json
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import presentation.detail.DetailScreen
import presentation.home.HomeScreen
import presentation.home.HomeScreenViewModel
import ui.theme.PokedexTheme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    PokedexTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavigationHost()
        }
    }
}

@Composable
fun NavigationHost() {
    val navigator = rememberNavigator()
    val homeScreenViewModel = HomeScreenViewModel()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = "/home",
    ) {
        scene(
            route = "/home",
            navTransition = NavTransition(),
        ) {
            HomeScreen(
                onDetailNav = { navigator.navigate("/detail/${it}", options = NavOptions()) },
                homeScreenViewModel = homeScreenViewModel
            )
        }
        scene(
            route = "/detail/{pokemon}",
            navTransition = NavTransition(),
        ) { backStackEntry ->
            val pokemonString: String? =
                backStackEntry.path<String>("pokemon")?.decodeBase64String()
            val pokemon: PokemonModel? =
                pokemonString?.let { Json.decodeFromString(PokemonModel.serializer(), it) }
            DetailScreen(
                navigator = navigator,
                pokemon = pokemon!!
            )
        }
    }
}


expect fun getPlatformName(): String