package com.pyrocodes.jetalarm.ui.screens.clock

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.pyrocodes.jetalarm.utils.getRadius
import com.pyrocodes.jetalarm.utils.oneMinuteRadians
import com.pyrocodes.jetalarm.utils.pieByTwo
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by akshay on 19,October,2020
 * akshay2211@github.io
 */

val handProgress = FloatPropKey()

@Composable
fun showClock() {
    Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
        handMovements()
    }
}

@Composable
fun handMovements() {
    var state = transition(definition = handAnimations, initState = 0, toState = 1) {}
    hands(state[handProgress])
}

@Composable()
fun hands(fl: Float) {
var color = MaterialTheme.colors.primary
    Canvas(modifier = Modifier.fillMaxSize()) {
        var progression = ((System.currentTimeMillis() % 1000) / 1000.0)
        Log.e("fl", " -> $fl   $progression")


        val centerX = (size.width / 2)
        val centerY = (size.height / 2)

        val sec = Calendar.getInstance().get(Calendar.SECOND)
        val min = Calendar.getInstance().get(Calendar.MINUTE)
        var hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour

        val animatedSecond = sec + progression
        val animatedMinute = min + animatedSecond / 60
        val animatedHour = (hour + (animatedMinute / 60)) * 5f

        secondHand(centerX, centerY, size.getRadius(0.8f), animatedSecond,color)
        minuteHand(centerX, centerY, size.getRadius(0.6f), animatedMinute,color)
        hourHand(centerX, centerY, size.getRadius(0.5f), animatedHour,color)
        Log.e("animated Second Minute", "$animatedSecond $animatedMinute $animatedHour")
    }
}

fun DrawScope.secondHand(
    centerX: Float,
    centerY: Float,
    clockRadius: Float,
    animatedSecond: Double,
    color:Color
) {
    val degree = animatedSecond * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * clockRadius
    val y = centerY + sin(degree) * clockRadius
    drawLine(
        start = Offset(centerX, centerY),
        end = Offset(x.toFloat(), y.toFloat()),
        color = color,
        strokeWidth = 6f
    )
}

fun DrawScope.minuteHand(
    centerX: Float,
    centerY: Float,
    clockRadius: Float,
    animatedMinute: Double,
    color:Color
) {
    val degree = animatedMinute * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * clockRadius
    val y = centerY + sin(degree) * clockRadius
    drawLine(
        start = Offset(centerX, centerY),
        end = Offset(x.toFloat(), y.toFloat()),
        color = color,
        strokeWidth = 10f
    )
}

fun DrawScope.hourHand(centerX: Float, centerY: Float, clockRadius: Float, animatedHour: Double,
                       color:Color) {
    val degree = animatedHour * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * clockRadius
    val y = centerY + sin(degree) * clockRadius
    drawLine(
        start = Offset(centerX, centerY),
        end = Offset(x.toFloat(), y.toFloat()),
        color = color,
        strokeWidth = 12f
    )
}


val handAnimations = transitionDefinition<Int> {
    state(0) {
        this[handProgress] = 0f
    }

    state(1) {
        this[handProgress] = 1f
    }

    transition(fromState = 0, toState = 1) {
        handProgress using repeatable(
            iterations = AnimationConstants.Infinite,
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    }
}