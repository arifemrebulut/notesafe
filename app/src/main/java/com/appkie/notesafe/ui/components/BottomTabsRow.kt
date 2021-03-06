package com.appkie.notesafe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.ui.theme.Blue
import com.appkie.notesafe.ui.theme.WhiteVariant
import com.appkie.notesafe.R

@Composable
fun BottomTabsRow(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf(
        BottomTabItem(
            label = stringResource(id = R.string.bottom_tabs_row_notes_label),
            icon = Icons.Outlined.Description
        ),
        BottomTabItem(
            label = stringResource(id = R.string.bottom_tabs_row_todos_label),
            icon = Icons.Outlined.Checklist
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhiteVariant),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        tabs.forEachIndexed { index, tabItem ->
            val selected = selectedTabIndex == index

            Column(
                modifier = Modifier
                    .selectable(
                        selected = selected,
                        onClick = { onTabSelected(index) }
                    )
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = tabItem.icon,
                    contentDescription = tabItem.label,
                    tint = if (selected) Blue else Color.Black.copy(alpha = ContentAlpha.disabled)
                )

                Text(
                    text = tabItem.label,
                    style = MaterialTheme.typography.body1,
                    color = if (selected) Blue else Color.Black.copy(alpha = ContentAlpha.disabled)
                )
            }
        }
    }
}

data class BottomTabItem(
    val label: String,
    val icon: ImageVector
)