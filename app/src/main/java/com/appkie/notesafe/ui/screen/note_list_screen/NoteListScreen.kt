package com.appkie.notesafe.ui.screen.note_list_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
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
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.ui.components.BottomTabsRow
import com.appkie.notesafe.ui.components.SearchTopBar
import com.appkie.notesafe.ui.components.SortingSettingsBar
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.screen.note_list_screen.components.NoteCard

@Composable
fun NoteListScreen(
    navController: NavController,
    noteListViewModel: NoteListViewModel = hiltViewModel(),
) {

    val notes by noteListViewModel.allNotes
    val searchedNotes by noteListViewModel.searchedNotes
    val searching = noteListViewModel.searchTextState.value.isNotBlank()

    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            SearchTopBar(
                onSearchTextChange = { searchQuery ->
                    noteListViewModel.onEvent(NoteListUiEvent.SearchNote(searchQuery))
                },
            )
        },
        bottomBar = {
            BottomTabsRow(
                selectedTabIndex = selectedTabIndex,
                onNotesClicked = {},
                onTodosClicked = { navController.navigate(Screen.TodoListScreen.route) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route + "/-1")
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
                    noteListViewModel.onEvent(NoteListUiEvent.ChangeCategory(it))
                },
                onSortingFilterChange = { orderType ->
                    noteListViewModel.onEvent(NoteListUiEvent.OrderNotes(orderType))
                }
            )

            LazyColumn(
                contentPadding = PaddingValues(top = 1.dp, bottom = 52.dp)
            ) {

                val noteList: List<Note> = if (searching) searchedNotes else notes

                items(noteList) { item ->
                    NoteCard(
                        note = item,
                        onNoteClicked = { navController.navigate(Screen.AddEditNoteScreen.route + "/${item.id}") }
                    )
                }
            }
        }
    }
}