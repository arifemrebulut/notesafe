package com.appkie.notesafe.ui.screen.home_screen.notes_tab

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appkie.notesafe.R
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.ui.components.SortingSettingsBar
import com.appkie.notesafe.ui.navigation.Screen
import com.appkie.notesafe.util.Utils.formatTextForShare

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    navController: NavController,
    noteListViewModel: NoteListViewModel = hiltViewModel(),
) {

    val notes by noteListViewModel.allNotes
    val categoryList by noteListViewModel.categoryList
    val searchedNotes by noteListViewModel.searchedNotes
    val searching = noteListViewModel.searchTextState.value.isNotBlank()

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SortingSettingsBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            categoryList = categoryList,
            onCategoryChange = {
                noteListViewModel.onEvent(NoteListUiEvent.ChangeCategory(it))
            },
            onSortingFilterChange = { orderType ->
                noteListViewModel.onEvent(NoteListUiEvent.OrderNotes(orderType))
            }
        )

        if (notes.isNotEmpty()) {

            val context = LocalContext.current

            LazyColumn(
                contentPadding = PaddingValues(top = 1.dp, bottom = 52.dp)
            ) {

                val noteList: List<Note> = if (searching) searchedNotes else notes

                items(items = noteList, key = { it.id.toString() }) { item ->
                    NoteCard(
                        note = item,
                        modifier = Modifier.animateItemPlacement(),
                        onNoteClicked = { navController.navigate(Screen.AddEditNoteScreen.route + "/${item.id}") },
                        swipeToRevealAnimationDurationMs = 300,
                        minDrag = 5,
                        cardOffset = 300.dp,
                        onDeleteClicked = {
                            noteListViewModel.onEvent(NoteListUiEvent.DeleteNote(it.id!!))
                        },
                        onShareClicked = {
                            val textToShare = formatTextForShare(item)

                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, textToShare)
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            ContextCompat.startActivity(context, shareIntent, null)
                        }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .offset(y = (-52).dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_no_notes),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(id = R.string.create_first_note_text),
                        style = MaterialTheme.typography.h5,
                        color = Color.Black.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}