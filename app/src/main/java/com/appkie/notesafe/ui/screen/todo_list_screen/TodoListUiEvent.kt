package com.appkie.notesafe.ui.screen.todo_list_screen

import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.util.Order

sealed class TodoListUiEvent {
    data class ChangeCategory(val category: String) : TodoListUiEvent()
    data class OrderTodos(val order: Order) : TodoListUiEvent()
    data class DeleteTodo(val todo: Todo) : TodoListUiEvent()
}
