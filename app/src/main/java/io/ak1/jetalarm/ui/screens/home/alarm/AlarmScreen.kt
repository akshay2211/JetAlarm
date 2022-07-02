package io.ak1.jetalarm.ui.screens.home.alarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.ak1.jetalarm.R
import io.ak1.jetalarm.data.viewmodels.AlarmViewModel
import io.ak1.jetalarm.ui.components.common.Container
import io.ak1.jetalarm.ui.screens.Destinations
import org.koin.java.KoinJavaComponent.inject

/**
 * Created by akshay on 21/10/21
 * https://ak1.io
 */

@Composable
fun AlarmScreen(navController: NavController) {
    val viewModel by inject<AlarmViewModel>(AlarmViewModel::class.java)
    val list = viewModel.list.collectAsState(initial = emptyList())
    Container(heading = "Alarms") {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(content = {
                items(list.value) { item ->
                    Text(text = "${item.desc} Alarm", modifier = Modifier.padding(20.dp))
                    /*TextField(value = "$i Alarm", onValueChange = {
                    })*/
                }
            })
            FloatingActionButton(
                onClick = {
                    navController.navigate(Destinations.CREATE_ALARM_ROUTE)
                },
                shape = CircleShape,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(80.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_globe),
                    contentDescription = stringResource(
                        id = R.string.image_desc
                    ),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}