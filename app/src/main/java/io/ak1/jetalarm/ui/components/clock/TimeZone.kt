@file:OptIn(ExperimentalMaterialApi::class)

package io.ak1.jetalarm.ui.components.clock

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import io.ak1.jetalarm.R
import io.ak1.jetalarm.data.local.TimesZonesTable
import io.ak1.jetalarm.utils.dateFormatterAA
import io.ak1.jetalarm.utils.dateFormatterDay
import io.ak1.jetalarm.utils.dateFormatterSimple
import java.util.*

/**
 * Created by akshay on 02/11/21
 * https://ak1.io
 */


@Composable
fun TimeZoneListRowView(timeZoneItem: TimesZonesTable, date: Date = Date()) {
    val timeZone = TimeZone.getTimeZone(timeZoneItem.time_id)
    val ampm = dateFormatterAA.apply { setTimeZone(timeZone) }.format(date)
    val time = dateFormatterSimple.apply { setTimeZone(timeZone) }.format(date)
    val day = dateFormatterDay.apply { setTimeZone(timeZone) }.format(date)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {

        Column(modifier = Modifier.weight(1f, fill = true)) {
            Text(
                text = timeZoneItem.name,
                style = MaterialTheme.typography.h5
            )

            Text(
                text = timeZone.id.split("/")[0],
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
            /* Text(
                 modifier = Modifier.align(Alignment.End),
                 text = day,
                 style = MaterialTheme.typography.caption
             )*/
        }
    }
}


@Composable
fun TimeZoneListRowSmallView(
    timeZoneItem: TimesZonesTable,
    date: Date = Date(),
    onClick: () -> Unit
) {
    val timeZone = TimeZone.getTimeZone(timeZoneItem.time_id)
    val ampm = dateFormatterAA.apply { setTimeZone(timeZone) }.format(date)
    val time = dateFormatterSimple.apply { setTimeZone(timeZone) }.format(date)
    Surface(onClick = onClick) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 0.dp)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f, fill = true),
                text = timeZoneItem.name,
                style = MaterialTheme.typography.h6
            )

            Row(modifier = Modifier.width(100.dp)) {
                Text(
                    text = time.subSequence(0, 5).toString(),
                    style = MaterialTheme.typography.h6
                )
                /*Text(
                    text = ampm.toUpperCase(Locale.current),
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.padding(3.dp, 12.dp, 0.dp, 0.dp)
                )*/
            }
            Image(
                painter = painterResource(id = if (timeZoneItem.selected) R.drawable.ic_check_circle else R.drawable.ic_circle),
                contentDescription = "Selector",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
            )
        }
    }
}