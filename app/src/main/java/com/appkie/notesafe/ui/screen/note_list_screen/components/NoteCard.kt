package com.appkie.notesafe.ui.screen.note_list_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.ui.theme.PastelBlue

@Composable
fun NoteCard(
    note: Note,
    onNoteClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable {
                onNoteClicked(note.id!!)
            },
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color(note.color),
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
                maxLines = 1,
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
                    text = "#${note.category}",
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Normal
                )

                Text(
                    text = note.creationTime,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}