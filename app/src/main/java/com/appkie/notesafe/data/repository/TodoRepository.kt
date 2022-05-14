package com.appkie.notesafe.data.repository

import com.appkie.notesafe.data.dao.TodoDao
import com.appkie.notesafe.data.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {

    fun getAllTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos()
    }

    fun searchTodo(searchQuery: String) : Flow<List<Todo>> {
        return todoDao.searchTodo(searchQuery = searchQuery)
    }

    suspend fun getTodoById(todoId: Int): Todo? {
        return todoDao.getTodoById(todoId = todoId)
    }

    suspend fun saveTodo(todo: Todo) {
        todoDao.saveTodo(todo = todo)
    }

    suspend fun deleteTodo(todoId: Int) {
        todoDao.deleteTodo(todoId)
    }
}