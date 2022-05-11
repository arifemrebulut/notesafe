package com.appkie.notesafe.ui.screen.note_list_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
) : ViewModel() {

    private val TAG = "NoteListViewModel"

    private val _allNotes = mutableStateOf<List<Note>>(emptyList())
    val allNotes: State<List<Note>> = _allNotes

    init {
        getAllNotes()
    }

    fun onEvent(event: NoteListUiEvent) {
        when (event) {
            is NoteListUiEvent.ChangeCategory -> {
                getAllNotes(event.category)
            }
            is NoteListUiEvent.OrderNotes -> {

            }
            is NoteListUiEvent.DeleteNote -> {

            }
        }
    }

    private fun getAllNotes(
        category: String = "All"
    ) {
        try {
            viewModelScope.launch {
                noteRepository.getAllNotes().collect { noteList ->
                    _allNotes.value = if (category == "All") {
                        noteList
                    } else {
                        noteList.filter {
                            it.category == category
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "getAllNotes: $e")
        }
    }
}