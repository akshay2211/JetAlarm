package io.ak1.jetalarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.ak1.jetalarm.ui.RootView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootView(window)
        }
    }

    /* override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContent {
             var darkTheme = isLight.collectAsState().value
             JetAlarmTheme(darkTheme) {
                 window?.setUpStatusNavigationBarColors(
                     darkTheme,
                     MaterialTheme.colors.primaryVariant.hashCode()
                 )

                 // JetAlarmApp()
             }
         }
     }*/

/*
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
    }*/
}

