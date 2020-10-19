package com.pyrocodes.jetalarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.pyrocodes.jetalarm.ui.components.TabBarComponent
import com.pyrocodes.jetalarm.ui.screens.clock.showClock
import com.pyrocodes.jetalarm.ui.theme.JetAlarmTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           JetAlarmApp()
        }
}
}

@Composable
fun JetAlarmApp() {
    JetAlarmTheme(true) {
        val allScreens = JetAlarmScreens.values().toList()
        var currentScreen by savedInstanceState { JetAlarmScreens.ClockScreen }
        Scaffold(
            topBar = {
                TabBarComponent(
                    allScreens = allScreens,
                    onTabSelected = { screen -> currentScreen = screen },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                currentScreen.content(onScreenChange = { screen -> currentScreen = screen })
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!",modifier = Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetAlarmTheme {
        Greeting("Android")
    }
}