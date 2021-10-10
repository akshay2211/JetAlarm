package io.ak1.jetalarm.ui.screens.clock

/*
import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pyrocodes.jetalarm.data.local.TimesZonesTable
import com.pyrocodes.jetalarm.ui.components.showClock
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
*/

/**
 * Created by akshay on 20,October,2020
 * akshay2211@github.io
 *//*
var CLOCK_SCREEN = "ClockScreen"

@Composable
fun clockScreen(viewModel: ClockViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val timeZone = TimeZone.getDefault()
        showTextClock(timeZone)
        showClock(timeZone)
        Text(
            text = timeZone.displayName,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.wrapContentWidth(align = Alignment.End)
                .padding(12.dp)
        )
        LazyRowForDemo(viewModel.liveTimeZonesList.collectAsState(ArrayList<TimesZonesTable>()).value)

    }
}

@Composable
fun LazyRowForDemo(mylist: List<TimesZonesTable>) {
    mylist.forEach { Log.e("${it.name}", "${it.time_id}") }

    LazyRowFor(items = mylist,
        modifier = Modifier,
        itemContent = { item ->
            Button(onClick = {

                Log.e("hi", "->   $item")
            }, modifier = Modifier.preferredWidth(100.dp).padding(10.dp)) {
                Text(text = item.name, style = TextStyle(fontSize = 80.sp))
            }

            Log.d("COMPOSE", "This get rendered ${item.name}")
        }
    )
}

@Composable
fun showTextClock(timeZone: TimeZone) {
    var date = Calendar.getInstance(timeZone).time
    var dateText = SimpleDateFormat("E,mm/dd").format(date)
    Spacer(modifier = Modifier.fillMaxWidth().preferredHeight(60.dp))
    Text(
        text = dateText,
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
    )
    textClock(date, timeZone)

}

@Composable
fun textClock(value: Date, timeZone: TimeZone) {
    var date = SimpleDateFormat("hh:mm").format(value)
    Text(
        text = date,
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
    )
}
*/