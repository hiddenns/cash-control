package com.cashcontrol.domain.repositories.categories

import com.cashcontrol.data.model.Category
import kotlinx.coroutines.flow.Flow

interface ICategoriesRepository {
    fun getAllCategories(): Flow<List<Category>>
    fun getCategoryById(id: Long): Category
    fun addCategory(category: Category)
    fun removeCategory(category: Category): Category
    fun updateCategory(category: Category): Category

}