package com.pyrocodes.jetalarm.ui.theme

import android.content.Context
import android.content.res.Configuration
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
val themePreferenceKey = intPreferencesKey("list_theme")

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

/**
 * extension [isDarkThemeOn] checks the saved theme from preference
 * and returns boolean
 */
fun Context.isDarkThemeOn() = dataStore.data
    .map { preferences ->
        // No type safety.
        preferences[themePreferenceKey] ?: 0
    }


@Composable
fun isSystemInDarkThemeCustom(): Boolean {

    val context = LocalContext.current
    val exampleData = runBlocking { context.dataStore.data.first() }
    val theme =
        context.isDarkThemeOn().collectAsState(initial = exampleData[themePreferenceKey] ?: 0)
    return when (theme.value) {
        2 -> true
        1 -> false
        else -> context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }
}

@Composable
fun Window.statusBarConfig(darkTheme: Boolean) {
    WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars =
        !darkTheme
    this.statusBarColor = MaterialTheme.colors.primary.toArgb()
}
