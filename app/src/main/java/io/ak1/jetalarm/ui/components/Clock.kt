package io.ak1.jetalarm.ui.components

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ak1.jetalarm.data.local.TimesZonesTable
import io.ak1.jetalarm.data.viewmodels.ClockViewModel
import io.ak1.jetalarm.utils.getRadius
import io.ak1.jetalarm.utils.oneMinuteRadians
import io.ak1.jetalarm.utils.pieByTwo
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.cos
import kotlin.math.sin


/**
 * Created by akshay on 06/10/21
 * https://ak1.io
 */
@Composable
fun ClockView(timeZone: TimeZone) {
    val infiniteTransition = rememberInfiniteTransition()
    val clockViewModel by inject<ClockViewModel>(ClockViewModel::class.java)

    val hand = infiniteTransition.animateValue(
        initialValue = 0f,
        targetValue = 1000f,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            hands(hand.value, timeZone)
            staticUi()
        }
        TextClock1(timeZone)
        var date1 = Calendar.getInstance(timeZone).time
        var date = SimpleDateFormat("hh:mm aa").format(date1)
        Text(
            text = date.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
        )
        //Image(painter = painterResource(id = R.drawable.add_icon), contentDescription = "add icon")
        LazyRowForDemo(clockViewModel)

    }

}

@Composable
fun LazyRowForDemo(clockViewModel: ClockViewModel) {
    val myList: List<TimesZonesTable> =
        clockViewModel.liveTimeZonesList.collectAsState(ArrayList<TimesZonesTable>()).value
    if (myList.isEmpty()) {
        clockViewModel.prePopulateDefaultTimeZone()
    }
    myList.forEach { Log.e("${it.name}", "${it.time_id}") }
    LazyRow(content = {
        items(myList) { item ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(text = item.name, style = TextStyle(fontSize = 20.sp))
                Divider(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(1.dp)
                )
            }
        }

    })
}

@Composable
fun TextClock1(timeZone: TimeZone) {
    var date = Calendar.getInstance(timeZone).time
    var dateText = SimpleDateFormat("E,mm/dd").format(date)
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
    )
    Text(
        text = dateText,
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
    )
}


@Composable
fun hands(unused: Float, timeZone: TimeZone) {


    var cal = Calendar.getInstance(timeZone)
    val color = MaterialTheme.colors.primary
    Canvas(modifier = Modifier.fillMaxSize()) {
        val progression = ((cal.timeInMillis % 1000) / 1000.0)
        // Log.e("fl", " -> $fl   $progression")


        val centerX = (size.width / 2)
        val centerY = (size.height / 2)

        val sec = cal.get(Calendar.SECOND)
        val min = cal.get(Calendar.MINUTE)
        var hour = cal.get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour

        val animatedSecond = sec + progression
        val animatedMinute = min + animatedSecond / 60
        val animatedHour = (hour + (animatedMinute / 60)) * 5f

        minuteHand(centerX, centerY, size.getRadius(0.6f), animatedMinute, Color.Red)
        hourHand(centerX, centerY, size.getRadius(0.45f), animatedHour, color)
        secondHand(centerX, centerY, size.getRadius(0.7f), animatedSecond, color)
    }
}

fun DrawScope.secondHand(
    centerX: Float,
    centerY: Float,
    clockRadius: Float,
    animatedSecond: Double,
    color: Color
) {
    val degree = animatedSecond * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * clockRadius
    val y = centerY + sin(degree) * clockRadius
    //val minusx = centerX + cos(degree) * -30
    //val minusy = centerY + sin(degree) * -30
    drawLine(
        //start = Offset(minusx.toFloat(), minusy.toFloat()),
        start = Offset(centerX, centerY),
        end = Offset(x.toFloat(), y.toFloat()),
        color = color,
        strokeWidth = 4f,
        cap = StrokeCap.Round
    )
}

fun DrawScope.minuteHand(
    centerX: Float,
    centerY: Float,
    clockRadius: Float,
    animatedMinute: Double,
    color: Color
) {
    val degree = animatedMinute * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * clockRadius
    val y = centerY + sin(degree) * clockRadius
    drawLine(
        start = Offset(centerX, centerY),
        end = Offset(x.toFloat(), y.toFloat()),
        color = color,
        strokeWidth = 8f,
        cap = StrokeCap.Round
    )
}

fun DrawScope.hourHand(
    centerX: Float, centerY: Float, clockRadius: Float, animatedHour: Double,
    color: Color
) {
    val degree = animatedHour * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * clockRadius
    val y = centerY + sin(degree) * clockRadius
    drawLine(
        start = Offset(centerX, centerY),
        end = Offset(x.toFloat(), y.toFloat()),
        color = color,
        strokeWidth = 8f,
        cap = StrokeCap.Round
    )
}

@Composable
fun staticUi() {
    val color = MaterialTheme.colors.primary
    val color2 = MaterialTheme.colors.background
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = color,
            radius = 15f
        )
        drawCircle(
            color = color2,
            radius = 7f
        )
        drawCircle(
            color = color,
            radius = size.getRadius(0.8f),
            style = Stroke(7f)
        )
    }
}