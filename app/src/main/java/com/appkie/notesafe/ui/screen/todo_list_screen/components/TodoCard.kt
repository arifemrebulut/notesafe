package com.appkie.notesafe.ui.screen.todo_list_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.ui.theme.Black
import com.appkie.notesafe.ui.theme.PastelBlue
import com.appkie.notesafe.ui.theme.Shapes
import com.appkie.notesafe.util.Utils

@Composable
fun TodoCard(
    todo: Todo,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        backgroundColor = Color(todo.color),
        shape = Shapes.medium
    ) {
        Row(
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
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                textDecoration = if (todo.checked) TextDecoration.LineThrough
                else TextDecoration.None,
                color = if (todo.checked) Black.copy(alpha = ContentAlpha.disabled)
                else Black.copy(alpha = ContentAlpha.high)
            )
        }
    }
}

@Preview
@Composable
fun TodoCardPreview() {
    TodoCard(
        todo = Todo(
            id = 600,
            title = "Hello World",
            checked = false,
            category = "All",
            color = PastelBlue.toArgb(),
            creationTime = Utils.getFormattedTime()
        ),
        onCheckedChange = {}
    )
}