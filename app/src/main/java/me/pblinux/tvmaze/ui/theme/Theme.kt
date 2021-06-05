package me.pblinux.tvmaze.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Lavender,
    primaryVariant = DarkLavender,
    secondary = LightGreen,
    background = RichBlack
)

private val LightColorPalette = lightColors(
    primary = Lavender,
    primaryVariant = DarkLavender,
    secondary = LightGreen,
    background = White
)

@Composable
fun TVMazeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography =  if (darkTheme) {
        DarkTypography
    } else {
        LightTypography
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}