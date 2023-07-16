package com.cashcontrol.domain.usecases

import com.cashcontrol.data.room.entity.Category
import com.cashcontrol.domain.repositories.categories.ICategoriesRepository

class GetCategoriesInteract(
    private val categoriesRepository: ICategoriesRepository
) {

    fun getAllCategories() = categoriesRepository.getAllCategories()

    fun addCategory(category: Category) = categoriesRepository.addCategory(category)

}