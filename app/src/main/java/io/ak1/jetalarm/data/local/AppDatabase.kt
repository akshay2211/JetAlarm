package io.ak1.jetalarm.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */

@Database(entities = [TimesZonesTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timeZoneDao(): TimesZonesTableDao
}

@Dao
interface TimesZonesTableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timezones: List<TimesZonesTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timezones: TimesZonesTable)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(timezones: TimesZonesTable)

    @Query("DELETE FROM timezones_table WHERE time_id = :time_id")
    suspend fun deleteOne(time_id: String)

    @Query("SELECT COUNT(*) FROM timezones_table")
    suspend fun count(): Int

    @Query("SELECT * FROM timezones_table")
    fun getAllTimeZones(): Flow<List<TimesZonesTable>>

    @Query("SELECT * FROM timezones_table WHERE id = :id")
    suspend fun getTimeZone(id: Int): TimesZonesTable

    @Query("SELECT * FROM timezones_table WHERE selected = 1")
    fun getSelectedTimeZones(): Flow<List<TimesZonesTable>>

    @Query("DELETE FROM timezones_table")
    suspend fun deleteTable()

}
