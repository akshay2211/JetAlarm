package io.ak1.jetalarm.data.viewmodels

import androidx.lifecycle.ViewModel
import io.ak1.jetalarm.data.local.TimesZonesTableDao

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */
class ClockViewModel(db: TimesZonesTableDao) : ViewModel() {
    var liveTimeZonesList = db.getAllTimeZones()
}