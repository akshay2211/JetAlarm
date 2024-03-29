@file:OptIn(ExperimentalMaterialNavigationApi::class)

package io.ak1.jetalarm.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.ak1.jetalarm.ui.screens.Destinations
import io.ak1.jetalarm.ui.screens.SettingsScreen
import io.ak1.jetalarm.ui.screens.home.HomeScreen
import io.ak1.jetalarm.ui.screens.home.alarm.CreateAlarmScreen
import io.ak1.jetalarm.ui.screens.home.clock.TimeZoneScreen
import io.ak1.jetalarm.ui.theme.JetAlarmTheme

/**
 * Created by akshay on 06/10/21
 * https://ak1.io
 */

@Composable
fun RootView(content: @Composable() () -> Unit) {
    JetAlarmTheme(true) {
        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight
        SideEffect { systemUiController.setSystemBarsColor(Color.Transparent, darkIcons) }
        Surface { content.invoke() }
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun NavigationContainer() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(
            modifier = containerModifier,
            navController = navController,
            startDestination = Destinations.HOME_ROUTE
        ) {
            composable(Destinations.HOME_ROUTE) { HomeScreen(navController) }
            bottomSheet(Destinations.TIMEZONE_ROUTE) {
                TimeZoneScreen(bottomSheetNavigator) {
                    navController.navigateUp()
                }
            }
            composable(Destinations.SETTINGS_ROUTE) {
                SettingsScreen {
                    navController.navigateUp()
                }
            }
            bottomSheet(Destinations.CREATE_ALARM_ROUTE) { CreateAlarmScreen(navController) }
        }
    }
}

private val containerModifier = Modifier
    .fillMaxSize()
    .statusBarsPadding()
    .navigationBarsPadding()

