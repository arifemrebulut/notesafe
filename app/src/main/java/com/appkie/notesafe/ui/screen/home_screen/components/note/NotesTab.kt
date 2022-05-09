package com.appkie.notesafe.ui.screen.home_screen.components.note

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.data.model.Note

@Composable
fun NotesTab(
    noteList: List<Note>,
    onNoteClicked: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 1.dp, bottom = 52.dp)
    ) {
        items(noteList) { item ->
            NoteCard(
                note = item,
                onNoteClicked = onNoteClicked
            )
        }
    }
}