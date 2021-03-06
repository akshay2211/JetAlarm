package com.pyrocodes.jetalarm.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Created by akshay on 07,November,2020
 * akshay2211@github.io
 */

const val TIMEZONE_TABLE = "timezones_table"

@Entity(
    tableName = TIMEZONE_TABLE,
    indices = [Index(value = ["time_id"], unique = true)]
)
data class TimesZonesTable(
    var name: String = "",
    var time_id: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    var id: Int = 0
}
