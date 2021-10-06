package com.pyrocodes.jetalarm.ui.components

import androidx.compose.ui.unit.dp
//import com.pyrocodes.jetalarm.ui.screens.clock.CLOCK_SCREEN
import com.pyrocodes.jetalarm.ui.screens.stopwatch.STOPWATCH_SCREEN

/**
 * Created by akshay on 18,October,2020
 * akshay2211@github.io
 */



private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100

sealed class Screen(val route: String) {
    //object ClockScreen : Screen(CLOCK_SCREEN)
    object StopWatchScreen : Screen(STOPWATCH_SCREEN)
}


val items = listOf(
    //Screen.ClockScreen,
    Screen.StopWatchScreen,
)

