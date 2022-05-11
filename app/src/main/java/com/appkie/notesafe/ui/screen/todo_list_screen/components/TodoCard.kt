package com.appkie.notesafe.ui.screen.todo_list_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.ui.theme.Black

@Composable
fun TodoCard(
    todo: Todo,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = todo.checked,
            onCheckedChange = {
                onCheckedChange(it)
            }
        )

        Text(
            text = todo.title,
            textDecoration = if (todo.checked) TextDecoration.LineThrough
                             else TextDecoration.None,
            color = if (todo.checked) Black.copy(alpha = ContentAlpha.disabled)
                    else Black.copy(alpha = ContentAlpha.high)
        )
    }
}