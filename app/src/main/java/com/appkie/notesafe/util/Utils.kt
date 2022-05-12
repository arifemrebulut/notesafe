package com.appkie.notesafe.util

import androidx.compose.ui.graphics.toArgb
import com.appkie.notesafe.ui.theme.PastelBlue
import com.appkie.notesafe.ui.theme.PastelGreen
import com.appkie.notesafe.ui.theme.PastelPurple
import com.appkie.notesafe.ui.theme.PastelYellow
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getFormattedTime(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.US)
        return simpleDateFormat.format(calendar.time).toString()
    }

    val noteColors = listOf(
        PastelYellow.toArgb(),
        PastelBlue.toArgb(),
        PastelGreen.toArgb(),
        PastelPurple.toArgb()
    )
}