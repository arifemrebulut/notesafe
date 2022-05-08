package com.appkie.notesafe.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appkie.notesafe.util.Consts.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
)