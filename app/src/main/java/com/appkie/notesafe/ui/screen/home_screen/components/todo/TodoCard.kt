package com.appkie.notesafe.ui.screen.home_screen.components.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.ui.theme.Black
import com.appkie.notesafe.ui.theme.NotesafeTheme

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
            textDecoration = TextDecoration.LineThrough,
            color = if (todo.checked) Black.copy(alpha = ContentAlpha.disabled)
                    else Black.copy(alpha = ContentAlpha.high)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoCardPreview() {
    NotesafeTheme {
        TodoCard(
            todo = Todo(
                title = "Buy some food from grocery store",
                checked = true
            ),
            onCheckedChange = {}
        )
    }
}