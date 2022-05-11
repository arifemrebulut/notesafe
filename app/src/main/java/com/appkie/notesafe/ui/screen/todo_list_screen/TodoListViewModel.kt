package com.appkie.notesafe.ui.screen.todo_list_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.data.repository.TodoRepository
import com.appkie.notesafe.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val TAG = "TodoListViewModel"

    private val _allTodos = mutableStateOf<List<Todo>>(emptyList())
    val allTodos: State<List<Todo>> = _allTodos

    private val _searchedTodos = mutableStateOf<List<Todo>>(emptyList())
    val searchedTodos: State<List<Todo>> = _searchedTodos

    private val _searchTextState = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    init {
        getAllTodos()
    }

    fun onEvent(event: TodoListUiEvent) {
        when (event) {
            is TodoListUiEvent.ChangeCategory -> {
                getAllTodos(event.category)
            }
            is TodoListUiEvent.OrderTodos -> {

            }
            is TodoListUiEvent.SearchTodo -> {
                searchTodo(event.searchQuery)
            }
            is TodoListUiEvent.DeleteTodo -> {

            }
        }
    }

    private fun getAllTodos(
        category: String = "All"
    ) {
        try {
            viewModelScope.launch {
                todoRepository.getAllTodos().collect { todoList ->
                    _allTodos.value = if (category == "All") {
                        todoList
                    } else {
                        todoList.filter {
                            it.category == category
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "getAllTodos: $e")
        }
    }

    private fun searchTodo(searchQuery: String) {
        try {
            viewModelScope.launch {
                todoRepository.searchTodo(searchQuery = "%$searchQuery%").collect {
                    _searchedTodos.value = it
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "searchTodo: $e")
        }
    }

    private fun filterByCategory(todos: List<Todo>, category: String) : List<Todo> {

        return if (category == "All") {
            todos
        } else {
            todos.filter {
                it.category == category
            }
        }
    }

    private fun sortByDateViewModel(todos: List<Todo>, orderType: OrderType) : List<Todo> {

        val simpleDateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.US)

        return if (orderType == OrderType.NEWEST) {
            todos.sortedByDescending {
                simpleDateFormat.parse(it.creationTime)
            }

        } else {
            todos.sortedBy {
                simpleDateFormat.parse(it.creationTime)
            }
        }
    }
}