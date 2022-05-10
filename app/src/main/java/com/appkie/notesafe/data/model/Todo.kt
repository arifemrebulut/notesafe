package com.appkie.notesafe.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appkie.notesafe.util.Consts.TODO_TABLE

@Entity(tableName = TODO_TABLE)
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val checked: Boolean,
    val category: String,
    val fav: Boolean,
    val color: Int,
    val creationTime: Long
)