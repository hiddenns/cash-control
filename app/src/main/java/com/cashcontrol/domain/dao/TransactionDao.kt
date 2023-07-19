package com.cashcontrol.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cashcontrol.data.room.entity.Transaction
import com.cashcontrol.domain.dto.TransactionWithCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @androidx.room.Transaction
    @Query("SELECT * FROM `transaction` ORDER BY date DESC")
    fun getAll(): Flow<List<TransactionWithCategoryEntity>>

    @androidx.room.Transaction
    @Query("SELECT * FROM `transaction`, `category` WHERE category.categoryId=:id")
    fun getAllByCategory(id: Long): Flow<TransactionWithCategoryEntity>

    @Query("SELECT * FROM `transaction` WHERE transactionId IN (:transactionId)")
    fun loadAllByIds(transactionId: String): Flow<List<Transaction>>

    @Insert
    fun insertAll(join: List<Transaction>)

    @Insert
    fun insert(join: Transaction)

    @Delete
    fun delete(transaction: Transaction)
}