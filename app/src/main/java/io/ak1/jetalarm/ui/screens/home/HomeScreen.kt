/*
 * Copyright (C) 2022 akshay2211 (Akshay Sharma)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.ak1.jetalarm.ui.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ak1.jetalarm.ui.components.common.BottomBar
import io.ak1.jetalarm.ui.screens.Destinations
import io.ak1.jetalarm.ui.screens.home.alarm.AlarmScreen
import io.ak1.jetalarm.ui.screens.home.clock.ClockScreen
import io.ak1.jetalarm.ui.screens.home.stopwatch.StopWatchScreen
import io.ak1.jetalarm.ui.screens.home.timer.TimerScreen

/**
 * Created by akshay on 29/06/22
 * https://ak1.io
 */

@Composable
fun HomeScreen(parentNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(parentNavController, navController) }) {
        NavHost(
            navController = navController,
            startDestination = Destinations.CLOCK_ROUTE
        ) {
            composable(Destinations.CLOCK_ROUTE) { ClockScreen(parentNavController) }
            composable(Destinations.ALARM_ROUTE) { AlarmScreen(parentNavController) }
            composable(Destinations.TIMER_ROUTE) { TimerScreen { navController.navigateUp() } }
            composable(Destinations.STOP_WATCH_ROUTE) { StopWatchScreen { navController.navigateUp() } }
        }
    }
}