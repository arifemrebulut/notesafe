package com.appkie.notesafe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.ui.theme.PastelBlue
import com.appkie.notesafe.ui.theme.PastelGreen
import com.appkie.notesafe.ui.theme.PastelPurple
import com.appkie.notesafe.ui.theme.PastelYellow

@Composable
fun AddEditSettingsSection() {

    var expended by remember { mutableStateOf(false) }
    val icon = if (expended) Icons.Outlined.ArrowDropUp else Icons.Outlined.ArrowDropDown

    Column {

        Divider()

        Row(
            modifier =  Modifier
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
                        text = "Category",
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
                    DropdownMenuItem(onClick = { expended = false }) {
                        Text(text = "Item 1")
                    }
                    DropdownMenuItem(onClick = { expended = false }) {
                        Text(text = "Item 2")
                    }
                    DropdownMenuItem(onClick = { expended = false }) {
                        Text(text = "Item 3")
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clip(CircleShape)
                        .background(PastelYellow)
                        .size(40.dp)
                        .clickable {}
                )
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clip(CircleShape)
                        .background(PastelBlue)
                        .size(40.dp)
                        .clickable {}
                )
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clip(CircleShape)
                        .background(PastelGreen)
                        .size(40.dp)
                        .clickable {}
                )
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clip(CircleShape)
                        .background(PastelPurple)
                        .size(40.dp)
                        .clickable {}
                )
            }
        }

        Divider()
    }
}