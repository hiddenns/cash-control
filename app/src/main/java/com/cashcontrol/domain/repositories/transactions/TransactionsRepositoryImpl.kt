package com.cashcontrol.domain.repositories.transactions

import com.cashcontrol.data.model.Transaction
import com.cashcontrol.domain.datasource.transaction.TransactionDataSource
import kotlinx.coroutines.flow.map

class TransactionsRepositoryImpl(
    private val transactionDataSource: TransactionDataSource
) : ITransactionsRepository {
    override fun getAllTransactions(categoryId: Long) =
        transactionDataSource.getAllTransactions().map { transactionsList ->
            transactionsList.map { transaction ->
                transaction.toModel()
            }
        }

    override fun addTransaction(transaction: Transaction) {
        transactionDataSource.addTransaction(transaction.toEntity())
    }

    override fun removeTransaction(categoryId: Long): Transaction {
        TODO("Not yet implemented")
    }

    fun Transaction.toEntity() = com.cashcontrol.data.room.entity.Transaction().apply {
        this.transactionId = this@toEntity.transactionId
        this.name = this@toEntity.name
        this.date = this@toEntity.date
        this.sum = this@toEntity.sum
        this.categoryId = this@toEntity.categoryId
        this.description = this@toEntity.description
        this.type = this@toEntity.type
        this.walletId = this@toEntity.walletId
    }

    fun com.cashcontrol.data.room.entity.Transaction.toModel() =
        Transaction().apply {
            this.transactionId = this@toModel.transactionId
            this.name = this@toModel.name
            this.date = this@toModel.date
            this.sum = this@toModel.sum
            this.categoryId = this@toModel.categoryId
            this.description = this@toModel.description
            this.walletId = this@toModel.walletId
        }

}