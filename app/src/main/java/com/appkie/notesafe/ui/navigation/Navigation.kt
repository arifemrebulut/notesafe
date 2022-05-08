package com.appkie.notesafe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.appkie.notesafe.ui.screen.add_edit_note_screen.AddEditNoteScreen
import com.appkie.notesafe.ui.screen.add_edit_todo_screen.AddEditTodoScreen
import com.appkie.notesafe.ui.screen.home_screen.HomeScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.AddEditNoteScreen.route + "/{noteId}") {
            AddEditNoteScreen(navController = navController)
        }

        composable(route = Screen.AddEditTodoScreen.route + "/{todoId}") {
            AddEditTodoScreen(navController = navController)
        }
    }
}