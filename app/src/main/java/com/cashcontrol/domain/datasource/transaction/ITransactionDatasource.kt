package com.cashcontrol.domain.datasource.transaction

import com.cashcontrol.data.room.entity.Transaction
import com.cashcontrol.domain.dto.TransactionWithCategoryEntity
import kotlinx.coroutines.flow.Flow

interface ITransactionDatasource {

    fun addTransaction(transaction: Transaction)

    fun getAllTransactions(): Flow<List<TransactionWithCategoryEntity>>

    fun getAllByCategoryId(categoryId: Long): Flow<TransactionWithCategoryEntity>
}