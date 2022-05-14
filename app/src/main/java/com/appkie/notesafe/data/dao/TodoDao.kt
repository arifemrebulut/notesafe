package com.appkie.notesafe.data.dao

import androidx.room.*
import com.appkie.notesafe.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchTodo(searchQuery: String): Flow<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE id = :todoId")
    suspend fun getTodoById(todoId: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodo(todo: Todo)

    @Query("DELETE FROM todo_table WHERE id = :todoId")
    suspend fun deleteTodo(todoId: Int)
}