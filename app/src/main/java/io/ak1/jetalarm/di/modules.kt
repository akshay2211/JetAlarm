package io.ak1.jetalarm.di

import io.ak1.jetalarm.data.viewmodels.AlarmViewModel
import io.ak1.jetalarm.data.viewmodels.ClockViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */

var databaseModule = module {
    single { getDb(androidApplication()) }
    single { getTimeZoneDao(get()) }
    single { getAlarmTableDao(get()) }

}
var viewModelModule = module {
    viewModel { ClockViewModel(get()) }
    viewModel { AlarmViewModel(get()) }
}