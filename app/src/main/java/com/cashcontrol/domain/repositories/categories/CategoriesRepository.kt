package com.cashcontrol.domain.repositories.categories

import com.cashcontrol.data.model.Category

interface CategoriesRepository {
    fun getAllCategories(): List<Category>
    fun getCategoryById(id: Long): Category

    fun addCategory(category: Category) : Category
    fun removeCategory(category: Category) : Category
    fun updateCategory(category: Category) : Category

}