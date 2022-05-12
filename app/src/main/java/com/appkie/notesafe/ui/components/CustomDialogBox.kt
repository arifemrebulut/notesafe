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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.appkie.notesafe.ui.theme.Black
import com.appkie.notesafe.ui.theme.Blue
import com.appkie.notesafe.ui.theme.NotesafeTheme
import com.appkie.notesafe.ui.theme.White

@Composable
fun CustomDialogBox(
    dialogText: String,
    leftButtonText: String,
    onLeftButtonClicked: () -> Unit,
    rightButtonText: String,
    onRightButtonClicked: () -> Unit,
    onDismiss: () -> Unit
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = dialogText,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        onClick = onLeftButtonClicked,
                        elevation = ButtonDefaults.elevation(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .width(100.dp)
                    ) {
                        Text(
                            text = leftButtonText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black.copy(alpha = ContentAlpha.medium)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = onRightButtonClicked,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Blue,
                            contentColor = White
                        ),
                        elevation = ButtonDefaults.elevation(0.dp),
                        modifier = Modifier
                            .width(100.dp)
                    ) {
                        Text(
                            text = rightButtonText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}