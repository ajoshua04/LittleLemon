package com.littlelemon.littlelemon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

private val DarkColorPalette = darkColors(
    primary = BrandColors.Primary,
    primaryVariant = BrandColors.PrimaryVariant,
    secondary = BrandColors.Secondary,
    secondaryVariant = BrandColors.SecondaryVariant,


    )

private val LightColorPalette = lightColors(
    primary = BrandColors.Primary,
    primaryVariant = BrandColors.PrimaryVariant,
    secondary = BrandColors.Secondary,
    secondaryVariant = BrandColors.SecondaryVariant,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun LittleLemonTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content,
    )
}