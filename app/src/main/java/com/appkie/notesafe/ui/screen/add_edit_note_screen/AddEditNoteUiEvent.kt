package com.appkie.notesafe.ui.screen.add_edit_note_screen


sealed class AddEditNoteUiEvent {
    object DeleteNote : AddEditNoteUiEvent()
    object SaveNote : AddEditNoteUiEvent()
    data class TitleChange(val title: String) : AddEditNoteUiEvent()
    data class DescriptionChange(val description: String) : AddEditNoteUiEvent()
    data class CategoryChange(val category: String) : AddEditNoteUiEvent()
    data class ColorChange(val color: Int) : AddEditNoteUiEvent()
}