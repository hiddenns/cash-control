package com.cashcontrol.domain.datasource.category

import com.cashcontrol.core.database.AppDatabase
import com.cashcontrol.data.room.entity.Category
import com.cashcontrol.domain.base.BaseDataSource
import com.cashcontrol.domain.dao.CategoryDao

class CategoryDataSource(database: AppDatabase) : BaseDataSource(), ICategoryDatasource {

    init {
        database.openHelper.writableDatabase
    }

    private var categoryDao: CategoryDao = database.categoryDao()

    override fun addCategory(category: Category) = categoryDao.insert(category)

    override fun getAllCategories() = categoryDao.getAll()

    override fun getCategoryById(categoryId: Long) = categoryDao.getById(categoryId)

}