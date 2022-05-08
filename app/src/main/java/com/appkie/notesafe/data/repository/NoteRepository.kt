package com.appkie.notesafe.data.repository

import com.appkie.notesafe.data.NoteDao
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    val noteDao: NoteDao
) {

    fun getAllTodos(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    fun searchNote(searchQuery: String) : Flow<List<Note>> {
        return noteDao.searchNote(searchQuery = searchQuery)
    }

    suspend fun getTodoById(noteId: Int): Note? {
        return noteDao.getNoteById(noteId = noteId)
    }

    suspend fun saveNote(note: Note) {
        noteDao.saveNote(note = note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note = note)
    }
}