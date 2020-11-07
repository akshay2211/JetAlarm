package com.pyrocodes.jetalarm.di

import android.content.Context
import androidx.room.Room
import com.pyrocodes.jetalarm.data.local.LocalDatabase
import com.pyrocodes.jetalarm.data.local.TimesZonesTableDao

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */
fun getDb(context: Context): LocalDatabase {
    return synchronized(context) {
        Room.databaseBuilder(
            context,
            LocalDatabase::class.java, "database-jet-alarm"
        ).build()
    }
}

fun getTimeZoneDao(localDatabase: LocalDatabase): TimesZonesTableDao {
    return localDatabase.timeZoneDao()
}