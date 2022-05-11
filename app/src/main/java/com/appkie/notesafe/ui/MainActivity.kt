package com.appkie.notesafe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.appkie.notesafe.ui.components.BottomTabsRow
import com.appkie.notesafe.ui.navigation.Navigation
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.ui.theme.NotesafeTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesafeTheme {
                val navController = rememberNavController()

                var selectedTabIndex by remember { mutableStateOf(0) }

                Scaffold(
                    bottomBar = {
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
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}