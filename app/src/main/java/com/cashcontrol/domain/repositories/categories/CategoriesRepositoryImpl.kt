package com.cashcontrol.domain.repositories.categories

import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction

class CategoriesRepositoryImpl : CategoriesRepository {
    override fun getAllCategories(): List<Category> {
        return categoriesMock
    }

    override fun getCategoryById(id: Long): Category {
        TODO("Not yet implemented")
    }

    override fun addCategory(category: Category): Category {
        TODO("Not yet implemented")
    }

    override fun removeCategory(category: Category): Category {
        TODO("Not yet implemented")
    }

    override fun updateCategory(category: Category): Category {
        TODO("Not yet implemented")
    }

    companion object {
        private val mock: List<String> = listOf(
            "first",
            "second",
            "third",
            "four",
            "five",
            "six",
            "seven",
            "nine",
            "ten",
            "first",
            "second",
            "third",
            "four",
            "five",
            "six",
            "seven",
            "nine",
            "ten",
            "first",
            "second",
            "third",
            "four",
            "five",
            "six",
            "seven",
            "nine",
            "ten",
            "first",
            "second",
            "third",
            "four",
            "five",
            "six",
            "seven",
            "nine",
            "ten"
        ).map { item ->
            "$item transaction"
        }

        private val categoriesMock = listOf<Category>(
            Category.Food().apply {
                this.id = -1
                this.name = "food"
                this.transactions = listOf(
                    Transaction(213, "food1", 213, 3123, "d", Category.Food()),
                    Transaction(214, "food2", 213, 3123, "d", Category.Food()),
                    Transaction(215, "food3", 213, 3123, "d", Category.Food()),
                    Transaction(216, "food4", 213, 3123, "d", Category.Food()),
                )
            },
            Category.Transport().apply {
                this.id = -1
                this.name = "Transport"
                this.transactions = listOf(
                    Transaction(213, "Transport1", 213, 3123, "d", Category.Food()),
                    Transaction(214, "Transport2", 213, 3123, "d", Category.Food()),
                    Transaction(215, "Transport3", 213, 3123, "d", Category.Food()),
                    Transaction(216, "Transport4", 213, 3123, "d", Category.Food()),
                )
            },
            Category.Health().apply {
                this.id = -1
                this.name = "Health"
                this.transactions = listOf(
                    Transaction(213, "Health1", 213, 3123, "d", Category.Food()),
                    Transaction(214, "Health2", 213, 3123, "d", Category.Food()),
                    Transaction(215, "Health3", 213, 3123, "d", Category.Food()),
                    Transaction(216, "Health4", 213, 3123, "d", Category.Food()),
                )
            },
            Category.Gifts().apply {
                this.id = -1
                this.name = "food"
                this.transactions = listOf(
                    Transaction(213, "Gifts1", 213, 3123, "d", Category.Food()),
                    Transaction(214, "Gifts2", 213, 3123, "d", Category.Food()),
                    Transaction(215, "Gifts3", 213, 3123, "d", Category.Food()),
                    Transaction(216, "Gifts4", 213, 3123, "d", Category.Food()),
                )
            },
        )
    }

}