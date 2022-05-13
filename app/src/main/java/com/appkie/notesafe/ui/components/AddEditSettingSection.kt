package com.appkie.notesafe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.data.model.Category
import com.appkie.notesafe.ui.theme.*
import com.appkie.notesafe.util.Utils

@Composable
fun AddEditSettingsSection(
    categoryList: List<Category>,
    currentCategory: String,
    onCategorySelected: (String) -> Unit,
    currentColor: Int,
    onColorChange: (Int) -> Unit,
    onAddCategoryClicked: () -> Unit
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

                    categoryList.forEach { category ->
                        DropdownMenuItem(
                            onClick = {
                                expended = false
                                onCategorySelected(category.name)
                            }
                        ) {
                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.subtitle1
                            )
                        }
                    }
                    DropdownMenuItem(
                        onClick =  onAddCategoryClicked,
                    ) {
                        Row(
                            modifier = Modifier
                                .width(88.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray.copy(alpha = 0.25f))
                                .padding(all = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Add New Category",
                                tint = White,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Blue)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "New",
                                fontWeight = FontWeight.Medium,
                                fontSize = MaterialTheme.typography.subtitle1.fontSize
                            )
                        }
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
        }
    }
}