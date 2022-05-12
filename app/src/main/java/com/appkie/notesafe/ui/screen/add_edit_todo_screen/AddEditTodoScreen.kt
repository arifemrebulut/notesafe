package com.appkie.notesafe.ui.screen.add_edit_todo_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.ui.components.AddEditSettingsSection
import com.appkie.notesafe.ui.components.AddEditTopBar
import com.appkie.notesafe.ui.components.CustomDialogBox
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.theme.PastelBlue

@Composable
fun AddEditTodoScreen(
    navController: NavController,
    addEditTodoViewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val id = addEditTodoViewModel.id
    val titleState = addEditTodoViewModel.title
    val categoryState = addEditTodoViewModel.category
    val colorState = addEditTodoViewModel.color

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
            CustomDialogBox(
                dialogText = "Are you sure you want to delete this todo?",
                leftButtonText = "Cancel",
                onLeftButtonClicked = { showDeleteDialog = false },
                rightButtonText = "Delete",
                onRightButtonClicked = {
                    showDeleteDialog = false

                    if (id != -1) {
                        addEditTodoViewModel.deleteTodo()
                    }
                    navController.navigate(Screen.TodoListScreen.route)
                },
                onDismiss = { showDeleteDialog = false }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AddEditSettingsSection(
                currentCategory = categoryState,
                onCategorySelected = {
                    addEditTodoViewModel.category = it
                },
                currentColor = PastelBlue.toArgb(),
                onColorChange = { selectedColor ->
                    addEditTodoViewModel.color = selectedColor
                }
            )

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
                    text = "Todo...",
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