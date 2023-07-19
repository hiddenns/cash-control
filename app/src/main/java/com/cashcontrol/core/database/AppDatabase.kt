package com.cashcontrol.core.database

import android.content.Context
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

@Database(
    entities = [Wallet::class, Transaction::class, Category::class, Currency::class],
    version = 1
)
//@TypeConverters(DefaultConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun walletDao(): WalletDao

    companion object {

        fun getCallBack(context: Context) = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                DefaultDatabaseConfig.getCategoriesDefault(context).forEach { category ->
                    db.execSQL(insertDefaultCategory(category))
                }
//
//                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'Food', '', 'EXPANSES', 0)")
//                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'Sport', '', 'EXPANSES', 0)")
//                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'House', '', 'EXPANSES', 0)")
//                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'Entertainment', '', 'EXPANSES', 0)")
//                db.execSQL("INSERT INTO CATEGORY (sum, name, imageSource, type, color) VALUES (0, 'Transport', '', 'EXPANSES', 0)")
            }
        }

        private fun insertDefaultCategory(item: Category) =
            "INSERT INTO CATEGORY (sum, name, imageSource, type, color) " +
                    "VALUES (${item.sum}, '${item.name}', ${item.imageSource}, '${item.type}', ${item.color})"
    }



}