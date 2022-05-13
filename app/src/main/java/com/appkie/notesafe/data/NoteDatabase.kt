package com.appkie.notesafe.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appkie.notesafe.data.dao.CategoryDao
import com.appkie.notesafe.data.dao.NoteDao
import com.appkie.notesafe.data.dao.TodoDao
import com.appkie.notesafe.data.model.Category
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.model.Todo

@Database(
    entities = [
        Note::class,
        Todo::class,
        Category::class
    ],
    version = 5,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao
    abstract val todoDao: TodoDao
    abstract val categoryDao: CategoryDao
}