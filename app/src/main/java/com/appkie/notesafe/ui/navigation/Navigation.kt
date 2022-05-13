package com.appkie.notesafe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.appkie.notesafe.ui.screen.add_edit_note_screen.AddEditNoteScreen
import com.appkie.notesafe.ui.screen.add_edit_todo_screen.AddEditTodoScreen
import com.appkie.notesafe.ui.screen.home_screen.HomeScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route + "/{bottomBarTabIndex}"
    ) {

        composable(
            route = Screen.HomeScreen.route + "/{bottomBarTabIndex}",
            arguments = listOf(navArgument(name = "bottomBarTabIndex") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val bottomBarTabIndex = navBackStackEntry.arguments?.getInt("bottomBarTabIndex")

            HomeScreen(
                navController = navController,
                bottomBarTabIndex = bottomBarTabIndex ?: 0
            )
        }

        composable(
            route = Screen.AddEditNoteScreen.route + "/{noteId}",
            arguments = listOf(navArgument(name = "noteId") {
                type = NavType.IntType
            })
        ) {
            AddEditNoteScreen(navController = navController)
        }

        composable(
            route = Screen.AddEditTodoScreen.route + "/{todoId}",
            arguments = listOf(navArgument(name = "todoId") {
                type = NavType.IntType
            })
        ) {
            AddEditTodoScreen(navController = navController)
        }
    }
}