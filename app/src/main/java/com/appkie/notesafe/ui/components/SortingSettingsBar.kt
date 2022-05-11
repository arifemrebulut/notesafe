package com.appkie.notesafe.ui.components

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.ui.theme.Blue

@Composable
fun SortingSettingsBar(
    modifier: Modifier = Modifier,
    onCategoryChange: (String) -> Unit,
    onSortingFilterChange: (String) -> Unit
) {
    val TAG = "SortingSettingsBar"

    val scrollState = rememberScrollState()

    val folders = listOf("All", "School", "Work", "Home", "Shopping", "Coding", "Kotlin", "Android")

    var selectedFolderIndex by remember { mutableStateOf(0) }
    var sortingDropdownExpended by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(end = 8.dp)
                .weight(9f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            folders.forEachIndexed { index, item ->
                val selected = selectedFolderIndex == index

                Text(
                    text = item,
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 30))
                        .border(
                            BorderStroke(
                                1.dp,
                                if (selected) Blue else Color.Black.copy(alpha = ContentAlpha.disabled)
                            ),
                            RoundedCornerShape(percent = 30)
                        )
                        .padding(6.dp)
                        .selectable(
                            selected = selected,
                            onClick = {
                                selectedFolderIndex = index
                                onCategoryChange(item)
                                Log.d(TAG, "SortingSettingsBar: $item")
                            }
                        ),
                    fontWeight = FontWeight.Medium,
                    color = if (selected) Blue else Color.Black.copy(alpha = ContentAlpha.disabled)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))
        
        Row(
            modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Divider(
                modifier = Modifier
                    .height(32.dp)
                    .width(1.dp)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Outlined.FilterList,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            sortingDropdownExpended = true
                        }
                        .padding(start = 8.dp)
                )
                DropdownMenu(
                    expanded = sortingDropdownExpended,
                    onDismissRequest = { sortingDropdownExpended = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            sortingDropdownExpended = false
                            onSortingFilterChange("New")
                        }
                    ) {
                        Text(text = "New")
                    }
                    DropdownMenuItem(
                        onClick = {
                            sortingDropdownExpended = false
                            onSortingFilterChange("Old")
                        }
                    ) {
                        Text(text = "Old")
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SortingSettingsBarPreview() {
//    SortingSettingsBar()
//}