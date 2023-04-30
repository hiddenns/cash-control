package com.cashcontrol.domain.usecases

import com.cashcontrol.data.model.Transaction
import com.cashcontrol.domain.repositories.transactions.TransactionsRepository

class GetTransactionsInteractor(private val transactionsRepository: TransactionsRepository) {

    suspend fun getTransactions() {

    }

    fun addTransaction(transaction: Transaction) {
        transactionsRepository.addTransaction(transaction)
    }

}