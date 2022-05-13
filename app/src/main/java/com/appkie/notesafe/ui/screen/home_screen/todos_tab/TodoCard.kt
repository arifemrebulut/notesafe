package com.appkie.notesafe.ui.screen.home_screen.todos_tab

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
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
    onTodoClicked: (Int) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .clickable {
                onTodoClicked(todo.id!!)
            },
        backgroundColor = Color(todo.color),
        shape = Shapes.medium
    ) {
        Column(
            Modifier.padding(
                start = 4.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Checkbox(
                    checked = todo.checked,
                    onCheckedChange = {
                        onCheckedChange(it)
                    },
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = todo.title,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    textDecoration = if (todo.checked) TextDecoration.LineThrough
                    else TextDecoration.None,
                    color = if (todo.checked) Black.copy(alpha = ContentAlpha.disabled)
                    else Black.copy(alpha = ContentAlpha.high),
                    maxLines = if (expandedState) 4 else 1,
                    modifier = Modifier.weight(8f)
                )

                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState)
                        .clickable {
                            expandedState = !expandedState
                        },
                    imageVector = Icons.Outlined.ExpandMore,
                    contentDescription = "Read More Arrow"
                )
            }

            if (expandedState) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "#${todo.category}",
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Normal
                    )

                    Text(
                        text = todo.creationTime,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
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
        onCheckedChange = {},
        onTodoClicked = {}
    )
}