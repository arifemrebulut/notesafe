package com.appkie.notesafe.ui.screen.todo_list_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val TAG = "TodoListViewModel"

    private val _allTodos = mutableStateOf<List<Todo>>(emptyList())
    val allTodos: State<List<Todo>> = _allTodos

    init {
        getAllTodos()
    }

    private fun getAllTodos() {
        try {
            viewModelScope.launch {
                todoRepository.getAllTodos().collect {
                    _allTodos.value = it
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "getAllTodos: $e")
        }
    }
}