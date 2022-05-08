package com.appkie.notesafe.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = White,
    primaryVariant = Gray,
    secondary = Blue,
    onPrimary = Black,
    onSecondary = White,
    onBackground = Black,
    onSurface = Black
)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = Gray,
    secondary = Blue,
    onPrimary = Black,
    onSecondary = White,
    onBackground = Black,
    onSurface = Black
)

@Composable
fun NotesafeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}