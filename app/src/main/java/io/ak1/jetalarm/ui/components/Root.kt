package io.ak1.jetalarm.ui.components

import android.util.Log
import android.view.Window
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ak1.jetalarm.ui.components.common.BottomBar
import io.ak1.jetalarm.ui.screens.ClockScreen
import io.ak1.jetalarm.ui.screens.Destinations
import io.ak1.jetalarm.ui.screens.SettingsScreen
import io.ak1.jetalarm.ui.screens.TimeZoneScreen
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
    var bottomBarVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    JetAlarmTheme(isDark) {
        window.statusBarConfig(isDark)
        Surface(color = MaterialTheme.colors.background) {
            val navController = rememberNavController().apply {

                this.addOnDestinationChangedListener { controller, destination, arguments ->
                    Log.e(
                        "navController",
                        "destination $destination ${destination.navigatorName} ${controller.currentDestination?.route}"
                    )
                    if (destination.route == Destinations.HOME_ROUTE) {
                        bottomBarVisibility.value = true
                        return@addOnDestinationChangedListener
                    }
                    bottomBarVisibility.value = false

                }
            }
            Scaffold(
                Modifier.fillMaxSize(),
                bottomBar = {
                    BottomBar(navController = navController, bottomBarVisibility.value)
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Destinations.HOME_ROUTE
                ) {
                    composable(Destinations.HOME_ROUTE) {
                        ClockScreen(navController)
                    }
                    composable(Destinations.HOME_ROUTE) {
                        ClockScreen(navController)
                    }
                    composable(Destinations.TIMEZONE_ROUTE) {
                        TimeZoneScreen(navController)
                    }
                    composable(Destinations.SETTINGS_ROUTE) {
                        SettingsScreen(navController)
                    }

                }
            }
        }
    }
}