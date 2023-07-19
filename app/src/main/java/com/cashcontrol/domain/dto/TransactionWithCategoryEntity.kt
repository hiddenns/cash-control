package com.cashcontrol.domain.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.cashcontrol.data.room.entity.Category
import com.cashcontrol.data.room.entity.Transaction

data class TransactionWithCategoryEntity(
    @Embedded val transaction: Transaction,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val category: List<Category>
)