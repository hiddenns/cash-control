package com.cashcontrol.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cashcontrol.data.model.ActionType

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var transactionId: Int? = null,
    @ColumnInfo var name: String = "",
    @ColumnInfo var sum: Int = 0,
    @ColumnInfo var date: Long = 0,
    @ColumnInfo var description: String = "",
    @ColumnInfo var categoryId: Long = -1,
    @ColumnInfo var walletId: Long = -1,
    @ColumnInfo var type: ActionType = ActionType.EXPENSE
)