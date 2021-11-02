package io.ak1.jetalarm.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ak1.jetalarm.data.local.TimesZonesTable
import io.ak1.jetalarm.data.local.TimesZonesTableDao
import io.ak1.jetalarm.utils.getTimeZones
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */
class ClockViewModel(private val db: TimesZonesTableDao) : ViewModel() {


    fun timeZoneList(): Flow<List<TimesZonesTable>> {
        var list: MutableStateFlow<List<TimesZonesTable>> =
            MutableStateFlow(emptyList()) // changed the type of list to mutableStateFlow
        viewModelScope.launch {
            prePopulateDataBase()
            list.value = db.getSelectedTimeZones()
        }
        return list
    }

    private suspend fun prePopulateDataBase() {
        if (db.count() == 0) {
            val list = getTimeZones()
            db.insert(list)

            db.update(db.getTimeZone(10).apply { selected = true })
        }
    }
}