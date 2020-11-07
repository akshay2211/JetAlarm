package com.pyrocodes.jetalarm

import androidx.multidex.MultiDexApplication
import com.pyrocodes.jetalarm.di.databaseModule
import com.pyrocodes.jetalarm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */
class JetAlarm : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //inject Android context
            androidContext(applicationContext)
            // use Android logger - Level.INFO by default
            androidLogger()
            koin.loadModules(listOf(databaseModule, viewModelModule))
            koin.createRootScope()
        }
    }
}