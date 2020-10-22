package com.pyrocodes.jetalarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.pyrocodes.jetalarm.ui.components.TabBarComponent
import com.pyrocodes.jetalarm.ui.theme.JetAlarmTheme
import com.pyrocodes.jetalarm.utils.setUpStatusNavigationBarColors


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isLight by mutableStateOf(true)

        setContent {
            JetAlarmApp {
                JetAlarmTheme(isLight) {
                    window.setUpStatusNavigationBarColors(
                        isLight,
                        MaterialTheme.colors.primaryVariant.hashCode()
                    )
                    val allScreens = JetAlarmScreens.values().toList()
                    var currentScreen by savedInstanceState { JetAlarmScreens.ClockScreen }
                    Scaffold(
                        topBar = {
                            TabBarComponent(
                                allScreens = allScreens,
                                onTabSelected = { screen -> currentScreen = screen },
                                currentScreen = currentScreen
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = {
                                isLight = !isLight
                            }) {

                            }
                        }
                    ) { innerPadding ->
                        Box(Modifier.padding(innerPadding)) {
                            currentScreen.content(onScreenChange = { screen ->
                                currentScreen = screen
                            })
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun JetAlarmApp(
    children: @Composable() () -> Unit
) {
    children()
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetAlarmTheme {
        Greeting("Android")
    }
}

