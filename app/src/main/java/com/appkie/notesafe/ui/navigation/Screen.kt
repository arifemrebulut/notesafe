package com.appkie.notesafe.ui.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen(route = "home_screen")
    object AddEditNoteScreen: Screen(route = "add_edit_note_screen")
    object AddEditTodoScreen: Screen(route = "add_edit_todo_screen")
}
