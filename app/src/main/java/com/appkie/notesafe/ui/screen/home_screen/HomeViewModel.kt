package com.appkie.notesafe.ui.screen.home_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.data.repository.NoteRepository
import com.appkie.notesafe.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val TAG = "HomeViewModel"

    private val _allNotes = mutableStateOf<List<Note>>(emptyList())
    val allNotes: State<List<Note>> = _allNotes

    private val _allTodos = mutableStateOf<List<Todo>>(emptyList())
    val allTodos: State<List<Todo>> = _allTodos

    init {
        getAllNotes()
        getAllTodos()
    }

    private fun getAllNotes() {
        try {
            viewModelScope.launch {
                noteRepository.getAllNotes().collect {
                    _allNotes.value = it
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "getAllNotes: $e")
        }
    }
    
    private fun getAllTodos() {
        try {
            viewModelScope.launch { 
                todoRepository.getAllTodos().collect() {
                    _allTodos.value = it
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "getAllTodos: $e")
        }
    }
}