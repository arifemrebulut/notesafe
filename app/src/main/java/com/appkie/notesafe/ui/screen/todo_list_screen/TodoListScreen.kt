package com.appkie.notesafe.ui.screen.todo_list_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.ui.components.SearchTopBar
import com.appkie.notesafe.ui.components.SortingSettingsBar
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.screen.todo_list_screen.components.TodoCard

@Composable
fun TodoListScreen(
    navController: NavController,
    todoListViewModel: TodoListViewModel = hiltViewModel()
) {

    val todos by todoListViewModel.allTodos
    val searchedTodos by todoListViewModel.searchedTodos
    val searching = todoListViewModel.searchTextState.value.isNotBlank()

    Scaffold(
        topBar = {
            SearchTopBar(
                onSearchTextChange = { searchQuery ->
                    todoListViewModel.onEvent(TodoListUiEvent.SearchTodo(searchQuery))
                }
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
                    .padding(bottom = 12.dp),
                onCategoryChange = {
                    todoListViewModel.onEvent(TodoListUiEvent.ChangeCategory(it))
                },
                onSortingFilterChange = { orderType ->
                    todoListViewModel.onEvent(TodoListUiEvent.OrderTodos(orderType))
                }
            )

            LazyColumn(
                contentPadding = PaddingValues(top = 1.dp, bottom = 52.dp)
            ) {

                val todoList = if (searching) searchedTodos else todos

                items(todoList) { item ->
                    TodoCard(
                        todo = item,
                        onCheckedChange = {
                            todoListViewModel.onEvent(TodoListUiEvent.CheckedChange(todo = item, checked = it))
                        }
                    )
                }
            }
        }
    }
}