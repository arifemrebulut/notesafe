package com.appkie.notesafe.ui.screen.add_edit_todo_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _id = mutableStateOf(-1)
    val id: State<Int> = _id

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _category = mutableStateOf("All")
    val category: State<String> = _category

    private val _color = mutableStateOf(PastelBlue.toArgb())
    val color: State<Int> = _color

    private val _checked = mutableStateOf(false)

    init {
        getTodoIdFromStateHandle()
        getTodoById()
    }

    fun onEvent(event: AddEditTodoUiEvent) {
        when (event) {
            is AddEditTodoUiEvent.SaveTodo -> {
                saveTodo()
            }
            is AddEditTodoUiEvent.DeleteTodo -> {
                deleteTodo()
            }
            is AddEditTodoUiEvent.TitleChange -> {
                _title.value = event.title
            }
            is AddEditTodoUiEvent.CheckedChange -> {
                _checked.value = event.checked
            }
            is AddEditTodoUiEvent.CategoryChange -> {
                _category.value = event.category
            }
            is AddEditTodoUiEvent.ColorChange -> {
                _color.value = event.color
            }
        }
    }

    private fun getTodoIdFromStateHandle() {
        savedStateHandle.get<Int>("todoId")?.let { todoId ->
            _id.value = todoId
        }
    }

    private fun getTodoById() {
        if (_id.value != -1) {
            viewModelScope.launch {
                val todo : Todo? = todoRepository.getTodoById(_id.value)

                todo?.let { todoById ->
                    _title.value = todoById.title
                    _checked.value = todoById.checked
                    _category.value = todoById.category
                    _color.value = todoById.color
                }
            }
        }
    }

    private fun saveTodo() {
        viewModelScope.launch {
            val todo = Todo(
                id = if(_id.value != -1) _id.value else null,
                title = _title.value.trim(),
                checked = _checked.value,
                category = _category.value,
                creationTime = getFormattedTime(),
                color = _color.value
            )
            todoRepository.saveTodo(todo = todo)
        }
    }

    private fun deleteTodo() {
        viewModelScope.launch {
            val todo = Todo(
                id = _id.value,
                title = _title.value,
                checked = _checked.value,
                category = _category.value,
                creationTime = getFormattedTime(),
                color = _color.value
            )
            todoRepository.deleteTodo(todo = todo)
        }
    }
}