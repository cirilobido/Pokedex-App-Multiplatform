package presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.PokedexTheme
import ui.theme.textPrimary
import ui.theme.textSecondary
import ui.theme.textTertiary_20

@OptIn(ExperimentalResourceApi::class)
@Composable
fun IconTextWithBox(modifier: Modifier = Modifier, icon: String, label: String, boxText: String) {
    Column(
        modifier = modifier
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
                label,
                modifier = Modifier
                    .fillMaxWidth(),
                style = PokedexTheme.typography.subtitle1,
                color = textSecondary
            )
        }
        Spacer(modifier = Modifier.height(PokedexTheme.dimens.spacerNormal))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(PokedexTheme.dimens.buttonHeightNormal)
                .border(
                    PokedexTheme.dimens.borderSmall,
                    textTertiary_20,
                    RoundedCornerShape(PokedexTheme.dimens.spacerMedium)
                )
                .clip(RoundedCornerShape(PokedexTheme.dimens.spacerMedium)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                boxText,
                modifier = Modifier
                    .padding(PokedexTheme.dimens.paddingSmall),
                style = PokedexTheme.typography.h5,
                color = textPrimary
            )
        }
    }
}