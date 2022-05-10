package com.appkie.notesafe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appkie.notesafe.ui.components.SearchBox

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        SearchBox(
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black.copy(alpha = 0.5f),
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                )
            },
            trailingIcon = null,
            modifier = Modifier
                .height(38.dp)
                .background(
                    Color.LightGray.copy(alpha = 0.3f),
                    RoundedCornerShape(percent = 50)
                ),
            fontSize = 14.sp,
            placeholderText = "Search your notes"
        )
    }
}