package com.pyrocodes.jetalarm.utils

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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


    private fun checkCommon(arr1: Array<String>, arr2: Array<String>): Boolean {
        return arr2.any { arr1.contains(it) }
        /*  for (i in arr2) {
              if(arr1.contains(i)){
                  return true
              }
          }
          return false*/
    }

    @Test
    fun checkArray() {
        var arr1 = ArrayList<String>().apply {
            repeat(1000) {
                add("$it")
            }
            add("x")
        }.toTypedArray()
        var arr2 = arrayOf("u", "v", "w", "x", "y", "z")
        println("" + checkCommon(arr1, arr2))
    }
}