package com.appkie.notesafe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appkie.notesafe.R

@Composable
fun SearchTopBar(
    onSearchTextChange: (String) -> Unit,
) {

    var text by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            modifier = Modifier
                .height(38.dp)
                .background(
                    Color.LightGray.copy(alpha = 0.3f),
                    RoundedCornerShape(percent = 50)
                ),
            value = text,
            onValueChange = {
                text = it
                onSearchTextChange(text)
            },
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            textStyle = LocalTextStyle.current.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 14.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Leading Icon
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        tint = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    )
                    Box(Modifier.weight(1f)) {

                        // Placeholder Text
                        if (text.isEmpty()) Text(
                            text = stringResource(id = R.string.search_placeholder_text),
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                                fontSize = 14.sp
                            )
                        )
                        innerTextField()
                    }

                    // Trailing Icon
                    if (text.isNotBlank()) {
                        IconButton(
                            onClick = {
                                text = ""
                                onSearchTextChange(text)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = stringResource(id = R.string.search_close_icon_description),
                                tint = Color.Black.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}