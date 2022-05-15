package com.appkie.notesafe.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.R
import com.appkie.notesafe.data.model.Category
import com.appkie.notesafe.ui.theme.Blue
import com.appkie.notesafe.util.OrderType

@Composable
fun SortingSettingsBar(
    modifier: Modifier = Modifier,
    categoryList: List<Category>,
    onCategoryChange: (String) -> Unit,
    onSortingFilterChange: (OrderType) -> Unit
) {

    val scrollState = rememberScrollState()

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
                .weight(8.6f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            categoryList.forEachIndexed { index, item ->
                val selected = selectedFolderIndex == index

                Text(
                    text = item.name,
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
                                onCategoryChange(item.name)
                            }
                        ),
                    fontWeight = FontWeight.Medium,
                    color = if (selected) Blue else Color.Black.copy(alpha = ContentAlpha.disabled)
                )
            }
        }

        Row(
            modifier = Modifier
                .weight(1.4f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Divider(
                modifier = Modifier
                    .height(36.dp)
                    .width(2.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FilterList,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                sortingDropdownExpended = true
                            }
                            .padding(6.dp)
                    )
                }

                DropdownMenu(
                    expanded = sortingDropdownExpended,
                    onDismissRequest = { sortingDropdownExpended = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            sortingDropdownExpended = false
                            onSortingFilterChange(OrderType.NEWEST)
                        }
                    ) {
                        Text(text = stringResource(id = R.string.sorting_newest_text))
                    }
                    DropdownMenuItem(
                        onClick = {
                            sortingDropdownExpended = false
                            onSortingFilterChange(OrderType.OLDEST)
                        }
                    ) {
                        Text(text = stringResource(id = R.string.sorting_oldest_text))
                    }
                }
            }
        }
    }
}