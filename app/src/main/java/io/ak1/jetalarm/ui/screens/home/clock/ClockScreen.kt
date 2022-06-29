package io.ak1.jetalarm.ui.screens.home.clock

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
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
import io.ak1.jetalarm.data.viewmodels.ClockViewModel
import io.ak1.jetalarm.ui.components.TimeZoneListRowView
import io.ak1.jetalarm.ui.components.clock.ClockView
import io.ak1.jetalarm.ui.components.common.Container
import io.ak1.jetalarm.ui.screens.Destinations
import io.ak1.jetalarm.utils.DATE_FORMAT_FULL
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by akshay on 06/10/21
 * https://ak1.io
 */

@Composable
fun ClockScreen(navController: NavController) {
    val viewModel by inject<ClockViewModel>(ClockViewModel::class.java)
    val list = viewModel.selectedTimeZoneList().collectAsState(initial = emptyList())

    Container("World Clock") {
        val timeZone = TimeZone.getDefault()
        val subHeading = "${timeZone.id} ${
            SimpleDateFormat(DATE_FORMAT_FULL).apply { setTimeZone(timeZone) }.format(Date())
        }"
        Box(Modifier.fillMaxSize()) {
            LazyColumn(
                content = {
                    item {
                        ClockView(timeZone)
                    }
                    items(list.value) { item ->
                        TimeZoneListRowView(item)
                    }
                    item {
                        Spacer(
                            modifier = Modifier
                                .height(56.dp)
                                .fillMaxWidth()
                        )
                    }

                }, modifier = Modifier
                    .fillMaxSize()
            )
            FloatingActionButton(
                onClick = {
                    navController.navigate(Destinations.TIMEZONE_ROUTE)
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


