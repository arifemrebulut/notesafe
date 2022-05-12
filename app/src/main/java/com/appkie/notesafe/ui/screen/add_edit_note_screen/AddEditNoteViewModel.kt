package com.appkie.notesafe.ui.screen.add_edit_note_screen

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.repository.NoteRepository
import com.appkie.notesafe.ui.theme.PastelBlue
import com.appkie.notesafe.util.Utils.getFormattedTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val TAG = "AddEditNoteViewModel"

    var id by mutableStateOf(-1)
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var category by mutableStateOf("All")
    var color by mutableStateOf(PastelBlue.toArgb())

    init {
        getNoteIdFromStateHandle()
        getNoteById()
    }

    private fun getNoteIdFromStateHandle() {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            id = noteId
        }
    }

    private fun getNoteById() {
        if (id != -1) {
            viewModelScope.launch {
                val note : Note? = noteRepository.getNoteById(id)

                note?.let { note ->
                    title = note.title
                    description = note.description
                    category = note.category
                    color = note.color
                }
            }
        }
    }

    fun saveNote() {
        viewModelScope.launch {
            val note = Note(
                id = if(id != -1) id else null,
                title = title.trim(),
                description = description.trim(),
                category = category,
                creationTime = getFormattedTime(),
                color = color
            )
            noteRepository.saveNote(note = note)
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            val note = Note(
                id = id,
                title = title,
                description = description,
                category = category,
                creationTime = getFormattedTime(),
                color = color
            )
            noteRepository.deleteNote(note = note)
        }
    }
}
