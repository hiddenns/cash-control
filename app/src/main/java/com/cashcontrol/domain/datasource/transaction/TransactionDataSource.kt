package com.cashcontrol.domain.datasource.transaction

import com.cashcontrol.core.database.AppDatabase
import com.cashcontrol.data.room.entity.Transaction
import com.cashcontrol.domain.base.BaseDataSource
import com.cashcontrol.domain.dao.TransactionDao

class TransactionDataSource(database: AppDatabase) : BaseDataSource(), ITransactionDatasource {

    init {
        database.openHelper.writableDatabase
    }

    private var transactionDao: TransactionDao = database.transactionDao()

    override fun addTransaction(transaction: Transaction) = transactionDao.insert(transaction)

    override fun getAllTransactions() = transactionDao.getAll()

    override fun getAllByCategoryId(categoryId: Long) = transactionDao.getAllByCategory(categoryId)
}