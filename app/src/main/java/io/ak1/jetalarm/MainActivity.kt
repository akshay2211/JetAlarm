package io.ak1.jetalarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.ak1.jetalarm.ui.components.RootView
import io.ak1.jetalarm.ui.screens.ClockScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootView(window)
        }
    }
    @Composable
    @Preview
    fun Preview() {
        ClockScreen()
    }
}

