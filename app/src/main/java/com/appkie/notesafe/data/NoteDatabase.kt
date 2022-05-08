package com.appkie.notesafe.data

import androidx.room.Database
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.model.Todo

@Database(
    entities = [
        Note::class,
        Todo::class
    ],
    version = 1
)
abstract class NoteDatabase {

    abstract val noteDao: NoteDao
    abstract val todoDao: TodoDao
}