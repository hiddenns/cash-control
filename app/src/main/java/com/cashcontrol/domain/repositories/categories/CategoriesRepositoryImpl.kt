package com.cashcontrol.domain.repositories.categories

import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction

class CategoriesRepositoryImpl : CategoriesRepository {
    override fun getAllCategories(): List<Category> {
        return emptyList()
    }

    override fun getCategoryById(id: Long): Category {
        TODO("Not yet implemented")
    }

    override fun addCategory(category: Category): Category {
        //categoriesMock.add(category)
        return category
    }

    override fun removeCategory(category: Category): Category {
        TODO("Not yet implemented")
    }

    override fun updateCategory(category: Category): Category {
        TODO("Not yet implemented")
    }
}
