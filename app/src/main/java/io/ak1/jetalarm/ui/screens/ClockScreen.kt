package io.ak1.jetalarm.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ak1.jetalarm.data.local.TimesZonesTable
import io.ak1.jetalarm.data.viewmodels.ClockViewModel
import io.ak1.jetalarm.ui.components.ClockView
import io.ak1.jetalarm.ui.components.offset
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by akshay on 06/10/21
 * https://ak1.io
 */

@Composable
fun ClockScreen() {
    val clockViewModel by inject<ClockViewModel>(ClockViewModel::class.java)
    val myList: List<TimesZonesTable> =
        clockViewModel.liveTimeZonesList.collectAsState(ArrayList<TimesZonesTable>()).value
    if (myList.isEmpty()) {
        clockViewModel.prePopulateDefaultTimeZone()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val timeZone = TimeZone.getDefault()
        val subHeading = "${timeZone.id} ${
            SimpleDateFormat("hh:mm aa, EEE , dd/MM").apply { setTimeZone(timeZone) }.format(Date())
        }"
        HeadingTitleView("World Clock", subHeading)
        LazyColumn(
            content = {
                item {
                    ClockView(timeZone)
                }
                items(myList) { item ->
                    TimeZoneListRowView(TimeZone.getTimeZone(item.time_id))
                }

            }, modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun TimeZoneListRowView(timeZone: TimeZone) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {

        Text(
            text = timeZone.displayName, style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary
        )
        Text(
            text = "${timeZone.id} ${timeZone.offset()}",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

@Composable
fun HeadingTitleView(title: String, subHeading: String?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 16.dp, 0.dp, 10.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onPrimary,
        )
        subHeading?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary,
            )
        }

    }
}
