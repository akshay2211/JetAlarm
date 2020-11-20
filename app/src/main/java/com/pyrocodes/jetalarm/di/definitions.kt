package com.pyrocodes.jetalarm.di

import android.content.Context
import androidx.room.Room
import com.pyrocodes.jetalarm.data.local.AppDatabase
import com.pyrocodes.jetalarm.data.local.TimesZonesTableDao

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */
fun getDb(context: Context): AppDatabase {
    return synchronized(context) {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-jetalarm"
        ).build()
    }
}

fun getTimeZoneDao(appDatabase: AppDatabase): TimesZonesTableDao {
    return appDatabase.timeZoneDao()
}