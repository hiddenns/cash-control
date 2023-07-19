package com.cashcontrol.domain.repositories.transactions

import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.data.room.entity.toModel
import com.cashcontrol.domain.datasource.category.CategoryDataSource
import com.cashcontrol.domain.datasource.transaction.TransactionDataSource
import kotlinx.coroutines.flow.map

class TransactionsRepositoryImpl(
    private val transactionDataSource: TransactionDataSource,
    private val categoryDataSource: CategoryDataSource
) : ITransactionsRepository {
    override fun getAllTransactions(categoryId: Long) =
        transactionDataSource
            .getAllTransactions().map { transactions ->
                transactions.map { transactionWithCategory ->
                    transactionWithCategory.transaction.toModel(
                        transactionWithCategory.category.first().toModel()
                    )
                }
            }

//    transactionDataSource
//    .getAllTransactions().map { transactions ->
//        transactions.map { transactionWithCategory ->
//            transactionWithCategory.transactions.map { item ->
//                item.toModel(transactionWithCategory.category.toModel())
//            }
//        }.flatten()
//    }


    // transactionWithCategory.transactions.firstOrNull() ?: throw IOException("transactionRepo transactions is null")

//        transactionDataSource.getAllByCategoryId(categoryId).map { transactionWithCategory ->
//            transactionWithCategory.transactions.map { transaction ->
//                Transaction(
//                    transactionId = transaction.transactionId ?: -1,
//                    name = transaction.name,
//                    date = transaction.date,
//                    sum = transaction.sum,
//                    description = transaction.description,
//                    walletId = transaction.walletId,
//                    type = transaction.type,
//                    category = transactionWithCategory.category.toModel()
//                )
//            }
//        }

    override fun addTransaction(transaction: Transaction) {
        transactionDataSource.addTransaction(transaction.toEntity())
    }

    override fun removeTransaction(categoryId: Long): Transaction {
        TODO("Not yet implemented")
    }

    fun Transaction.toEntity() =
        com.cashcontrol.data.room.entity.Transaction().apply {
            this.name = this@toEntity.name
            this.date = this@toEntity.date
            this.sum = this@toEntity.sum
            this.categoryId = this@toEntity.category.categoryId
            this.description = this@toEntity.description
            this.type = this@toEntity.type
            this.walletId = this@toEntity.walletId
        }

    fun com.cashcontrol.data.room.entity.Transaction.toModel(category: Category) =
        Transaction(
            transactionId = this@toModel.transactionId ?: -1,
            name = this@toModel.name,
            date = this@toModel.date,
            sum = this@toModel.sum,
            description = this@toModel.description,
            walletId = this@toModel.walletId,
            type = this@toModel.type,
            category = category
        )

}