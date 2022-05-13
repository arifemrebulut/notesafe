package com.appkie.notesafe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.appkie.notesafe.ui.navigation.Navigation
import com.appkie.notesafe.ui.theme.NotesafeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesafeTheme {
                val navController = rememberNavController()

                Navigation(navController = navController)
            }
        }
    }
}