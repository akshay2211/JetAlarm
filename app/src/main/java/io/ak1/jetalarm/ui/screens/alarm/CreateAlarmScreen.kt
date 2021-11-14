package io.ak1.jetalarm.ui.screens.alarm

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*

/**
 * Created by akshay on 05/11/21
 * https://ak1.io
 */

@Composable
fun CreateAlarmScreen(navController: NavController) {
    val context = LocalContext.current
    val time = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val c: Calendar = Calendar.getInstance()
        val hour: Int = c.get(Calendar.HOUR_OF_DAY)
        val minute: Int = c.get(Calendar.MINUTE)
        time.value = "$hour:$minute"

        TextButton(onClick = {
            val c: Calendar = Calendar.getInstance()
            val hour: Int = c.get(Calendar.HOUR_OF_DAY)
            val minute: Int = c.get(Calendar.MINUTE)
            time.value = "$hour:$minute"
            TimePickerDialog(
                context,
                { view, hourOfDay, minute ->
                    time.value = "$hour:$minute"
                },
                hour,
                minute,
                false
            ).show()
        }) {
            Text(
                text = time.value,
                style = MaterialTheme.typography.h3,
            )

        }

    }
}


