package com.appkie.notesafe.ui.screen.add_edit_todo_screen

sealed class AddEditTodoUiEvent {
    object DeleteTodo : AddEditTodoUiEvent()
    object SaveTodo : AddEditTodoUiEvent()
    data class TitleChange(val title: String) : AddEditTodoUiEvent()
    data class CheckedChange(val checked: Boolean) : AddEditTodoUiEvent()
    data class CategoryChange(val category: String) : AddEditTodoUiEvent()
    data class ColorChange(val color: Int) : AddEditTodoUiEvent()
    data class AddNewCategory(val categoryName: String) : AddEditTodoUiEvent()
}