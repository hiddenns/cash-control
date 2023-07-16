package com.cashcontrol.domain.repositories.transactions

import com.cashcontrol.data.model.Transaction
import kotlinx.coroutines.flow.Flow

interface ITransactionsRepository {

    fun getAllTransactions(categoryId: Long = DEFAULT_CATEGORY_ID) : Flow<List<Transaction>>
    fun addTransaction(transaction: Transaction)
    fun removeTransaction(categoryId: Long): Transaction


    companion object {
        const val DEFAULT_CATEGORY_ID = -1L
    }
}