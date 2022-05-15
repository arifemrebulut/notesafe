package com.appkie.notesafe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.appkie.notesafe.R
import com.appkie.notesafe.ui.theme.Black
import com.appkie.notesafe.ui.theme.Blue
import com.appkie.notesafe.ui.theme.White

@Composable
fun AddCategoryDialog(
    onDismiss: () -> Unit,
    onAddClicked: (String) -> Unit
) {

    var categoryText by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .height(170.dp)
                .width(280.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.dialog_title),
                    style = MaterialTheme.typography.h6,
                    color = Black.copy(alpha = ContentAlpha.medium)
                )

                Spacer(modifier = Modifier.height(4.dp))

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                        .background(
                            Color.LightGray.copy(alpha = 0.3f),
                            RoundedCornerShape(8.dp)
                        ),
                    value = categoryText,
                    onValueChange = {
                        categoryText = it
                    },
                    singleLine = true,
                    cursorBrush = SolidColor(MaterialTheme.colors.primary),
                    textStyle = LocalTextStyle.current.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 14.sp
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    decorationBox = { innerTextField ->

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {

                            // Placeholder Text
                            if (categoryText.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.dialog_placeholder),
                                    style = MaterialTheme.typography.subtitle1,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                                )
                            }

                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        onClick = onDismiss,
                        elevation = ButtonDefaults.elevation(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .width(100.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel_text),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black.copy(alpha = ContentAlpha.medium)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { onAddClicked(categoryText) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Blue,
                            contentColor = White
                        ),
                        elevation = ButtonDefaults.elevation(0.dp),
                        modifier = Modifier
                            .width(100.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.dialog_add_button_text),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
