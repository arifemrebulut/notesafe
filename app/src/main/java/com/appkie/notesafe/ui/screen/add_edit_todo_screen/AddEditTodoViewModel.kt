package com.appkie.notesafe.ui.screen.add_edit_todo_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appkie.notesafe.data.model.Category
import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.data.repository.CategoryRepository
import com.appkie.notesafe.data.repository.TodoRepository
import com.appkie.notesafe.ui.theme.PastelBlue
import com.appkie.notesafe.util.Utils.getFormattedTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val categoryRepository: CategoryRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _id = mutableStateOf(-1)
    val id: State<Int> = _id

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _category = mutableStateOf("All")
    val category: State<String> = _category

    private val _categoryList = mutableStateOf<List<Category>>(emptyList())
    val categoryList: State<List<Category>> = _categoryList

    private val _color = mutableStateOf(PastelBlue.toArgb())
    val color: State<Int> = _color

    private val _checked = mutableStateOf(false)

    init {
        getTodoIdFromStateHandle()
        getTodoById()
        getCategoryList()
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
            is AddEditTodoUiEvent.AddNewCategory -> {
                addNewCategory(event.categoryName)
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

    private fun getCategoryList() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collect {
                _categoryList.value = it
            }
        }
    }

    private fun addNewCategory(categoryName: String) {
        viewModelScope.launch {
            val category = Category(
                name = categoryName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            )
            categoryRepository.addCategory(category)
        }
    }
}