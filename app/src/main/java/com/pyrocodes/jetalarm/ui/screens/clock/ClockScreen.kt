package com.pyrocodes.jetalarm.ui.screens.clock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by akshay on 20,October,2020
 * akshay2211@github.io
 */
@Composable
fun clockScreen(){
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
      showClock()
    }
}