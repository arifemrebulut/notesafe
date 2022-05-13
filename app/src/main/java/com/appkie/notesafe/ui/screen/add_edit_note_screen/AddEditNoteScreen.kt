package com.appkie.notesafe.ui.screen.add_edit_note_screen

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.ui.components.AddEditSettingsSection
import com.appkie.notesafe.ui.components.AddEditTopBar
import com.appkie.notesafe.ui.components.CustomDialogBox
import com.appkie.notesafe.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    addEditNoteViewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val id by addEditNoteViewModel.id
    val titleState by addEditNoteViewModel.title
    val descriptionState by addEditNoteViewModel.description
    val categoryState by addEditNoteViewModel.category
    val colorState by addEditNoteViewModel.color

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

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var showDeleteDialog by remember { mutableStateOf(false) }

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
                        addEditNoteViewModel.onEvent(AddEditNoteUiEvent.SaveNote)
                        navController.navigate(Screen.HomeScreen.route + "/0")
                    } else {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Title cannot be empty!",
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
                dialogText = "Are you sure you want to delete this note?",
                leftButtonText = "Cancel",
                onLeftButtonClicked = { showDeleteDialog = false },
                rightButtonText = "Delete",
                onRightButtonClicked = {
                    showDeleteDialog = false

                    if (id != -1) {
                        addEditNoteViewModel.onEvent(AddEditNoteUiEvent.DeleteNote)
                    }
                    navController.navigate(Screen.HomeScreen.route + "/0")
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
                    addEditNoteViewModel.onEvent(AddEditNoteUiEvent.CategoryChange(selectedCategory))
                },
                currentColor = colorState,
                onColorChange = { selectedColor ->
                    coroutineScope.launch {
                        animatableBackgroundColor.animateTo(
                            targetValue = Color(selectedColor),
                            animationSpec = tween(
                                durationMillis = 500
                            )
                        )
                    }

                    addEditNoteViewModel.onEvent(AddEditNoteUiEvent.ColorChange(selectedColor))
                }
            )

            NoteContent(
                titleState = titleState,
                onTitleChange = { newTitle ->
                    addEditNoteViewModel.onEvent(AddEditNoteUiEvent.TitleChange(newTitle))
                },
                descriptionState = descriptionState,
                onDescriptionChange = { newDescription ->
                    addEditNoteViewModel.onEvent(AddEditNoteUiEvent.DescriptionChange(newDescription))
                },
                backgroundColor = animatableBackgroundColor.value
            )
        }
    }
}

@Composable
fun NoteContent(
    titleState: String,
    onTitleChange: (String) -> Unit,
    descriptionState: String,
    onDescriptionChange: (String) -> Unit,
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
                    text = "Title",
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

        TextField(
            value = descriptionState,
            onValueChange = { newText ->
                onDescriptionChange(newText)
            },
            modifier = Modifier
                .fillMaxSize(),
            placeholder = {
                Text(text = "Note description starts here")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}