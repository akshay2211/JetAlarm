package com.pyrocodes.jetalarm.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */

@Database(entities = [TimesZonesTable::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun timeZoneDao(): TimesZonesTableDao
}

@Dao
interface TimesZonesTableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timezones: List<TimesZonesTable>)

    @Query("DELETE FROM timezones_table WHERE time_id = :time_id")
    suspend fun deleteOne(time_id: String)

    @Query("SELECT * FROM timezones_table")
    fun getAllTimeZones(): Flow<List<TimesZonesTable>>

    @Query("DELETE FROM timezones_table")
    suspend fun deleteTable()
}
