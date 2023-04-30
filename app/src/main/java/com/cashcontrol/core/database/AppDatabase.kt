package com.cashcontrol.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cashcontrol.data.room.converters.TransactionListConverter
import com.cashcontrol.data.room.entity.PaymentMethod
import com.cashcontrol.domain.repositories.dao.PaymentMethodsDao

@Database(entities = [PaymentMethod::class], version = 1)
@TypeConverters(TransactionListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paymentMethodDao(): PaymentMethodsDao
}