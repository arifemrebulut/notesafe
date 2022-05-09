package com.appkie.notesafe.ui.screen.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Description
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.screen.home_screen.components.HomeTopBar
import com.appkie.notesafe.ui.screen.home_screen.components.NoteCard
import com.appkie.notesafe.ui.screen.home_screen.components.SortingSettingsBar
import com.appkie.notesafe.ui.theme.Blue

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val notes by homeViewModel.allNotes

    Scaffold(
        topBar = {
            HomeTopBar(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        },
        bottomBar = { BottomTabsRow() },
        content = {
            ContentSection(notes = notes, navController = navController)
        }
    )
}

@Composable
fun ContentSection(
    notes: List<Note>,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SortingSettingsBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        NoteList(
            noteList = notes,
            onNoteClicked = { id ->
                navController.navigate(Screen.AddEditNoteScreen.route + "/$id")
            }
        )
    }
}

@Composable
fun NoteList(
    noteList: List<Note>,
    onNoteClicked: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 1.dp, bottom = 52.dp)
    ) {
        items(noteList) { item ->
            NoteCard(
                note = item,
                onNoteClicked = onNoteClicked
            )
        }
    }
}

@Composable
fun BottomTabsRow() {
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
    var selectedTabIndex by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.03f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        tabs.forEachIndexed { index, tabItem ->
            val selected = selectedTabIndex == index

            Column(
                modifier = Modifier
                    .selectable(
                        selected = selected,
                        onClick = { selectedTabIndex = index }
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

@Preview(showBackground = true)
@Composable
fun BottomTabsRowPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomTabsRow()
    }
}

data class BottomTabItem(
    val label: String,
    val icon: ImageVector
)