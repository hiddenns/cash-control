package com.cashcontrol.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cashcontrol.data.room.entity.Category
import com.cashcontrol.data.room.entity.Currency
import com.cashcontrol.data.room.entity.Transaction
import com.cashcontrol.data.room.entity.Wallet
import com.cashcontrol.domain.dao.CategoryDao
import com.cashcontrol.domain.dao.TransactionDao
import com.cashcontrol.domain.dao.WalletDao

@Database(entities = [Wallet::class, Transaction::class, Category::class, Currency::class], version = 1)
//@TypeConverters(DefaultConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun walletDao(): WalletDao

    companion object {
        val CALLBACK = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'Food', '', 'EXPANSES', 0)")
                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'Sport', '', 'EXPANSES', 0)")
                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'House', '', 'EXPANSES', 0)")
                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'Entertainment', '', 'EXPANSES', 0)")
                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'Transport', '', 'EXPANSES', 0)")
            }
        }
    }

}