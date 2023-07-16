package com.cashcontrol.domain.repositories.categories

import com.cashcontrol.data.room.entity.Category
import com.cashcontrol.domain.datasource.category.CategoryDataSource

class CategoriesRepositoryImpl (private val categoryDataSource: CategoryDataSource) : ICategoriesRepository {
    override fun getAllCategories() = categoryDataSource.getAllCategories()

    override fun getCategoryById(id: Long): Category {
        TODO("Not yet implemented")
    }
    override fun addCategory(category: Category) = categoryDataSource.addCategory(category)

    override fun removeCategory(category: Category): Category {
        TODO("Not yet implemented")
    }

    override fun updateCategory(category: Category): Category {
        TODO("Not yet implemented")
    }
}
