package presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.PokedexTheme
import ui.theme.Resources

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FilterButton(
    modifier: Modifier = Modifier,
    title: String,
    icon: String = "",
    backgroundColor: Color,
    contentColor: Color,
    onTap: () -> Unit = { }
) {
    Button(
        modifier = modifier.height(PokedexTheme.dimens.buttonHeightNormal),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
        ),
        shape = CircleShape,
        onClick = { onTap() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon.isNotEmpty()) Icon(
                painterResource(icon),
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(PokedexTheme.dimens.iconSizeMedium)
                    .weight(0.2f),
            )
            Text(
                title,
                modifier = Modifier
                    .weight(0.9f),
                textAlign = TextAlign.Center,
                style = PokedexTheme.typography.body2,
                color = contentColor
            )
            Spacer(modifier = Modifier.width(PokedexTheme.dimens.spacerSmall))
            Icon(
                painterResource(Resources.Icons.arrowDown),
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(PokedexTheme.dimens.iconSizeMedium)
                    .weight(0.1f),
            )
        }
    }
}