package com.appkie.notesafe.ui.screen.home_screen

import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.ui.components.BottomTabsRow
import com.appkie.notesafe.ui.components.SearchTopBar
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.screen.home_screen.notes_tab.NoteListScreen
import com.appkie.notesafe.ui.screen.home_screen.notes_tab.NoteListUiEvent
import com.appkie.notesafe.ui.screen.home_screen.notes_tab.NoteListViewModel
import com.appkie.notesafe.ui.screen.home_screen.todos_tab.TodoListScreen
import com.appkie.notesafe.ui.screen.home_screen.todos_tab.TodoListUiEvent
import com.appkie.notesafe.ui.screen.home_screen.todos_tab.TodoListViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    bottomBarTabIndex: Int = 0,
    noteListViewModel: NoteListViewModel = hiltViewModel(),
    todoListViewModel: TodoListViewModel = hiltViewModel()
) {
    var selectedTabIndex by remember { mutableStateOf(bottomBarTabIndex) }

    Scaffold(
        topBar = {
            SearchTopBar(
                onSearchTextChange = { searchQuery ->
                    if (selectedTabIndex == 0) {
                        noteListViewModel.onEvent(NoteListUiEvent.SearchNote(searchQuery))
                    } else {
                        todoListViewModel.onEvent(TodoListUiEvent.SearchTodo(searchQuery))
                    }
                },
            )
        },
        bottomBar = {
            BottomTabsRow(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index ->
                    selectedTabIndex = index
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (selectedTabIndex == 0) {
                        navController.navigate(Screen.AddEditNoteScreen.route + "/-1")
                    } else {
                        navController.navigate(Screen.AddEditTodoScreen.route + "/-1")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "New Note"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        if (selectedTabIndex == 0) {
            NoteListScreen(navController = navController)
        } else {
            TodoListScreen(navController = navController)
        }
    }
}