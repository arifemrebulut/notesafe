package com.appkie.notesafe.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getFormattedTime(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.US)
        return simpleDateFormat.format(calendar.time).toString()
    }

    fun sortByDate(dates: List<String>, orderType: OrderType) : List<String> {

        val simpleDateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.US)

        return if (orderType == OrderType.NEWEST) {
            dates.sortedBy {
                simpleDateFormat.parse(it)
            }
        } else {
            dates.sortedByDescending {
                simpleDateFormat.parse(it)
            }
        }
    }
}