package com.cashcontrol.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cashcontrol.data.model.ActionType

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) var categoryId: Long = -1,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "sum") var sum: Long = 0,
    @ColumnInfo(name = "imageSource") var imageSource: String = "",
    @ColumnInfo(name = "type") var type: ActionType = ActionType.EXPENSE,
    @ColumnInfo(name = "color") var color: Int = -1
)

fun Category.toModel() =
    com.cashcontrol.data.model.Category().apply {
        this.sum = this@toModel.sum
        this.type = this@toModel.type
        this.color = this@toModel.color
        this.name = this@toModel.name
        this.imageSource = this@toModel.imageSource
        this.categoryId = this@toModel.categoryId
    }