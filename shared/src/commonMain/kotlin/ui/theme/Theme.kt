package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import domain.model.PlatformsEnum.Android
import getPlatformName

/*
Screen Sizes
*/
private const val COMPACT_SCREEN_WIDTH = 600

private val LocalDimens = staticCompositionLocalOf { DefaultsDimens }
private val LocalTypography = staticCompositionLocalOf { Typography }

private val DarkColorScheme = darkColors(
    primary = primary,
    onPrimary = primary,
    secondary = secondary,
    onSecondary = tertiary,
    surface = background,
    background = background,
)

private val LightColorScheme = lightColors(
    primary = primary,
    onPrimary = primary,
    secondary = secondary,
    onSecondary = tertiary,
    surface = background,
    background = background,
)

@Composable
private fun ProvideDimens(
    dimensions: Dimens,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalDimens provides dimensionSet, content = content)
}

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    BoxWithConstraints {

        val dimensions = getDimens(currentWidth = constraints.maxWidth)

        ProvideDimens(dimensions = dimensions) {
            MaterialTheme(
                colors = colorScheme,
                typography = Typography,
                content = content
            )
        }
    }
}

private fun getDimens(currentWidth: Int): Dimens {
    val isAndroid = getPlatformName() == Android.name
    return if (currentWidth > COMPACT_SCREEN_WIDTH)
        if (isAndroid) TabletDimens else TabletDimensIOS
    else
        if (isAndroid) DefaultsDimens else DefaultsDimensIOS
}

object PokedexTheme {
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
}