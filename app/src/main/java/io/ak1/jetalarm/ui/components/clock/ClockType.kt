package io.ak1.jetalarm.ui.components.clock

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import io.ak1.jetalarm.utils.clockPreferenceKey
import io.ak1.jetalarm.utils.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by akshay on 28/10/21
 * https://ak1.io
 */


enum class ClockType {
    CLOCK_ONE, CLOCK_TWO
}


fun Context.getClockType() = dataStore.data
    .map { preferences ->
        // No type safety.
        preferences[clockPreferenceKey] ?: 0
    }


@Composable
fun currentClockType(): ClockType {

    val context = LocalContext.current
    val preferences = runBlocking { context.dataStore.data.first() }
    val theme =
        context.getClockType().collectAsState(initial = preferences[clockPreferenceKey] ?: 0)
    return when (theme.value) {
        0 -> ClockType.CLOCK_ONE
        else -> ClockType.CLOCK_TWO
    }
}

fun TimeZone.offset(): String? {
    val calendar: Calendar = Calendar.getInstance(this, Locale.getDefault())
    val timeZone: String = SimpleDateFormat("Z").format(calendar.time)
    return timeZone.substring(0, 3) + ":" + timeZone.substring(3, 5)
}