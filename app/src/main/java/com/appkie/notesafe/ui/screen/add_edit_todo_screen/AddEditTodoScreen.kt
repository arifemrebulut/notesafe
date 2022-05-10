package com.appkie.notesafe.ui.screen.add_edit_todo_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.ui.components.AddEditSettingsSection
import com.appkie.notesafe.ui.components.AddEditTopBar
import com.appkie.notesafe.ui.components.DeleteDialog
import com.appkie.notesafe.ui.navigation.Screen

@Composable
fun AddEditTodoScreen(
    navController: NavController,
    addEditTodoViewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val id = addEditTodoViewModel.id
    val titleState = addEditTodoViewModel.title

    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .padding(8.dp),
        topBar = {
            AddEditTopBar(
                onBackClicked = {
                    navController.navigateUp()
                },
                onDeleteClicked = {
                    showDeleteDialog = true
                },
                onSaveClicked = {
                    addEditTodoViewModel.saveTodo()
                    navController.navigate(Screen.TodoListScreen.route)
                },
                onShareClicked = {

                }
            )
        }
    ) {
        if (showDeleteDialog) {
            DeleteDialog(
                onDismiss = { showDeleteDialog = false },
                onCancelClicked = { showDeleteDialog = false },
                onDeleteClicked = {
                    if (id != -1) {
                        addEditTodoViewModel.deleteTodo()
                    }
                    navController.navigate(Screen.TodoListScreen.route)
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AddEditSettingsSection()

            TodoContent(
                titleState = titleState,
                onTitleChange = {
                    addEditTodoViewModel.title = it
                }
            )
        }
    }
}

@Composable
fun TodoContent(
    titleState: String,
    onTitleChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = titleState,
            onValueChange = { newText ->
                onTitleChange(newText)
            },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.h6
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.h6
        )
    }
}