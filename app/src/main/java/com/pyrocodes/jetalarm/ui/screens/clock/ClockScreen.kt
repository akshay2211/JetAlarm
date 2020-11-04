package com.pyrocodes.jetalarm.ui.screens.clock

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.pyrocodes.jetalarm.ui.components.showClock
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by akshay on 20,October,2020
 * akshay2211@github.io
 */
var CLOCK_SCREEN = "ClockScreen"

@Composable
fun clockScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        val timeZone = TimeZone.getDefault()
        showTextClock(timeZone)
        showClock(timeZone)
        Text(
            text = timeZone.displayName,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp)
        )
    }
}

@Composable
fun showTextClock(timeZone: TimeZone) {
    textClock(0, timeZone)
}

@Composable
fun textClock(value: Int, timeZone: TimeZone) {
    var date = SimpleDateFormat("hh:MM aa").format(Calendar.getInstance(timeZone).time)
    Text(
        text = date,
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxWidth()
            .padding(12.dp)
    )
}

@Preview
@Composable
fun preview() {
    clockScreen()
}
