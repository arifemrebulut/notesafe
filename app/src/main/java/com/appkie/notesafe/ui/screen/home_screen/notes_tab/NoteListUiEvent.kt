package com.appkie.notesafe.ui.screen.home_screen.notes_tab

import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.util.OrderType

sealed class NoteListUiEvent {
    data class ChangeCategory(val category: String) : NoteListUiEvent()
    data class OrderNotes(val orderType: OrderType) : NoteListUiEvent()
    data class SearchNote(val searchQuery: String) : NoteListUiEvent()
    data class DeleteNote(val note: Note) : NoteListUiEvent()
}
