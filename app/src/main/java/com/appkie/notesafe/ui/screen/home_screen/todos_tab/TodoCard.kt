package com.appkie.notesafe.ui.screen.home_screen.todos_tab

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.appkie.notesafe.data.model.Todo
import com.appkie.notesafe.ui.theme.Black
import com.appkie.notesafe.ui.theme.Shapes
import kotlin.math.roundToInt

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun TodoCard(
    todo: Todo,
    modifier: Modifier,
    swipeToRevealAnimationDurationMs: Int,
    cardOffset: Dp,
    minDrag: Int = 5,
    onTodoClicked: (Int) -> Unit,
    onShareClicked: () -> Unit,
    onDeleteClicked: (Todo) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    var isRevealed by remember { mutableStateOf(false) }

    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(transitionState, "cardTransition")

    val offsetTransition by transition.animateFloat(
        label = "offsetTransition",
        transitionSpec = { tween(durationMillis = swipeToRevealAnimationDurationMs) },
        targetValueByState = { if (isRevealed) cardOffset.value else 0f },
    )

    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = modifier
            .padding(bottom = 8.dp)
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.04f))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { onDeleteClicked(todo) }
            ) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete Note")
            }

            IconButton(
                onClick = onShareClicked
            ) {
                Icon(imageVector = Icons.Outlined.Share, contentDescription = "Share Note")
            }
        }

        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .offset { -IntOffset(offsetTransition.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        when {
                            dragAmount <= minDrag -> isRevealed = true
                            dragAmount > -minDrag -> isRevealed = false
                        }
                    }
                }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
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
    }
}