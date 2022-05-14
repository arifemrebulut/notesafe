package com.appkie.notesafe.ui.screen.home_screen.notes_tab

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appkie.notesafe.data.model.Category
import com.appkie.notesafe.data.model.Note
import com.appkie.notesafe.data.repository.CategoryRepository
import com.appkie.notesafe.data.repository.NoteRepository
import com.appkie.notesafe.util.OrderType
import com.appkie.notesafe.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    private val TAG = "NoteListViewModel"

    private val _allNotes = mutableStateOf<List<Note>>(emptyList())
    val allNotes: State<List<Note>> = _allNotes

    private val _searchedNotes = mutableStateOf<List<Note>>(emptyList())
    val searchedNotes: State<List<Note>> = _searchedNotes

    private val _searchTextState = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    private val _categoryList = mutableStateOf<List<Category>>(emptyList())
    val categoryList: State<List<Category>> = _categoryList

    private var currentCategory: String = "All"
    private var currentOrderType: OrderType = OrderType.NEWEST


    init {
        getAllNotes()
        getCategoryList()
    }

    fun onEvent(event: NoteListUiEvent) {
        when (event) {
            is NoteListUiEvent.ChangeCategory -> {
                currentCategory = event.category
                getAllNotes()
            }
            is NoteListUiEvent.OrderNotes -> {
                currentOrderType = event.orderType
                getAllNotes()
            }
            is NoteListUiEvent.SearchNote -> {
                _searchTextState.value = event.searchQuery
                searchNote(event.searchQuery)
            }
            is NoteListUiEvent.DeleteNote -> {
                deleteNote(event.noteId)
            }
        }
    }

    private fun getAllNotes() {
        try {
            viewModelScope.launch {
                noteRepository.getAllNotes().collect { noteList ->

                    val notesFilteredByCategory = filterByCategory(noteList, currentCategory)

                    _allNotes.value = sortByDateViewModel(notesFilteredByCategory, currentOrderType)
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "getAllNotes: $e")
        }
    }

    private fun getCategoryList() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collect {
                _categoryList.value = it
            }
        }
    }

    private fun searchNote(searchQuery: String) {
        try {
            viewModelScope.launch {
                noteRepository.searchNote(searchQuery = "%$searchQuery%").collect {
                    _searchedNotes.value = it
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "searchNote: $e")
        }
    }

    private fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
        }
    }

    private fun filterByCategory(notes: List<Note>, category: String) : List<Note> {

        return if (category == "All") {
            notes
        } else {
            notes.filter {
                it.category == category
            }
        }
    }

    private fun sortByDateViewModel(notes: List<Note>, orderType: OrderType) : List<Note> {

        val simpleDateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.US)

        return if (orderType == OrderType.NEWEST) {
            notes.sortedByDescending {
                simpleDateFormat.parse(it.creationTime)
            }

        } else {
            notes.sortedBy {
                simpleDateFormat.parse(it.creationTime)
            }
        }
    }
}