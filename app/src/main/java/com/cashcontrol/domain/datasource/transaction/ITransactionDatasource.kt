package com.cashcontrol.domain.datasource.transaction

import com.cashcontrol.data.room.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface ITransactionDatasource {

    fun addTransaction(transaction: Transaction)

    fun getAllTransactions(): Flow<List<Transaction>>

}