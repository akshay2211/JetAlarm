package com.pyrocodes.jetalarm.ui.screens.clock

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.min
import com.pyrocodes.jetalarm.oneMinuteRadians
import com.pyrocodes.jetalarm.pieByTwo
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
    Box(modifier = Modifier.size(Dp(300f))) {
        handMovements()
    }


}
@Composable
fun handMovements(){
    var state = transition(definition = handAnimations, initState = 0, toState = 1) {}
    hands(state[handProgress])
}

@Composable()
fun hands(fl: Float) {

    Canvas(modifier = Modifier.fillMaxSize()) {
        var progression = ((System.currentTimeMillis() % 1000) / 1000.0)
        Log.e("fl"," -> $fl   $progression")
        val clockRadius = 1f * min(Dp(size.width / 2), Dp(size.height / 2)).value

        val centerX = (size.width / 2)
        val centerY = (size.height / 2)

        val sec = Calendar.getInstance().get(Calendar.SECOND)
        val min = Calendar.getInstance().get(Calendar.MINUTE)
        var hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour

        val animatedSecond = sec + progression
        val animatedMinute = min + animatedSecond / 60
        val animatedHour = (hour + (animatedMinute / 60)) * 5f

        secondHand(centerX, centerY, clockRadius, animatedSecond)
        minuteHand(centerX, centerY, clockRadius, animatedMinute)
        hourHand(centerX, centerY, clockRadius, animatedHour)
        Log.e("animated Second Minute", "$animatedSecond $animatedMinute $animatedHour")
    }
}

fun DrawScope.secondHand(centerX: Float, centerY: Float, clockRadius: Float, animatedSecond: Double) {
    var handLength = 0.8f * clockRadius
    val degree = animatedSecond * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * handLength
    val y = centerY + sin(degree) * handLength
    drawLine(start = Offset(centerX, centerY),
            end = Offset(x.toFloat(), y.toFloat()),
            color = Color.Blue,
            strokeWidth = 6f)
}

fun DrawScope.minuteHand(centerX: Float, centerY: Float, clockRadius: Float, animatedMinute: Double) {
    var handLength = 0.6f * clockRadius
    val degree = animatedMinute * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * handLength
    val y = centerY + sin(degree) * handLength
    drawLine(start = Offset(centerX, centerY),
            end = Offset(x.toFloat(), y.toFloat()),
            color = Color.Blue,
            strokeWidth = 10f)
}

fun DrawScope.hourHand(centerX: Float, centerY: Float, clockRadius: Float, animatedHour: Double) {
    var handLength = 0.5f * clockRadius
    val degree = animatedHour * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * handLength
    val y = centerY + sin(degree) * handLength
    drawLine(start = Offset(centerX, centerY),
            end = Offset(x.toFloat(), y.toFloat()),
            color = Color.Blue,
            strokeWidth = 12f)
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