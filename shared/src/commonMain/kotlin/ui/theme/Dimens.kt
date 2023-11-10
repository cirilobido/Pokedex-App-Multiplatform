package ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val borderSmall: Dp = 1.dp,
    val bottomSheetCompact: Float = 0.5f,
    val bottomSheetExpanded: Float = 0.8f,
    val buttonHeightNormal: Dp = 48.dp,
    val detailsGridCells: Int = 2,
    val detailsCircleRadius: Dp = 300.dp,
    val detailsCircleTranslate: Float = -500f,
    val detailsHeaderCircleSize: Dp = 200.dp,
    val detailsHeaderSize: Dp = 250.dp,
    val detailsWeaknessesSize: Dp = 120.dp,
    val elevationNone: Dp = 0.dp,
    val elevationNormal: Dp = 2.dp,
    val gridCells: Int = 1,
    val iconSizeSmall: Dp = 16.dp,
    val iconSizeNormal: Dp = 24.dp,
    val iconSizeMedium: Dp = 36.dp,
    val imageSizeNormal: Dp = 128.dp,
    val paddingSmall: Dp = 4.dp,
    val paddingNormal: Dp = 8.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingExtraLarge: Dp = 40.dp,
    val pokemonIconTypeSize: Dp = 104.dp,
    val pokemonItemContainerSize: Dp = 120.dp,
    val pokemonSize: Dp = 104.dp,
    val pokemonDetailSize: Dp = 224.dp,
    val roundedShapeNormal: Dp = 20.dp,
    val spacerSmall: Dp = 4.dp,
    val spacerNormal: Dp = 8.dp,
    val spacerMedium: Dp = 16.dp,
    val spacerLarge: Dp = 40.dp,
    val textFieldDefault: Dp = 52.dp
)

val DefaultsDimens = Dimens()

val TabletDimens = Dimens(
    bottomSheetExpanded = 0.5f,
    detailsCircleTranslate = -300f,
    detailsWeaknessesSize = 160.dp,
    gridCells = 2,
    iconSizeSmall = 24.dp,
    iconSizeNormal = 36.dp,
    iconSizeMedium = 48.dp,
    imageSizeNormal = 200.dp,
    paddingSmall = 8.dp,
    paddingNormal = 16.dp,
    paddingMedium = 24.dp,
    paddingExtraLarge = 56.dp,
    pokemonIconTypeSize = 132.dp,
    pokemonItemContainerSize = 144.dp,
    pokemonSize = 232.dp,
    roundedShapeNormal = 40.dp,
    spacerSmall = 8.dp,
    spacerNormal = 16.dp,
    spacerMedium = 24.dp,
    spacerLarge = 56.dp,
)


val DefaultsDimensIOS = Dimens(
    pokemonItemContainerSize = 110.dp,
)

val TabletDimensIOS = Dimens(
    bottomSheetExpanded = 0.5f,
    detailsCircleTranslate = -300f,
    detailsWeaknessesSize = 160.dp,
    gridCells = 2,
    iconSizeSmall = 24.dp,
    iconSizeNormal = 36.dp,
    iconSizeMedium = 48.dp,
    imageSizeNormal = 200.dp,
    paddingSmall = 8.dp,
    paddingNormal = 16.dp,
    paddingMedium = 24.dp,
    paddingExtraLarge = 56.dp,
    pokemonIconTypeSize = 132.dp,
    pokemonItemContainerSize = 144.dp,
    pokemonSize = 232.dp,
    roundedShapeNormal = 40.dp,
    spacerSmall = 8.dp,
    spacerNormal = 16.dp,
    spacerMedium = 24.dp,
    spacerLarge = 56.dp,
)
