package com.appkie.notesafe.ui.screen.home_screen.todos_tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.R
import com.appkie.notesafe.ui.components.SortingSettingsBar
import com.appkie.notesafe.ui.navigation.Screen

@Composable
fun TodoListScreen(
    navController: NavController,
    todoListViewModel: TodoListViewModel = hiltViewModel()
) {

    val todos by todoListViewModel.allTodos
    val categoryList by todoListViewModel.categoryList
    val searchedTodos by todoListViewModel.searchedTodos
    val searching = todoListViewModel.searchTextState.value.isNotBlank()

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SortingSettingsBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            categoryList = categoryList,
            onCategoryChange = {
                todoListViewModel.onEvent(TodoListUiEvent.ChangeCategory(it))
            },
            onSortingFilterChange = { orderType ->
                todoListViewModel.onEvent(TodoListUiEvent.OrderTodos(orderType))
            }
        )

        if (todos.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(top = 1.dp, bottom = 52.dp)
            ) {

                val todoList = if (searching) searchedTodos else todos

                items(todoList) { item ->
                    TodoCard(
                        todo = item,
                        onCheckedChange = {
                            todoListViewModel.onEvent(
                                TodoListUiEvent.CheckedChange(
                                    todo = item,
                                    checked = it
                                )
                            )
                        },
                        onTodoClicked = { navController.navigate(Screen.AddEditTodoScreen.route + "/${item.id}") }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .offset(y = (-52).dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_no_todos),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Create Your First Todos!",
                        style = MaterialTheme.typography.h5,
                        color = Color.Black.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}