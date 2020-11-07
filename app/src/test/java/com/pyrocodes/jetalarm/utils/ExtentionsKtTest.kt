package com.pyrocodes.jetalarm.utils

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by akshay on 01,November,2020
 * akshay2211@github.io
 */
class ExtentionsKtTest {

    @Test
    fun getTimeZones() {
        val timeZone = "timezone"
        var default = TimeZone.getDefault()
        println(
            "$timeZone  default ${default.id} ${
                default.getDisplayName(
                    default.useDaylightTime(),
                    TimeZone.LONG
                )
            } ${default.dstSavings} ${default.rawOffset}  ${default.useDaylightTime()}"
        )
        TimeZone.getAvailableIDs().forEach {
            var default = TimeZone.getTimeZone(it)
            var size1 = 30 - default.id.length
            var name1 = default.id
            repeat(size1) {
                name1 += " "
            }
            var name = default.getDisplayName(default.useDaylightTime(), TimeZone.LONG)
            var size = 40 - name.length
            repeat(size) {
                name += " "
            }
            val cal = Calendar.getInstance(default)
            println(
                "$timeZone               $name1 $name ${cal.calendarType}   ${
                    SimpleDateFormat("hh:MM:ss aa").format(
                        cal.time
                    )
                }  ${default.useDaylightTime()} ${
                    default.getDisplayName(
                        default.useDaylightTime(),
                        TimeZone.SHORT
                    )
                }"
            )
        }
    }
}