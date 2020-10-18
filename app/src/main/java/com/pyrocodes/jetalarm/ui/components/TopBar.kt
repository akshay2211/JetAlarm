package com.pyrocodes.jetalarm.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
import com.pyrocodes.jetalarm.JetAlarmScreens

/**
 * Created by akshay on 18,October,2020
 * akshay2211@github.io
 */
@Composable
fun TabBarComponent(
    allScreens: List<JetAlarmScreens>,
    onTabSelected: (JetAlarmScreens) -> Unit,
    currentScreen: JetAlarmScreens
) {
    Surface(androidx.compose.ui.Modifier.preferredHeight(TabHeight).fillMaxWidth()) {
        Row {
            allScreens.forEach { screen ->
                TabComponent(
                    text = screen.name.toUpperCase(),
                    icon = screen.icon,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )
            }
        }
    }
}
@Composable
fun TabComponent(
    text: String,
    icon: VectorAsset,
    onSelected: () -> Unit,
    selected: Boolean){

}



private val TabHeight = 56.dp