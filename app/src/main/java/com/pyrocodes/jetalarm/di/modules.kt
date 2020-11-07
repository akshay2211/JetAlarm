package com.pyrocodes.jetalarm.di

import com.pyrocodes.jetalarm.ui.screens.clock.ClockViewModel
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

}
var viewModelModule = module {
    viewModel { ClockViewModel(get()) }
}