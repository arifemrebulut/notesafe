package com.appkie.notesafe.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.appkie.notesafe.data.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * from category_table ORDER BY id ASC")
    fun getAllCategories() : Flow<List<Category>>

    @Insert
    suspend fun addCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * from category_table WHERE name = :categoryName")
    suspend fun getCategoryByName(categoryName: String) : Category?
}