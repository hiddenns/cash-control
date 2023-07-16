package com.cashcontrol.domain.usecases

import com.cashcontrol.data.model.Transaction
import com.cashcontrol.domain.repositories.transactions.ITransactionsRepository

class GetTransactionsInteract(private val transactionsRepository: ITransactionsRepository) {

    fun getAllTransactions() = transactionsRepository.getAllTransactions()

    fun addTransaction(transaction: Transaction) {
        transactionsRepository.addTransaction(transaction)
    }

}