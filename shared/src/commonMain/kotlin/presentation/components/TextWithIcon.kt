package presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.PokedexTheme
import ui.theme.textSecondary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    icon: String,
    text: String
) {
    Box(
        modifier = modifier,
        contentAlignment = alignment
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(icon),
                contentDescription = null,
                tint = textSecondary,
                modifier = Modifier.size(PokedexTheme.dimens.iconSizeSmall)
            )
            Spacer(modifier = Modifier.width(PokedexTheme.dimens.spacerNormal))
            Text(
                text,
                style = PokedexTheme.typography.caption,
                color = textSecondary
            )
        }
    }
}