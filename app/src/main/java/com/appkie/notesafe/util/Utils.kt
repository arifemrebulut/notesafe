package com.appkie.notesafe.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getFormattedTime(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.US)
        return simpleDateFormat.format(calendar.time).toString()
    }
}