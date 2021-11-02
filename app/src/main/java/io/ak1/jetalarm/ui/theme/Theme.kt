package io.ak1.jetalarm.ui.theme

import android.content.Context
import android.content.res.Configuration
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat
import io.ak1.jetalarm.utils.dataStore
import io.ak1.jetalarm.utils.themePreferenceKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


private val DarkColorPalette = darkColors(
    primary = White200,
    primaryVariant = Grey200,
    secondaryVariant = White200,
    secondary = Red200,
    onPrimary = Black200,
    onSecondary = Black200,
    background = Black200
)

private val LightColorPalette = lightColors(
    primary = Black200,
    primaryVariant = Grey200,
    secondaryVariant = Black200,
    secondary = Red200,
    onPrimary = White200,
    onSecondary = White200,
    background = White200

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
    this.statusBarColor = MaterialTheme.colors.background.toArgb()
}
