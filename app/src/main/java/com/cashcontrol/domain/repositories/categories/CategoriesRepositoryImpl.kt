package com.cashcontrol.domain.repositories.categories

import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.toEntity
import com.cashcontrol.data.room.entity.toModel
import com.cashcontrol.domain.datasource.category.CategoryDataSource
import kotlinx.coroutines.flow.map

class CategoriesRepositoryImpl(
    private val categoryDataSource: CategoryDataSource
) : ICategoriesRepository {
    override fun getAllCategories() = categoryDataSource
        .getAllCategories().map { list ->
            list.map { category ->
                category.toModel()
            }
        }

    override fun getCategoryById(id: Long) = categoryDataSource.getCategoryById(id).toModel()

    override fun addCategory(category: Category) =
        categoryDataSource.addCategory(category.toEntity())

    override fun removeCategory(category: Category): Category {
        TODO("Not yet implemented")
    }

    override fun updateCategory(category: Category): Category {
        TODO("Not yet implemented")
    }
}
