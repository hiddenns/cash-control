package com.cashcontrol.data.room.converters

import androidx.room.TypeConverter
import com.cashcontrol.data.room.entity.Transaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TransactionListConverter {
    @TypeConverter
    fun fromString(value: String): List<Transaction> {
        val listType = object : TypeToken<List<Transaction>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Transaction>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}