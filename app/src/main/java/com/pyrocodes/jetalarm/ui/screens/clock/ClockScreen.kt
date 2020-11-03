package com.pyrocodes.jetalarm.ui.screens.clock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pyrocodes.jetalarm.ui.components.showClock
import java.util.*

/**
 * Created by akshay on 20,October,2020
 * akshay2211@github.io
 */
var CLOCK_SCREEN = "ClockScreen"
@Composable
fun clockScreen(){
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        showClock(TimeZone.getDefault())
    }
}