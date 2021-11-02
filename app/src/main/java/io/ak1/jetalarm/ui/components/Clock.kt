package io.ak1.jetalarm.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import io.ak1.jetalarm.utils.getRadius
import io.ak1.jetalarm.utils.oneMinuteRadians
import io.ak1.jetalarm.utils.pieByTwo
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
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            hands(hand.value, timeZone, currentClockType())
            staticUi()
        }
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
        )

        var date1 = Calendar.getInstance(timeZone).time
        var date = SimpleDateFormat("hh:mm aa").format(date1)
        Text(
            text = date.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
        )
        TextClock1(timeZone)
        //Image(painter = painterResource(id = R.drawable.add_icon), contentDescription = "add icon")
    }

}


@Composable
fun TextClock1(timeZone: TimeZone) {
    var date = Calendar.getInstance(timeZone).time
    var dateText = SimpleDateFormat("E,mm/dd").format(date)
    Text(
        text = dateText,
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
    )
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    )
}

enum class ClockType {
    CLOCK_ONE, CLOCK_TWO
}


@Composable
fun hands(unused: Float, timeZone: TimeZone, clockType: ClockType) {


    var cal = Calendar.getInstance(timeZone)
    val color = MaterialTheme.colors.onPrimary
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
        if (clockType == ClockType.CLOCK_ONE) {
            minuteHand(centerX, centerY, size.getRadius(0.6f), animatedMinute, color)
            hourHand(centerX, centerY, size.getRadius(0.45f), animatedHour, Color.Red)
            secondHand(centerX, centerY, size.getRadius(0.7f), animatedSecond, color)
        } else {
            hourHand2(centerX, centerY, size.getRadius(0.45f), animatedHour, color)
            minuteHand2(centerX, centerY, size.getRadius(0.6f), animatedMinute, color)
        }
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
    val color = MaterialTheme.colors.onPrimary
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


// second clock UI methods
fun DrawScope.minuteHand2(
    centerX: Float,
    centerY: Float,
    clockRadius: Float,
    animatedMinute: Double,
    color: Color
) {
    val degree = animatedMinute * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * clockRadius
    val y = centerY + sin(degree) * clockRadius
    val minusx = centerX + cos(degree) * -30
    val minusy = centerY + sin(degree) * -30
    drawLine(
        start = Offset(minusx.toFloat(), minusy.toFloat()),
        end = Offset(x.toFloat(), y.toFloat()),
        color = color,
        strokeWidth = 8f,
        cap = StrokeCap.Round
    )
}

fun DrawScope.hourHand2(
    centerX: Float, centerY: Float, clockRadius: Float, animatedHour: Double,
    color: Color
) {
    val degree = animatedHour * oneMinuteRadians - pieByTwo
    val x = centerX + cos(degree) * clockRadius
    val y = centerY + sin(degree) * clockRadius
    val minusx = centerX + cos(degree) * -30
    val minusy = centerY + sin(degree) * -30
    drawLine(
        start = Offset(minusx.toFloat(), minusy.toFloat()),
        end = Offset(x.toFloat(), y.toFloat()),
        color = color,
        strokeWidth = 8f,
        cap = StrokeCap.Round
    )
}