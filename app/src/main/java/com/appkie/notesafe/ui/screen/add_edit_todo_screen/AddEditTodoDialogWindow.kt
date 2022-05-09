package com.appkie.notesafe.ui.screen.add_edit_todo_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.ui.theme.White

@Composable
fun AddEditTodoDialogWindow(
    navController: NavController,
    addEditTodoViewModel: AddEditTodoViewModel = hiltViewModel()
) {
    @Composable
    fun DeleteDialog(
        onDismiss: () -> Unit,
        onCancelClicked: () -> Unit,
        onDeleteClicked: () -> Unit
    ) {
        Dialog(
            onDismissRequest = onDismiss,
        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .width(280.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(White)

            ) {

            }
        }
    }
}