package com.cashcontrol.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wallet(
    @PrimaryKey (autoGenerate = true) val walletId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "balance") val balance: Long,
    @ColumnInfo(name = "currencyId") val currencyId: Long,
    @ColumnInfo(name = "date") val date: Long
)