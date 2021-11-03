package io.ak1.jetalarm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import io.ak1.jetalarm.R
import io.ak1.jetalarm.data.local.TimesZonesTable
import io.ak1.jetalarm.utils.DATE_FORMAT_AA
import io.ak1.jetalarm.utils.DATE_FORMAT_DAY_DATE
import io.ak1.jetalarm.utils.DATE_FORMAT_TIME
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by akshay on 02/11/21
 * https://ak1.io
 */


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
            Row(modifier = Modifier.align(Alignment.End)) {
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
                modifier = Modifier.align(Alignment.End),
                text = day,
                style = MaterialTheme.typography.caption
            )
        }
    }
}


@Composable
fun TimeZoneListRowSmallView(timeZoneItem: TimesZonesTable, onClick: () -> Unit) {
    val timeZone = TimeZone.getTimeZone(timeZoneItem.time_id)
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val date = Date()
        val ampm = SimpleDateFormat(DATE_FORMAT_AA).apply { setTimeZone(timeZone) }.format(date)
        val time = SimpleDateFormat(DATE_FORMAT_TIME).apply { setTimeZone(timeZone) }.format(date)
        Text(
            modifier = Modifier.weight(1f, fill = true),
            text = "${timeZoneItem.name}",
            style = MaterialTheme.typography.h5
        )

        Row(modifier = Modifier.width(100.dp)) {
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
        Button(
            modifier = Modifier.padding(0.dp),
            contentPadding = PaddingValues(0.dp),
            shape = CircleShape,
            onClick = onClick,
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = android.R.color.transparent))
        ) {
            Image(
                painter = painterResource(id = if (timeZoneItem.selected) R.drawable.ic_check_circle else R.drawable.ic_circle),
                contentDescription = stringResource(id = R.string.image_desc),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
            )
        }
    }
}