package io.ak1.jetalarm.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ak1.jetalarm.data.local.TimesZonesTable
import io.ak1.jetalarm.data.local.TimesZonesTableDao
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */
class ClockViewModel(private val db: TimesZonesTableDao) : ViewModel() {
    /*    var liveTimeZonesList = {
            var list = db.getAllTimeZones()
            if (!list.value.isNullOrEmpty()) {
                var timeZone = TimeZone.getDefault()
                viewModelScope.launch {
                    db.insert(TimesZonesTable(name = timeZone.displayName, time_id = timeZone.id))
                }
                db.getAllTimeZones()
            } else {
                list
            }
        }*/
    var liveTimeZonesList = db.getAllTimeZones()

    fun prePopulateDefaultTimeZone() {
        var timeZone = TimeZone.getDefault()
        viewModelScope.launch {
            db.insert(TimesZonesTable(name = timeZone.displayName, time_id = timeZone.id))
        }
    }
}