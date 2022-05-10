package com.appkie.notesafe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.appkie.notesafe.ui.components.BottomTabsRow
import com.appkie.notesafe.ui.navigation.Navigation
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.components.HomeTopBar
import com.appkie.notesafe.ui.theme.NotesafeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesafeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val coroutineScope = rememberCoroutineScope()
                val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

                val showTopBottomBars = currentRoute != Screen.AddEditNoteScreen.route + "/{noteId}"

                var selectedTabIndex by remember { mutableStateOf(0) }

                ModalBottomSheetLayout(
                    sheetState = bottomState,
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            TextField(value = "Text", onValueChange = {})
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            if (showTopBottomBars) {
                                HomeTopBar(
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                )
                            }
                        },
                        bottomBar = {
                            if (showTopBottomBars) {
                                BottomTabsRow(
                                    selectedTabIndex = selectedTabIndex,
                                    onTabSelected = { index ->
                                        selectedTabIndex = index
                                        when (selectedTabIndex) {
                                            0 -> navController.navigate(Screen.NoteListScreen.route)
                                            1 -> navController.navigate(Screen.TodoListScreen.route)
                                        }
                                    }
                                )
                            }
                        },
                        floatingActionButton = {
                            if (showTopBottomBars) {
                                FloatingActionButton(
                                    onClick = {
                                        when (selectedTabIndex) {
                                            0 -> navController.navigate(Screen.AddEditNoteScreen.route + "/-1")
                                            1 -> {
                                                coroutineScope.launch {
                                                    bottomState.show()
                                                }
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Add,
                                        contentDescription = "New Note"
                                    )
                                }
                            }
                        }
                    ) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}