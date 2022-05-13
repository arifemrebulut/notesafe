package com.appkie.notesafe.ui.screen.add_edit_note_screen

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appkie.notesafe.data.model.Category
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.repository.CategoryRepository
import com.appkie.notesafe.data.repository.NoteRepository
import com.appkie.notesafe.ui.theme.PastelBlue
import com.appkie.notesafe.util.Utils.getFormattedTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val categoryRepository: CategoryRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _id = mutableStateOf(-1)
    val id: State<Int> = _id

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _description = mutableStateOf("")
    val description: State<String> = _description

    private val _category = mutableStateOf("All")
    val category: State<String> = _category

    private val _categoryList = mutableStateOf<List<Category>>(emptyList())
    val categoryList: State<List<Category>> = _categoryList

    private val _color = mutableStateOf(PastelBlue.toArgb())
    val color: State<Int> = _color

    init {
        getNoteIdFromStateHandle()
        getNoteById()
        getCategoryList()
    }

    fun onEvent(event: AddEditNoteUiEvent) {
        when (event) {
            is AddEditNoteUiEvent.SaveNote -> {
                saveNote()
            }
            is AddEditNoteUiEvent.DeleteNote -> {
                deleteNote()
            }
            is AddEditNoteUiEvent.TitleChange -> {
                _title.value = event.title
            }
            is AddEditNoteUiEvent.DescriptionChange -> {
                _description.value = event.description
            }
            is AddEditNoteUiEvent.CategoryChange -> {
                _category.value = event.category
            }
            is AddEditNoteUiEvent.ColorChange -> {
                _color.value = event.color
            }
            is AddEditNoteUiEvent.AddNewCategory -> {
                addNewCategory(event.categoryName)
            }
        }
    }

    private fun getNoteIdFromStateHandle() {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            _id.value = noteId
        }
    }

    private fun getNoteById() {
        if (_id.value != -1) {
            viewModelScope.launch {
                val note : Note? = noteRepository.getNoteById(_id.value)

                note?.let { noteById ->
                    _title.value = noteById.title
                    _description.value = noteById.description
                    _category.value = noteById.category
                    _color.value = noteById.color
                }
            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            val note = Note(
                id = if(_id.value != -1) _id.value else null,
                title = _title.value.trim(),
                description = _description.value.trim(),
                category = _category.value,
                creationTime = getFormattedTime(),
                color = _color.value
            )
            noteRepository.saveNote(note = note)
        }
    }

    private fun deleteNote() {
        viewModelScope.launch {
            val note = Note(
                id = _id.value,
                title = _title.value,
                description = _description.value,
                category = _category.value,
                creationTime = getFormattedTime(),
                color = _color.value
            )
            noteRepository.deleteNote(note = note)
        }
    }

    private fun getCategoryList() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collect {
                _categoryList.value = it
            }
        }
    }

    private fun addNewCategory(categoryName: String) {
        viewModelScope.launch {
            val category = Category(
                name = categoryName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            )
            categoryRepository.addCategory(category)
        }
    }
}
