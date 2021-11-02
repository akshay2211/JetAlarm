package io.ak1.jetalarm.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import io.ak1.jetalarm.R
import io.ak1.jetalarm.data.local.TimesZonesTable
import io.ak1.jetalarm.data.viewmodels.ClockViewModel
import io.ak1.jetalarm.ui.components.ClockView
import io.ak1.jetalarm.utils.DATE_FORMAT_AA
import io.ak1.jetalarm.utils.DATE_FORMAT_DAY_DATE
import io.ak1.jetalarm.utils.DATE_FORMAT_TIME
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by akshay on 06/10/21
 * https://ak1.io
 */

@Composable
fun ClockScreen() {
    val viewModel by inject<ClockViewModel>(ClockViewModel::class.java)
    val list = viewModel.timeZoneList().collectAsState(initial = emptyList())
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
                    Log.e("print all", "size -> ${list.value.size}")
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

@Composable
fun TimeZoneListRowView(timeZoneItem: TimesZonesTable) {
    val timeZone = TimeZone.getTimeZone(timeZoneItem.time_id)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        val date = Date()
        val ampm = SimpleDateFormat(DATE_FORMAT_AA).apply { setTimeZone(timeZone) }.format(date)
        val time = SimpleDateFormat(DATE_FORMAT_TIME).apply { setTimeZone(timeZone) }.format(date)
        val day =
            SimpleDateFormat(DATE_FORMAT_DAY_DATE).apply { setTimeZone(timeZone) }.format(date)

        Column(modifier = Modifier.weight(1f, fill = true)) {
            Text(
                text = "${timeZoneItem.name}",
                style = MaterialTheme.typography.h5
            )

            Text(
                text = "${timeZone.id.split("/")[0]}",
                style = MaterialTheme.typography.caption
            )
        }
        Column(modifier = Modifier.width(100.dp)) {
            Row(modifier = Modifier.align(End)) {
                Text(
                    text = time.subSequence(0, 5).toString(),
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = ampm.toUpperCase(Locale.current),
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.padding(3.dp, 12.dp, 0.dp, 0.dp)
                )
            }
            Text(
                modifier = Modifier.align(End),
                text = day,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun HeadingTitleView(title: String, subHeading: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h3,
        )
        subHeading?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.body2,
            )
        }

    }
}
