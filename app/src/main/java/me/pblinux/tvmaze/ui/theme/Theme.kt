package me.pblinux.tvmaze.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Lavender,
    primaryVariant = DarkLavender,
    secondary = LightGreen,
    background = RichBlack,
    surface = Color.Transparent,
)

private val LightColorPalette = lightColors(
    primary = Lavender,
    primaryVariant = DarkLavender,
    secondary = LightGreen,
    background = White,
    surface = Color.Transparent,
)

@Composable
fun TVMazeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !darkTheme

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

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}