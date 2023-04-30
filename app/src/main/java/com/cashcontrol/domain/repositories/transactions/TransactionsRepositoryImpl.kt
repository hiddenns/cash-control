package com.cashcontrol.domain.repositories.transactions

import com.cashcontrol.data.model.Transaction

class TransactionsRepositoryImpl : TransactionsRepository {
    override fun getAllTransactions(categoryId: Long): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun addTransaction(transaction: Transaction): Transaction {
        return transaction
    }

    override fun removeTransaction(categoryId: Long): Transaction {
        TODO("Not yet implemented")
    }
}