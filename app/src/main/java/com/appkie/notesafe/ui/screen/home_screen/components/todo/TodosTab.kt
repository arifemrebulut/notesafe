package com.appkie.notesafe.ui.screen.home_screen.components.todo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.data.model.Todo

@Composable
fun TodosTab(
    todoList: List<Todo>,
    onCheckedChange: (Boolean) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 1.dp, bottom = 52.dp)
    ) {
        items(todoList) { item ->
            TodoCard(
                todo = item,
                onCheckedChange = onCheckedChange
            )
        }
    }
}