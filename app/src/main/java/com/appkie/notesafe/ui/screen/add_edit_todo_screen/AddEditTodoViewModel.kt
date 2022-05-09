package com.appkie.notesafe.ui.screen.add_edit_todo_screen

import androidx.lifecycle.ViewModel
import com.appkie.notesafe.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

}