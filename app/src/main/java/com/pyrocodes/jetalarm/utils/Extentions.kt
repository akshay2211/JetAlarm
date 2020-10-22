package com.pyrocodes.jetalarm.utils

import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.min


const val oneMinuteRadians = Math.PI / 30
const val pieByTwo = Math.PI / 2

fun Size.getRadius(expo: Float = 1f) = expo * min(Dp(width / 2), Dp(height / 2)).value

fun Window.setUpStatusNavigationBarColors(isLight: Boolean, hashCode: Int) {
    statusBarColor = hashCode
    navigationBarColor = hashCode
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        setDecorFitsSystemWindows(isLight)
    } else {
        @Suppress("DEPRECATION")
        decorView.systemUiVisibility = if (isLight) {
            0
        } else {
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}