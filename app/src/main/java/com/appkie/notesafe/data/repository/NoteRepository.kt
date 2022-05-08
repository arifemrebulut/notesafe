package com.appkie.notesafe.data.repository

import com.appkie.notesafe.data.NoteDao
import com.appkie.notesafe.data.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    val noteDao: NoteDao
) {

    fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    fun searchNote(searchQuery: String) : Flow<List<Note>> {
        return noteDao.searchNote(searchQuery = searchQuery)
    }

    suspend fun getNoteById(noteId: Int): Note? {
        return noteDao.getNoteById(noteId = noteId)
    }

    suspend fun saveNote(note: Note) {
        noteDao.saveNote(note = note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note = note)
    }
}