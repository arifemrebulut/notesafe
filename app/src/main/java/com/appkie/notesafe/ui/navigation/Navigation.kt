package com.appkie.notesafe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.appkie.notesafe.ui.screen.add_edit_note_screen.AddEditNoteScreen
import com.appkie.notesafe.ui.screen.home_screen.HomeScreen
import com.appkie.notesafe.ui.screen.note_list_screen.NoteListScreen
import com.appkie.notesafe.ui.screen.todo_list_screen.TodoListScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NoteListScreen.route
    ) {
        composable(route = Screen.NoteListScreen.route) {
            NoteListScreen(navController = navController)
        }

        composable(route = Screen.TodoListScreen.route) {
            TodoListScreen()
        }

        composable(
            route = Screen.AddEditNoteScreen.route + "/{noteId}",
            arguments = listOf(navArgument(name = "noteId") {
                type = NavType.IntType
            })
        ) {
            AddEditNoteScreen(navController = navController)
        }
    }
}