package com.appkie.notesafe.ui.screen.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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