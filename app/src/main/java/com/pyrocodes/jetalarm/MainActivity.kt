package com.pyrocodes.jetalarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.*
import com.pyrocodes.jetalarm.ui.components.TabBarComponent
import com.pyrocodes.jetalarm.ui.screens.clock.CLOCK_SCREEN
import com.pyrocodes.jetalarm.ui.screens.clock.ClockViewModel
import com.pyrocodes.jetalarm.ui.screens.clock.clockScreen
import com.pyrocodes.jetalarm.ui.screens.stopwatch.STOPWATCH_SCREEN
import com.pyrocodes.jetalarm.ui.theme.JetAlarmTheme
import com.pyrocodes.jetalarm.utils.setUpStatusNavigationBarColors
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    var isLight = MutableStateFlow(true)
    val clockViewModel by inject<ClockViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme = isLight.collectAsState().value
            JetAlarmTheme(darkTheme) {
                window?.setUpStatusNavigationBarColors(
                    darkTheme,
                    MaterialTheme.colors.primaryVariant.hashCode()
                )
                JetAlarmApp()
            }
        }
    }


    @Composable
    fun JetAlarmApp() {

        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                TabBarComponent(
                    onTabSelected = { screen ->
                        navController.popBackStack(navController.graph.startDestination, false)
                        // This if check gives us a "singleTop" behavior where we do not create a
                        // second instance of the composable if we are already on that destination
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route)
                        }
                    },
                    currentScreen = "" + currentRoute

                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    isLight.value = !isLight.value
                }) {

                }
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                NavHost(navController, startDestination = CLOCK_SCREEN) {
                    composable(CLOCK_SCREEN) { clockScreen(clockViewModel) }
                    composable(STOPWATCH_SCREEN) { }
                }
            }
        }
    }
}

