package com.cashcontrol.domain.usecases

import com.cashcontrol.data.model.Category
import com.cashcontrol.domain.repositories.categories.CategoriesRepository

class GetCategoriesInteract(
    val categoriesRepository: CategoriesRepository
) {

    suspend fun getAllCategories() = categoriesRepository.getAllCategories()

}