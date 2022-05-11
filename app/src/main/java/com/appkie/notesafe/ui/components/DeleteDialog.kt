package com.appkie.notesafe.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.appkie.notesafe.ui.theme.Black
import com.appkie.notesafe.ui.theme.White

@Composable
fun DeleteDialog(
    onDismiss: () -> Unit,
    onCancelClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .width(280.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(White)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .offset((-4).dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Icon",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(26.dp)
                    )
                    Text(
                        text = "Delete",
                        style = MaterialTheme.typography.h6
                    )
                }

                Text(
                    text = "Are you sure you want to delete this note?",
                    style = MaterialTheme.typography.body1,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        onClick = onCancelClicked,
                        elevation = ButtonDefaults.elevation(0.dp),
                        border = BorderStroke(1.dp, Black),
                        modifier = Modifier
                            .width(120.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.button,
                        )
                    }

                    Button(
                        onClick = onDeleteClicked,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Red,
                            contentColor = White
                        ),
                        elevation = ButtonDefaults.elevation(0.dp),
                        modifier = Modifier
                            .width(120.dp)
                    ) {
                        Text(
                            text = "Delete",
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DeleteDialogPreview() {
    DeleteDialog(onDismiss = { /*TODO*/ }, onCancelClicked = { /*TODO*/ }) {
        
    }
}