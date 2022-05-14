package com.appkie.notesafe.data.repository

import com.appkie.notesafe.data.dao.CategoryDao
import com.appkie.notesafe.data.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {

    fun getAllCategories() : Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }

    suspend fun addCategory(category: Category) {
        categoryDao.addCategory(category)
    }
}