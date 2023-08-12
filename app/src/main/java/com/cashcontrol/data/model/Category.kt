package com.cashcontrol.data.model

import com.cashcontrol.data.room.entity.Category

data class Category(
    var categoryId: Long = -1,
    var name: String = "",
    var sum: Long = 0,
    var imageSource: String = "",
    var type: ActionType = ActionType.EXPENSE,
    var color: Int = -1
)

fun com.cashcontrol.data.model.Category.toEntity() =
    Category().apply {
        this.name = this@toEntity.name
        this.sum = this@toEntity.sum
        this.type = this@toEntity.type
        this.imageSource = this@toEntity.imageSource
        this.color = this@toEntity.color
        this.categoryId = this@toEntity.categoryId
    }