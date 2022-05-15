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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.R
import com.appkie.notesafe.ui.components.AddCategoryDialog
import com.appkie.notesafe.ui.components.AddEditSettingsSection
import com.appkie.notesafe.ui.components.AddEditTopBar
import com.appkie.notesafe.ui.components.CustomDialogBox
import com.appkie.notesafe.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun AddEditTodoScreen(
    navController: NavController,
    addEditTodoViewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val id by addEditTodoViewModel.id
    val titleState by addEditTodoViewModel.title
    val categoryState by addEditTodoViewModel.category
    val categoryList by addEditTodoViewModel.categoryList
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
    val scaffoldState = rememberScaffoldState()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showAddCategoryDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .padding(8.dp),
        scaffoldState = scaffoldState,
        topBar = {
            AddEditTopBar(
                onBackClicked = {
                    navController.navigateUp()
                },
                onDeleteClicked = {
                    showDeleteDialog = true
                },
                onSaveClicked = {
                    if (titleState.isNotBlank()) {
                        addEditTodoViewModel.onEvent(AddEditTodoUiEvent.SaveTodo)
                        navController.navigate(Screen.HomeScreen.route + "/1")
                    } else {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = R.string.empty_todo_snackbar_text.toString(),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                onShareClicked = {

                }
            )
        }
    ) {
        if (showDeleteDialog) {
            CustomDialogBox(
                dialogText = stringResource(id = R.string.delete_todo_dialog_text),
                leftButtonText = stringResource(id = R.string.cancel_text),
                onLeftButtonClicked = { showDeleteDialog = false },
                rightButtonText = stringResource(id = R.string.delete_text),
                onRightButtonClicked = {
                    showDeleteDialog = false

                    if (id != -1) {
                        addEditTodoViewModel.onEvent(AddEditTodoUiEvent.DeleteTodo)
                    }
                    navController.navigate(Screen.HomeScreen.route + "/1")
                },
                onDismiss = { showDeleteDialog = false }
            )
        }

        if (showAddCategoryDialog) {
            AddCategoryDialog(
                onAddClicked = { newCategory ->
                    addEditTodoViewModel.onEvent(AddEditTodoUiEvent.AddNewCategory(newCategory))
                    showAddCategoryDialog = false
                },
                onDismiss = { showAddCategoryDialog = false },
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AddEditSettingsSection(
                categoryList = categoryList,
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
                },
                onAddCategoryClicked = {
                    showAddCategoryDialog = true
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
                    text = stringResource(id = R.string.todo_title_placeholder),
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