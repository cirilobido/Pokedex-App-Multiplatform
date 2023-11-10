package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.PokedexTheme

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun CustomCardWithIcon(
    modifier: Modifier = Modifier,
    title: String,
    icon: String = "",
    backgroundColor: Color,
    contentColor: Color,
    onTap: () -> Unit = { }
) {
    Card(
        modifier = modifier,
        elevation = PokedexTheme.dimens.elevationNone,
        shape = CircleShape,
        backgroundColor = backgroundColor,
        onClick = { onTap() }
    ) {
        Row(
            modifier = Modifier
                .padding(PokedexTheme.dimens.paddingSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon.isNotEmpty()) Icon(
                painterResource(icon),
                contentDescription = null,
                tint = backgroundColor,
                modifier = Modifier
                    .size(PokedexTheme.dimens.iconSizeNormal)
                    .clip(CircleShape)
                    .background(contentColor)
                    .padding(PokedexTheme.dimens.paddingSmall)
            )
            if (icon.isNotEmpty()) Spacer(modifier = Modifier.width(PokedexTheme.dimens.spacerSmall))
            Text(
                title,
                style = PokedexTheme.typography.body2,
                color = contentColor,
            )
            Spacer(modifier = Modifier.width(PokedexTheme.dimens.spacerSmall))
        }
    }
}