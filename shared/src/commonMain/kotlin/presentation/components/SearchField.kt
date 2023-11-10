package presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.PokedexTheme
import ui.theme.Resources
import ui.theme.primary
import ui.theme.secondary
import ui.theme.tertiary
import ui.theme.textPrimary
import ui.theme.textSecondary
import ui.theme.textTertiary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (value: String) -> Unit = { },
    onClear: () -> Unit = { },
    isError: Boolean = false
) {
    OutlinedTextField(
        value,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(
                minHeight = PokedexTheme.dimens.textFieldDefault
            ),
        onValueChange = {
            onChange(it)
        },
        shape = CircleShape,
        textStyle = PokedexTheme.typography.subtitle1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = textPrimary,
            cursorColor = secondary,
            placeholderColor = textTertiary,
            leadingIconColor = textSecondary,
            trailingIconColor = primary,
            unfocusedBorderColor = textTertiary,
            focusedBorderColor = tertiary,
        ),
        isError = isError,
        placeholder = {
            Text(
                text = "Search Pokemon...",
                style = PokedexTheme.typography.subtitle2,
            )
        },
        leadingIcon = {
            Icon(
                painterResource(Resources.Icons.search),
                contentDescription = null,
                modifier = Modifier.size(PokedexTheme.dimens.iconSizeSmall)
            )
        },
        trailingIcon = {
            AnimatedVisibility(
                value.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Clear Search Field",
                    modifier = Modifier
                        .size(PokedexTheme.dimens.iconSizeNormal)
                        .clickable { onClear() },
                    tint = textTertiary
                )
            }
        }
    )
}