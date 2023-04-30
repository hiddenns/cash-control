package com.cashcontrol.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey val id: String = "-1",
    @ColumnInfo val name: String = "default",
    @ColumnInfo val sum: Int = 0,
    @ColumnInfo val date: Int = 0,
    @ColumnInfo val description: String = "",
    @ColumnInfo val categoryId: String = "-1",
    @ColumnInfo val paymentMethodId: String = "-1"
)