package com.pyrocodes.jetalarm.ui.components

import android.util.Log
import androidx.compose.animation.animate
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Surface(Modifier.preferredHeight(TabHeight).fillMaxWidth()) {
        Row(modifier = Modifier.background(MaterialTheme.colors.background)) {
            allScreens.forEach { screen ->
                TabComponent(
                    text = screen.name,
                    icon = screen.icon,
                    onSelected = {
                        Log.e("clicked","${screen.name}")
                        onTabSelected(screen) },
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


    val color = MaterialTheme.colors.primary
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor = animate(
        target = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animSpec = animSpec
    )
    Row(
        modifier = Modifier
           // .padding(16.dp)
            .animateContentSize()
            .preferredHeight(TabHeight)//.background(Color.Cyan)
            .selectable(
                selected = selected,
                onClick = onSelected,
                indication = RippleIndication(bounded = false)
            )
    ) {
        Icon(asset = icon, tint = tabTintColor,modifier = Modifier.padding(16.dp))
        if (selected) {
            Text(text, color = tabTintColor, modifier = Modifier.padding(vertical = 16.dp))
            Spacer(Modifier.preferredWidth(12.dp))
        }
    }
}

private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100