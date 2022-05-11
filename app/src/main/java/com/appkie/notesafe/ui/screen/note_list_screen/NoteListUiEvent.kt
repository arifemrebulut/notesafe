package com.appkie.notesafe.ui.screen.note_list_screen

import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.util.Order

sealed class NoteListUiEvent() {
    data class ChangeCategory(val category: String) : NoteListUiEvent()
    data class Order(val order: Order) : NoteListUiEvent()
    data class DeleteNote(val note: Note) : NoteListUiEvent()
}
