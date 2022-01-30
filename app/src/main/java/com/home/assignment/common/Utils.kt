package com.home.assignment.common

import android.content.Context
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat

class Utils {

    companion object {


        fun showToastMsg(requireContext: Context, s: String) {
            Toast.makeText(requireContext, s, Toast.LENGTH_SHORT).show()
        }

        fun dateToMilliSeconds(date: String): Long {
            var timeInMilliseconds: Long = 0
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            try {
                val mDate = sdf.parse(date)
                timeInMilliseconds = mDate.time
                println("Date in milli :: $timeInMilliseconds")
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return timeInMilliseconds
        }

        fun dateTimeToMilliSeconds(date: String): Long {
            var timeInMilliseconds: Long = 0
            val sdf = SimpleDateFormat("dd MMM,yyyy hh:mm:ss")
            try {
                val mDate = sdf.parse(date)
                timeInMilliseconds = mDate.time
                println("Date in milli :: $timeInMilliseconds")
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return timeInMilliseconds
        }
    }
}


