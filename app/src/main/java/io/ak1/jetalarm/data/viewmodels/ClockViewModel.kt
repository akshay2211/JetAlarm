package io.ak1.jetalarm.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ak1.jetalarm.data.local.TimesZonesTable
import io.ak1.jetalarm.data.local.TimesZonesTableDao
import io.ak1.jetalarm.utils.getTimeZones
import kotlinx.coroutines.launch

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */
class ClockViewModel(private val db: TimesZonesTableDao) : ViewModel() {


    fun timeZoneList() = db.getAllTimeZones()

    fun selectedTimeZoneList() = db.getSelectedTimeZones()

    suspend fun prePopulateDataBase() {
        if (db.count() == 0) {
            val list = getTimeZones()
            db.insert(list)
        }
    }

    fun updateTimeZone(timesZonesTable: TimesZonesTable, isSelected: (Boolean) -> Unit) =
        viewModelScope.launch {
            db.update(timesZonesTable.apply {
                selected = !selected
                isSelected(selected)
            })

        }

}