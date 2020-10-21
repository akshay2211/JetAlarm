package com.pyrocodes.jetalarm.utils

import androidx.compose.ui.geometry.Size

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.min
import java.util.*


const val oneMinuteRadians = Math.PI / 30
const val pieByTwo = Math.PI / 2

fun Size.getRadius(expo:Float = 1f) = expo * min(Dp(width / 2), Dp(height / 2)).value