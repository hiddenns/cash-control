package com.cashcontrol.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Currency(
    @PrimaryKey(autoGenerate = true) val currencyId: Long,
    @ColumnInfo(name = "code") val code: Long,
    @ColumnInfo(name = "abbreviation") val abbreviation: String,
    @ColumnInfo(name = "name") val name: String,
)