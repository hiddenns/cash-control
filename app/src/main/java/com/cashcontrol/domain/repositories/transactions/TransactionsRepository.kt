package com.cashcontrol.domain.repositories.transactions

import com.cashcontrol.data.model.Transaction

interface TransactionsRepository {

    fun getAllTransactions(categoryId: Long = DEFAULT_CATEGORY_ID) : List<Transaction>
    fun addTransaction(categoryId: Long): Transaction
    fun removeTransaction(categoryId: Long): Transaction


    companion object {
        const val DEFAULT_CATEGORY_ID = -1L
    }
}