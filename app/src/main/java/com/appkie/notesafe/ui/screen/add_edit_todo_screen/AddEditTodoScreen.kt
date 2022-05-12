package com.appkie.notesafe.ui.screen.add_edit_todo_screen

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.ui.components.AddEditSettingsSection
import com.appkie.notesafe.ui.components.AddEditTopBar
import com.appkie.notesafe.ui.components.CustomDialogBox
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.theme.PastelBlue
import kotlinx.coroutines.launch

@Composable
fun AddEditTodoScreen(
    navController: NavController,
    addEditTodoViewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val id by addEditTodoViewModel.id
    val titleState by addEditTodoViewModel.title
    val categoryState by addEditTodoViewModel.category
    val colorState by addEditTodoViewModel.color

    val animatableBackgroundColor = remember {
        Animatable(initialValue = Color(colorState))
    }

    var colorLoaded by remember { mutableStateOf(false) }

    if (!colorLoaded) {
        LaunchedEffect(key1 = colorState) {
            colorLoaded = true
            animatableBackgroundColor.snapTo(Color(colorState))
        }
    }


    val coroutineScope = rememberCoroutineScope()

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
                    addEditTodoViewModel.onEvent(AddEditTodoUiEvent.SaveTodo)
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
                        addEditTodoViewModel.onEvent(AddEditTodoUiEvent.DeleteTodo)
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
                onCategorySelected = { selectedCategory ->
                    addEditTodoViewModel.onEvent(AddEditTodoUiEvent.CategoryChange(selectedCategory))
                },
                currentColor = colorState,
                onColorChange = { selectedColor ->
                    addEditTodoViewModel.onEvent(AddEditTodoUiEvent.ColorChange(selectedColor))

                    coroutineScope.launch {
                        animatableBackgroundColor.animateTo(
                            targetValue = Color(selectedColor),
                            animationSpec = tween(

                                durationMillis = 500
                            )
                        )
                    }
                }
            )

            TodoContent(
                titleState = titleState,
                onTitleChange = { newTitle ->
                    addEditTodoViewModel.onEvent(AddEditTodoUiEvent.TitleChange(newTitle))
                },
                backgroundColor = animatableBackgroundColor.value
            )
        }
    }
}

@Composable
fun TodoContent(
    titleState: String,
    onTitleChange: (String) -> Unit,
    backgroundColor: Color
) {
    Column(
        modifier = Modifier
            .padding(bottom = 60.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxSize()
            .background(backgroundColor),
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
                backgroundColor = backgroundColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.h6
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoContentPreview() {
    TodoContent(
            titleState = "Title",
        onTitleChange = {

        },
        backgroundColor = PastelBlue
    )
}