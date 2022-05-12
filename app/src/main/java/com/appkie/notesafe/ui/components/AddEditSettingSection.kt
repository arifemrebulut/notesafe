package com.appkie.notesafe.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.appkie.notesafe.ui.theme.Black
import com.appkie.notesafe.util.Utils

@Composable
fun AddEditSettingsSection(
    currentCategory: String,
    onCategorySelected: (String) -> Unit,
    currentColor: Int,
    onColorChange: (Int) -> Unit
) {

    var expended by remember { mutableStateOf(false) }
    val icon = if (expended) Icons.Outlined.ArrowDropUp else Icons.Outlined.ArrowDropDown

    Column {

        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { expended = true }
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = currentCategory,
                        style = MaterialTheme.typography.h6,
                    )
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                DropdownMenu(
                    expanded = expended,
                    onDismissRequest = { expended = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            expended = false
                            onCategorySelected("All")
                        }
                    ) {
                        Text(text = "All")
                    }

                    DropdownMenuItem(
                        onClick = {
                            expended = false
                            onCategorySelected("Work")
                        }
                    ) {
                        Text(text = "Work")
                    }

                    DropdownMenuItem(
                        onClick = {
                            expended = false
                            onCategorySelected("School")
                        }
                    ) {

                        Text(text = "School")
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

                Utils.noteColors.forEach { color ->

                    val selected = currentColor == color

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(
                                if (selected) 42.dp else 40.dp
                            )
                            .clip(CircleShape)
                            .background(Color(color))
                            .border(
                                width = 2.dp,
                                color = if (selected) {
                                    Black.copy(alpha = 0.1f)
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                onColorChange(color)
                            }
                    )
                }
            }

            Divider()
        }
    }
}