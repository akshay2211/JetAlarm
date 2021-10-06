package io.ak1.jetalarm.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.pyrocodes.jetalarm.utils.getRadius
import java.util.*

/**
 * Created by akshay on 06/10/21
 * https://ak1.io
 */
@Composable
fun ClockView(timeZone: TimeZone) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)) {
        //handMovements(timeZone)
        staticUi()
    }
}

@Composable
fun staticUi() {
    val color = MaterialTheme.colors.primary
    val color2 = MaterialTheme.colors.background
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = color,
            radius = 15f
        )
        drawCircle(
            color = color2,
            radius = 7f
        )
        drawCircle(
            color = color,
            radius = size.getRadius(0.8f),
            style = Stroke(7f)
        )
    }
}