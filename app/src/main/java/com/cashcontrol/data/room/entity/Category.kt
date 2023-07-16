package com.cashcontrol.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cashcontrol.data.model.ActionType

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val categoryId: Long = -1,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "sum") val sum: Long = 0,
    @ColumnInfo(name = "imageSource") val imageSource: String = "",
    @ColumnInfo(name = "type") val type: ActionType = ActionType.EXPENSE,
    @ColumnInfo(name = "color") val color: Int = -1
)