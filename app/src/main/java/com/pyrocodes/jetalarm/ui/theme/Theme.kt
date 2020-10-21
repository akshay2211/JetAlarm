package com.pyrocodes.jetalarm.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
        primary = Color.White,
        primaryVariant = Color.Black,
        secondary = Color.DarkGray,
    background = Color.Black,

   // onSurface = Color.DarkGray
)

private val LightColorPalette = lightColors(
        primary = Color.DarkGray,
        primaryVariant = Color.White,
        secondary = Color.DarkGray,
    background = Color.White,

   // onSurface = Color.White

  /*  background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,*/
)

@Composable
fun JetAlarmTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
    )
}