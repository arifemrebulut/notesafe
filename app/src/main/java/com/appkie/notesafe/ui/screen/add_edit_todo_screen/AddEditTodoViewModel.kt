package com.appkie.notesafe.ui.screen.add_edit_todo_screen

import androidx.compose.animation.Animatable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.data.repository.TodoRepository
import com.appkie.notesafe.ui.theme.PastelBlue
import com.appkie.notesafe.util.Utils.getFormattedTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var id by mutableStateOf(-1)
    var title by mutableStateOf("")
    var checked by mutableStateOf(false)
    var category by mutableStateOf("All")
    var color by mutableStateOf(PastelBlue.toArgb())

    init {
        getTodoIdFromStateHandle()
        getTodoById()
    }

    private fun getTodoIdFromStateHandle() {
        savedStateHandle.get<Int>("todoId")?.let { todoId ->
            id = todoId
        }
    }

    private fun getTodoById() {
        if (id != -1) {
            viewModelScope.launch {
                val todo : Todo? = todoRepository.getTodoById(id)

                todo?.let { todo ->
                    title = todo.title
                    checked = todo.checked
                    color = color
                }
            }
        }
    }

    fun saveTodo() {
        viewModelScope.launch {
            val todo = Todo(
                id = if(id != -1) id else null,
                title = title.trim(),
                checked = checked,
                category = category,
                creationTime = getFormattedTime(),
                color = color
            )
            todoRepository.saveTodo(todo = todo)
        }
    }

    fun deleteTodo() {
        viewModelScope.launch {
            val todo = Todo(
                id = id,
                title = title,
                checked = checked,
                category = category,
                creationTime = getFormattedTime(),
                color = color
            )
            todoRepository.deleteTodo(todo = todo)
        }
    }
}