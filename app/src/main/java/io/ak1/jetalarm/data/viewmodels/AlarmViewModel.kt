package io.ak1.jetalarm.data.viewmodels

import androidx.lifecycle.ViewModel
import io.ak1.jetalarm.data.local.AlarmTableDao

/**
 * Created by akshay on 05/11/21
 * https://ak1.io
 */
class AlarmViewModel(private val db: AlarmTableDao) : ViewModel() {
    val list = db.getAllAlarms()
}