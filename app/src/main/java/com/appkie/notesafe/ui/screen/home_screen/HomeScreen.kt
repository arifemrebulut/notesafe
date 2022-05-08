package com.appkie.notesafe.ui.screen.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.ui.components.SearchBox
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.theme.PastelBlue

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val notes by homeViewModel.allNotes

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        topBar = { HomeTopBar() },
        content = {
            NoteList(
                noteList = notes,
                onNoteClicked = { noteId ->
                    navController.navigate(Screen.AddEditNoteScreen.route + "/$noteId")
                }
            )
        }
    )
}

@Composable
fun NoteList(
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

@Composable
fun NoteCard(
    note: Note,
    onNoteClicked: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = PastelBlue,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable {
                onNoteClicked(note.id!!)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = note.title ?: "",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium
            )

            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                text = note.description ?: "",
                maxLines = 3,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "#Category",
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Normal
                )

                Text(
                    text = "20:15 - 12/03/2022",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        SearchBox(
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black.copy(alpha = 0.5f),
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                )
            },
            trailingIcon = null,
            modifier = Modifier
                .height(38.dp)
                .background(
                    Color.LightGray.copy(alpha = 0.3f),
                    RoundedCornerShape(percent = 50)
                ),
            fontSize = 14.sp,
            placeholderText = "Search your notes"
        )
    }
}