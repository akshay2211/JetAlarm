package io.ak1.jetalarm.ui

import android.view.Window
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import io.ak1.jetalarm.screens.ClockScreen
import io.ak1.jetalarm.ui.theme.JetAlarmTheme
import io.ak1.jetalarm.ui.theme.isSystemInDarkThemeCustom
import io.ak1.jetalarm.ui.theme.statusBarConfig

/**
 * Created by akshay on 06/10/21
 * https://ak1.io
 */

@Composable
fun RootView(window: Window) {
    val isDark = isSystemInDarkThemeCustom()
    window.statusBarConfig(isDark)
    JetAlarmTheme(isDark) {
        Surface(color = MaterialTheme.colors.background) {
            ClockScreen()
        }
    }
}