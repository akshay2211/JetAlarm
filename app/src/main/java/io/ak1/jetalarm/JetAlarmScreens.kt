package io.ak1.jetalarm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

//import androidx.compose.ui.graphics.vector.VectorAsset

/**
 * Created by akshay on 18,October,2020
 * akshay2211@github.io
 */
enum class JetAlarmScreens(
    val icon: ImageVector,
    private val body: @Composable ((JetAlarmScreens) -> Unit) -> Unit
) {
    ClockScreen(
        icon = Icons.Outlined.CheckCircle,//vectorResource(id = R.drawable.ic_replay),
        body = { /*clockScreen()*/ }
    ),
    AlarmScreen(
        icon = Icons.Outlined.CheckCircle,
        body = {  }
    ),
    TimerScreen(
        icon = Icons.Outlined.CheckCircle,
        body = { }
    );

    @Composable
    fun content(onScreenChange: (JetAlarmScreens) -> Unit) {
        body(onScreenChange)
    }
}