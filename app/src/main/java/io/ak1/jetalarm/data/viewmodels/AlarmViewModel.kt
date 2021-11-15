package io.ak1.jetalarm.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ak1.jetalarm.data.local.AlarmTable
import io.ak1.jetalarm.data.local.AlarmTableDao
import kotlinx.coroutines.launch

/**
 * Created by akshay on 05/11/21
 * https://ak1.io
 */
class AlarmViewModel(private val db: AlarmTableDao) : ViewModel() {
    fun saveNewAlarm(time: Long, s: String, requestCode: Int) {
        viewModelScope.launch {
            db.insert(AlarmTable(time, s, requestCode))
        }
    }

    val list = db.getAllAlarms()
}