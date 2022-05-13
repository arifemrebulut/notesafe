package com.appkie.notesafe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.ui.theme.Blue
import com.appkie.notesafe.ui.theme.White
import com.appkie.notesafe.ui.theme.WhiteVariant

@Composable
fun BottomTabsRow(
    selectedTabIndex: Int,
    onNotesClicked: () -> Unit,
    onTodosClicked: () -> Unit
) {
    val tabs = listOf(
        BottomTabItem(
            label = "Notes",
            icon = Icons.Outlined.Description
        ),
        BottomTabItem(
            label = "Todos",
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
                    .clickable {
                        if (index == 0) {
                            onNotesClicked()
                        } else {
                            onTodosClicked()
                        }
                    }
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