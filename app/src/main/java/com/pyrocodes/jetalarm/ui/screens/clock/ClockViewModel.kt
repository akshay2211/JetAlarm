package com.pyrocodes.jetalarm.ui.screens.clock

import androidx.lifecycle.ViewModel
import com.pyrocodes.jetalarm.data.local.TimesZonesTableDao

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */
class ClockViewModel(db: TimesZonesTableDao) : ViewModel() {
    var liveTimeZonesList = db.getAllTimeZones()
}