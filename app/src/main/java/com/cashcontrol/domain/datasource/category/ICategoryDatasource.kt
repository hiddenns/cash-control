package com.cashcontrol.domain.datasource.category

import com.cashcontrol.data.room.entity.Category
import kotlinx.coroutines.flow.Flow

interface ICategoryDatasource {

    fun addCategory(category: Category)

    fun getAllCategories(): Flow<List<Category>>

}