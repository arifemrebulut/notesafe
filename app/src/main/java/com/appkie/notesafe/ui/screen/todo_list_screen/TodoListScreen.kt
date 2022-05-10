package com.appkie.notesafe.ui.screen.todo_list_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.ui.components.HomeTopBar
import com.appkie.notesafe.ui.components.SortingSettingsBar
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.screen.todo_list_screen.components.TodoCard

@Composable
fun TodoListScreen(
    navController: NavController,
    todoListViewModel: TodoListViewModel = hiltViewModel()
) {

    val todoList by todoListViewModel.allTodos

    Scaffold(
        topBar = {
            HomeTopBar(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditTodoScreen.route + "/-1")
                },
                modifier = Modifier.offset(y = (-56).dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "New Todo"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SortingSettingsBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(top = 1.dp, bottom = 52.dp)
            ) {
                items(todoList) { item ->
                    TodoCard(
                        todo = item,
                        onCheckedChange = {}
                    )
                }
            }
        }
    }
}