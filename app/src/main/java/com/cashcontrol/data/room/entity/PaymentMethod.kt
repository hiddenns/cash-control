package com.cashcontrol.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity
data class PaymentMethod(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "balance") val balance: Long,
    @ColumnInfo(name = "transactions") val transactions: List<Transaction>,
    @ColumnInfo(name = "user_id") val userId: String,

)

@TypeConverter
fun fromList(list: List<Transaction>): String {
    val gson = Gson()
    return gson.toJson(list)
}