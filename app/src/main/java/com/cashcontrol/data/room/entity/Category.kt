package com.cashcontrol.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "sum") val sum: String,
    @ColumnInfo(name = "image") val image: String,
)