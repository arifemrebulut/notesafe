package com.appkie.notesafe.util

import android.util.Log
import androidx.compose.ui.graphics.toArgb
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.model.Todo
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

    fun <T> formatTextForShare(item: T): String {
        return when (item) {
            is Note -> {
                """
                    ${item.title}
                                    
                    ${item.description}
                                    
                    ${item.creationTime}
                 """.trimIndent()
            }
            is Todo -> {
                """
                    ${item.title}
                                    
                    ${item.creationTime}
                 """.trimIndent()
            }
            else -> {
                Log.d("formatTextForShare", "formatTextForShare: $item")
                return "Text type mismatch!"
            }
        }
    }

    fun formatCategoryName(categoryName: String) : String {
        return categoryName
            .lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }

    val noteColors = listOf(
        PastelYellow.toArgb(),
        PastelBlue.toArgb(),
        PastelGreen.toArgb(),
        PastelPurple.toArgb()
    )
}