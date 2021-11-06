package io.ak1.jetalarm.ui.screens.alarm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.ak1.jetalarm.data.viewmodels.AlarmViewModel
import io.ak1.jetalarm.ui.components.common.HeadingTitleView
import io.ak1.jetalarm.utils.DATE_FORMAT_FULL
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by akshay on 21/10/21
 * https://ak1.io
 */

@Composable
fun AlarmScreen(navController: NavController) {
    val viewModel by inject<AlarmViewModel>(AlarmViewModel::class.java)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val timeZone = TimeZone.getDefault()
        val subHeading = "${timeZone.id} ${
            SimpleDateFormat(DATE_FORMAT_FULL).apply { setTimeZone(timeZone) }.format(Date())
        }"
        HeadingTitleView("World Clock", subHeading)
        LazyColumn(content = {
            for (i in 0..10) {
                item {
                    Text(text = "$i Alarm", modifier = Modifier.padding(20.dp))
                    /*TextField(value = "$i Alarm", onValueChange = {
                    })*/
                }
            }
        })


    }
}