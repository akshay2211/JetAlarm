package io.ak1.jetalarm.utils

import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.min
import io.ak1.jetalarm.data.local.TimesZonesTable
import java.util.*

const val oneMinuteRadians = Math.PI / 30
const val pieByTwo = Math.PI / 2

fun Size.getRadius(expo: Float = 1f) = expo * min(Dp(width / 2), Dp(height / 2)).value

fun getTimeZones(): List<TimesZonesTable> {
    Log.e("retriving", "getTimeZones ")
    return TimeZone.getAvailableIDs().map {
        val timeZone = TimeZone.getTimeZone(it)
        timeZone
    }.filter {
        it.id.length > 3 &&
                it.id != "CST6CDT" &&
                it.id != "EST5EDT" &&
                !it.id.contains("gmt", true) &&
                !it.id.contains("act", true) &&
                !it.id.contains("utc", true) &&
                !it.id.contains("uct", true) &&
                !it.id.isNullOrEmpty()
    }
        .map {
            var text = it.id
            if (it.id.contains("/")) {
                text = it.id.split("/")[1]
            }
            if (text.contains("_"))
                text = text.replace("_", " ")

            TimesZonesTable(text, it.id)
        }
        .sortedBy { it.name }.distinctBy { it.name }
}

