package com.appkie.notesafe.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appkie.notesafe.util.Consts.CATEGORY_TABLE

@Entity(tableName = CATEGORY_TABLE)
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String
)