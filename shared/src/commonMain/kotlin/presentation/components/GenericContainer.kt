package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.PokedexTheme
import ui.theme.textSecondary


@OptIn(ExperimentalResourceApi::class)
@Composable
fun GenericContainer(
    modifier: Modifier = Modifier,
    icon: String,
    text: String,
    isLoading: Boolean = false
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(PokedexTheme.dimens.imageSizeNormal)
                    .padding(PokedexTheme.dimens.paddingSmall)
            )
            if (isLoading) Spacer(
                modifier = Modifier
                    .height(PokedexTheme.dimens.spacerNormal)
            )
            if (isLoading) CircularProgressIndicator()
            Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerNormal))
            Text(
                text,
                textAlign = TextAlign.Center,
                style = PokedexTheme.typography.h3,
                color = textSecondary
            )
        }
    }
}