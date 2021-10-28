package io.ak1.jetalarm.ui.components

import android.view.Window
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ak1.jetalarm.ui.screens.ClockScreen
import io.ak1.jetalarm.ui.screens.Destinations
import io.ak1.jetalarm.ui.screens.SettingsScreen
import io.ak1.jetalarm.ui.theme.JetAlarmTheme
import io.ak1.jetalarm.ui.theme.isSystemInDarkThemeCustom
import io.ak1.jetalarm.ui.theme.statusBarConfig

/**
 * Created by akshay on 06/10/21
 * https://ak1.io
 */

@Composable
fun RootView(window: Window) {
    val isDark = isSystemInDarkThemeCustom()
    JetAlarmTheme(isDark) {
        window.statusBarConfig(isDark)
        Surface(color = MaterialTheme.colors.background) {
            val navController = rememberNavController()
            Scaffold(
                Modifier.fillMaxSize(),
                bottomBar = {
                    BottomBar(navController = navController)
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Destinations.HOME_ROUTE
                ) {
                    composable(Destinations.HOME_ROUTE) {
                        ClockScreen()
                    }
                    composable(Destinations.SETTINGS_ROUTE) {
                        SettingsScreen(navController)
                    }

                }
            }
        }
    }
}