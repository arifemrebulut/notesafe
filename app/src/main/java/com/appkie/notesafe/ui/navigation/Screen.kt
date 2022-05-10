package com.appkie.notesafe.ui.navigation

sealed class Screen(val route: String) {
    object NoteListScreen: Screen(route = "note_list_screen")
    object TodoListScreen: Screen(route = "todo_list_screen")
    object AddEditNoteScreen: Screen(route = "add_edit_note_screen")
    object AddEditTodoScreen: Screen(route = "add_edit_todo_screen")
}
