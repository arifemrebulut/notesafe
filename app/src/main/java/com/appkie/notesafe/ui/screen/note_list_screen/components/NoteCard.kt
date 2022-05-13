package com.appkie.notesafe.ui.screen.note_list_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.ui.theme.NotesafeTheme
import com.appkie.notesafe.ui.theme.PastelBlue
import com.appkie.notesafe.ui.theme.Shapes
import com.appkie.notesafe.util.Utils

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteCard(
    note: Note,
    onNoteClicked: (Int) -> Unit
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable {
                onNoteClicked(note.id!!)
            }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        backgroundColor = Color(note.color),
        onClick = { onNoteClicked(note.id!!) }
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                )

                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState)
                        .clickable {
                            expandedState = !expandedState
                        },
                    imageVector = Icons.Outlined.ExpandMore,
                    contentDescription = "Read More Arrow"
                )
            }

            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                text = note.description.ifBlank { "No description here.." },
                maxLines = if (expandedState) 4 else 1,
                style = MaterialTheme.typography.subtitle1,
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

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    NotesafeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            NoteCard(
                note = Note(
                    id = 500,
                    title = "Hello World",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
                            " eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                            " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris" +
                            " nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor" +
                            " in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla " +
                            "pariatur. Excepteur sint occaecat cupidatat non proident, sunt" +
                            " in culpa qui officia deserunt mollit anim id est laborum.",
                    category = "All",
                    color = PastelBlue.toArgb(),
                    creationTime = Utils.getFormattedTime()
                ),
                onNoteClicked = {}
            )
        }
    }
}