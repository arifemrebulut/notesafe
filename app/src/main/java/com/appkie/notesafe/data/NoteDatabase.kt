package com.appkie.notesafe.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.model.Todo

@Database(
    entities = [
        Note::class,
        Todo::class
    ],
    version = 2,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao
    abstract val todoDao: TodoDao
}