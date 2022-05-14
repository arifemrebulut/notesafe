package com.appkie.notesafe.ui.screen.home_screen.todos_tab

import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.util.OrderType

sealed class TodoListUiEvent {
    data class ChangeCategory(val category: String) : TodoListUiEvent()
    data class OrderTodos(val orderType: OrderType) : TodoListUiEvent()
    data class SearchTodo(val searchQuery: String) : TodoListUiEvent()
    data class DeleteTodo(val todoId: Int) : TodoListUiEvent()
    data class CheckedChange(val todo: Todo, val checked: Boolean) : TodoListUiEvent()
}
