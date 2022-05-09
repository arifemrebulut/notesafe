package com.appkie.notesafe.ui.screen.note_list_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.ui.components.SortingSettingsBar
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.screen.note_list_screen.components.NoteCard

@Composable
fun NoteListScreen(
    navController: NavController,
    noteListViewModel: NoteListViewModel = hiltViewModel(),
) {
    val noteList by noteListViewModel.allNotes

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
            items(noteList) { item ->
                NoteCard(
                    note = item,
                    onNoteClicked = { navController.navigate(Screen.AddEditNoteScreen.route + "/{${item.id}}") }
                )
            }
        }
    }
}