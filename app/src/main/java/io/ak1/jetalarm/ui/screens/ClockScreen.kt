package io.ak1.jetalarm.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ak1.jetalarm.ui.components.ClockView
import java.util.*

/**
 * Created by akshay on 06/10/21
 * https://ak1.io
 */

@Composable
fun ClockScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val timeZone = TimeZone.getDefault()
        ClockView(timeZone)
    }
}